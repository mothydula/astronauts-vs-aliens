package characters.Astronauts;

import ammo.Ammo;
import ammo.AstroJoeAmmo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to AstroJoe's properties; this defender is the least powerful
 * (on a holistic scale) out of the defenders who have the ability shoot (so he is understandably
 * the cheapest to buy).
 */
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
		return "Astro Joe\n" + super.infoCard();
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
