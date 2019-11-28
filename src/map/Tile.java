package map;

import characters.BoardCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {
	// Class fields
	private boolean isOccupied;
	private BoardCharacter characterAtTile;
	private Image image;
	private ImageView view; // Required for Node of GridPane
	
	// Constructor
	public Tile(BoardCharacter character) {
		characterAtTile = character;
	}
	
	// Methods
	public boolean isOccupied() {
		return isOccupied;
	}
	
	public BoardCharacter getCharacter() {
		return characterAtTile;
	}
	
	public void placeCharacter(BoardCharacter character) {
		this.characterAtTile = character;
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
	
}
