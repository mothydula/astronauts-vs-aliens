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
 * File: AstroJoeAmmo.java
 * 
 * Description: This class describes the specific bullet AstroJoe
 * will shoot.
 */

package ammo;

import characters.Astronauts.AstroJoe;
import javafx.scene.image.Image;

/**
 * AstroJoe's Ammo class
 * */
public class AstroJoeAmmo extends Ammo {
	
	/**Constructor
	 * */
	public AstroJoeAmmo(AstroJoe alien) {
		super(alien, new Image(ASTROJOE_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
}
