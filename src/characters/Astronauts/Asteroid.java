package characters.Astronauts;

import ammo.Ammo;
import javafx.scene.image.Image;

public class Asteroid extends DefenderTower{
	
	public Asteroid() {
		super(HEALTH_ASTEROID, ATTACK_SPEED_ASTEROID, 
				DAMAGE_ASTEROID, new Image(ASTEROID_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_ASTEROID;
	}
	
	public String toString() {
		return "Asteroid\n" + super.infoCard();
	}

	@Override
	public Ammo shoot() {
		return null;
	}

	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return false;
	}
}
