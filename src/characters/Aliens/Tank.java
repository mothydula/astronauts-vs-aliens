package characters.Aliens;

import javafx.scene.image.Image;

public class Tank extends Enemy{
	
	public Tank() {
		super(HEALTH_TANK, ATTACK_SPEED_TANK, 
				DAMAGE_TANK, new Image(TANK_IMAGE, 50, 50, false, false));
	}
}
