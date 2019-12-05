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
		super(HEALTH_GRUNT, ATTACK_SPEED_GRUNT, DAMAGE_GRUNT, new Image(GRUNT_IMAGE, 50, 50, false, false));
		
		generateAnimations();
	}
	
	/**
	 * Generates the walk, attack, and die animations and sets the 
	 * initial imageview shown on the StackPane to be the walk
	 * animation.
	 */
	public void generateAnimations() {
		// Walk
		ImageView walkView = new ImageView(GRUNT_WALK);
		walkView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				GRUNT_WALK_WIDTH, 
				GRUNT_WALK_HEIGHT
			));
		walkView.setFitWidth(IMAGE_WIDTH);
		walkView.setFitHeight(IMAGE_HEIGHT);
		Animation walkAnimation = new SpriteAnimation(walkView, Duration.millis(GRUNT_WALK_TIME), 
				GRUNT_WALK_COUNT,
				GRUNT_WALK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				GRUNT_WALK_WIDTH, 
				GRUNT_WALK_HEIGHT
			);
		walkAnimation.setCycleCount(Animation.INDEFINITE);
		walkAnimation.play();
		super.setImageView(walkView); // This will be the initial image view that is shown
		super.setWalkAnimation(walkAnimation);

		// Attack
		final ImageView attackView = new ImageView(GRUNT_ATTACK);
		attackView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				GRUNT_ATTACK_WIDTH, 
				GRUNT_ATTACK_HEIGHT
			));
		attackView.setFitWidth(IMAGE_WIDTH);
		attackView.setFitHeight(IMAGE_HEIGHT);
		final Animation attackAnimation = new SpriteAnimation(attackView, Duration.millis(GRUNT_ATTACK_TIME),
				GRUNT_ATTACK_COUNT, 
				GRUNT_ATTACK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				GRUNT_ATTACK_WIDTH, 
				GRUNT_ATTACK_HEIGHT
			);
		attackAnimation.setCycleCount(Animation.INDEFINITE);
		attackAnimation.play();
		super.setAttackAnimation(attackAnimation);

		// Die
		final ImageView dieView = new ImageView(GRUNT_DIE);
		dieView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				GRUNT_DIE_WIDTH, 
				GRUNT_DIE_HEIGHT));
		dieView.setFitWidth(IMAGE_WIDTH);
		dieView.setFitHeight(IMAGE_HEIGHT);
		final Animation dieAnimation = new SpriteAnimation(dieView, Duration.millis(GRUNT_DIE_TIME), 
				GRUNT_DIE_COUNT,
				GRUNT_DIE_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				GRUNT_DIE_WIDTH, 
				GRUNT_DIE_HEIGHT
			);
		dieAnimation.setCycleCount(Animation.INDEFINITE);
		dieAnimation.play();
		super.setDieAnimation(dieAnimation);
	}

}
