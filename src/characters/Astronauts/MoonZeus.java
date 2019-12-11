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
 * File: MoonZeus.java
 * 
 * Description: This class describes the specific characteristics
 * and behavior of the tower MoonZeus.
 */

package characters.Astronauts;

import ammo.Ammo;
import ammo.MoonZeusAmmo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to Moon Zeus' properties; this defender is the 2nd most powerful
 * (on a holistic scale) out of the 6 defenders who have the ability shoot. He is outmatched by only
 * the Millenium Falcon.
 */
public class MoonZeus extends DefenderTower{
	
	public MoonZeus() {
		super(HEALTH_MOON_ZEUS, ATTACK_SPEED_MOON_ZEUS, 
				DAMAGE_MOON_ZEUS, new Image(MOON_ZEUS_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	/**
	 * Getter for cost attribute
	 */
	public int getCost() {
		return COST_MOON_ZEUS * Controller.costMultiplier;
	}
	
	/**
	 * String representation of object
	 */
	public String toString() {
		return "Moon Zeus\n" + super.infoCard();
	}

	/**
	 * Returns the specific type of ammo this defender shoots
	 */
	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return new MoonZeusAmmo(this);
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
