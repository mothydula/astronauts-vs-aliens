package characters.Aliens;

import game.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ManHunter extends Enemy {

	// Manhunter - Walk
	protected static final Image MANHUNTER_WALK = new Image("file:assets/aliens/manhunter-walk-spritesheet.png");
	protected static final int MH_WALK_COLUMNS = 33;
	protected static final int MH_WALK_COUNT = 33;
	protected static final int MH_WALK_WIDTH = 687; // pixel height of image
	protected static final int MH_WALK_HEIGHT = 632; // pixel width of image
	protected static final int MH_WALK_TIME = 1300;

	// Manhunter - Attack
	protected static final Image MANHUNTER_ATTACK = new Image("file:assets/aliens/manhunter-attack-spritesheet.png");
	protected static final int MH_ATTACK_COLUMNS = 33;
	protected static final int MH_ATTACK_COUNT = 33;
	protected static final int MH_ATTACK_WIDTH = 1117; // pixel height of image
	protected static final int MH_ATTACK_HEIGHT = 1040; // pixel width of image
	protected static final int MH_ATTACK_TIME = 1500;

	// Manhunter - Die
	protected static final Image MANHUNTER_DIE = new Image("file:assets/aliens/manhunter-die-spritesheet.png");
	protected static final int MH_DIE_COLUMNS = 21;
	protected static final int MH_DIE_COUNT = 21;
	protected static final int MH_DIE_WIDTH = 1141; // pixel height of image
	protected static final int MH_DIE_HEIGHT = 729; // pixel width of image
	protected static final int MH_DIE_TIME = 1500;

	public ManHunter() {
		super(HEALTH_MAN_HUNTER, ATTACK_SPEED_MAN_HUNTER, DAMAGE_MAN_HUNTER,
				new Image(MANHUNTER_IMAGE, 50, 50, false, false));
		
		generateAnimations();
	}
	
	/**
	 * Generates the walk, attack, and die animations and sets the 
	 * initial imageview shown on the StackPane to be the walk
	 * animation.
	 */
	public void generateAnimations() {
		// Walk
		ImageView walkView = new ImageView(MANHUNTER_WALK);
		walkView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				MH_WALK_WIDTH, 
				MH_WALK_HEIGHT
			));
		walkView.setFitWidth(IMAGE_WIDTH);
		walkView.setFitHeight(IMAGE_HEIGHT);
		Animation walkAnimation = new SpriteAnimation(walkView, Duration.millis(MH_WALK_TIME), 
				MH_WALK_COUNT,
				MH_WALK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				MH_WALK_WIDTH, 
				MH_WALK_HEIGHT
			);
		walkAnimation.setCycleCount(Animation.INDEFINITE);
		walkAnimation.play();
		super.setImageView(walkView); // This will be the initial image view that is shown
		super.setWalkAnimation(walkAnimation);

		// Attack
		final ImageView attackView = new ImageView(MANHUNTER_ATTACK);
		attackView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				MH_ATTACK_WIDTH, 
				MH_ATTACK_HEIGHT
			));
		attackView.setFitWidth(IMAGE_WIDTH);
		attackView.setFitHeight(IMAGE_HEIGHT);
		final Animation attackAnimation = new SpriteAnimation(attackView, Duration.millis(MH_ATTACK_TIME),
				MH_ATTACK_COUNT, 
				MH_ATTACK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				MH_ATTACK_WIDTH, 
				MH_ATTACK_HEIGHT
			);
		attackAnimation.setCycleCount(Animation.INDEFINITE);
		attackAnimation.play();
		super.setAttackAnimation(attackAnimation);

		// Die
		final ImageView dieView = new ImageView(MANHUNTER_DIE);
		dieView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				MH_DIE_WIDTH, 
				MH_DIE_HEIGHT));
		dieView.setFitWidth(IMAGE_WIDTH);
		dieView.setFitHeight(IMAGE_HEIGHT);
		final Animation dieAnimation = new SpriteAnimation(dieView, Duration.millis(MH_DIE_TIME), 
				MH_DIE_COUNT,
				MH_DIE_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				MH_DIE_WIDTH, 
				MH_DIE_HEIGHT
			);
		dieAnimation.setCycleCount(Animation.INDEFINITE);
		dieAnimation.play();
		super.setDieAnimation(dieAnimation);
	}

}
