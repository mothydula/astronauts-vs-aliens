package characters.Astronauts;

import ammo.Ammo;
import ammo.ExplosiveAstroJoeAmmo;
import javafx.scene.image.Image;

public class RailGun extends DefenderTower{

	public RailGun() {
		super(HEALTH_RAIL_GUN, ATTACK_SPEED_RAIL_GUN, 
				DAMAGE_RAIL_GUN, new Image(RAIL_GUN_IMAGE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}

	public int getCost() {
		return COST_EXPLOSIVE_ASTROJOE;
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