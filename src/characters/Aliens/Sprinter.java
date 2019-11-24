package characters.Aliens;

import javafx.scene.image.Image;

public class Sprinter extends Enemy{

	public Sprinter() {
		super(HEALTH_SPRINTER, ATTACK_SPEED_SPRINTER, 
				DAMAGE_SPRINTER, new Image(SPRINTER_IMAGE, 50, 50, false, false));
	}
}
