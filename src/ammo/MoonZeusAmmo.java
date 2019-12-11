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
 * File: MoonZeusAmmo.java
 * 
 * Description: This class describes the specific bullet MoonZeus
 * will shoot.
 */

package ammo;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;

/**
 * Moon Zeus' Ammo class
 * */
public class MoonZeusAmmo extends Ammo{
	
	/**Constructor
	 * */
	public MoonZeusAmmo(DefenderTower dt) {
		super(dt, new Image(MOON_ZEUS_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
		// TODO Auto-generated constructor stub
	}

}
