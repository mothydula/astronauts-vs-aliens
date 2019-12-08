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
 * File: Tile.java
 * 
 * Description: Tile Object represents the contents of a specific
 * location within the game grid. This Object stores information 
 * about the state of the given location and its helpful attributes
 * to all classes with the MVC environment.
 */

package map;


import characters.BoardCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {
	// Class fields
	private BoardCharacter characterAtTile;
	private Image image;
	private ImageView view; // Required for Node of GridPane
	private boolean restricted;
	
	// Constructor
	public Tile(BoardCharacter character) {
		characterAtTile = character;
	}
	
	/**
	 * Utility method to check if the Tile currently contains
	 * and object in it (BoardCharacter)
	 * @return boolean result from checking if Tile contains an object
	 */
	public boolean isEmpty() {
		return characterAtTile == null;
	}
	
	/**
	 * Public accessor method of the characterAtTile attribute
	 * @return BoardCharacter object
	 */
	public BoardCharacter getCharacter() {
		return characterAtTile;
	}
	
	/**
	 * Public mutator method of the characterAtTile attribute
	 * @param character BoardCharacter to place in this Tile
	 */
	public void placeCharacter(BoardCharacter character) {
		this.characterAtTile = character;
	}
	
	/**
	 * Method that removes the BoardCharacter from this Tile instantiation
	 * and sets the attribute to null.
	 */
	public void deleteCharacter() {
		this.characterAtTile = null;
	}
	
	/**
	 * Public accessor method of the image attribute
	 * @return Image object
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Public mutator method of the image attribute
	 * @param image Image object to be set
	 */
	public void setImage(Image image)  {
		this.image = image;
	}

	/**
	 * Public accessor method of the view attribute
	 * @return ImageView object
	 */
	public ImageView getImageView() {
		return view;
	}
	
	/**
	 * Public mutator of the view attribute
	 * @param view ImageView Object to be set 
	 */
	public void setImageView(ImageView view) {
		this.view = view;
	}
	
	/**
	 * Public mutator of the restricted attribute
	 * @param restricted Boolean value to be set
	 */
	public void setRestriction(boolean restricted) {
		this.restricted = restricted;
	}
	
	/**
	 * Public accessor of the restricted attribute
	 * @return Boolean stored in the restricted attribute
	 */
	public boolean isRestrictred() {
		return restricted;
	}
}
