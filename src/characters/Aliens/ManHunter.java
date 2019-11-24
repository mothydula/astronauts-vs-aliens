package characters.Aliens;

import javafx.scene.image.Image;

public class ManHunter extends Enemy{

	public ManHunter() {
		super(HEALTH_MAN_HUNTER, ATTACK_SPEED_MAN_HUNTER, 
				DAMAGE_MAN_HUNTER, new Image(MANHUNTER_IMAGE, 50, 50, false, false));
	}
}
