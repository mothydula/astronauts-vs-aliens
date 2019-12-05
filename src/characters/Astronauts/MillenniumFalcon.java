package characters.Astronauts;

import ammo.Ammo;
import javafx.scene.image.Image;

public class MillenniumFalcon extends DefenderTower{
	
	public MillenniumFalcon() {
		super(HEALTH_MILLENNIUM_FALCON, ATTACK_SPEED_MILLENNIUM_FALCON, 
				DAMAGE_MILLENNIUM_FALCON, new Image(MILLENNIUM_FALCON_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_MILLENNIUM_FALCON;
	}
	
	public String toString() {
		return "Millennium Falcon\n" + super.infoCard();
	}

	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return false;
	}
}
