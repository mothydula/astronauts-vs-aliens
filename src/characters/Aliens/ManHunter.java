package characters.Aliens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		super(HEALTH_MAN_HUNTER, ATTACK_SPEED_MAN_HUNTER, 
				DAMAGE_MAN_HUNTER, new Image(MANHUNTER_IMAGE, 80, 80, false, false));
		generateAnimations();
	}
	
	/**
	 * Generates the walk, attack, and die animations and sets the 
	 * initial imageview shown on the StackPane to be the walk
	 * animation.
	 */
	public void generateAnimations() {
		ImageView walkView = generateAnimation(
				MANHUNTER_WALK, 
				MH_WALK_COUNT, 
				MH_WALK_COLUMNS, 
				MH_WALK_WIDTH, 
				MH_WALK_HEIGHT, 
				MH_WALK_TIME,
				WALK_ID);
		super.setWalkView(walkView);
		
		ImageView attackView = generateAnimation(
				MANHUNTER_ATTACK, 
				MH_ATTACK_COUNT, 
				MH_ATTACK_COLUMNS, 
				MH_ATTACK_WIDTH, 
				MH_ATTACK_HEIGHT, 
				MH_ATTACK_TIME,
				ATTACK_ID);
		super.setAttackView(attackView);
		
		ImageView dieView = generateAnimation(
				MANHUNTER_DIE, 
				MH_DIE_COUNT, 
				MH_DIE_COLUMNS, 
				MH_DIE_WIDTH, 
				MH_DIE_HEIGHT, 
				MH_DIE_TIME,
				DIE_ID);
		super.setDieView(dieView);
	}

}
