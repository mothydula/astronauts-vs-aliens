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
 * File: MillenniumFalcon.java
 * 
 * Description: This class describes the specific characteristics
 * and behavior of the tower MillenniumFalcon.
 */

package characters.Astronauts;

import ammo.Ammo;
import ammo.MoonZeusAmmo;
import ammo.TarsAmmo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to the Millenium Falcon's properties; this defender is the
 * most powerful (on a holistic scale) out of the 6 defenders who have the ability shoot. The ammo
 * fired from this defender does the most damage out of any.
 */
public class MillenniumFalcon extends DefenderTower{
	
	public MillenniumFalcon() {
		super(HEALTH_MILLENNIUM_FALCON, ATTACK_SPEED_MILLENNIUM_FALCON, 
				DAMAGE_MILLENNIUM_FALCON, new Image(MILLENNIUM_FALCON_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	/**
	 * Getter for cost attribute
	 */
	public int getCost() {
		return COST_MILLENNIUM_FALCON * Controller.costMultiplier;
	}
	
	/**
	 * String representation of object
	 */
	public String toString() {
		return "Millennium Falcon\n" + super.infoCard();
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
	 * Returns the first type of Ammo this defender can shoot
	 * @return Ammo instance
	 */
	public Ammo shotOne() {
		return new MoonZeusAmmo(this);
	}
	
	/**
	 * Returns the second type of Ammo this defender can shoot
	 * @return Ammo instance
	 */
	public Ammo shotTwo() {
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
