package ammo;

import java.io.File;

import characters.Astronauts.DefenderTower;
import game.View;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
/**
 * Super class for all ammo
 * */
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/*
 * This class contains the necessary sound and image files to display the ammo of the defenders.
 * It also contains the methods that allow the ammo sprite/image to be moved across the board after
 * it is 'fired'. All of the specific/individual ammo classes inherit from this one.
 */
public class Ammo {
	
	//The basic stats of the ammo
	private int damage;
	private int speed;
	private Image sprite;
	private StackPane stackPane;
	private DefenderTower defender;
	private int col;
	private int row;
	
	//Image sizing
	protected static final int SPRITE_WIDTH = 35;
	protected static final int SPRITE_HEIGHT = 35;
	
	//AstroJoe Ammo Fields
	public static final String ASTROJOE_AMMO_SPRITE = "file:assets/ammo/astro-joe-ammo.png";
	
	
	//Explosive AstroJoe Ammo Fields
	public static final String EXPLOSIVE_ASTROJOE_AMMO_SPRITE = "file:assets/ammo/explosive-astro-joe-ammo.png";
	
	
	//MoonZeus Ammo Fields
	public static final String MOON_ZEUS_AMMO_SPRITE = "file:assets/ammo/moon-zeus-ammo.png";
	
	
	//Startrell Cluggins Ammo Fields
	public static final String STARTRELL_CLUGGINS_AMMO_SPRITE = "file:assets/ammo/startrell-cluggins-ammo.png";
	
	
	//TARS Ammo Fields
	public static final String TARS_AMMO_SPRITE = "file:assets/ammo/tars-ammo.png";
	
	// RailGun Ammo Image (same as MoonZeus)
	public static final String RAIL_GUN_AMMO_SPRITE = MOON_ZEUS_AMMO_SPRITE;
	

	
	// Constructor
	public Ammo (DefenderTower dt, Image sprite) {
		
		//Grabs the stats that are held in each unique DefenderTower object
		this.speed = dt.getAttackSpeed() * 10;
		this.damage = dt.getDamage();
		this.sprite = sprite;
		this.col = dt.getCol();
		this.row = dt.getRow();
		this.defender = dt;
		setStackPane();
	}
	public void setStackPane() {
		stackPane = new StackPane();
		ImageView imageView = new ImageView(this.getImage());
		stackPane.getChildren().add(imageView);
	}
	
	public int getDamage() {
		return damage;
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
		stackPane.setTranslateX(movement);
	}
	
	private int calculateCol(double xPos) {
		double x = xPos - View.COLUMN_OFFSET;
		return (int) x / View.GP_CELL_SIZE;
	}
	
	
	
}
