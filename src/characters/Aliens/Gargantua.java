package characters.Aliens;

import game.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Gargantua extends Enemy{
	
	// Gargantua - Walk
	protected static final Image GARGANTUA_WALK = new Image("file:assets/aliens/gargantua-walk-spritesheet.png");
	protected static final int GARGANTUA_WALK_COLUMNS = 17;
	protected static final int GARGANTUA_WALK_COUNT = 17;
	protected static final int GARGANTUA_WALK_WIDTH = 939; // pixel height of image
	protected static final int GARGANTUA_WALK_HEIGHT = 712; // pixel width of image
	protected static final int GARGANTUA_WALK_TIME = 1500;

	// Gargantua - Attack
	protected static final Image GARGANTUA_ATTACK = new Image("file:assets/aliens/gargantua-attack-spritesheet.png");
	protected static final int GARGANTUA_ATTACK_COLUMNS = 13;
	protected static final int GARGANTUA_ATTACK_COUNT = 13;
	protected static final int GARGANTUA_ATTACK_WIDTH = 1249; // pixel height of image
	protected static final int GARGANTUA_ATTACK_HEIGHT = 767; // pixel width of image
	protected static final int GARGANTUA_ATTACK_TIME = 1500;

	// Gargantua - Die
	protected static final Image GARGANTUA_DIE = new Image("file:assets/aliens/gargantua-die-spritesheet.png");
	protected static final int GARGANTUA_DIE_COLUMNS = 13;
	protected static final int GARGANTUA_DIE_COUNT = 13;
	protected static final int GARGANTUA_DIE_WIDTH = 1181; // pixel height of image
	protected static final int GARGANTUA_DIE_HEIGHT = 703; // pixel width of image
	protected static final int GARGANTUA_DIE_TIME = 1500;

	public Gargantua() {
		super(HEALTH_GARGANTUA, ATTACK_SPEED_GARGANTUA, 
				DAMAGE_GARGANTUA, new Image(GARGANTUA_IMAGE, 50, 50, false, false));
		
		generateAnimations();
	}
	
	/**
	 * Generates the walk, attack, and die animations and sets the 
	 * initial imageview shown on the StackPane to be the walk
	 * animation.
	 */
	public void generateAnimations() {
		// Walk
		ImageView walkView = new ImageView(GARGANTUA_WALK);
		walkView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				GARGANTUA_WALK_WIDTH, 
				GARGANTUA_WALK_HEIGHT
			));
		walkView.setFitWidth(IMAGE_WIDTH);
		walkView.setFitHeight(IMAGE_HEIGHT);
		Animation walkAnimation = new SpriteAnimation(walkView, Duration.millis(GARGANTUA_WALK_TIME), 
				GARGANTUA_WALK_COUNT,
				GARGANTUA_WALK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				GARGANTUA_WALK_WIDTH, 
				GARGANTUA_WALK_HEIGHT
			);
		walkAnimation.setCycleCount(Animation.INDEFINITE);
		walkAnimation.play();
		super.setImageView(walkView); // This will be the initial image view that is shown
		super.setWalkAnimation(walkAnimation);

		// Attack
		final ImageView attackView = new ImageView(GARGANTUA_ATTACK);
		attackView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				GARGANTUA_ATTACK_WIDTH, 
				GARGANTUA_ATTACK_HEIGHT
			));
		attackView.setFitWidth(IMAGE_WIDTH);
		attackView.setFitHeight(IMAGE_HEIGHT);
		final Animation attackAnimation = new SpriteAnimation(attackView, Duration.millis(GARGANTUA_ATTACK_TIME),
				GARGANTUA_ATTACK_COUNT, 
				GARGANTUA_ATTACK_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				GARGANTUA_ATTACK_WIDTH, 
				GARGANTUA_ATTACK_HEIGHT
			);
		attackAnimation.setCycleCount(Animation.INDEFINITE);
		attackAnimation.play();
		super.setAttackAnimation(attackAnimation);

		// Die
		final ImageView dieView = new ImageView(GARGANTUA_DIE);
		dieView.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				GARGANTUA_DIE_WIDTH, 
				GARGANTUA_DIE_HEIGHT));
		dieView.setFitWidth(IMAGE_WIDTH);
		dieView.setFitHeight(IMAGE_HEIGHT);
		final Animation dieAnimation = new SpriteAnimation(dieView, Duration.millis(GARGANTUA_DIE_TIME), 
				GARGANTUA_DIE_COUNT,
				GARGANTUA_DIE_COLUMNS, 
				OFFSET_X, 
				OFFSET_Y, 
				GARGANTUA_DIE_WIDTH, 
				GARGANTUA_DIE_HEIGHT
			);
		dieAnimation.setCycleCount(Animation.INDEFINITE);
		dieAnimation.play();
		super.setDieAnimation(dieAnimation);
	}

}
