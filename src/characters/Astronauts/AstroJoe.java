package characters.Astronauts;

import javafx.scene.image.Image;

public class AstroJoe extends DefenderTower {
	// Class fields
	
	// Constructor
	public AstroJoe() {
		super(HEALTH_ASTROJOE, ATTACK_SPEED_ASTROJOE, 
				DAMAGE_ASTROJOE, new Image(ASTRO_JOE_IMAGE, 50, 50, false, false));
	}
}
