package game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

import ammo.Ammo;
import ammo.ExplosiveAstroJoeAmmo;
import characters.BoardCharacter;
import characters.Aliens.*;
import characters.Astronauts.DefenderTower;
import characters.Astronauts.MillenniumFalcon;
import characters.IncomeTowers.IncomeTower;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

public class Controller {
	// Class fields
	public static final int ROWS = 6;
	public static final int COLS = 12;
	private Model model;
	private static final long FRAME_TIME = 100; // Milliseconds
	private static final int WAVE_ONE_ALIENS = 15;
	private static final int WAVE_TWO_ALIENS = 30;
	private static final int WAVE_THREE_ALIENS = 40;
	private int currentIncome;
	private final int CURRENCY_TIMELINE = 10; // seconds
	private final int CURRENCY_DEPOSIT = 25;
	private static Random rand;
	private static final int RANDOM_COLUMN_BOUND = 3;
	private final long WAVE_DELAY = 3000l;
	private AtomicLong timeElapsed;
	private int speedMultiplier;
	private boolean waveOneDone = false;
	private boolean waveTwoDone = false;
	private boolean waveThreeDone = false;
	private Timer gamePlayTimer;

	
	// Constructor
	public Controller(Model model) {
		this.model = model;
	}
	
	// Methods
	public void initialize() {
		timeElapsed = new AtomicLong(0);
		rand = new Random();
		model.depositSpacebucks(500);
		currentIncome = 0;
		speedMultiplier = 1;
		generateAliens();
		startTimeLine();
	}
	
