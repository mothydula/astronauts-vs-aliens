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
 * File: MoneyBush.java
 * 
 * Description: This class describes the specific characteristics
 * and behavior of the tower MoneyBush.
 */

package characters.Astronauts;

import ammo.Ammo;
import characters.IncomeTowers.IncomeTower;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to the MoneyBush's properties (a.k.a. SpaceBucks Printer);
 * this 'defender' is one of three non-shooting board characters. The purpose of this character is 
 * to increase the amount of money in the bank by 50 SpaceBucks every 10 seconds.
 */
public class MoneyBush extends IncomeTower {
	
	public MoneyBush() {
		super(HEALTH_MONEY_BUSH, ATTACK_SPEED_MONEY_BUSH, 
				DAMAGE_MONEY_BUSH, new Image(MONEY_BUSH_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	/**
	 * Getter for cost attribute
	 */
	public int getCost() {
		return COST_MONEY_BUSH * Controller.costMultiplier;
	}
	
	/**
	 * Getter for the amount of seconds it takes to generate money
	 */
	public int getTimeline() {
		return MONEY_BUSH_GEN_TIMELINE;
	}
	
	/**
	 * Getter for the amount to be generated every certain amount of time.
	 */
	public int getDepositAmount() {
		return MONEY_BUSH_GEN_AMOUNT;
	}
	
	/**
	 * String representation of object
	 */
	public String toString() {
		return "Money Bush\n" + super.infoCard();
	}

	/**
	 * Returns the specific type of ammo this defender shoots
	 */
	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns whether or not this defender is capable of shooting
	 */
	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return false;
	}
}
