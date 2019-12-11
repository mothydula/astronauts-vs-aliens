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
 * File: ExplosiveAstroJoeAmmo.java
 * 
 * Description: This class describes the specific bullet ExplosiveAstroJoeAmmo
 * will shoot.
 */

package ammo;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;
/**
 * ExplosiveAstroJoe's Ammo class
 * */
public class ExplosiveAstroJoeAmmo extends Ammo{
	
	/**Constructor
	 * */
	public ExplosiveAstroJoeAmmo(DefenderTower dt) {
		super(dt, new Image(EXPLOSIVE_ASTROJOE_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
		// TODO Auto-generated constructor stub
	}

}