	public void startTimeLine() {
		gamePlayTimer = new Timer();
		TimerTask turnTask = new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(() -> calculateHitsOrDeaths());
				Platform.runLater(() -> animate());
			}
			
		};
		
		TimerTask bullets = new TimerTask() {

			@Override
			public void run() {
				for(DefenderTower tower : model.getTowers()) {
					if (tower instanceof MillenniumFalcon) {
						Platform.runLater(() -> model.addBullet(((MillenniumFalcon) tower).shotOne()));
						Platform.runLater(() -> model.addBullet(((MillenniumFalcon) tower).shotTwo()));
						Platform.runLater(() -> model.addBullet(new ExplosiveAstroJoeAmmo(tower)));

					}
					else if (tower.canShoot()) {
						Platform.runLater(() -> model.addBullet(tower.shoot()));
					}
				}
			}
			
		};
		
		TimerTask money = new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(() -> depositSpacebucks(CURRENCY_DEPOSIT + currentIncome));
			}
			
		};
		
		TimerTask increaseTime = new TimerTask() {

			@Override
			public void run() {
				long newValue = timeElapsed.get() + 1 * speedMultiplier;
				timeElapsed.set(newValue);
			}
			
		};
		
		gamePlayTimer.schedule(turnTask, 0, 100 / speedMultiplier);
		gamePlayTimer.schedule(bullets, 0, 1000 / speedMultiplier);
		gamePlayTimer.schedule(money, 0, 5000 / speedMultiplier);
		gamePlayTimer.schedule(increaseTime, 0, 1);
	}
	
	private void animate() {
		for (Enemy alien : model.getAliens()) {
			try {
				DefenderTower tower = model.getDefenderAt(alien.getRow(), alien.getCol());
				if (tower == null) {
					alien.move();
				} else {
					tower.decreaseHealth(alien.getDamage());
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				alien.move();
			}
		}
		
		List<Ammo> bullets = new ArrayList<>(model.getBullets());
		for(Ammo bullet: bullets) {
			if (bullet.getCol() < COLS+2) {
				bullet.move();
			} else {
				model.removeBullet(bullet);
			}
		}
	}
	
	
	private void calculateHitsOrDeaths() {
		if (!model.hasAliens()) {
			if (waveOneDone && !waveTwoDone && !waveThreeDone) {
				model.setWaveNumber(2);
				generateAliens();
			} else if (waveTwoDone && !waveThreeDone) {
				model.setWaveNumber(3);
				generateAliens();
			} else if (waveThreeDone){
				System.out.println("YOU WON!!!");
				System.exit(0);
			}
		}
		List<Ammo> bullets = new ArrayList<Ammo>(model.getBullets());
		for (Ammo bullet : bullets) {
			boolean hit = false;
			for (Enemy alien : model.getAliens()) {
				if (bullet.getCol() == alien.getCol() && bullet.getRow() == alien.getRow()) {
					alien.decreaseHealth(bullet.getDamage());
					hit = true;
					continue;
				}
			}
			if (hit) {
				model.removeBullet(bullet);
			}
		}
		List<Enemy> aliens = new ArrayList<Enemy>(model.getAliens());
		for (Enemy alien : aliens) {
			if (alien.getCol() == -1 || alien.getHealth() <= 0) {
				Platform.runLater(() -> model.removeAlien(alien));
			}
		}
	}
	
	public int getSpeedMultiplier() {
		return speedMultiplier;
	}
	
	private void generateAliens() {
		Timer timer = new Timer();
		int waveNumber = model.getWaveNumber();
		
		if (waveNumber == 1 && !waveOneDone) {						// WAVE ONE
			System.out.println("WAVE ONE");
			TimerTask firstWave = new TimerTask() {

				@Override
				public void run() {
					generateLittleGreenMan(3);
					generateGrunt(3);
				}
				
			};
			
			TimerTask secondWave = new TimerTask() {

				@Override
				public void run() {
					generateLittleGreenMan(3);
					generateGrunt(3);
					generateSprinter(3);
					waveOneDone = true;
				}
				
			};
			timer.schedule(firstWave, 0);
			timer.schedule(secondWave, WAVE_DELAY);
		} else if (waveNumber == 2 && waveOneDone && !waveTwoDone) {				// WAVE TWO

			TimerTask firstWave = new TimerTask() {

				@Override
				public void run() {
					generateLittleGreenMan(6);
					generateGrunt(6);
					generateSprinter(3);
					generateManHunter(3);
					generateTank(3);
				}
				
			};
			
			TimerTask secondWave = new TimerTask() {

				@Override
				public void run() {
					generateTank(3);
					generateManHunter(3);
					waveTwoDone = true;
					System.out.println("WAVE TWO");
				}
				
			};
			timer.schedule(firstWave, WAVE_DELAY);
			timer.schedule(secondWave, WAVE_DELAY);
			
		} else if (waveNumber == 3 && waveTwoDone) {				// WAVE THREE
			
			TimerTask firstWave = new TimerTask() {

				@Override
				public void run() {
					generateLittleGreenMan(6);
					generateGrunt(6);
					generateSprinter(6);
					generateManHunter(6);
					generateTank(3);
					generateGargantua(3);
					waveThreeDone = true;
					System.out.println("WAVE THREE");
				}
				
			};
			timer.schedule(firstWave, WAVE_DELAY);
			
		}
	}
	
	private void generateLittleGreenMan(int amount) {
		for (int i = 0; i < amount; i++) {
			LittleGreenMen alien = new LittleGreenMen();
			int row = rand.nextInt(ROWS);
			int col = rand.nextInt(RANDOM_COLUMN_BOUND) + COLS + 2;
			alien.setRow(row);
			alien.setCol(col);
			alien.setStackPane();
			
			model.addAlien(alien);
			Platform.runLater(() -> model.placeCharacter(alien, row, col));
		}
	}
	
	private void generateSprinter(int amount) {
		for (int i = 0; i < amount; i++) {
			Sprinter alien = new Sprinter();
			int row = rand.nextInt(ROWS);
			int col = rand.nextInt(RANDOM_COLUMN_BOUND) + COLS + 2;
			alien.setRow(row);
			alien.setCol(col);
			alien.setStackPane();
			
			model.addAlien(alien);
			Platform.runLater(() -> model.placeCharacter(alien, row, col));
		}
	}
	
	private void generateGrunt(int amount) {
		for (int i = 0; i < amount; i++) {
			Grunt alien = new Grunt();
			int row = rand.nextInt(ROWS);
			int col = rand.nextInt(RANDOM_COLUMN_BOUND) + COLS + 2;
			alien.setRow(row);
			alien.setCol(col);
			alien.setStackPane();
			
			model.addAlien(alien);
			Platform.runLater(() -> model.placeCharacter(alien, row, col));
		}
	}
	
	private void generateManHunter(int amount) {
		for (int i = 0; i < amount; i++) {
			ManHunter alien = new ManHunter();
			int row = rand.nextInt(ROWS);
			int col = rand.nextInt(RANDOM_COLUMN_BOUND) + COLS + 2;
			alien.setRow(row);
			alien.setCol(col);
			alien.setStackPane();
			
			model.addAlien(alien);
			Platform.runLater(() -> model.placeCharacter(alien, row, col));
		}
	}
	
	private void generateTank(int amount) {
		for (int i = 0; i < amount; i++) {
			Tank alien = new Tank();
			int row = rand.nextInt(ROWS);
			int col = rand.nextInt(RANDOM_COLUMN_BOUND) + COLS + 2;
			alien.setRow(row);
			alien.setCol(col);
			alien.setStackPane();
			
			model.addAlien(alien);
			Platform.runLater(() -> model.placeCharacter(alien, row, col));
		}
	}
	
	private void generateGargantua(int amount) {
		for (int i = 0; i < amount; i++) {
			Gargantua alien = new Gargantua();
			int row = rand.nextInt(ROWS);
			int col = rand.nextInt(RANDOM_COLUMN_BOUND) + COLS + 2;
			alien.setRow(row);
			alien.setCol(col);
			alien.setStackPane();
			
			model.addAlien(alien);
			Platform.runLater(() -> model.placeCharacter(alien, row, col));
		}
	}
	
	
	public void increaseSpeed() {
		if (speedMultiplier == 8) {
			speedMultiplier = 1;
		} else {
			speedMultiplier *= 2;
		}
		pause();
		resume();
	}
	
	
	public void pause() {
		gamePlayTimer.cancel();
	}
	
	public void resume() {
		startTimeLine();
	}
	
	public void placeCharacter(BoardCharacter character, int row, int col) {
		int currBank = model.getSpacebucks();
		
		// Check if the tile is empty
		if (model.isEmpty(row, col)) {
			
			// Check if user can afford the tower
			if ((character instanceof DefenderTower) && (currBank >= ((DefenderTower)character).getCost())) {
				
				// Check if currency tower
				if (character instanceof IncomeTower) {
					// Add a timeline for currency generation for this specific income tower
					IncomeTower incomeTower = (IncomeTower)character;
					currentIncome += incomeTower.getDepositAmount();
				}
				model.placeCharacter(character, row, col);
			} else {
				model.notifyInsufficientFunds(); // not enough funds
			}
		} else {
			model.notifyInvalidPlacement(); // tile is taken
		}
	}
	
	public void depositSpacebucks(int amount) {
		model.depositSpacebucks(amount);
	}
	
	public void removeTower(DefenderTower towerToRemove, int row, int col) {
		if (model.isEmpty(row, col)) {
			// model.notifyInvalidRemoval();
		} else {
			model.removeTower(towerToRemove, row, col);
		}
	}
}
