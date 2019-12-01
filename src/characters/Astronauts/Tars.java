package characters.Astronauts;

import javafx.scene.image.Image;

public class Tars extends DefenderTower{
	
	public Tars() {
		super(HEALTH_TARS, ATTACK_SPEED_TARS, 
				DAMAGE_TARS, new Image(TARS_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_TARS;
	}
	
	public String toString() {
		return "Tars\n" + super.infoCard();
	}
}
