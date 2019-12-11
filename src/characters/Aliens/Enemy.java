/**
 * @author Adrian Bao
 * @author Trey Bryant
 * @author Mauricio Herrera
 * @author Tim Lukau
 * 
 * CSC 335 - Object Oriented Programming and Design
 * 
 * Title: Astronauts vs Aliens
 * 
 * File: Enemy.java
 * 
 * Description: This class is the parent class of all aliens. It contains as constants the file paths for all the
 * individual alien sprites as well as the specs for each individual alien; health, attack speed and 
 * damage (damage that they are able to take from bullets before death). Also contained within this class
 * are methods associated with the animation of aliens. They are moved with reference to pixels but must
 * still have a semblance of what row and column they're in so it can be known if a bullet 'hits' them.
 * All individual alien classes extend this one.
 */

package characters.Aliens;

import characters.BoardCharacter;
import game.SpriteAnimation;
import game.View;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Enemy extends BoardCharacter {
	// Default values for characters

	// File paths for the images of each Enemy
	public static final String LITTLEGREENMEN_IMAGE = "file:assets/aliens/little-green-man-attack.gif";
	public static final String GRUNT_IMAGE 			= "file:assets/aliens/grunt-walk.gif";
	public static final String SPRINTER_IMAGE 		= "file:assets/aliens/sprinter-walk.gif";
	public static final String TANK_IMAGE 			= "file:assets/aliens/tank-walk.gif";
	public static final String MANHUNTER_IMAGE 		= "file:assets/aliens/manhunter-attack.gif";
	public static final String GARGANTUA_IMAGE 		= "file:assets/aliens/gargantua-walk/gargantua-10.png";

	protected static final int IMAGE_WIDTH = 80;
	protected static final int IMAGE_HEIGHT = 80;
	protected static final int OFFSET_X = 0;
    protected static final int OFFSET_Y = 0;
	
	// LittleGreenMen
	protected static final int HEALTH_LITTLE_GREEN_MEN = 300;
	protected static final int ATTACK_SPEED_LITTLE_GREEN_MEN = 300;
	protected static final int DAMAGE_LITTLE_GREEN_MEN = 25;

	// Grunt
	protected static final int HEALTH_GRUNT = 500;
	protected static final int ATTACK_SPEED_GRUNT = 400;
	protected static final int DAMAGE_GRUNT = 50;
    
	// Sprinter
	protected static final int HEALTH_SPRINTER = 600;
	protected static final int ATTACK_SPEED_SPRINTER = 600;
	protected static final int DAMAGE_SPRINTER = 60;
	
	// Tank
	protected static final int HEALTH_TANK = 3000;
	protected static final int ATTACK_SPEED_TANK = 200;
	protected static final int DAMAGE_TANK = 10;
    
	// ManHunter
	protected static final int HEALTH_MAN_HUNTER = 750;
	protected static final int ATTACK_SPEED_MAN_HUNTER = 450;
	protected static final int DAMAGE_MAN_HUNTER = 100;

	// Gargantua
	protected static final int HEALTH_GARGANTUA = 1500;
	protected static final int ATTACK_SPEED_GARGANTUA = 500;
	protected static final int DAMAGE_GARGANTUA = 250;
	
	
	// Class variables
	private StackPane stackPane;
	private boolean isAttacking;
	
	// Animations
	private ImageView walkView;
	private ImageView attackView;
	private ImageView dieView;
	private Animation walkAnimation;
	private Animation attackAnimation;
	private Animation dieAnimation;
	
	public static final String WALK_ID = "walk";
	public static final String ATTACK_ID = "attack";
	public static final String DIE_ID = "die";

	// Constructor
	protected Enemy(int health, int attackSpeed, int damage, Image sprite) {
		super(health, attackSpeed, damage, sprite);
		isAttacking = false;
	}
	
	/**
	 * Public mutator method used to setup a stack pane with the associated
	 * animations and image views necessary for the aspects of the game.
	 */
	public void setStackPane() {
		stackPane = new StackPane();
		
		// Add all views but only make walk animation visible
		walkView.setVisible(true);
		walkView.setId(WALK_ID);
		attackView.setVisible(false);
		attackView.setId(ATTACK_ID);
		dieView.setVisible(false);
		dieView.setId(DIE_ID);
		stackPane.getChildren().addAll(walkView, attackView, dieView);
	}
	
	/**
	 * Public accessor method for the stackPane attribute
	 * @return StackPane object attribute
	 */
	public StackPane getStackPane() {
		return stackPane;
	}
	
//	public void playAlienMunchNoise() {
//		// TODO: Have a bullet noise for each defender's ammo,
//		// each time a piece of ammo is fired
//		/*MediaPlayer munchNoise;
//		String resource = new File(munchNoiseFile.mp3).toURI().toString();
//		munchNoise = new MediaPlayer(new Media(resource));
//		munchNoise.play();*/
//	}
	
	/**
	 * Public accessor method for the isAttacking attribute
	 * @return boolean value of isAttacking attribute
	 */
	public boolean isAttacking() {
		return isAttacking;
	}
	
	/**
	 * Public mutator method for the isAttacking attribute
	 * @param isAttacking boolean value to be set for isAttacking
	 */
	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	
	/**
	 * Moves the stackPane for this Enemy object a specified distance in
	 * accordance to its attributes.
	 */
	public void move() {
		double xPos = stackPane.getTranslateX();
		double movement = xPos - ((double)this.getAttackSpeed() / 100.0);
		this.setCol(calculateCol(xPos));
		stackPane.setTranslateX(movement); // Negative value will be alien speed
	}
	
	/**
	 * Calculates the column that the stackPane attribute should move to
	 * @param xPos current xPos of the stackPane
	 * @return integer represent the column the stackpane should move to
	 */
	private int calculateCol(double xPos) {
		double x = xPos - View.COLUMN_OFFSET;
		return (int) x / View.GP_CELL_SIZE;
	}
	
	/**
	 * Public mutator method for the walkView attribute
	 * @param walkView ImageView object to be set
	 */
	public void setWalkView(ImageView walkView) {
		this.walkView = walkView;
	}
	
	/**
	 * Public accessor method for the walkView attribute
	 * @return ImageView object
	 */
	public ImageView getWalkView() {
		return walkView;
	}
	
	/**
	 * Public mutator method for the attackView attribute
	 * @param attackView ImageView object to be set
	 */
	public void setAttackView(ImageView attackView) {
		this.attackView = attackView;
	}
	
	/**
	 * Public accessor method for the attackView atttribute
	 * @return ImageView object
	 */
	public ImageView getAttackView() {
		return attackView;
	}
	
	/**
	 * Public mutator method for the dieView attribute
	 * @param dieView ImageView object to be set
	 */
	public void setDieView(ImageView dieView) {
		this.dieView = dieView;	
	}
	
	/**
	 * Public accessor method for the dieView attribute
	 * @return ImageView object
	 */
	public ImageView getDieView() {
		return dieView;
	} 
	
	/**
	 * Triggers an animation of a specified view given an animationId.
	 * This will decide which animation, either walk, attack, or die
	 * is played in the imageView.
	 * @param animationId String id to find the corresponding animation
	 */
	public void triggerAnimation(String animationId) {
		for (Node node : stackPane.getChildren()) {
			ImageView view = (ImageView) node;
			if (view.getId().equals(animationId)) {
				view.setVisible(true);
			} else {
				view.setVisible(false);
			}
		}
	}
	
	/**
	 * Generates an animation for the sprite stored in the stackPane.
	 * @param spriteImage Image object of the spritesheet
	 * @param count Number of images inside of the spritesheet
	 * @param columns Number of columns included in the spritesheet
	 * @param spriteWidth Width, in pixels, of an individual sprite
	 * @param spriteHeight Height, in pixels, of an individual sprite
	 * @param animationTime Total elapsed time of an animation
	 * @param animationId Animation id used to set the corresponding ImageView
	 * @return ImageView with the running animation
	 */
	public ImageView generateAnimation(Image spriteImage, int count, int columns, int spriteWidth, int spriteHeight, int animationTime, String animationId) {
		ImageView view = new ImageView(spriteImage);
		view.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				spriteWidth, 
				spriteHeight
			));
		view.setFitWidth(IMAGE_WIDTH);
		view.setFitHeight(IMAGE_HEIGHT);
		Animation animation = new SpriteAnimation(view, Duration.millis(animationTime), 
				count,
				columns, 
				OFFSET_X, 
				OFFSET_Y, 
				spriteWidth, 
				spriteHeight
			);
		
		if (animationId.equals(WALK_ID)) {
			walkAnimation = animation;
		} else if (animationId.equals(ATTACK_ID)) {
			attackAnimation = animation;
		} else if (animationId.equals(DIE_ID)) {
			dieAnimation = animation;
		}
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
		return view;
	}

	/**
	 * Utility method used to update the animation speed for each of the animations
	 * in order to support the functionality of fast forwarding
	 * @param speed int rate that will be set as the animation speed
	 */
	public void updateAnimationSpeed(int speed) {
		walkAnimation.setRate(speed);
		attackAnimation.setRate(speed);
		dieAnimation.setRate(speed);
	}
	
}
