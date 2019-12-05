package characters.Aliens;

import game.SpriteAnimation;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LittleGreenMen extends Enemy {

	// LittleGreenMen - Walk
	protected static final Image LITTLEGREENMAN_WALK = new Image("file:assets/aliens/littlegreenman-walk-spritesheet.png");
	protected static final int LGM_WALK_COLUMNS = 17;
	protected static final int LGM_WALK_COUNT = 17;
	protected static final int LGM_WALK_WIDTH = 428; // pixel height of image
	protected static final int LGM_WALK_HEIGHT = 298; // pixel width of image
	protected static final int LGM_WALK_TIME = 1500;

	// LittleGreenMen - Attack
	protected static final Image LITTLEGREENMAN_ATTACK = new Image("file:assets/aliens/littlegreenman-attack-spritesheet.png");
	protected static final int LGM_ATTACK_COLUMNS = 13;
	protected static final int LGM_ATTACK_COUNT = 13;
	protected static final int LGM_ATTACK_WIDTH = 637; // pixel height of image
	protected static final int LGM_ATTACK_HEIGHT = 336; // pixel width of image
	protected static final int LGM_ATTACK_TIME = 1000;

	// LittleGreenMen - Die
	protected static final Image LITTLEGREENMAN_DIE = new Image("file:assets/aliens/littlegreenman-die-spritesheet.png");
	protected static final int LGM_DIE_COLUMNS = 15;
	protected static final int LGM_DIE_COUNT = 15;
	protected static final int LGM_DIE_WIDTH = 727; // pixel height of image
	protected static final int LGM_DIE_HEIGHT = 387; // pixel width of image
	protected static final int LGM_DIE_TIME = 1700;

	public LittleGreenMen() {
		super(HEALTH_LITTLE_GREEN_MEN, ATTACK_SPEED_LITTLE_GREEN_MEN, DAMAGE_LITTLE_GREEN_MEN,
				new Image(LITTLEGREENMEN_IMAGE, 80, 80, false, false, false));
		generateAnimations();
	}

	/**
	 * Generates the walk, attack, and die animations and sets the 
	 * initial imageview shown on the StackPane to be the walk
	 * animation.
	 */
	public void generateAnimations() {
		// Walk
		ImageView walkView = new ImageView(LITTLEGREENMAN_WALK);
		walkView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				LGM_WALK_WIDTH, 
				LGM_WALK_HEIGHT
			));
		walkView.setFitWidth(IMAGE_WIDTH);
		walkView.setFitHeight(IMAGE_HEIGHT);
		Animation walkAnimation = new SpriteAnimation(walkView, Duration.millis(LGM_WALK_TIME), 
				LGM_WALK_COUNT,
				LGM_WALK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				LGM_WALK_WIDTH, 
				LGM_WALK_HEIGHT
			);
		walkAnimation.setCycleCount(Animation.INDEFINITE);
		walkAnimation.play();
		super.setImageView(walkView); // This will be the initial image view that is shown
		super.setWalkAnimation(walkAnimation);

		// Attack
		final ImageView attackView = new ImageView(LITTLEGREENMAN_ATTACK);
		attackView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				LGM_ATTACK_WIDTH, 
				LGM_ATTACK_HEIGHT
			));
		attackView.setFitWidth(IMAGE_WIDTH);
		attackView.setFitHeight(IMAGE_HEIGHT);
		final Animation attackAnimation = new SpriteAnimation(attackView, Duration.millis(LGM_ATTACK_TIME),
				LGM_ATTACK_COUNT, 
				LGM_ATTACK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				LGM_ATTACK_WIDTH, 
				LGM_ATTACK_HEIGHT
			);
		attackAnimation.setCycleCount(Animation.INDEFINITE);
		attackAnimation.play();
		super.setAttackAnimation(attackAnimation);

		// Die
		final ImageView dieView = new ImageView(LITTLEGREENMAN_DIE);
		dieView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				LGM_DIE_WIDTH, 
				LGM_DIE_HEIGHT));
		dieView.setFitWidth(IMAGE_WIDTH);
		dieView.setFitHeight(IMAGE_HEIGHT);
		final Animation dieAnimation = new SpriteAnimation(dieView, Duration.millis(LGM_DIE_TIME), LGM_DIE_COUNT,
				LGM_DIE_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				LGM_DIE_WIDTH, 
				LGM_DIE_HEIGHT
			);
		dieAnimation.setCycleCount(Animation.INDEFINITE);
		dieAnimation.play();
		super.setDieAnimation(dieAnimation);
	}

}
