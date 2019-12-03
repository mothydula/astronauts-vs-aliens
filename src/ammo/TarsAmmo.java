package ammo;

import characters.Astronauts.DefenderTower;
import characters.Astronauts.Tars;
import javafx.scene.image.Image;

/**
 * Class for Tars' Ammo
 * */
public class TarsAmmo extends Ammo{

	public TarsAmmo(DefenderTower dt, Image sprite) {
		super(new Tars(), new Image(TARS_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
		// TODO Auto-generated constructor stub
	}

}
