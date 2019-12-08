package characters.Astronauts;

import ammo.Ammo;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to the Asteroid's properties; this defender is one of three
 * non-shooting board characters. The purpose of the asteroid is to simply take alien damage; it cannot
 * attack/shoot.
 */
public class Asteroid extends DefenderTower{
	
	public Asteroid() {
		super(HEALTH_ASTEROID, ATTACK_SPEED_ASTEROID, 
				DAMAGE_ASTEROID, new Image(ASTEROID_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_ASTEROID * Controller.costMultiplier;
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
