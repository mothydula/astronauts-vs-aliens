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
 * File: AstroJoe.java
 * 
 * Description: This class describes the specific characteristics
 * and behavior of the tower AstroJoe.
 */

package characters.Astronauts;

import ammo.Ammo;
import ammo.AstroJoeAmmo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to AstroJoe's properties; this defender is the least powerful
 * (on a holistic scale) out of the defenders who have the ability shoot (so he is understandably
 * the cheapest to buy).
 */
public class AstroJoe extends DefenderTower {
	// Class fields
	
	// Constructor
	public AstroJoe() {
		super(HEALTH_ASTROJOE, ATTACK_SPEED_ASTROJOE, 
				DAMAGE_ASTROJOE, new Image(ASTRO_JOE_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	/**
	 * Getter for cost attribute
	 */
	public int getCost() {
		return COST_ASTROJOE * Controller.costMultiplier;
	}
	
	/**
	 * String representation of object
	 */
	public String toString() {
		return "Astro Joe\n" + super.infoCard();
	}

	/**
	 * Returns the specific type of ammo this defender shoots
	 */
	@Override
	public Ammo shoot() {
		return new AstroJoeAmmo(this);
	}

	/**
	 * Returns whether or not this defender is capable of shooting
	 */
	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return true;
	}
}
