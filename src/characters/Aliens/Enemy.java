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

	// File paths for the images of each DefenderTower
	public static final String LITTLEGREENMEN_IMAGE = "file:assets/aliens/gargantua-walk/gargantua-0.png";
	public static final String GRUNT_IMAGE 			= "file:assets/grunt.png";
	public static final String SPRINTER_IMAGE 		= "file:assets/sprinter.png";
	public static final String TANK_IMAGE 			= "file:assets/tank.png";
	public static final String MANHUNTER_IMAGE 		= "file:assets/manhunter.png";
	public static final String GARGANTUA_IMAGE 		= "file:assets/gargantua.png";

	protected static final int IMAGE_WIDTH = 80;
	protected static final int IMAGE_HEIGHT = 80;
	protected static final int OFFSET_X = 0;
    protected static final int OFFSET_Y = 0;
	
	// LittleGreenMen
	protected static final int HEALTH_LITTLE_GREEN_MEN = 100;
	protected static final int ATTACK_SPEED_LITTLE_GREEN_MEN = 100;
	protected static final int DAMAGE_LITTLE_GREEN_MEN = 100;

	// Grunt
	protected static final int HEALTH_GRUNT = 100;
	protected static final int ATTACK_SPEED_GRUNT = 100;
	protected static final int DAMAGE_GRUNT = 100;
    
	// Sprinter
	protected static final int HEALTH_SPRINTER = 100;
	protected static final int ATTACK_SPEED_SPRINTER = 100;
	protected static final int DAMAGE_SPRINTER = 100;
	
	// Tank
	protected static final int HEALTH_TANK = 100;
	protected static final int ATTACK_SPEED_TANK = 100;
	protected static final int DAMAGE_TANK = 100;
    
	// ManHunter
	protected static final int HEALTH_MAN_HUNTER = 100;
	protected static final int ATTACK_SPEED_MAN_HUNTER = 100;
	protected static final int DAMAGE_MAN_HUNTER = 100;

	// Gargantua
	protected static final int HEALTH_GARGANTUA = 100;
	protected static final int ATTACK_SPEED_GARGANTUA = 100;
	protected static final int DAMAGE_GARGANTUA = 100;
	
	
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
//		imageView = new ImageView(this.getImage());
//		stackPane.setStyle("-fx-border-color: black");
		stackPane.getChildren().add(imageView);
	}
	
	public StackPane getStackPane() {
		return stackPane;
	}
	
	public void move() {
		double xPos = stackPane.getTranslateX();
		this.setCol(calculateCol(xPos));
//		Platform.runLater(() -> stackPane.setTranslateX((xPos - 0.8))); // Negative value will be alien speed
		stackPane.setTranslateX((xPos - 0.8)); // Negative value will be alien speed
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
