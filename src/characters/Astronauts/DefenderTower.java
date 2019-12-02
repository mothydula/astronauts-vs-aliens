package characters.Astronauts;

import characters.BoardCharacter;
import javafx.scene.image.Image;

public abstract class DefenderTower extends BoardCharacter{
	// Default values for characters
	public static final int MONEY_GEN_TIMELINE = 15; // Currency
	public static final double REFUND_MULTIPLIER = 0.4; // Refund multiplier (60%)
	
	// AstroJoe
	protected static final int HEALTH_ASTROJOE = 100;
	protected static final int ATTACK_SPEED_ASTROJOE = 100;	
	protected static final int DAMAGE_ASTROJOE = 100;
	protected static final int COST_ASTROJOE = 25;
	public static final String ASTRO_JOE_IMAGE 			= "file:assets/astro-joe.png";
	public static final String ASTRO_JOE_GIF			= "file:assets/astro-joe.gif";
	public static final String ASTRO_JOE_FIRING_GIF 	= "file:assets/astro-joe-firing.gif";
	
	// ExplosiveAstroJoe
	protected static final int HEALTH_EXPLOSIVE_ASTROJOE = 100;
	protected static final int ATTACK_SPEED_EXPLOSIVE_ASTROJOE = 100;
	protected static final int DAMAGE_EXPLOSIVE_ASTROJOE = 100;
	protected static final int COST_EXPLOSIVE_ASTROJOE = 50;
	public static final String EXPLOSIVE_ASTRO_JOE_IMAGE 	= "file:assets/explosive-astro-joe.png";
	public static final String EXPLOSIVE_ASTRO_JOE_GIF		= "file:assets/explosive-astro-joe.gif";
	
	// StartrellCluggins
	protected static final int HEALTH_STARTRELL_CLUGGINS = 100;
	protected static final int ATTACK_SPEED_STARTRELL_CLUGGINS = 100;
	protected static final int DAMAGE_STARTRELL_CLUGGINS = 100;
	protected static final int COST_STARTRELL_CLUGGINS = 50;
	public static final String STARTRELL_CLUGGINS_IMAGE 		= "file:assets/startrell-cluggins.png";
	public static final String STARTRELL_CLUGGINS_GIF   		= "file:assets/startrell-cluggins.gif";
	public static final String STARTRELL_CLUGGINS_FIRING_GIF   	= "file:assets/startrell-cluggins-firing.gif";
	
	// Asteroid
	protected static final int HEALTH_ASTEROID = 100;
	protected static final int ATTACK_SPEED_ASTEROID = 100;
	protected static final int DAMAGE_ASTEROID = 100;
	protected static final int COST_ASTEROID = 25;
	public static final String ASTEROID_IMAGE 			= "file:assets/asteroid.png";
	public static final String ASTEROID_GIF				= "file:assets/asteroid.gif";
	
	// Tars
	protected static final int HEALTH_TARS = 100;
	protected static final int ATTACK_SPEED_TARS = 100;
	protected static final int DAMAGE_TARS = 100;
	protected static final int COST_TARS = 75;
	public static final String TARS_IMAGE 				= "file:assets/tars.png";
	public static final String TARS_GIF					= "file:assets/tars.gif";
	public static final String TARS_FIRING_GIF			= "file:assets/tars-firing.gif";
	
	// MoonZeus
	protected static final int HEALTH_MOON_ZEUS = 100;
	protected static final int ATTACK_SPEED_MOON_ZEUS = 100;
	protected static final int DAMAGE_MOON_ZEUS = 100;
	protected static final int COST_MOON_ZEUS = 125;
	public static final String MOON_ZEUS_IMAGE 			= "file:assets/moon-zeus.png";
	public static final String MOON_ZEUS_GIF 			= "file:assets/moon-zeus.gif";
	public static final String MOON_ZEUS_FIRING_GIF		= "file:assets/moon-zeus-firing.gif";
	
	//MillenniumFalcon
	protected static final int HEALTH_MILLENNIUM_FALCON = 100;
	protected static final int ATTACK_SPEED_MILLENNIUM_FALCON = 100;
	protected static final int DAMAGE_MILLENNIUM_FALCON = 100;
	protected static final int COST_MILLENNIUM_FALCON = 250;
	public static final String MILLENNIUM_FALCON_IMAGE		= "file:assets/spaceship.png";
	public static final String MILLENNIUM_FALCON_GIF		= "file:assets/spaceship.gif";
	public static final String MILLENNIUM_FALCON_FIRING_GIF = "file:assets/spaceship-firing.gif";
	
	// Constructor
	protected DefenderTower(int health, int attackSpeed, int damage, Image sprite) {
		super(health, attackSpeed, damage, sprite);
	}
	
	// Methods
	public abstract int getCost();
	
	
}
