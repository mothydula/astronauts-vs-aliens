package characters.Astronauts;

import javafx.scene.image.Image;

public class Asteroid extends DefenderTower{
	
	public Asteroid() {
		super(HEALTH_ASTEROID, ATTACK_SPEED_ASTEROID, 
				DAMAGE_ASTEROID, new Image(ASTEROID_IMAGE, 50, 50, false, false));
	}
}
