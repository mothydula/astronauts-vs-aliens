package characters.Aliens;

import game.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Sprinter extends Enemy {

	// Sprinter - Walk
	protected static final Image SPRINTER_WALK = new Image("file:assets/aliens/sprinter-walk-spritesheet.png");
	protected static final int SPRINTER_WALK_COLUMNS = 17;
	protected static final int SPRINTER_WALK_COUNT = 17;
	protected static final int SPRINTER_WALK_WIDTH = 985; // pixel height of image
	protected static final int SPRINTER_WALK_HEIGHT = 743; // pixel width of image
	protected static final int SPRINTER_WALK_TIME = 750;

	// Sprinter - Attack
	protected static final Image SPRINTER_ATTACK = new Image("file:assets/aliens/sprinter-attack-spritesheet.png");
	protected static final int SPRINTER_ATTACK_COLUMNS = 13;
	protected static final int SPRINTER_ATTACK_COUNT = 13;
	protected static final int SPRINTER_ATTACK_WIDTH = 1008; // pixel height of image
	protected static final int SPRINTER_ATTACK_HEIGHT = 773; // pixel width of image
	protected static final int SPRINTER_ATTACK_TIME = 1000;

	// Sprinter - Die
	protected static final Image SPRINTER_DIE = new Image("file:assets/aliens/sprinter-die-spritesheet.png");
	protected static final int SPRINTER_DIE_COLUMNS = 21;
	protected static final int SPRINTER_DIE_COUNT = 21;
	protected static final int SPRINTER_DIE_WIDTH = 1544; // pixel height of image
	protected static final int SPRINTER_DIE_HEIGHT = 849; // pixel width of image
	protected static final int SPRINTER_DIE_TIME = 1500;

	public Sprinter() {
		super(HEALTH_SPRINTER, ATTACK_SPEED_SPRINTER, 
				DAMAGE_SPRINTER, new Image(SPRINTER_IMAGE, 80, 80, false, false));
		generateAnimations();
	}
	
	/**
	 * Generates the walk, attack, and die animations and sets the 
	 * initial imageview shown on the StackPane to be the walk
	 * animation.
	 */
	public void generateAnimations() {
		// Walk
		ImageView walkView = new ImageView(SPRINTER_WALK);
		walkView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				SPRINTER_WALK_WIDTH, 
				SPRINTER_WALK_HEIGHT
			));
		walkView.setFitWidth(IMAGE_WIDTH);
		walkView.setFitHeight(IMAGE_HEIGHT);
		Animation walkAnimation = new SpriteAnimation(walkView, Duration.millis(SPRINTER_WALK_TIME), 
				SPRINTER_WALK_COUNT,
				SPRINTER_WALK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				SPRINTER_WALK_WIDTH, 
				SPRINTER_WALK_HEIGHT
			);
		walkAnimation.setCycleCount(Animation.INDEFINITE);
		walkAnimation.play();
		super.setImageView(walkView); // This will be the initial image view that is shown
		super.setWalkAnimation(walkAnimation);

		// Attack
		final ImageView attackView = new ImageView(SPRINTER_ATTACK);
		attackView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				SPRINTER_ATTACK_WIDTH, 
				SPRINTER_ATTACK_HEIGHT
			));
		attackView.setFitWidth(IMAGE_WIDTH);
		attackView.setFitHeight(IMAGE_HEIGHT);
		final Animation attackAnimation = new SpriteAnimation(attackView, Duration.millis(SPRINTER_ATTACK_TIME),
				SPRINTER_ATTACK_COUNT, 
				SPRINTER_ATTACK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				SPRINTER_ATTACK_WIDTH, 
				SPRINTER_ATTACK_HEIGHT
			);
		attackAnimation.setCycleCount(Animation.INDEFINITE);
		attackAnimation.play();
		super.setAttackAnimation(attackAnimation);

		// Die
		final ImageView dieView = new ImageView(SPRINTER_DIE);
		dieView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				SPRINTER_DIE_WIDTH, 
				SPRINTER_DIE_HEIGHT));
		dieView.setFitWidth(IMAGE_WIDTH);
		dieView.setFitHeight(IMAGE_HEIGHT);
		final Animation dieAnimation = new SpriteAnimation(dieView, Duration.millis(SPRINTER_DIE_TIME), 
				SPRINTER_DIE_COUNT,
				SPRINTER_DIE_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				SPRINTER_DIE_WIDTH, 
				SPRINTER_DIE_HEIGHT
			);
		dieAnimation.setCycleCount(Animation.INDEFINITE);
		dieAnimation.play();
		super.setDieAnimation(dieAnimation);
	}

}
