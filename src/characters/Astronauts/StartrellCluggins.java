package characters.Astronauts;

import ammo.Ammo;
import ammo.StartrellClugginsAmmo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to Startrell's properties; this defender is the 4th most powerful
 * (on a holistic scale) out of the 6 defenders who have the ability shoot.
 */
public class StartrellCluggins extends DefenderTower{
	
	public StartrellCluggins() {
		super(HEALTH_STARTRELL_CLUGGINS, ATTACK_SPEED_STARTRELL_CLUGGINS, 
				DAMAGE_STARTRELL_CLUGGINS, new Image(STARTRELL_CLUGGINS_FIRING_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_STARTRELL_CLUGGINS * Controller.costMultiplier;
	}
	
	public String toString() {
		return "Startrell Cluggins\n" + super.infoCard();
	}

	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return new StartrellClugginsAmmo(this);
	}

	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return true;
	}
}
