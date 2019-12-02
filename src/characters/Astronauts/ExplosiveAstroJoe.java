package characters.Astronauts;

import javafx.scene.image.Image;

public class ExplosiveAstroJoe extends DefenderTower{

	public ExplosiveAstroJoe() {
		super(HEALTH_EXPLOSIVE_ASTROJOE, ATTACK_SPEED_EXPLOSIVE_ASTROJOE, 
				DAMAGE_EXPLOSIVE_ASTROJOE, new Image(EXPLOSIVE_ASTRO_JOE_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}

	public int getCost() {
		return COST_EXPLOSIVE_ASTROJOE;
	}
	
	public String toString() {
		return "ExplosiveAstroJoe\n" + super.infoCard();
	}
}
