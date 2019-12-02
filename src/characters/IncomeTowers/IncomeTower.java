package characters.IncomeTowers;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;

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
	public abstract int getCost();
	public abstract int getTimeline();
	public abstract int getDepositAmount();
	
}
