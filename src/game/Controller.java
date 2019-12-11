/**
 * @author Adrian Bao
 * @author Trey Bryant
 * @author Mauricio Herrera
 * @author Tim Lukau
 * 
 * CSC 335 - Object Oriented Programming and Design
 * 
 * Title: Astronauts vs Aliens
 * 
 * File: Controller.java
 * 
 * Description: Controller class of the MVC design pattern
 * that tracks the game logic ensuring that proper handling and
 * updates are executed accordingly.
 */

package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import ammo.*;
import ammo.ExplosiveAstroJoeAmmo;
import characters.BoardCharacter;
import characters.Aliens.*;
import characters.Astronauts.DefenderTower;
import characters.Astronauts.MillenniumFalcon;
import characters.IncomeTowers.IncomeTower;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Controller {
	// Static constants
	public static final int ROWS = 6;
	public static final int COLS = 12;
	public static final int STAGE_ONE_ID = 1;
	public static final int STAGE_TWO_ID = 2;
	public static final int STAGE_THREE_ID = 3;
	public static final int STANDARD_MODE = 1;
	public static final int DOUBLE_COST_MODE = 2;
	
	// Sound Effect Constants
	public static final String ASTROJOE_AMMO_NOISE = "assets/sounds/ammo_noises/astroJoeAmmoNoise.wav";
	public static final String EXPLOSIVE_ASTROJOE_AMMO_NOISE = "assets/sounds/ammo_noises/explosiveAstroJoeAmmoNoise.wav";
	public static final String MOON_ZEUS_AMMO_NOISE = "assets/sounds/ammo_noises/moonZeusAmmoNoise.wav";
	public static final String STARTRELL_CLUGGINS_AMMO_NOISE = "assets/sounds/ammo_noises/startrellClugginsAmmoNoise.wav";
	public static final String TARS_AMMO_NOISE = "assets/sounds/ammo_noises/tarsAmmoNoise.wav";
	public static final String MILLENIUM_FALCON_AMMO_NOISE = "assets/sounds/ammo_noises/milleniumFalconAmmoNoise.wav";
	
	// MediaPlayer object fields for each individual ammo noise
	private MediaPlayer astroJoeAmmoNoise = new MediaPlayer(new Media(new File(ASTROJOE_AMMO_NOISE).toURI().toString()));
	private MediaPlayer explosiveAstroJoeAmmoNoise = new MediaPlayer(new Media(new File(EXPLOSIVE_ASTROJOE_AMMO_NOISE).toURI().toString()));
	private MediaPlayer moonZeusAmmoNoise = new MediaPlayer(new Media(new File(MOON_ZEUS_AMMO_NOISE).toURI().toString()));
	private MediaPlayer startrellClugginsAmmoNoise = new MediaPlayer(new Media(new File(STARTRELL_CLUGGINS_AMMO_NOISE).toURI().toString()));
	private MediaPlayer tarsAmmoNoise = new MediaPlayer(new Media(new File(TARS_AMMO_NOISE).toURI().toString()));
	
	// Class fields
	private Model model;
	private int currentIncome;
	private final int CURRENCY_TIMELINE = 10000; // seconds
	private final int CURRENCY_DEPOSIT = 25;
	private static Random rand;
	private static final int RANDOM_COLUMN_BOUND = 3;
	private final long WAVE_DELAY = 0;
	private AtomicLong timeElapsed;
	private int speedMultiplier;
	private AtomicBoolean waveOneDone = new AtomicBoolean(false);
	private AtomicBoolean waveTwoDone = new AtomicBoolean(false);
	private AtomicBoolean waveTwoStarted = new AtomicBoolean(false);
	private AtomicBoolean waveThreeDone = new AtomicBoolean(false);
	private Timer gamePlayTimer;
	public Map<Integer, Set<Integer>> restrictedTiles;
	public static int costMultiplier = 1;
	
	public TimerTask secondWave;
	public TimerTask firstWave;
	
	// Constructor
	public Controller(Model model) {
		this.model = model;
		restrictedTiles = new HashMap<Integer, Set<Integer>>();
	}
	
	/**
	 * Initializes the environment for the game including starter
	 * values, timelines, and Alien generation.
	 */
	public void initialize() {
		timeElapsed = new AtomicLong(0);
		rand = new Random();
		model.depositSpacebucks(175);
		currentIncome = 0;
		speedMultiplier = 1;
		generateAliens();
		startTimeLine();
		
	}
	
	/**
	 * Generates the gameplay Timeline object that is the central
	 * timer for the game. Include are various TimerTasks that run
	 * scheduled tasks in the background to support the various
	 * functionalities of the game.
	 */
	public void startTimeLine() {
		gamePlayTimer = new Timer();
		TimerTask turnTask = new TimerTask() {

			@Override
			public void run() {
				calculateHitsOrDeaths();
				try {
					animate();
				} catch(ConcurrentModificationException | NullPointerException e) {
					
				}
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
		gamePlayTimer.schedule(money, 0, CURRENCY_TIMELINE / speedMultiplier);
		gamePlayTimer.schedule(increaseTime, 0, 1);
	}
	
	/**
	 * Triggers animations for Aliens, Towers, and Bullets
	 */
	public void animate() {
		for (Enemy alien : model.getAliens()) {
			try {
				DefenderTower tower = model.getDefenderAt(alien.getRow(), alien.getCol());
				if (tower == null) {
					if (alien.isAttacking()) {
						alien.setAttacking(false);
					}
					alien.triggerAnimation(Enemy.WALK_ID);
					Platform.runLater(() -> alien.move());
					if (isGameOver(alien)) {
						Platform.runLater( () -> model.setGameOver());
					}
				} else {
					alien.setAttacking(true);
					alien.triggerAnimation(Enemy.ATTACK_ID);
					tower.decreaseHealth(alien.getDamage());
					if (tower.isDead()) {
						Platform.runLater(() -> model.removeTower(tower, tower.getRow(), tower.getCol()));
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				Platform.runLater(() -> alien.move());
			}
		}
		
		List<Ammo> bullets = new ArrayList<>(model.getBullets());
		for(Ammo bullet : bullets) {
			if (bullet.getCol() < COLS+2) {
				Platform.runLater(() -> bullet.move());
			} else {
				Platform.runLater(() -> model.removeBullet(bullet));
			}
		}
	}
	
	/**
	 * Method responsible for handling hits/deaths of aliens. Also
	 * tracks the current state of the game waves to trigger the 
	 * game won functionality if player survives all waves.
	 */
	private void calculateHitsOrDeaths() throws ConcurrentModificationException {
		if (!model.hasAliens()) {
			if (waveOneDone.get() && !waveTwoStarted.get()) {
				model.setWaveNumber(2);
				generateAliens();
			} else if (waveTwoDone.get() && !waveThreeDone.get()) {
				model.setWaveNumber(3);
				generateAliens();
			} else if (waveThreeDone.get()){
				pause();
				Platform.runLater( () -> model.setGameWon());
			}
		}
		List<Ammo> bullets = new ArrayList<Ammo>(model.getBullets());
		for (Ammo bullet : bullets) {
			boolean hit = false;
			for (Enemy alien : model.getAliens()) {
				if (bullet.getCol() == alien.getCol() && bullet.getRow() == alien.getRow()) {
					alien.decreaseHealth(bullet.getDamage());
					if (alien.getHealth() <= 0) {
						Platform.runLater(() -> model.removeAlien(alien));
					}
					hit = true;
					continue;
				}
			}
			if (hit) {
				Platform.runLater(() -> model.removeBullet(bullet));
			}
		}
	}
	
	/**
	 * Method that checks if the game is over, if so, pauses the timer
	 * and initiated the game over updating presenting user with a Modal.
	 */
	public boolean isGameOver(Enemy alien) {
		if (alien.getCol() == 0) {
			// Game over, pause timer, animate attacking aliens & end game
			pause();
			for (Enemy survivingAlien : model.getAliens()) {
				survivingAlien.triggerAnimation(Enemy.ATTACK_ID);
			}
			return true;
		}
		return false;
		
	}
	
	/**
	 * Public Accessor method for the speedMultiplier attribute
	 * @return
	 */
	public int getSpeedMultiplier() {
		return speedMultiplier;
	}
	
	/**
	 * Generates the alien waves with differing numbers of aliens
	 * and differing types at each level.
	 */
	public void generateAliens() {
		Timer timer = new Timer();
		int waveNumber = model.getWaveNumber();
		
		if (waveNumber == 1) {						// WAVE ONE
			firstWave = new TimerTask() {

				@Override
				public void run() {					
					Platform.runLater(() -> model.displayWaveToast());
					waveOneSpawn();
				}
				
			};
			
			secondWave = new TimerTask() {

				@Override
				public void run() {
					waveOnePtFiveSpawn();
					
				}
				
			};
			timer.schedule(firstWave, 0);
			timer.schedule(secondWave, WAVE_DELAY);
		} else if (waveNumber == 2) {				// WAVE TWO
			firstWave = new TimerTask() {

				@Override
				public void run() {
					waveTwoStarted.set(true);
					Platform.runLater(() -> model.displayWaveToast());
					waveTwoSpawn();
				}
				
			};
			
			secondWave = new TimerTask() {

				@Override
				public void run() {
					waveTwoPtFiveSpawn();
				}
				
			};
			timer.schedule(firstWave, WAVE_DELAY);
			timer.schedule(secondWave, WAVE_DELAY);
			
		} else if (waveNumber == 3) {				// WAVE THREE
			
			firstWave = new TimerTask() {

				@Override
				public void run() {
					waveThreeDone.set(true);
					Platform.runLater(() -> model.displayWaveToast());
					
				}
				
			};
			timer.schedule(firstWave, WAVE_DELAY);

		}
	}
	
	/**
	 * Utility method used to delay the spawning. Place the Thread
	 * to sleep for a specified period of time, given by delay.
	 * @param delay Time specified to place the Thread to its sleep state
	 */
	public void delaySpawn(long delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Handles the spawning for the first set in the first
	 * wave of enemies
	 * */
	public void waveOneSpawn() {
		generateLittleGreenMan(4);
		delaySpawn(6000);
		generateGrunt(3);
		delaySpawn(3000);
		generateGrunt(2);
	}
	
	/**
	 * Handles the spawning for the second set in the first
	 * wave of enemies
	 * */
	public void waveOnePtFiveSpawn() {
		waveOneDone.set(true);
		delaySpawn(10000);
		generateLittleGreenMan(3);
		delaySpawn(5000);
		generateGrunt(3);
		delaySpawn(4000);
		generateSprinter(3);
	}
	
	/**
	 * Handles the spawning for the first set in the second
	 * wave of enemies
	 * */
	public void waveTwoSpawn() {
		generateLittleGreenMan(4);
		delaySpawn(7000);
		generateGrunt(4);
		generateSprinter(2);
		delaySpawn(10000);
		generateManHunter(3);
		generateTank(2);
		generateGrunt(5);
		generateLittleGreenMan(2);
	}
	
	/**
	 * Handles the spawning for the second set in the second
	 * wave of enemies
	 * */
	public void waveTwoPtFiveSpawn() {
		waveTwoDone.set(true);
		delaySpawn(15000);
		generateTank(3);
		delaySpawn(6000);
		generateManHunter(3);
	}
	
	/**
	 * Handles the spawning for the first set in the third
	 * wave of enemies
	 * */
	public void waveThreeSpawn() {
		generateLittleGreenMan(4);
		delaySpawn(5000);
		generateGrunt(4);
		generateTank(2);
		generateLittleGreenMan(4);
		delaySpawn(10000);
		generateSprinter(4);
		generateManHunter(4);
		delaySpawn(7000);
		generateGargantua(3);
		generateLittleGreenMan(4);
	}
	
	
	/**
	 * Generates a LittleGreenMen object of a specified Amount
	 * @param amount Requested number of Enemies to generate
	 */
	public void generateLittleGreenMan(int amount) {
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
	
	/**
	 * Generates a Sprinter object of a specified Amount
	 * @param amount Requested number of Enemies to generate
	 */
	public void generateSprinter(int amount) {
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
	
	/**
	 * Generates a Grunt object of a specified Amount
	 * @param amount Requested number of Enemies to generate
	 */
	public void generateGrunt(int amount) {
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
	
	/**
	 * Generates a ManHunter object of a specified Amount
	 * @param amount Requested number of Enemies to generate
	 */
	public void generateManHunter(int amount) {
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
	
	/**
	 * Generates a Tank object of a specified Amount
	 * @param amount Requested number of Enemies to generate
	 */
	public void generateTank(int amount) {
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
	
	/**
	 * Generates a Gargantua object of a specified Amount
	 * @param amount Requested number of Enemies to generate
	 */
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
	
	/**
	 * Utility method used to increase the animation/play speed
	 * of the game. Currently supports 1x and 2x speed.
	 */
	public void increaseSpeed() {
		if (speedMultiplier == 1) {
			speedMultiplier = 2;
		} else {
			speedMultiplier = 1;
		}
		pause();
		resume();
	}
	
	/**
	 * Utility method to update an Enemy animation according to 
	 * a give speed.
	 * @param speed Updated speed that the animation should run at 
	 */
	public void updateAlienAnimation(int speed) throws ConcurrentModificationException{
		for (Enemy alien : model.getAliens()) {
			alien.updateAnimationSpeed(speed);
		}
	}
	
	/**
	 * Pauses the gameplay timer and updates all animations 
	 * to pause them.
	 */
	public void pause() throws ConcurrentModificationException{
		gamePlayTimer.cancel();
		updateAlienAnimation(0);
	}
	
	/**
	 * Resumes the gameplay timer and updates all animations to
	 * resume them at the current speedMultiplier.
	 */
	public void resume() {
		startTimeLine();
		updateAlienAnimation(speedMultiplier);
	}
	
	/**
	 * Places a BoardCharacter object at a specificed row and col on 
	 * the board. Ensures that each character type is handled accordingly.
	 * @param character BoardCharacter to be placed
	 * @param row given row position for placement
	 * @param col given col position for placement
	 */
	public void placeCharacter(BoardCharacter character, int row, int col) {
		int currBank = model.getSpacebucks();
		
		// Check if the tile is empty
		if (model.isAvailable(row, col)) {
			
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
	
	/**
	 * Deposits specified amount of spacebucks into the players bank
	 * @param amount
	 */
	public void depositSpacebucks(int amount) {
		model.depositSpacebucks(amount);
	}
	
	/**
	 * Removes a given tower specified at a given row/col.
	 * @param towerToRemove DefenderTower to remove 
	 * @param row given row position to remove
	 * @param col given col position to remove
	 */
	public void removeTower(DefenderTower towerToRemove, int row, int col) {
		if (model.containsTower(row, col)) {
			model.removeTower(towerToRemove, row, col);
		}
	}
	
	/**
	 * Generates a HashMap of restricted row, [col, col, ...] pairs. This
	 * will be used to ensure that the location if prohibited from placement 
	 * and populated with the corresponding image in the View.
	 * @param mapId ID for the given map to distinguish the level of difficulty
	 */
	public void assignMap(int mapId) {
		if (mapId == STAGE_TWO_ID) {
			restrictedTiles.put(0,new HashSet<Integer>(Arrays.asList(3,4)));
			restrictedTiles.put(1,new HashSet<Integer>(Arrays.asList(4,5)));
			restrictedTiles.put(2,new HashSet<Integer>(Arrays.asList(4,5)));
			restrictedTiles.put(3,new HashSet<Integer>(Arrays.asList(3,4)));
			restrictedTiles.put(4,new HashSet<Integer>(Arrays.asList(5,6)));
			restrictedTiles.put(5,new HashSet<Integer>(Arrays.asList(3,4)));
		} else if (mapId == STAGE_THREE_ID) {
			restrictedTiles.put(0,new HashSet<Integer>(Arrays.asList(1,3,6)));
			restrictedTiles.put(1,new HashSet<Integer>(Arrays.asList(1,4,7)));
			restrictedTiles.put(2,new HashSet<Integer>(Arrays.asList(1,2,5)));
			restrictedTiles.put(3,new HashSet<Integer>(Arrays.asList(1,4)));
			restrictedTiles.put(4,new HashSet<Integer>(Arrays.asList(1,3,6)));
			restrictedTiles.put(5,new HashSet<Integer>(Arrays.asList(1,1,4)));
		}
		model.setRestrictionedTiles(restrictedTiles);
	}
	
	/**
	 * Assigns the requested game mode, depending on the provided ID.
	 * Can either be standard or double cost game modes
	 * @param modeId Int ID associated with requested game mode
	 */
	public void assignGameMode(int modeId) {
		if (modeId == STANDARD_MODE) {
			costMultiplier = 1;
		} else if (modeId == DOUBLE_COST_MODE) {
			costMultiplier = 2;
		}
	}
	
	/**
	 * Public Accesssor method for restrictedTiles HashMap
	 * @return HashMap populated using assignMap()
	 */
	public Map<Integer, Set<Integer>> getRestrictedTiles() {
		return restrictedTiles;
	}
	
	/**
	 * Given a specified Bullet object, it will play the corresponding
	 * sound snippet for that particular class.
	 * @param bullet Specified bullet to play sound effect for
	 */
	public void playBulletNoise(Ammo bullet) {
		if (bullet instanceof AstroJoeAmmo) {
			Platform.runLater( () -> {
				astroJoeAmmoNoise.play();
				astroJoeAmmoNoise.seek(Duration.seconds(0));	
			});
		} else if (bullet instanceof ExplosiveAstroJoeAmmo) {
			Platform.runLater( () -> {
				explosiveAstroJoeAmmoNoise.play();
				explosiveAstroJoeAmmoNoise.seek(Duration.seconds(0));				
			});
		} else if (bullet instanceof StartrellClugginsAmmo) {
			Platform.runLater( () -> {
				startrellClugginsAmmoNoise.play();
				startrellClugginsAmmoNoise.seek(Duration.seconds(0));	
			});
		} else if (bullet instanceof TarsAmmo) {
			Platform.runLater( () -> {
				tarsAmmoNoise.play();
				tarsAmmoNoise.seek(Duration.seconds(0));
			});
		} else if (bullet instanceof MoonZeusAmmo) {
			Platform.runLater( () -> {
				moonZeusAmmoNoise.play();
				moonZeusAmmoNoise.seek(Duration.seconds(0));
			});
		}
	}
}
