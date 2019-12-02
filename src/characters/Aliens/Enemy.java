package characters.Aliens;

import characters.BoardCharacter;
import javafx.scene.image.Image;

public class Enemy extends BoardCharacter {
	// Default values for characters

	// File paths for the images of each DefenderTower
	public static final String LITTLEGREENMEN_IMAGE = "file:assets/litte-green-men.png";
	public static final String GRUNT_IMAGE 			= "file:assets/grunt.png";
	public static final String SPRINTER_IMAGE 		= "file:assets/sprinter.png";
	public static final String TANK_IMAGE 			= "file:assets/tank.png";
	public static final String MANHUNTER_IMAGE 		= "file:assets/manhunter.png";
	public static final String GARGANTUA_IMAGE 		= "file:assets/gargantua.png";

	// LittleGreenMen
	protected static final int HEALTH_LITTLE_GREEN_MEN = 100;
	protected static final int ATTACK_SPEED_LITTLE_GREEN_MEN = 100;
	protected static final int DAMAGE_LITTLE_GREEN_MEN = 100;

	// Grunt
	protected static final int HEALTH_GRUNT = 100;
	protected static final int ATTACK_SPEED_GRUNT = 100;
	protected static final int DAMAGE_GRUNT = 100;

	// Sprinter
	protected static final int HEALTH_SPRINTER = 100;
	protected static final int ATTACK_SPEED_SPRINTER = 100;
	protected static final int DAMAGE_SPRINTER = 100;

	// Tank
	protected static final int HEALTH_TANK = 100;
	protected static final int ATTACK_SPEED_TANK = 100;
	protected static final int DAMAGE_TANK = 100;

	// ManHunter
	protected static final int HEALTH_MAN_HUNTER = 100;
	protected static final int ATTACK_SPEED_MAN_HUNTER = 100;
	protected static final int DAMAGE_MAN_HUNTER = 100;

	// Gargantua
	protected static final int HEALTH_GARGANTUA = 100;
	protected static final int ATTACK_SPEED_GARGANTUA = 100;
	protected static final int DAMAGE_GARGANTUA = 100;

	// Constructor
	protected Enemy(int health, int attackSpeed, int damage, Image sprite) {
		super(health, attackSpeed, damage, sprite);
	}
}
