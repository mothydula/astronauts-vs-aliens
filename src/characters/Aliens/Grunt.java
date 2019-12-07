package characters.Aliens;

import game.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Grunt extends Enemy {

	// Grunt - Walk
	protected static final Image GRUNT_WALK = new Image("file:assets/aliens/grunt-walk-spritesheet.png");
	protected static final int GRUNT_WALK_COLUMNS = 13;
	protected static final int GRUNT_WALK_COUNT = 13;
	protected static final int GRUNT_WALK_WIDTH = 734; // pixel height of image
	protected static final int GRUNT_WALK_HEIGHT = 386; // pixel width of image
	protected static final int GRUNT_WALK_TIME = 1000;

	// Grunt - Attack
	protected static final Image GRUNT_ATTACK = new Image("file:assets/aliens/grunt-attack-spritesheet.png");
	protected static final int GRUNT_ATTACK_COLUMNS = 9;
	protected static final int GRUNT_ATTACK_COUNT = 9;
	protected static final int GRUNT_ATTACK_WIDTH = 756; // pixel height of image
	protected static final int GRUNT_ATTACK_HEIGHT = 523; // pixel width of image
	protected static final int GRUNT_ATTACK_TIME = 1000;

	// Grunt - Die
	protected static final Image GRUNT_DIE = new Image("file:assets/aliens/grunt-die-spritesheet.png");
	protected static final int GRUNT_DIE_COLUMNS = 13;
	protected static final int GRUNT_DIE_COUNT = 13;
	protected static final int GRUNT_DIE_WIDTH = 771; // pixel height of image
	protected static final int GRUNT_DIE_HEIGHT = 390; // pixel width of image
	protected static final int GRUNT_DIE_TIME = 1000;

	public Grunt() {
		super(HEALTH_GRUNT, ATTACK_SPEED_GRUNT, 
				DAMAGE_GRUNT, new Image(GRUNT_IMAGE, 80, 80, false, false));
		generateAnimations();
	}
	
	/**
	 * Generates the walk, attack, and die animations and sets the 
	 * initial imageview shown on the StackPane to be the walk
	 * animation.
	 */
	public void generateAnimations() {
		ImageView walkView = generateAnimation(
				GRUNT_WALK, 
				GRUNT_WALK_COUNT, 
				GRUNT_WALK_COLUMNS, 
				GRUNT_WALK_WIDTH, 
				GRUNT_WALK_HEIGHT, 
				GRUNT_WALK_TIME,
				WALK_ID);
		super.setWalkView(walkView);
		
		ImageView attackView = generateAnimation(
				GRUNT_ATTACK, 
				GRUNT_ATTACK_COUNT, 
				GRUNT_ATTACK_COLUMNS, 
				GRUNT_ATTACK_WIDTH, 
				GRUNT_ATTACK_HEIGHT, 
				GRUNT_ATTACK_TIME,
				ATTACK_ID);
		super.setAttackView(attackView);
		
		ImageView dieView = generateAnimation(
				GRUNT_DIE, 
				GRUNT_DIE_COUNT, 
				GRUNT_DIE_COLUMNS, 
				GRUNT_DIE_WIDTH, 
				GRUNT_DIE_HEIGHT, 
				GRUNT_DIE_TIME,
				DIE_ID);
		super.setDieView(dieView);
	}

}
