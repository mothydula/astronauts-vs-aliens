package characters.Aliens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * This class contains constants and methods related to Sprinter's animations; this alien is the
 * 4th most powerful out of the 6 (on a holistic scale), but has a defining characteristic of being
 * the fastest alien.
 */
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
		ImageView walkView = generateAnimation(
				SPRINTER_WALK, 
				SPRINTER_WALK_COUNT, 
				SPRINTER_WALK_COLUMNS, 
				SPRINTER_WALK_WIDTH, 
				SPRINTER_WALK_HEIGHT, 
				SPRINTER_WALK_TIME,
				WALK_ID);
		super.setWalkView(walkView);
		
		ImageView attackView = generateAnimation(
				SPRINTER_ATTACK, 
				SPRINTER_ATTACK_COUNT, 
				SPRINTER_ATTACK_COLUMNS, 
				SPRINTER_ATTACK_WIDTH, 
				SPRINTER_ATTACK_HEIGHT, 
				SPRINTER_ATTACK_TIME,
				ATTACK_ID);
		super.setAttackView(attackView);
		
		ImageView dieView = generateAnimation(
				SPRINTER_DIE, 
				SPRINTER_DIE_COUNT, 
				SPRINTER_DIE_COLUMNS, 
				SPRINTER_DIE_WIDTH, 
				SPRINTER_DIE_HEIGHT, 
				SPRINTER_DIE_TIME,
				DIE_ID);
		super.setDieView(dieView);
	}

	public String toString() {
		return "Sprinter\n" + super.infoCard();
	}
	
}
