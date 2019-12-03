package ammo;

import characters.Astronauts.DefenderTower;
import characters.Astronauts.ExplosiveAstroJoe;
import javafx.scene.image.Image;
/**
 * ExplosiveAstroJoe's Ammo class
 * */
public class ExplosiveAstroJoeAmmo extends Ammo{
	
	public ExplosiveAstroJoeAmmo(DefenderTower dt, Image sprite) {
		super(new ExplosiveAstroJoe(), new Image(EXPLOSIVE_ASTROJOE_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
		// TODO Auto-generated constructor stub
	}

}
