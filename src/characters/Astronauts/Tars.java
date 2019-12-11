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
 * File: Tars.java
 * 
 * Description: This class describes the specific characteristics
 * and behavior of the tower Tars.
 */

package characters.Astronauts;

import ammo.Ammo;
import ammo.TarsAmmo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to TARS' properties; this defender is the 3rd most powerful
 * (on a holistic scale) out of the 6 defenders who have the ability shoot. It is outmatched by only
 * Moon Zeus and the Millenium Falcon.
 */
public class Tars extends DefenderTower{
	
	public Tars() {
		super(HEALTH_TARS, ATTACK_SPEED_TARS, 
				DAMAGE_TARS, new Image(TARS_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	/**
	 * Getter for cost attribute
	 */
	public int getCost() {
		return COST_TARS * Controller.costMultiplier;
	}
	
	/**
	 * String representation of object
	 */
	public String toString() {
		return "Tars\n" + super.infoCard();
	}

	/**
	 * Returns the specific type of ammo this defender shoots
	 */
	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return new TarsAmmo(this);
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
