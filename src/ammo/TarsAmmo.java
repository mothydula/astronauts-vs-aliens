package ammo;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;

/**
 * Class for Tars' Ammo
 * */
public class TarsAmmo extends Ammo{

	/**Constructor
	 * */
	public TarsAmmo(DefenderTower dt) {
		super(dt, new Image(TARS_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
		// TODO Auto-generated constructor stub
	}

}
