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
 * File: IncomeTower.java
 * 
 * Description: This class implements an income tower which is
 * the character that the user can place on the board to generate money.
 * 
 */

package characters.IncomeTowers;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;

/* This abstract class is a specification of DefenderTower (inherits from DefenderTower).
 * However, the difference in the defenders that inherit from this class is that they generate
 * SpaceBucks during the duration of time that they remain on the board. This class contains
 * abstract methods that its children must implement (getTimeline() and getDepositAmount() are
 * specific to IncomeTower(). The two classes that extend this one are MoneyBush and MoneyTree.
 */
public abstract class IncomeTower extends DefenderTower {
	
	// MoneyBush
	protected static final int HEALTH_MONEY_BUSH = 100;
	protected static final int ATTACK_SPEED_MONEY_BUSH = 0;
	protected static final int DAMAGE_MONEY_BUSH = 0;
	protected static final int COST_MONEY_BUSH = 50;
	public static int MONEY_BUSH_GEN_AMOUNT = 50; // Gen 50 every 15 seconds
	public static int MONEY_BUSH_GEN_TIMELINE = 15;
	public static final String MONEY_BUSH_IMAGE = "file:assets/defenders/money-bush.png";
	public static final String MONEY_BUSH_GIF = "file:assets/defenders/money-bush.gif";

	// MoneyTree
	protected static final int HEALTH_MONEY_TREE = 100;
	protected static final int ATTACK_SPEED_MONEY_TREE = 0;
	protected static final int DAMAGE_MONEY_TREE = 0;
	protected static final int COST_MONEY_TREE = 150;
	public static int MONEY_TREE_GEN_AMOUNT = 100; // Gen 100 every 10 seconds
	public static int MONEY_TREE_GEN_TIMELINE = 10;
	public static final String MONEY_TREE_IMAGE = "file:assets/defenders/money-tree.png";
	public static final String MONEY_TREE_GIF = "file:assets/defenders/money-tree.gif";

	// Constructor
	protected IncomeTower(int health, int attackSpeed, int damage, Image sprite) {
		super(health, attackSpeed, damage, sprite);
	}
	
	// Methods
	
	/**
	 * Getter for cost attribute
	 */
	public abstract int getCost();
	
	/**
	 * Getter for the amount of seconds it takes to generate money
	 */
	public abstract int getTimeline();
	
	/**
	 * Getter for the amount to be generated every certain amount of time.
	 */
	public abstract int getDepositAmount();
	
}