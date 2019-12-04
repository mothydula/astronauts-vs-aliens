package characters.Aliens;

import javafx.scene.image.Image;

public class Grunt extends Enemy{
	
	public Grunt() {
		super(HEALTH_GRUNT, ATTACK_SPEED_GRUNT, 
				DAMAGE_GRUNT, new Image(GRUNT_IMAGE, 80, 80, false, false));
	}
}
