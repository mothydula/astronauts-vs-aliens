package characters.Aliens;

import characters.BoardCharacter;

public class Enemy extends BoardCharacter{
	// Default values for characters
	
	// AstroJoe
	protected static final int HEALTH_LITTLE_GREEN_MEN = 100;
	protected static final int ATTACK_SPEED_LITTLE_GREEN_MEN = 100;	
	protected static final int DAMAGE_LITTLE_GREEN_MEN = 100;
	
	// LoadedAstroJoe
	protected static final int HEALTH_GRUNT = 100;
	protected static final int ATTACK_SPEED_GRUNT = 100;
	protected static final int DAMAGE_GRUNT = 100;
	
	// SpaceBucksPrinter
	protected static final int HEALTH_SPRINTER = 100;
	protected static final int ATTACK_SPEED_SPRINTER = 100;
	protected static final int DAMAGE_SPRINTER = 100;
	
	// SpaceBucksFactory
	protected static final int HEALTH_TANK = 100;
	protected static final int ATTACK_SPEED_TANK = 100;
	protected static final int DAMAGE_TANK = 100;
	
	// StartrellCluggins
	protected static final int HEALTH_MAN_HUNTER = 100;
	protected static final int ATTACK_SPEED_MAN_HUNTER = 100;
	protected static final int DAMAGE_MAN_HUNTER = 100;
	
	// Asteroid
	protected static final int HEALTH_GARGANTUA = 100;
	protected static final int ATTACK_SPEED_GARGANTUA = 100;
	protected static final int DAMAGE_GARGANTUA = 100;
	
	// Constructor
	protected Enemy(int health, int attackSpeed, int damage) {
		super(health, attackSpeed, damage);
	}
}
