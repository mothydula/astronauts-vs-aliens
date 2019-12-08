package characters.Astronauts;

import ammo.Ammo;
import characters.BoardCharacter;
import javafx.scene.image.Image;

/*
 * This abstract class is the parent class of all defenders. It contains as constants the file paths
 * for all the individual defender sprites as well as the specs for each individual defender; health,
 * attack speed, damage (damage that they are able to take from aliens before death) as well as cost
 * (the SpaceBucks required to 'buy' them and place them on the board). Also contained within this
 * class are abstract methods implemented within the individual child defender classes. All individual
 * defender classes extend this one.
 */
public abstract class DefenderTower extends BoardCharacter{
	// Default values for characters
	public static final int MONEY_GEN_TIMELINE = 15; // Currency
	public static final double REFUND_MULTIPLIER = 0.4; // Refund multiplier (60%)
	
	// AstroJoe
	protected static final int HEALTH_ASTROJOE = 1000;
	protected static final int ATTACK_SPEED_ASTROJOE = 100;	
	protected static final int DAMAGE_ASTROJOE = 25;
	protected static final int COST_ASTROJOE = 25;
	public static final String ASTRO_JOE_IMAGE 			= "file:assets/defenders/astro-joe.png";
	public static final String ASTRO_JOE_GIF			= "file:assets/defenders/astro-joe.gif";
	public static final String ASTRO_JOE_FIRING_GIF 	= "file:assets/defenders/astro-joe-firing.gif";
	
	// ExplosiveAstroJoe
	protected static final int HEALTH_EXPLOSIVE_ASTROJOE = 2000;
	protected static final int ATTACK_SPEED_EXPLOSIVE_ASTROJOE = 150;
	protected static final int DAMAGE_EXPLOSIVE_ASTROJOE = 40;
	protected static final int COST_EXPLOSIVE_ASTROJOE = 50;
	public static final String EXPLOSIVE_ASTRO_JOE_IMAGE 	= "file:assets/defenders/explosive-astro-joe.png";
	public static final String EXPLOSIVE_ASTRO_JOE_GIF		= "file:assets/defenders/explosive-astro-joe.gif";
	
	// StartrellCluggins
	protected static final int HEALTH_STARTRELL_CLUGGINS = 2500;
	protected static final int ATTACK_SPEED_STARTRELL_CLUGGINS = 500;
	protected static final int DAMAGE_STARTRELL_CLUGGINS = 10;
	protected static final int COST_STARTRELL_CLUGGINS = 50;
	public static final String STARTRELL_CLUGGINS_IMAGE 		= "file:assets/defenders/startrell-cluggins.png";
	public static final String STARTRELL_CLUGGINS_GIF   		= "file:assets/defenders/startrell-cluggins.gif";
	public static final String STARTRELL_CLUGGINS_FIRING_GIF   	= "file:assets/defenders/startrell-cluggins-firing.gif";
	
	// Asteroid
	protected static final int HEALTH_ASTEROID = 10000;
	protected static final int ATTACK_SPEED_ASTEROID = 0;
	protected static final int DAMAGE_ASTEROID = 0;
	protected static final int COST_ASTEROID = 25;
	public static final String ASTEROID_IMAGE 			= "file:assets/defenders/asteroid.png";
	public static final String ASTEROID_GIF				= "file:assets/defenders/asteroid.gif";
	
	// Tars
	protected static final int HEALTH_TARS = 3000;
	protected static final int ATTACK_SPEED_TARS = 150;
	protected static final int DAMAGE_TARS = 50;
	protected static final int COST_TARS = 75;
	public static final String TARS_IMAGE 				= "file:assets/defenders/tars.png";
	public static final String TARS_GIF					= "file:assets/defenders/tars.gif";
	public static final String TARS_FIRING_GIF			= "file:assets/defenders/tars-firing.gif";
	
	// MoonZeus
	protected static final int HEALTH_MOON_ZEUS = 4000;
	protected static final int ATTACK_SPEED_MOON_ZEUS = 100;
	protected static final int DAMAGE_MOON_ZEUS = 60;
	protected static final int COST_MOON_ZEUS = 125;
	public static final String MOON_ZEUS_IMAGE 			= "file:assets/defenders/moon-zeus.png";
	public static final String MOON_ZEUS_GIF 			= "file:assets/defenders/moon-zeus.gif";
	public static final String MOON_ZEUS_FIRING_GIF		= "file:assets/defenders/moon-zeus-firing.gif";
	
	//MillenniumFalcon
	protected static final int HEALTH_MILLENNIUM_FALCON = 30000;
	protected static final int ATTACK_SPEED_MILLENNIUM_FALCON = 100;
	protected static final int DAMAGE_MILLENNIUM_FALCON = 200;
	protected static final int COST_MILLENNIUM_FALCON = 250;
	public static final String MILLENNIUM_FALCON_IMAGE		= "file:assets/defenders/spaceship.png";
	public static final String MILLENNIUM_FALCON_GIF		= "file:assets/defenders/spaceship.gif";
	public static final String MILLENNIUM_FALCON_FIRING_GIF = "file:assets/defenders/spaceship-firing.gif";
	
	// Constructor
	protected DefenderTower(int health, int attackSpeed, int damage, Image sprite) {
		super(health, attackSpeed, damage, sprite);
	}
	
	// Methods
	public abstract int getCost();
	
	public abstract Ammo shoot();
	
	public abstract boolean canShoot();
	
}