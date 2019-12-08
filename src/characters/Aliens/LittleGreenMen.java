package characters.Aliens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * This class contains constants and methods related to Little Green Man's animations;
 * this alien is the least powerful out of the 6 (on a holistic scale).
 */
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
		ImageView walkView = generateAnimation(
				LITTLEGREENMAN_WALK, 
				LGM_WALK_COUNT, 
				LGM_WALK_COLUMNS, 
				LGM_WALK_WIDTH, 
				LGM_WALK_HEIGHT, 
				LGM_WALK_TIME,
				WALK_ID);
		super.setWalkView(walkView);
		
		ImageView attackView = generateAnimation(
				LITTLEGREENMAN_ATTACK, 
				LGM_ATTACK_COUNT, 
				LGM_ATTACK_COLUMNS, 
				LGM_ATTACK_WIDTH, 
				LGM_ATTACK_HEIGHT, 
				LGM_ATTACK_TIME,
				ATTACK_ID);
		super.setAttackView(attackView);
		
		ImageView dieView = generateAnimation(
				LITTLEGREENMAN_DIE, 
				LGM_DIE_COUNT, 
				LGM_DIE_COLUMNS, 
				LGM_DIE_WIDTH, 
				LGM_DIE_HEIGHT, 
				LGM_DIE_TIME,
				DIE_ID);
		super.setDieView(dieView);
	}
	
	public String toString() {
		return "LittleGreenMen\n" + super.infoCard();
	}

}
