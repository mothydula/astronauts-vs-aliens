package characters.Astronauts;

import javafx.scene.image.Image;

public class Asteroid extends DefenderTower{
	
	public Asteroid() {
		super(HEALTH_ASTEROID, ATTACK_SPEED_ASTEROID, 
				DAMAGE_ASTEROID, new Image(ASTEROID_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_ASTEROID;
	}
}
