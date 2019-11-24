package characters.Astronauts;

import javafx.scene.image.Image;

public class SpaceBucksFactory extends DefenderTower{
	
	public SpaceBucksFactory() {
		super (HEALTH_SPACEBUCKS_FACTORY, ATTACK_SPEED_SPACEBUCKS_FACTORY, 
				DAMAGE_SPACEBUCKS_FACTORY, new Image(SPACEBUCKS_FACTORY_IMAGE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_SPACEBUCKS_PRINTER;
	} 
}
