package characters.Astronauts;

import ammo.Ammo;
import ammo.MoonZeusAmmo;
import ammo.TarsAmmo;
import game.Controller;
import javafx.scene.image.Image;

public class MillenniumFalcon extends DefenderTower{
	
	public MillenniumFalcon() {
		super(HEALTH_MILLENNIUM_FALCON, ATTACK_SPEED_MILLENNIUM_FALCON, 
				DAMAGE_MILLENNIUM_FALCON, new Image(MILLENNIUM_FALCON_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_MILLENNIUM_FALCON * Controller.costMultiplier;
	}
	
	public String toString() {
		return "Millenium Falcon\n" + super.infoCard();
	}

	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Ammo shotOne() {
		return new MoonZeusAmmo(this);
	}
	
	public Ammo shotTwo() {
		return new TarsAmmo(this);
	}

	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return false;
	}
}
