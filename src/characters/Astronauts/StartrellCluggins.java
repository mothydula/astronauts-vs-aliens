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
 * File: StartrellCluggins.java
 * 
 * Description: This class describes the specific characteristics
 * and behavior of the tower StartrellCluggins.
 */

package characters.Astronauts;

import ammo.Ammo;
import ammo.StartrellClugginsAmmo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to Startrell's properties; this defender is the 4th most powerful
 * (on a holistic scale) out of the 6 defenders who have the ability shoot.
 */
public class StartrellCluggins extends DefenderTower{
	
	public StartrellCluggins() {
		super(HEALTH_STARTRELL_CLUGGINS, ATTACK_SPEED_STARTRELL_CLUGGINS, 
				DAMAGE_STARTRELL_CLUGGINS, new Image(STARTRELL_CLUGGINS_FIRING_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	/**
	 * Getter for cost attribute
	 */
	public int getCost() {
		return COST_STARTRELL_CLUGGINS * Controller.costMultiplier;
	}
	
	/**
	 * String representation of object
	 */
	public String toString() {
		return "Startrell Cluggins\n" + super.infoCard();
	}

	/**
	 * Returns the specific type of ammo this defender shoots
	 */
	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return new StartrellClugginsAmmo(this);
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
