package characters.Aliens;

import game.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Tank extends Enemy {

	// Tank - Walk
	protected static final Image TANK_WALK = new Image("file:assets/aliens/tank-walk-spritesheet.png");
	protected static final int TANK_WALK_COLUMNS = 17;
	protected static final int TANK_WALK_COUNT = 17;
	protected static final int TANK_WALK_WIDTH = 885; // pixel height of image
	protected static final int TANK_WALK_HEIGHT = 686; // pixel width of image
	protected static final int TANK_WALK_TIME = 1700;

	// Tank - Attack
	protected static final Image TANK_ATTACK = new Image("file:assets/aliens/tank-attack-spritesheet.png");
	protected static final int TANK_ATTACK_COLUMNS = 17;
	protected static final int TANK_ATTACK_COUNT = 17;
	protected static final int TANK_ATTACK_WIDTH = 788; // pixel height of image
	protected static final int TANK_ATTACK_HEIGHT = 1135; // pixel width of image
	protected static final int TANK_ATTACK_TIME = 1800;

	// Tank - Die
	protected static final Image TANK_DIE = new Image("file:assets/aliens/tank-die-spritesheet.png");
	protected static final int TANK_DIE_COLUMNS = 23;
	protected static final int TANK_DIE_COUNT = 23;
	protected static final int TANK_DIE_WIDTH = 1332; // pixel height of image
	protected static final int TANK_DIE_HEIGHT = 1173; // pixel width of image
	protected static final int TANK_DIE_TIME = 2000;

	public Tank() {
		super(HEALTH_TANK, ATTACK_SPEED_TANK, 
				DAMAGE_TANK, new Image(TANK_IMAGE, 80, 80, false, false));
		generateAnimations();
	}
	
	/**
	 * Generates the walk, attack, and die animations and sets the 
	 * initial imageview shown on the StackPane to be the walk
	 * animation.
	 */
	public void generateAnimations() {
		ImageView walkView = generateAnimation(
				TANK_WALK, 
				TANK_WALK_COUNT, 
				TANK_WALK_COLUMNS, 
				TANK_WALK_WIDTH, 
				TANK_WALK_HEIGHT, 
				TANK_WALK_TIME,
				WALK_ID);
		super.setWalkView(walkView);
		
		ImageView attackView = generateAnimation(
				TANK_ATTACK, 
				TANK_ATTACK_COUNT, 
				TANK_ATTACK_COLUMNS, 
				TANK_ATTACK_WIDTH, 
				TANK_ATTACK_HEIGHT, 
				TANK_ATTACK_TIME,
				ATTACK_ID);
		super.setAttackView(attackView);
		
		ImageView dieView = generateAnimation(
				TANK_DIE, 
				TANK_DIE_COUNT, 
				TANK_DIE_COLUMNS, 
				TANK_DIE_WIDTH, 
				TANK_DIE_HEIGHT, 
				TANK_DIE_TIME,
				DIE_ID);
		super.setDieView(dieView);
	}

}
