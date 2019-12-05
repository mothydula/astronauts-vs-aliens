package characters.Astronauts;

import ammo.Ammo;
import ammo.StartrellClugginsAmmo;
import javafx.scene.image.Image;

public class StartrellCluggins extends DefenderTower{
	
	public StartrellCluggins() {
		super(HEALTH_STARTRELL_CLUGGINS, ATTACK_SPEED_STARTRELL_CLUGGINS, 
				DAMAGE_STARTRELL_CLUGGINS, new Image(STARTRELL_CLUGGINS_FIRING_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_STARTRELL_CLUGGINS;
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
