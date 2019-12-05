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
		// Walk
		ImageView walkView = new ImageView(TANK_WALK);
		walkView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				TANK_WALK_WIDTH, 
				TANK_WALK_HEIGHT
			));
		walkView.setFitWidth(IMAGE_WIDTH);
		walkView.setFitHeight(IMAGE_HEIGHT);
		Animation walkAnimation = new SpriteAnimation(walkView, Duration.millis(TANK_WALK_TIME), 
				TANK_WALK_COUNT,
				TANK_WALK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				TANK_WALK_WIDTH, 
				TANK_WALK_HEIGHT
			);
		walkAnimation.setCycleCount(Animation.INDEFINITE);
		walkAnimation.play();
		super.setImageView(walkView); // This will be the initial image view that is shown
		super.setWalkAnimation(walkAnimation);

		// Attack
		final ImageView attackView = new ImageView(TANK_ATTACK);
		attackView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				TANK_ATTACK_WIDTH, 
				TANK_ATTACK_HEIGHT
			));
		attackView.setFitWidth(IMAGE_WIDTH);
		attackView.setFitHeight(IMAGE_HEIGHT);
		final Animation attackAnimation = new SpriteAnimation(attackView, Duration.millis(TANK_ATTACK_TIME),
				TANK_ATTACK_COUNT, 
				TANK_ATTACK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				TANK_ATTACK_WIDTH, 
				TANK_ATTACK_HEIGHT
			);
		attackAnimation.setCycleCount(Animation.INDEFINITE);
		super.setAttackAnimation(attackAnimation);

		// Die
		final ImageView dieView = new ImageView(TANK_DIE);
		dieView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				TANK_DIE_WIDTH, 
				TANK_DIE_HEIGHT));
		dieView.setFitWidth(IMAGE_WIDTH);
		dieView.setFitHeight(IMAGE_HEIGHT);
		final Animation dieAnimation = new SpriteAnimation(dieView, Duration.millis(TANK_DIE_TIME), 
				TANK_DIE_COUNT,
				TANK_DIE_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				TANK_DIE_WIDTH, 
				TANK_DIE_HEIGHT
			);
		dieAnimation.setCycleCount(Animation.INDEFINITE);
		dieAnimation.play();
		super.setDieAnimation(dieAnimation);
	}

}
