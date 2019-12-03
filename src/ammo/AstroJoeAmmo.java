package ammo;

import characters.Astronauts.AstroJoe;
import javafx.scene.image.Image;

public class AstroJoeAmmo extends Ammo{
	
	public AstroJoeAmmo() {
		super(new AstroJoe(), new Image(ASTROJOE_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
}
