package characters.Astronauts;

import ammo.Ammo;
import ammo.ExplosiveAstroJoeAmmo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to ExplosiveAstroJoe's properties; this defender is the 5th
 * most powerful (on a holistic scale) out of the 6 defenders who have the ability shoot. He is
 * better than only AstroJoe.
 */
public class ExplosiveAstroJoe extends DefenderTower{

	public ExplosiveAstroJoe() {
		super(HEALTH_EXPLOSIVE_ASTROJOE, ATTACK_SPEED_EXPLOSIVE_ASTROJOE, 
				DAMAGE_EXPLOSIVE_ASTROJOE, new Image(EXPLOSIVE_ASTRO_JOE_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}

	public int getCost() {
		return COST_EXPLOSIVE_ASTROJOE * Controller.costMultiplier;
	}
	
	public String toString() {
		return "ExplosiveAstroJoe\n" + super.infoCard();
	}

	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return new ExplosiveAstroJoeAmmo(this);
	}

	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return true;
	}
}
