package ammo;

import characters.Astronauts.AstroJoe;
import javafx.scene.image.Image;

/**
 * AstroJoe's Ammo class
 * */
public class AstroJoeAmmo extends Ammo{
	
	/**Constructor
	 * */
	public AstroJoeAmmo() {
		super(new AstroJoe(), new Image(ASTROJOE_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
}
