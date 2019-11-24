package characters.Astronauts;

import characters.BoardCharacter;
import javafx.scene.image.Image;

public abstract class DefenderTower extends BoardCharacter{
	// Default values for characters
	
	// File paths for the images of each DefenderTower
	public static final String ASTRO_JOE_IMAGE 			= "file:assets/astro-joe.png";
	public static final String LOADED_ASTRO_JOE_IMAGE 	= "file:assets/loaded-astro-joe.png";
	public static final String STARTRELL_CLUGGINS_IMAGE = "file:assets/startrell-cluggins.png";
	public static final String TARS_IMAGE 				= "file:assets/tars.png";
	public static final String MOON_ZEUS_IMAGE 			= "file:assets/moon-zeus.png";
	public static final String ASTEROID_IMAGE 			= "file:assets/asteroid.png";
	public static final String MILLENNIUM_FALCON_IMAGE	= "file:assets/millennium-falcon.png";
	public static final String SPACEBUCKS_PRINTER_IMAGE = "file:assets/spacebucks-printer.png";
	public static final String SPACEBUCKS_FACTORY_IMAGE = "file:assets/spacebucks-factory.png";
	
	public static final int SPRITE_WIDTH = 85;
	public static final int SPRITE_HEIGHT = 85;
	
	// AstroJoe
	protected static final int HEALTH_ASTROJOE = 100;
	protected static final int ATTACK_SPEED_ASTROJOE = 100;	
	protected static final int DAMAGE_ASTROJOE = 100;
	protected static final int COST_ASTROJOE = 15;
	
	// LoadedAstroJoe
	protected static final int HEALTH_LOADED_ASTROJOE = 100;
	protected static final int ATTACK_SPEED_LOADED_ASTROJOE = 100;
	protected static final int DAMAGE_LOADED_ASTROJOE = 100;
	protected static final int COST_LOADED_ASTROJOE = 25;
	
	// SpaceBucksPrinter
	protected static final int HEALTH_SPACEBUCKS_PRINTER = 100;
	protected static final int ATTACK_SPEED_SPACEBUCKS_PRINTER = 100;
	protected static final int DAMAGE_SPACEBUCKS_PRINTER = 100;
	protected static final int COST_SPACEBUCKS_PRINTER = 10;
	
	// SpaceBucksFactory
	protected static final int HEALTH_SPACEBUCKS_FACTORY = 100;
	protected static final int ATTACK_SPEED_SPACEBUCKS_FACTORY = 100;
	protected static final int DAMAGE_SPACEBUCKS_FACTORY = 100;
	protected static final int COST_SPACEBUCKS_FACTORY = 30;
	
	// StartrellCluggins
	protected static final int HEALTH_STARTRELL_CLUGGINS = 100;
	protected static final int ATTACK_SPEED_STARTRELL_CLUGGINS = 100;
	protected static final int DAMAGE_STARTRELL_CLUGGINS = 100;
	protected static final int COST_STARTRELL_CLUGGINS = 25;
	
	// Asteroid
	protected static final int HEALTH_ASTEROID = 100;
	protected static final int ATTACK_SPEED_ASTEROID = 100;
	protected static final int DAMAGE_ASTEROID = 100;
	protected static final int COST_ASTEROID = 20;
	
	// Tars
	protected static final int HEALTH_TARS = 100;
	protected static final int ATTACK_SPEED_TARS = 100;
	protected static final int DAMAGE_TARS = 100;
	protected static final int COST_TARS = 40;
	
	// MoonZeus
	protected static final int HEALTH_MOON_ZEUS = 100;
	protected static final int ATTACK_SPEED_MOON_ZEUS = 100;
	protected static final int DAMAGE_MOON_ZEUS = 100;
	protected static final int COST_MOON_ZEUS = 50;
	
	//MillenniumFalcon
	protected static final int HEALTH_MILLENNIUM_FALCON = 100;
	protected static final int ATTACK_SPEED_MILLENNIUM_FALCON = 100;
	protected static final int DAMAGE_MILLENNIUM_FALCON = 100;
	protected static final int COST_MILLENNIUM_FALCON = 100;
	
	
	// Constructor
	protected DefenderTower(int health, int attackSpeed, int damage, Image sprite) {
		super(health, attackSpeed, damage, sprite);
	}
	
	// Methods
	public abstract int getCost();
}
