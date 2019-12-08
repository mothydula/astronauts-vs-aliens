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
