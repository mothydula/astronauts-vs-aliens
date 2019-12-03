package ammo;

import characters.Astronauts.DefenderTower;
import characters.Astronauts.StartrellCluggins;
import javafx.scene.image.Image;

/**
 * Startrell's Ammo class
 * */
public class StartrellClugginsAmmo extends Ammo{

	/**Constructor
	 * */
	public StartrellClugginsAmmo(DefenderTower dt, Image sprite) {
		super(new StartrellCluggins(), new Image(STARTRELL_CLUGGINS_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
		// TODO Auto-generated constructor stub
	}

}
