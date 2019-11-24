package characters.Astronauts;

import javafx.scene.image.Image;

public class MillenniumFalcon extends DefenderTower{
	
	public MillenniumFalcon() {
		super(HEALTH_MILLENNIUM_FALCON, ATTACK_SPEED_MILLENNIUM_FALCON, 
				DAMAGE_MILLENNIUM_FALCON, new Image(MILLENNIUM_FALCON_IMAGE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
}
