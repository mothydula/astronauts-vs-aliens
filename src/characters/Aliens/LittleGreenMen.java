package characters.Aliens;

import javafx.scene.image.Image;

public class LittleGreenMen extends Enemy{

	public LittleGreenMen() {
		super(HEALTH_LITTLE_GREEN_MEN, ATTACK_SPEED_LITTLE_GREEN_MEN, 
				DAMAGE_LITTLE_GREEN_MEN, new Image(LITTLEGREENMEN_IMAGE, 80, 80, false, false));
	}
	
}
