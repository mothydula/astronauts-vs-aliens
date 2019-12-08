package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import characters.Astronauts.*;
import game.*;
import javafx.application.Application;

class Testing {
	
	@BeforeClass
	public static void setUpClass() throws InterruptedException {
	    // Initialise Java FX

	    System.out.printf("About to launch FX App\n");
	    Thread t = new Thread("JavaFX Init Thread") {
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
		//Initialize the MVC
		setUpClass();
		Model testModel = new Model();
		Controller testController = new Controller(testModel);
		
		//Give the test bot enough money
		testModel.depositSpacebucks(10000);
		
		//Placing the characters on the board
		testController.placeCharacter(new StartrellCluggins(), 0, 0);
		testController.placeCharacter(new AstroJoe(), 0, 1);
		testController.placeCharacter(new MoonZeus(), 0, 2);
		testController.placeCharacter(new ExplosiveAstroJoe(), 0, 3);
		testController.placeCharacter(new Asteroid(), 0, 4);
		testController.placeCharacter(new MoneyBush(), 0, 5);
		testController.placeCharacter(new MillenniumFalcon(), 1, 0);
		testController.placeCharacter(new Tars(), 1, 1);
		testController.placeCharacter(new MoneyTree(), 1, 2);
		
		
		
				
		//Testing Defender detection
		assertTrue(testModel.getDefenderAt(0, 0) instanceof StartrellCluggins);
				
		//StartrellCluggins coverage
		StartrellCluggins startrell = (StartrellCluggins)testModel.getDefenderAt(0, 0);
		startrell.shoot();
		assertTrue(startrell.canShoot());
		assertTrue(startrell.toString().startsWith("StartrellCluggins"));
		
		//AstroJoe coverage
		AstroJoe astroJoe = (AstroJoe)testModel.getDefenderAt(0, 1);
		astroJoe.shoot();
		assertTrue(astroJoe.canShoot());
		assertTrue(astroJoe.toString().startsWith("AstroJoe"));
		
		//MoonZeus coverage
		MoonZeus zeus = (MoonZeus)testModel.getDefenderAt(0, 2);
		zeus.shoot();
		assertTrue(zeus.canShoot());
		assertTrue(zeus.toString().startsWith("MoonZeus"));
		
		//ExplosiveAstroJoe coverage
		ExplosiveAstroJoe eAstroJoe = (ExplosiveAstroJoe)testModel.getDefenderAt(0, 3);
		eAstroJoe.shoot();	
		assertTrue(eAstroJoe.canShoot());
		assertTrue(eAstroJoe.toString().startsWith("ExplosiveAstroJoe"));
		
		//Asteroid coverage
		Asteroid asteroid = (Asteroid)testModel.getDefenderAt(0, 4);
		assertFalse(asteroid.canShoot());
		assertTrue(asteroid.toString().startsWith("Asteroid"));
		
		//MoneyBush coverage
		MoneyBush moneyBush = (MoneyBush)testModel.getDefenderAt(0, 5);
		assertFalse(moneyBush.canShoot());
		assertTrue(moneyBush.toString().startsWith("MoneyBush"));
		
		//Millennium Falcon coverage
		MillenniumFalcon mF = (MillenniumFalcon)testModel.getDefenderAt(1, 0);
		mF.shoot();
		assertTrue(mF.canShoot());
		assertTrue(mF.toString().startsWith("MillenniumFalcon"));
		
		
		//Tars coverage
		Tars tars = (Tars)testModel.getDefenderAt(1, 1);
		tars.shoot();
		assertTrue(tars.canShoot());
		
		//MoneyTree coverage
		MoneyTree moneyTree = (MoneyTree)testModel.getDefenderAt(1, 2);
		assertFalse(moneyTree.canShoot());
		
	}

}
