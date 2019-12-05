package characters.Aliens;

import characters.BoardCharacter;
import game.View;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

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
	protected static final int HEALTH_LITTLE_GREEN_MEN = 100;
	protected static final int ATTACK_SPEED_LITTLE_GREEN_MEN = 300;
	protected static final int DAMAGE_LITTLE_GREEN_MEN = 1;

	// Grunt
	protected static final int HEALTH_GRUNT = 100;
	protected static final int ATTACK_SPEED_GRUNT = 400;
	protected static final int DAMAGE_GRUNT = 2;
    
	// Sprinter
	protected static final int HEALTH_SPRINTER = 100;
	protected static final int ATTACK_SPEED_SPRINTER = 600;
	protected static final int DAMAGE_SPRINTER = 4;
	
	// Tank
	protected static final int HEALTH_TANK = 100;
	protected static final int ATTACK_SPEED_TANK = 200;
	protected static final int DAMAGE_TANK = 6;
    
	// ManHunter
	protected static final int HEALTH_MAN_HUNTER = 100;
	protected static final int ATTACK_SPEED_MAN_HUNTER = 450;
	protected static final int DAMAGE_MAN_HUNTER = 7;

	// Gargantua
	protected static final int HEALTH_GARGANTUA = 100;
	protected static final int ATTACK_SPEED_GARGANTUA = 500;
	protected static final int DAMAGE_GARGANTUA = 10;
	
	
	// Class variables
	private StackPane stackPane;
	
	// Animations
	private ImageView imageView;
	private Animation walkAnimation;
	private Animation attackAnimation;
	private Animation dieAnimation;

	// Constructor
	protected Enemy(int health, int attackSpeed, int damage, Image sprite) {
		super(health, attackSpeed, damage, sprite);		
	}
	
	public void setStackPane() {
		stackPane = new StackPane();
//		stackPane.setStyle("-fx-border-color: black");
		stackPane.getChildren().add(imageView);
	}
	
	public StackPane getStackPane() {
		return stackPane;
	}
	
	public void move() {
		double xPos = stackPane.getTranslateX();
		
		double movement = xPos - ((double)this.getAttackSpeed() / 100.0);
		this.setCol(calculateCol(xPos));
//		Platform.runLater(() -> stackPane.setTranslateX((movement))); // Negative value will be alien speed
		stackPane.setTranslateX(movement); // Negative value will be alien speed
	}
	
	private int calculateCol(double xPos) {
		double x = xPos - View.COLUMN_OFFSET;
		return (int) x / View.GP_CELL_SIZE;
//		characterPane.setTranslateX((GP_CELL_SIZE * message.getCol()) + COLUMN_OFFSET);
	}
	
	public void setWalkAnimation(Animation animation) {
		walkAnimation = animation;
	}
	
	public Animation getWalkAnimation() {
		return walkAnimation;
	}
	
	public void setAttackAnimation(Animation animation) {
		attackAnimation = animation;
	}
	
	public Animation getAttackAnimation(Animation animation) {
		return attackAnimation;
	}
	
	public void setDieAnimation(Animation animation) {
		dieAnimation = animation;
	}
	
	public Animation getDieAnimation() {
		return dieAnimation;
	}
	
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
}
