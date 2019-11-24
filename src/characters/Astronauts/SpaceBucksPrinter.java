package characters.Astronauts;

import javafx.scene.image.Image;

public class SpaceBucksPrinter extends DefenderTower{
	
	public SpaceBucksPrinter() {
		super(HEALTH_SPACEBUCKS_PRINTER, ATTACK_SPEED_SPACEBUCKS_PRINTER, 
				DAMAGE_SPACEBUCKS_PRINTER, new Image(SPACEBUCKS_PRINTER_IMAGE, 50, 50, false, false));
	}
}
