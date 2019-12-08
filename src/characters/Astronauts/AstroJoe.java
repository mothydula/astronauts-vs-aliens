package characters.Astronauts;

import ammo.Ammo;
import ammo.AstroJoeAmmo;
import game.Controller;
import javafx.scene.image.Image;

public class AstroJoe extends DefenderTower {
	// Class fields
	
	// Constructor
	public AstroJoe() {
		super(HEALTH_ASTROJOE, ATTACK_SPEED_ASTROJOE, 
				DAMAGE_ASTROJOE, new Image(ASTRO_JOE_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_ASTROJOE * Controller.costMultiplier;
	}
	
	public String toString() {
		return "AstroJoe\n" + super.infoCard();
	}

	@Override
	public Ammo shoot() {
		return new AstroJoeAmmo(this);
	}

	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return true;
	}
}
