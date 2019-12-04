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
	
	// LittleGreenMen - Walk
	protected static final Image LITTLEGREENMAN_WALK = new Image("file:littlegreenman-walk-spritesheet.png");
	protected static final int LGM_WALK_COLUMNS  =   17;
    protected static final int LGM_WALK_COUNT    =  17;
    protected static final int LGM_WALK_WIDTH    = 428; // pixel height of image
    protected static final int LGM_WALK_HEIGHT   = 298; // pixel width of image
    protected static final int LGM_WALK_TIME = 1500;
	
    // LittleGreenMen - Attack
    protected static final Image LITTLEGREENMAN_ATTACK = new Image("file:littlegreenman-attack-spritesheet.png");
	protected static final int LGM_ATTACK_COLUMNS  =   13;
    protected static final int LGM_ATTACK_COUNT    =  13;
    protected static final int LGM_ATTACK_WIDTH    = 637; // pixel height of image
    protected static final int LGM_ATTACK_HEIGHT   = 336; // pixel width of image
    protected static final int LGM_ATTACK_TIME = 1000;
    
    // LittleGreenMen - Die
    protected static final Image LITTLEGREENMAN_DIE = new Image("file:littlegreenman-die-spritesheet.png");
    protected static final int LGM_DIE_COLUMNS  =   15;
    protected static final int LGM_DIE_COUNT    =  15;
    protected static final int LGM_DIE_WIDTH    = 727; // pixel height of image
    protected static final int LGM_DIE_HEIGHT   = 387; // pixel width of image
    protected static final int LGM_DIE_TIME   = 1700;

    
	// Grunt
	protected static final int HEALTH_GRUNT = 100;
	protected static final int ATTACK_SPEED_GRUNT = 100;
	protected static final int DAMAGE_GRUNT = 100;
	
	// Grunt - Walk
	protected static final Image GRUNT_WALK = new Image("file:grunt-walk-spritesheet.png");
	protected static final int GRUNT_WALK_COLUMNS  =   13;
    protected static final int GRUNT_WALK_COUNT    =  13;
    protected static final int GRUNT_WALK_WIDTH    = 734; // pixel height of image
    protected static final int GRUNT_WALK_HEIGHT   = 386; // pixel width of image
    protected static final int GRUNT_WALK_TIME = 1000;
	
	// Grunt - Attack
    protected static final Image GRUNT_ATTACK = new Image("file:grunt-attack-spritesheet.png");
    protected static final int GRUNT_ATTACK_COLUMNS  =   9;
    protected static final int GRUNT_ATTACK_COUNT    =  9;
    protected static final int GRUNT_ATTACK_WIDTH    = 756; // pixel height of image
    protected static final int GRUNT_ATTACK_HEIGHT   = 523; // pixel width of image
    protected static final int GRUNT_ATTACK_TIME = 1000;
    
	// Grunt - Die
    protected static final Image GRUNT_DIE = new Image("file:grunt-die-spritesheet.png");
    protected static final int GRUNT_DIE_COLUMNS  =   13;
    protected static final int GRUNT_DIE_COUNT    =  13;
    protected static final int GRUNT_DIE_WIDTH    = 771; // pixel height of image
    protected static final int GRUNT_DIE_HEIGHT   = 390; // pixel width of image
    protected static final int GRUNT_DIE_TIME = 1000;
    
	// Sprinter
	protected static final int HEALTH_SPRINTER = 100;
	protected static final int ATTACK_SPEED_SPRINTER = 100;
	protected static final int DAMAGE_SPRINTER = 100;
	
	// Sprinter - Walk
	protected static final Image SPRINTER_WALK = new Image("file:sprinter-walk-spritesheet.png");
	protected static final int SPRINTER_WALK_COLUMNS  =   17;
    protected static final int SPRINTER_WALK_COUNT    =  17;
    protected static final int SPRINTER_WALK_WIDTH    = 985; // pixel height of image
    protected static final int SPRINTER_WALK_HEIGHT   = 743; // pixel width of image
    protected static final int SPRINTER_WALK_TIME = 750;
	
	// Sprinter - Attack
    protected static final Image SPRINTER_ATTACK = new Image("file:sprinter-attack-spritesheet.png");
    protected static final int SPRINTER_ATTACK_COLUMNS  =   13;
    protected static final int SPRINTER_ATTACK_COUNT    =  13;
    protected static final int SPRINTER_ATTACK_WIDTH    = 1008; // pixel height of image
    protected static final int SPRINTER_ATTACK_HEIGHT   = 773; // pixel width of image
    protected static final int SPRINTER_ATTACK_TIME = 1000;
    
	// Sprinter - Die
    protected static final Image SPRINTER_DIE = new Image("file:sprinter-die-spritesheet.png");
    protected static final int SPRINTER_DIE_COLUMNS  =   21;
    protected static final int SPRINTER_DIE_COUNT    =  21;
    protected static final int SPRINTER_DIE_WIDTH    = 1544; // pixel height of image
    protected static final int SPRINTER_DIE_HEIGHT   = 849; // pixel width of image
    protected static final int SPRINTER_DIE_TIME = 1500;
    
    
	// Tank
	protected static final int HEALTH_TANK = 100;
	protected static final int ATTACK_SPEED_TANK = 100;
	protected static final int DAMAGE_TANK = 100;
	
	// Tank - Walk
	protected static final Image TANK_WALK = new Image("file:tank-walk-spritesheet.png");
	protected static final int TANK_WALK_COLUMNS  =   17;
    protected static final int TANK_WALK_COUNT    =  17;
    protected static final int TANK_WALK_WIDTH    = 885; // pixel height of image
    protected static final int TANK_WALK_HEIGHT   = 686; // pixel width of image
    protected static final int TANK_WALK_TIME = 1700;
    
    // Tank - Attack
    protected static final Image TANK_ATTACK = new Image("file:tank-attack-spritesheet.png");
    protected static final int TANK_ATTACK_COLUMNS  =   17;
    protected static final int TANK_ATTACK_COUNT    =  17;
    protected static final int TANK_ATTACK_WIDTH    = 788; // pixel height of image
    protected static final int TANK_ATTACK_HEIGHT   = 1135; // pixel width of image
    protected static final int TANK_ATTACK_TIME = 1800;
    
    // Tank - Die
    protected static final Image TANK_DIE = new Image("file:tank-die-spritesheet.png");
    protected static final int TANK_DIE_COLUMNS  =   23;
    protected static final int TANK_DIE_COUNT    =  23;
    protected static final int TANK_DIE_WIDTH    = 1332; // pixel height of image
    protected static final int TANK_DIE_HEIGHT   = 1173; // pixel width of image
    protected static final int TANK_DIE_TIME = 2000;

    
	// ManHunter
	protected static final int HEALTH_MAN_HUNTER = 100;
	protected static final int ATTACK_SPEED_MAN_HUNTER = 100;
	protected static final int DAMAGE_MAN_HUNTER = 100;
	
	// Manhunter - Walk
	protected static final Image MANHUNTER_WALK = new Image("file:manhunter-walk-spritesheet.png");
	protected static final int MH_WALK_COLUMNS  =   33;
    protected static final int MH_WALK_COUNT    =  33;
    protected static final int MH_WALK_WIDTH    = 687; // pixel height of image
    protected static final int MH_WALK_HEIGHT   = 632; // pixel width of image
    protected static final int MH_WALK_TIME = 1300;
    
    // Manhunter - Attack
    protected static final Image MANHUNTER_ATTACK = new Image("file:manhunter-attack-spritesheet.png");
    protected static final int MH_ATTACK_COLUMNS  =   33;
    protected static final int MH_ATTACK_COUNT    =  33;
    protected static final int MH_ATTACK_WIDTH    = 1117; // pixel height of image
    protected static final int MH_ATTACK_HEIGHT   = 1040; // pixel width of image
    protected static final int MH_ATTACK_TIME = 1500;
    
    // Manhunter - Die
    protected static final Image MANHUNTER_DIE = new Image("file:manhunter-die-spritesheet.png");
    protected static final int MH_DIE_COLUMNS  =   21;
    protected static final int MH_DIE_COUNT    =  21;
    protected static final int MH_DIE_WIDTH    = 1141; // pixel height of image
    protected static final int MH_DIE_HEIGHT   = 729; // pixel width of image
    protected static final int MH_DIE_TIME = 1500;
   
    
	// Gargantua
	protected static final int HEALTH_GARGANTUA = 100;
	protected static final int ATTACK_SPEED_GARGANTUA = 100;
	protected static final int DAMAGE_GARGANTUA = 100;
	
	// Gargantua - Walk
	protected static final Image GARGANTUA_WALK = new Image("file:gargantua-walk-spritesheet.png");
	protected static final int GARGANTUA_WALK_COLUMNS  =   17;
    protected static final int GARGANTUA_WALK_COUNT    =  17;
    protected static final int GARGANTUA_WALK_WIDTH    = 939; // pixel height of image
    protected static final int GARGANTUA_WALK_HEIGHT   = 712; // pixel width of image
    protected static final int GARGANTUA_WALK_TIME = 1500;
	
	// Gargantua - Attack
    protected static final Image GARGANTUA_ATTACK = new Image("file:gargantua-attack-spritesheet.png");
    protected static final int GARGANTUA_ATTACK_COLUMNS  =   13;
    protected static final int GARGANTUA_ATTACK_COUNT    =  13;
    protected static final int GARGANTUA_ATTACK_WIDTH    = 1249; // pixel height of image
    protected static final int GARGANTUA_ATTACK_HEIGHT   = 767; // pixel width of image
    protected static final int GARGANTUA_ATTACK_TIME = 1500;
	
	// Gargantua - Die
    protected static final Image GARGANTUA_DIE = new Image("file:gargantua-die-spritesheet.png");
    protected static final int GARGANTUA_DIE_COLUMNS  =   13;
    protected static final int GARGANTUA_DIE_COUNT    =  13;
    protected static final int GARGANTUA_DIE_WIDTH    = 1181; // pixel height of image
    protected static final int GARGANTUA_DIE_HEIGHT   = 703; // pixel width of image
    protected static final int GARGANTUA_DIE_TIME = 1500;
	
	//Class variables
	private StackPane stackPane;
	
	// Animations
	private Animation walkAnimation;
	private Animation attackAnimation;
	private Animation dieAnimation;

	// Constructor
	protected Enemy(int health, int attackSpeed, int damage, Image sprite) {
		super(health, attackSpeed, damage, sprite);
	}
	
	public void setStackPane() {
		stackPane = new StackPane();
		ImageView imageView = new ImageView(this.getImage());
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
}
