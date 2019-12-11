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
 * File: AstronautsVsAliens.java
 * 
 * Description: Main class in charge of launching the GUI.
 */

package game;

import javafx.application.Application;

/*
 * This class simply launches the game/application.
 */
public class AstronautsVsAliens {

	public static void main(String[] args) {
		Application.launch(View.class, args);
	}

}
