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
 * File: Model.java
 * 
 * Description: Model of the MVC Design Pattern that stores the game 
 * state of Astronauts vs Aliens. This class maintains the game board and 
 * its associated surrounding/internal objects to make sure the game
 * state is tracked and updated accordingly. This is the Observable for 
 * the View and any modifications made to the game state will be updated
 * by the Observers (View).
 */

package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import ammo.Ammo;
import characters.*;
import characters.Aliens.Enemy;
import characters.Astronauts.DefenderTower;
import map.Tile;

public class Model extends Observable {
	// Class fields
	private Tile[][] board;
	private int bank;
	private int currentWave;
	private List<Enemy> aliens;
	private List<Ammo> bullets;
	private List<DefenderTower> towers;
	
	// Constructor
	public Model () {
		towers = new ArrayList<DefenderTower>();
		bullets = new ArrayList<Ammo>();
		bank = 0;
		currentWave = 1;
		aliens = new ArrayList<Enemy>();
		board = new Tile[Controller.ROWS][Controller.COLS];		
		initializeBoard();
	}
	
	/**
	 * Adds an Ammo object to the current Ammo objects that exist 
	 * on the game board
	 * @param bullet Ammo object to add to game state
	 */
	public void addBullet(Ammo bullet) {
		bullets.add(bullet);
		
		MoveMessage message = new MoveMessage(MoveMessage.BULLET_PLACEMENT);
		message.setBullet(bullet);
		
		setChanged();
		notifyObservers(message);
	}
	
	/**
	 * Removes an Ammo object from the gameboard
	 * @param bullet Ammo object to remove
	 */
	public void removeBullet(Ammo bullet) {
		bullets.remove(bullet);
		
		MoveMessage message = new MoveMessage(MoveMessage.BULLET_REMOVAL);
		message.setBullet(bullet);
		
		setChanged();
		notifyObservers(message);
	}
	
	/**
	 * Public accessor method for the towers attribute
	 * @return List of DefenderTower objects
	 */
	public List<DefenderTower> getTowers() {
		return towers;
	}
	
	/**
	 * Public accessor method for the bullets attribute
	 * @return List of Ammo Objects
	 */
	public List<Ammo> getBullets() {
		return this.bullets;
	}
	
	/**
	 * Public mutator method for the currentWave attribute
	 * @param waveNumber Integer current wave number
	 */
	public void setWaveNumber(int waveNumber) {
		this.currentWave = waveNumber;
	}
	
	/**
	 * Adds a given alien to the list of active aliens on the board
	 * @param alien Enemy to be added to the game board
	 */
	public void addAlien(Enemy alien) {
		aliens.add(alien);
	}
	
	/**
	 * Removes a given alien from the list of active aliens on the board
	 * @param alien Enemy to be removed from the game board
	 */
	public void removeAlien(Enemy alien) {
		aliens.remove(alien);
		MoveMessage message = new MoveMessage(MoveMessage.VALID_MOVE, alien, 0, 0, true);
		setChanged();
		notifyObservers(message);
	}
	
	/**
	 * Public accessor method of the aliens attribute
	 * @return List of Enemy objects
	 */
	public List<Enemy> getAliens() {
		return aliens;
	}
	
	/**
	 * Utility method used to see if the current game state contains
	 * active aliens on the board.
	 * @return Boolean result of the check for active aliens
	 */
	public boolean hasAliens() {
		return !aliens.isEmpty();
	}
	
	/**
	 * Public accessor method for the currentWave attribute
	 * @return int current wave number
	 */
	public int getWaveNumber() {
		return currentWave;
	}
	
	/**
	 * Initializes the game board with Empty Tile objects
	 */
	public void initializeBoard() {
		for (int row = 0; row < Controller.ROWS; row++) {
			for (int col = 0; col < Controller.COLS; col++) {
				board[row][col] = new Tile(null); // Initialize empty tile
			}
		}
	}
	
	/**
	 * Utility method used to check if a given row,col position on the board
	 * contains an Object
	 * @param row Integer row position to check
	 * @param col Integer col position to check
	 * @return boolean result of the check for an existing Object at row,col
	 */
	public boolean containsTower(int row, int col) {
		return !board[row][col].isEmpty();
	}
	
	/**
	 * Utility method used to check if a given location is availabe, i.e, the 
	 * Tile does not have any BoardCharacter in it AND the Tile is not flagged
	 * as restricted.
	 * @param row Integer row position to check
	 * @param col Integer col position to check
	 * @return boolean result of the check of availability of the Tile at row,col
	 */
	public boolean isAvailable(int row, int col) {
		return board[row][col].isEmpty() && !board[row][col].isRestrictred();
	}
	
	/**
	 * Public accessor method of the board attribute
	 * @return Two-dimensional array of Tile objects
	 */
	public Tile[][] getBoard() {
		return board;
	}
	
	/**
	 * Public accessor method of the in-game currency amount, bank
	 * @return Integer bank amount
	 */
	public int getSpacebucks() {
		return bank;
	}
	
