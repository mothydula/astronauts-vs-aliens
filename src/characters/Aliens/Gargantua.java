package characters.Aliens;

import javafx.scene.image.Image;

public class Gargantua extends Enemy{

	public Gargantua() {
		super(HEALTH_GARGANTUA, ATTACK_SPEED_GARGANTUA, 
				DAMAGE_GARGANTUA, new Image(GARGANTUA_IMAGE, 80, 80, false, false));
	}
}
