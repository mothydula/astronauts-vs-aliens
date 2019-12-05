package ammo;

import characters.Astronauts.DefenderTower;
import game.View;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
/**
 * Super class for all ammo
 * */
public class Ammo {
	
	//The basic stats of the ammo
	private int damage;
	private int speed;
	private Image sprite;
	private StackPane stackPane;
	private int col;
	private int row;
	
	//Image sizing
	protected static final int SPRITE_WIDTH = 35;
	protected static final int SPRITE_HEIGHT = 35;
	
	//AstroJoe Ammo Image
	public static final String ASTROJOE_AMMO_SPRITE = "file:assets/ammo/astro-joe-ammo.png";
	
	//Explosive AstroJoe Ammo Image
	public static final String EXPLOSIVE_ASTROJOE_AMMO_SPRITE = "file:assets/ammo/explosive-astro-joe-ammo.png";
	
	//MoonZeus Ammo Image
	public static final String MOON_ZEUS_AMMO_SPRITE = "file:assets/ammo/moon-zeus-ammo.png";
	
	//Startrell Cluggins Ammo Image
	public static final String STARTRELL_CLUGGINS_AMMO_SPRITE = "file:assets/ammo/startrell-cluggins-ammo.png";
	
	//TARS Ammo Image
	public static final String TARS_AMMO_SPRITE = "file:assets/ammo/tars-ammo.png";
	
	/**Constructor
	 * */
	public Ammo (DefenderTower dt, Image sprite) {
		
		//Grabs the stats that are held in each unique DefenderTower object
		this.speed = dt.getAttackSpeed() * 10;
		this.damage = dt.getDamage();
		this.sprite = sprite;
		this.col = dt.getCol();
		this.row = dt.getRow();
		setStackPane();
	}
	public void setStackPane() {
		stackPane = new StackPane();
		ImageView imageView = new ImageView(this.getImage());
		stackPane.getChildren().add(imageView);
	}
	
	public StackPane getStackPane() {
		return stackPane;
	}
	
	public Image getImage() {
		return this.sprite;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void move() {
		double xPos = stackPane.getTranslateX();
		
		double movement = xPos + ((double)this.speed / 100.0);
		this.setCol(calculateCol(xPos));
		Platform.runLater(() -> stackPane.setTranslateX(movement)); 
	}
	
	private int calculateCol(double xPos) {
		double x = xPos - View.COLUMN_OFFSET;
		return (int) x / View.GP_CELL_SIZE;
//		characterPane.setTranslateX((GP_CELL_SIZE * message.getCol()) + COLUMN_OFFSET);
	}
	
	
	
}
