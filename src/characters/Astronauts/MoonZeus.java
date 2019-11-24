package characters.Astronauts;

import javafx.scene.image.Image;

public class MoonZeus extends DefenderTower{
	
	public MoonZeus() {
		super(HEALTH_MOON_ZEUS, ATTACK_SPEED_MOON_ZEUS, 
				DAMAGE_MOON_ZEUS, new Image(MOON_ZEUS_IMAGE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
}
