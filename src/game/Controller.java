package game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import ammo.Ammo;
import characters.BoardCharacter;
import characters.Aliens.*;
import characters.Astronauts.DefenderTower;
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
	private Timeline moneyTimeline;
	private Timeline alienTimeline;
	private Timeline bulletsTimeline;
	private static Random rand;
	private static final int RANDOM_COLUMN_BOUND = 3;
	private final long WAVE_DELAY = 3000l;
	private AtomicLong timeElapsed;
	private Timer gameTimer;
	private int speedMultiplier;
	private boolean waveOneDone = false;
	private boolean waveTwoDone = false;
	private boolean waveThreeDone = false;

	
	// Constructor
	public Controller(Model model) {
		this.model = model;
	}
	
	// Methods
	public void initialize() {
		timeElapsed = new AtomicLong(0);
		startGameTimer();
		rand = new Random();
		model.depositSpacebucks(500);
		currentIncome = 0;
		speedMultiplier = 1;
		generateAliens();
		new Thread(() -> startAlienTimeline()).start();
		startMoneyTimeline();
		startBulletsTimeline();
	}
	
	public int getSpeedMultiplier() {
		return speedMultiplier;
	}
	
	private void startGameTimer() {
		gameTimer = new Timer();
		TimerTask increaseTime = new TimerTask() {

			@Override
			public void run() {
				long newValue = timeElapsed.get() + 1 * speedMultiplier;
				timeElapsed.set(newValue);
			}
			
		};
		gameTimer.schedule(increaseTime, 0, 1);
	}
	
	private void pauseGameTimer() {
		gameTimer.cancel();
	}
	
	private void resumeGameTimer() {
		gameTimer = new Timer();
		TimerTask increaseTime = new TimerTask() {

			@Override
			public void run() {
				long newValue = timeElapsed.get() + 1 * speedMultiplier;
				timeElapsed.set(newValue);
			}
			
		};
		gameTimer.schedule(increaseTime, 0, 1);
	}
	
	private void startBulletsTimeline() {
		bulletsTimeline = new Timeline(new KeyFrame(Duration.millis(1000 / speedMultiplier), e -> {
			for (DefenderTower tower : model.getTowers()) {
				if(tower.canShoot()) {
					model.addBullet(tower.shoot());
				}
			}
		}));
		bulletsTimeline.setCycleCount(Timeline.INDEFINITE);
		bulletsTimeline.play();
	}
	
	private void startMoneyTimeline() {
		// Currency generator - deposit 50 space bucks every 5 seconds
		moneyTimeline = new Timeline(new KeyFrame(Duration.seconds(CURRENCY_TIMELINE / speedMultiplier), e -> {
			Platform.runLater(() -> depositSpacebucks(CURRENCY_DEPOSIT + currentIncome));
		}));

		moneyTimeline.setCycleCount(Timeline.INDEFINITE);
		moneyTimeline.play();
	}
	
	private void startAlienTimeline() {
		alienTimeline = new Timeline(new KeyFrame(Duration.millis(FRAME_TIME / speedMultiplier), e -> {
			if (model.hasAliens()) {
				animate();
			} else if (waveThreeDone) {						// GAME OVER
				Platform.exit();
				System.exit(0);
			} else {
				model.setWaveNumber(model.getWaveNumber()+1);
				generateAliens();
			}
		}));
		
		alienTimeline.setCycleCount(Timeline.INDEFINITE);
		alienTimeline.play();
	}
	
	private void generateAliens() {
		Timer timer = new Timer();
		int waveNumber = model.getWaveNumber();
		
		if (waveNumber == 1) {						// WAVE ONE
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
		} else if (waveNumber == 2 && waveOneDone) {				// WAVE TWO
			System.out.println("WAVE TWO");

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
				}
				
			};
			timer.schedule(firstWave, WAVE_DELAY);
			timer.schedule(secondWave, WAVE_DELAY/3);
			
		} else if (waveNumber == 3 && waveTwoDone) {				// WAVE THREE
			System.out.println("WAVE THREE");
			
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
		alienTimeline.stop();
		startAlienTimeline();
		
		moneyTimeline.stop();
		startMoneyTimeline();
		
		bulletsTimeline.stop();
		startBulletsTimeline();
	}
	
	
	public void pause() {
		pauseGameTimer();
		moneyTimeline.pause();
		alienTimeline.pause();
		bulletsTimeline.pause();
	}
	
	public void resume() {
		resumeGameTimer();
		moneyTimeline.play();
		alienTimeline.play();
		bulletsTimeline.play();
	}
	
	private void animate() {
		calculateHitsOrDeaths();
		List<Enemy> aliensToMove = new ArrayList<Enemy>();
		for (Enemy alien : model.getAliens()) {
			try {
				DefenderTower tower = model.getDefenderAt(alien.getRow(), alien.getCol());
				if (tower == null) {
					aliensToMove.add(alien);
				} else {
					tower.decreaseHealth(alien.getDamage());
					if (tower.isDead()) {
						model.removeTower(tower, tower.getRow(), tower.getCol());
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				aliensToMove.add(alien);
			}
		}
		for (Enemy alien : aliensToMove) {
			alien.move();
		}
		
		List<Ammo> bulletsToRemove = new ArrayList<Ammo>();
		
		for(Ammo bullet: model.getBullets()) {
			bullet.move();
			if (bullet.getCol() >= COLS) {
				bulletsToRemove.add(bullet);
			}
		}
		
		for (Ammo bullet : bulletsToRemove) {
			model.removeBullet(bullet);
		}
		
	}
	
	private void calculateHitsOrDeaths() {
		List<Enemy> aliens = new ArrayList<Enemy>(model.getAliens());
		for (Enemy alien : aliens) {
			if (alien.getCol() == -1) {
				model.removeAlien(alien);
			}
		}
	}
	
	
	public void purchaseTower() {
		
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
	
	public void collectSpaceBucks() {
		// TODO: implement
	}
}
