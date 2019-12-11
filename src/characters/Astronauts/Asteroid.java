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
 * File: Asteroid.java
 * 
 * Description: This class describes the specific characteristics
 * and behavior of the tower Asteroid.
 */

package characters.Astronauts;

import ammo.Ammo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to the Asteroid's properties; this defender is one of three
 * non-shooting board characters. The purpose of the asteroid is to simply take alien damage; it cannot
 * attack/shoot.
 */
public class Asteroid extends DefenderTower{
	
	public Asteroid() {
		super(HEALTH_ASTEROID, ATTACK_SPEED_ASTEROID, 
				DAMAGE_ASTEROID, new Image(ASTEROID_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	/**
	 * Getter for cost attribute
	 */
	public int getCost() {
		return COST_ASTEROID * Controller.costMultiplier;
	}
	
	/**
	 * String representation of object
	 */
	public String toString() {
		return "Asteroid\n" + super.infoCard();
	}

	/**
	 * Returns the specific type of ammo this defender shoots
	 */
	@Override
	public Ammo shoot() {
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
