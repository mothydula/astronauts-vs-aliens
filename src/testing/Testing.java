package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import characters.Astronauts.*;
import game.*;
import javafx.application.Application;

class Testing {
	private static Thread t;
	private Model testModel = new Model();
	private Controller testController = new Controller(testModel);
	@BeforeClass
	public static void setUpClass() throws InterruptedException {
		// Initialise Java FX

		System.out.printf("About to launch FX App\n");
		t = new Thread("JavaFX Init Thread") {
			public void run() {
				Application.launch(View.class, new String[0]);
			}
		};
		t.setDaemon(true);
		t.start();
		System.out.printf("FX App thread started\n");
		Thread.sleep(500);
	}

	@Test
	void characterTests() throws InterruptedException {
		// Initialize the MVC
		setUpClass();
		
		//Place a character when broke
		testController.placeCharacter(new StartrellCluggins(), 0, 0);
		
		// Give the test bot enough money
		testModel.depositSpacebucks(10000);

		// Placing the characters on the board
		testController.placeCharacter(new StartrellCluggins(), 0, 0);
		testController.placeCharacter(new AstroJoe(), 0, 1);
		testController.placeCharacter(new MoonZeus(), 0, 2);
		testController.placeCharacter(new ExplosiveAstroJoe(), 0, 3);
		testController.placeCharacter(new Asteroid(), 0, 4);
		testController.placeCharacter(new MoneyBush(), 0, 5);
		testController.placeCharacter(new MillenniumFalcon(), 1, 0);
		testController.placeCharacter(new Tars(), 1, 1);
		testController.placeCharacter(new MoneyTree(), 1, 2);

		// Testing Defender detection
		assertTrue(testModel.getDefenderAt(0, 0) instanceof StartrellCluggins);

		// StartrellCluggins coverage
		StartrellCluggins startrell = (StartrellCluggins) testModel.getDefenderAt(0, 0);
		startrell.shoot();
		assertTrue(startrell.canShoot());
		assertTrue(startrell.toString().startsWith("Startrell Cluggins"));

		// AstroJoe coverage
		AstroJoe astroJoe = (AstroJoe) testModel.getDefenderAt(0, 1);
		astroJoe.shoot();
		assertTrue(astroJoe.canShoot());
		assertTrue(astroJoe.toString().startsWith("Astro Joe"));

		// MoonZeus coverage
		MoonZeus zeus = (MoonZeus) testModel.getDefenderAt(0, 2);
		zeus.shoot();
		assertTrue(zeus.canShoot());
		assertTrue(zeus.toString().startsWith("Moon Zeus"));

		// ExplosiveAstroJoe coverage
		ExplosiveAstroJoe eAstroJoe = (ExplosiveAstroJoe) testModel.getDefenderAt(0, 3);
		eAstroJoe.shoot();
		assertTrue(eAstroJoe.canShoot());
		assertTrue(eAstroJoe.toString().startsWith("Explosive Astro Joe"));

		// Asteroid coverage
		Asteroid asteroid = (Asteroid) testModel.getDefenderAt(0, 4);
		asteroid.shoot();
		assertFalse(asteroid.canShoot());
		assertTrue(asteroid.toString().startsWith("Asteroid"));

		// MoneyBush coverage
		MoneyBush moneyBush = (MoneyBush) testModel.getDefenderAt(0, 5);
		moneyBush.shoot();
		assertFalse(moneyBush.canShoot());
		assertTrue(moneyBush.toString().startsWith("Money Bush"));

		// Millennium Falcon coverage
		MillenniumFalcon mF = (MillenniumFalcon) testModel.getDefenderAt(1, 0);
		mF.shoot();
		assertTrue(mF.canShoot());
		assertTrue(mF.toString().startsWith("Millennium Falcon"));
		mF.shotOne();
		mF.shotTwo();

		// Tars coverage
		Tars tars = (Tars) testModel.getDefenderAt(1, 1);
		tars.shoot();
		assertTrue(tars.canShoot());
		assertTrue(tars.toString().startsWith("Tars"));

		// MoneyTree coverage
		MoneyTree moneyTree = (MoneyTree) testModel.getDefenderAt(1, 2);
		moneyTree.shoot();
		assertFalse(moneyTree.canShoot());
		assertTrue(moneyTree.toString().startsWith("Money Tree"));
		
		//If there is already a Character on the board
		testController.placeCharacter(new Asteroid(), 0, 0);
		
		//Remove the tile
		testModel.removeTower(startrell, 0, 0);
		
		//Run the pause and play testing
		pausePlayTests();
		
		
	}
	
	@Test
	void pausePlayTests() {
		
	}

}