	/**
	 * Observable method that places a given BoardCharacter at the specified
	 * row, col on the game board. Notifies observers once the change has been
	 * made.
	 * @param character BoardCharacter to place
	 * @param row Integer row position to place
	 * @param col Integer col position to place
	 */
	public void placeCharacter(BoardCharacter character, int row, int col) {
		MoveMessage message = null;
		// Adjust bank amount
		if (character instanceof DefenderTower) {
			towers.add((DefenderTower)character);
			bank -= ((DefenderTower)character).getCost();
			
			// Place character
			character.setRow(row);
			character.setCol(col);
			board[row][col].placeCharacter(character);
			
			// Creating message to send to view
			message = new MoveMessage(MoveMessage.VALID_MOVE, (DefenderTower)character, row, col, false);
		} else if (character instanceof Enemy) {
			message = new MoveMessage(MoveMessage.VALID_MOVE, (Enemy)character, row, col, false);
		}
		
		// Notify Observers
		setChanged();
		notifyObservers(message);
	}
	
	/**
	 * Observable method that deposits a specified number of currency into
	 * the players bank attribute. Notifies observers once the change has been
	 * made
	 * @param amount Integer amount to deposit
	 */
	public void depositSpacebucks(int amount) {
		bank += amount;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Observable method that extends an INVALID_MOVE notification to the View.
	 * This method is called with either a Tile is taken by another character 
	 * or it is a restricted tile, yielding and invalid move response.
	 */
	public void notifyInvalidPlacement() {
		MoveMessage message = new MoveMessage(MoveMessage.INVALID_MOVE);
		setChanged();
		notifyObservers(message);
	}
	
	/**
	 * Observable method that extends an INSUFFICIENT_FUNDS notification to the View.
	 * This method is called with the player does not have enough in-game currency
	 * to purchase the Tower they have attempted to place. 
	 */
	public void notifyInsufficientFunds() {
		MoveMessage message = new MoveMessage(MoveMessage.INSUFFICIENT_FUNDS);
		setChanged();
		notifyObservers(message);
	}

	/**
	 * Observable method that removes a specific tower from the board and 
	 * process a partial refund with a specified discount constant. Notifies 
	 * observers once the change has been made.
	 * @param towerToRemove DefenderTower object to remove from the board
	 * @param row Integer row position of the tower to remove
	 * @param col Integer col position of the tower to remove
	 */
	public void removeTower(DefenderTower towerToRemove, int row, int col) {
		towers.remove(towerToRemove);
		// adjusts bank amount
		bank += DefenderTower.REFUND_MULTIPLIER * towerToRemove.getCost();
		
		board[row][col] = new Tile(null);
		
		// Creating message to send to view
		MoveMessage message = new MoveMessage(MoveMessage.VALID_MOVE, towerToRemove, row, col, true);
		
		setChanged();
		notifyObservers(message);
	}

	/**
	 * Utility method to retrieve the DefenderTower located at board[row][col]
	 * @param row Integer row position to retrieve
	 * @param col Integer col position to retrieve
	 * @return DefenderTower object at that position
	 */
	public DefenderTower getDefenderAt(int row, int col) {
		return (DefenderTower) board[row][col].getCharacter();
	}
	
	/**
	 * Observable method that extends a GAME_OVER notification. This method
	 * is called when the game has been lost.
	 */
	public void setGameOver() {
		MoveMessage message = new MoveMessage(MoveMessage.GAME_OVER, null, 0, 0, false);
		setChanged();
		notifyObservers(message);
	}
	
	/**
	 * Observable method that extends a GAME_WON notification. This method
	 * is called when the game has been won.
	 */
	public void setGameWon() {
		MoveMessage message = new MoveMessage(MoveMessage.GAME_WON, null, 0, 0, false);
		setChanged();
		notifyObservers(message);
	}
	
	/**
	 * Observable method that extends a Toast Message to the View notifying
	 * the user of the state-changes in stages waves.
	 */
	public void displayWaveToast() {
		String waveNumber = "";
		if (currentWave == 1) {
			waveNumber = "Wave One!";
		} else if (currentWave == 2) {
			waveNumber = "Wave Two!";
		} else if (currentWave == 3) {
			waveNumber = "Wave Three!";
		}
		setChanged();
		notifyObservers(waveNumber);
	}
	
	/**
	 * Utility method that iterates through the game board and restricts specified
	 * tiles according to the restrictedTiles Map that is provided. Each Tile that 
	 * is found to be restricted, its 'restricted' flag will be set to true.
	 * @param restrictedTiles Map containing restrictedTile information
	 */
	public void setRestrictionedTiles(Map<Integer, Set<Integer>> restrictedTiles) {
		// Update restricted tiles on board
		for (Integer row : restrictedTiles.keySet()) {
			for (Integer col : restrictedTiles.get(row)) {
				board[row][col].setRestriction(true);
			}
		}
	}
}
