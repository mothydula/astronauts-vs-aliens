package map;


import characters.BoardCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {
	// Class fields
	private BoardCharacter characterAtTile;
	private Image image;
	private ImageView view; // Required for Node of GridPane
	private boolean containsRailGun;
	
	// Constructor
	public Tile(BoardCharacter character) {
		characterAtTile = character;
		containsRailGun = false;
	}
	
	// Methods
	
	public boolean isEmpty() {
		return characterAtTile == null;
	}
	
	public BoardCharacter getCharacter() {
		return characterAtTile;
	}
	
	public void placeCharacter(BoardCharacter character) {
		this.characterAtTile = character;
	}
	
	public void deleteCharacter() {
		this.characterAtTile = null;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image)  {
		this.image = image;
	}

	public ImageView getImageView() {
		return view;
	}
	
	public void setImageView(ImageView view) {
		this.view = view;
	}
	
	public void setRailGun(boolean containsRailGun) {
		this.containsRailGun = containsRailGun;
	}
	
	public boolean containsRailGun() {
		return containsRailGun;
	}
	
}
