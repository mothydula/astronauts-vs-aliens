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
 * File: TarsAmmo.java
 * 
 * Description: This class describes the specific bullet Tars
 * will shoot.
 */

package ammo;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;

/**
 * Class for TARS' Ammo
 * */
public class TarsAmmo extends Ammo{

	/**Constructor
	 * */
	public TarsAmmo(DefenderTower dt) {
		super(dt, new Image(TARS_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
		// TODO Auto-generated constructor stub
	}

}
