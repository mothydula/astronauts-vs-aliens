package characters.Astronauts;

import javafx.scene.image.Image;

public class AstroJoe extends DefenderTower {
	// Class fields
	
	// Constructor
	public AstroJoe() {
		super(HEALTH_ASTROJOE, ATTACK_SPEED_ASTROJOE, 
				DAMAGE_ASTROJOE, new Image(ASTRO_JOE_IMAGE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_ASTROJOE;
	}
}
