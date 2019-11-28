package characters.Astronauts;

import javafx.scene.image.Image;

public class StartrellCluggins extends DefenderTower{
	
	public StartrellCluggins() {
		super(HEALTH_STARTRELL_CLUGGINS, ATTACK_SPEED_STARTRELL_CLUGGINS, 
				DAMAGE_STARTRELL_CLUGGINS, new Image(STARTRELL_CLUGGINS_FIRING_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_STARTRELL_CLUGGINS;
	}
}
