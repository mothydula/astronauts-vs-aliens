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
 * File: StartrellClugginsAmmo.java
 * 
 * Description: This class describes the specific bullet StartrellCluggins
 * will shoot.
 */

package ammo;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;

/**
 * Startrell's Ammo class
 * */
public class StartrellClugginsAmmo extends Ammo{

	/**Constructor
	 * */
	public StartrellClugginsAmmo(DefenderTower dt) {
		super(dt, new Image(STARTRELL_CLUGGINS_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
		// TODO Auto-generated constructor stub
	}

}
