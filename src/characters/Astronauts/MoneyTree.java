package characters.Astronauts;

import characters.IncomeTowers.IncomeTower;
import javafx.scene.image.Image;

public class MoneyTree extends IncomeTower {
	
	public MoneyTree() {
		super (HEALTH_MONEY_TREE, ATTACK_SPEED_MONEY_TREE, 
				DAMAGE_MONEY_TREE, new Image(MONEY_TREE_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_MONEY_TREE;
	} 
	
	public int getTimeline() {
		return MONEY_TREE_GEN_TIMELINE;
	}
	
	public int getDepositAmount() {
		return MONEY_TREE_GEN_AMOUNT;
	}
	
	public String toString() {
		return "SpaceBucksFactory";
	}
}
