package characters.Astronauts;

import ammo.Ammo;
import ammo.TarsAmmo;
import game.Controller;
import javafx.scene.image.Image;

public class Tars extends DefenderTower{
	
	public Tars() {
		super(HEALTH_TARS, ATTACK_SPEED_TARS, 
				DAMAGE_TARS, new Image(TARS_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_TARS * Controller.costMultiplier;
	}
	
	public String toString() {
		return "Tars\n" + super.infoCard();
	}

	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return new TarsAmmo(this);
	}

	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return true;
	}
}
