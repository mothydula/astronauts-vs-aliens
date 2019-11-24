package characters.Astronauts;

import javafx.scene.image.Image;

public class Tars extends DefenderTower{
	
	public Tars() {
		super(HEALTH_TARS, ATTACK_SPEED_TARS, 
				DAMAGE_TARS, new Image(TARS_IMAGE, 50, 50, false, false));
	}
}
