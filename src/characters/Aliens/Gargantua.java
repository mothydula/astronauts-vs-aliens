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
				DAMAGE_GARGANTUA, new Image(GARGANTUA_IMAGE, 80, 80, false, false));
		
		generateAnimations();
	}
	
	/**
	 * Generates the walk, attack, and die animations and sets the 
	 * initial imageview shown on the StackPane to be the walk
	 * animation.
	 */
	public void generateAnimations() {
		ImageView walkView = generateAnimation(
				GARGANTUA_WALK, 
				GARGANTUA_WALK_COUNT, 
				GARGANTUA_WALK_COLUMNS, 
				GARGANTUA_WALK_WIDTH, 
				GARGANTUA_WALK_HEIGHT, 
				GARGANTUA_WALK_TIME);
		super.setWalkView(walkView);
		
		ImageView attackView = generateAnimation(
				GARGANTUA_ATTACK, 
				GARGANTUA_ATTACK_COUNT, 
				GARGANTUA_ATTACK_COLUMNS, 
				GARGANTUA_ATTACK_WIDTH, 
				GARGANTUA_ATTACK_HEIGHT, 
				GARGANTUA_ATTACK_TIME);
		super.setAttackView(attackView);
		
		ImageView dieView = generateAnimation(
				GARGANTUA_DIE, 
				GARGANTUA_DIE_COUNT, 
				GARGANTUA_DIE_COLUMNS, 
				GARGANTUA_DIE_WIDTH, 
				GARGANTUA_DIE_HEIGHT, 
				GARGANTUA_DIE_TIME);
		super.setDieView(dieView);
	}

}
