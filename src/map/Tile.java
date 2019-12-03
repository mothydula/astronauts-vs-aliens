package map;

import java.util.ArrayList;
import java.util.List;

import characters.BoardCharacter;
import characters.Aliens.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {
	// Class fields
	private BoardCharacter characterAtTile;
	private Image image;
	private ImageView view; // Required for Node of GridPane
	
	// Constructor
	public Tile(BoardCharacter character) {
		characterAtTile = character;
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
	
}
