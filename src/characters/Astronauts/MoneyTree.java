package characters.Astronauts;

import ammo.Ammo;
import characters.IncomeTowers.IncomeTower;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to the MoneyTree's properties (a.k.a. SpaceBucks Factory);
 * this 'defender' is one of three non-shooting board characters. The purpose of this character is 
 * to increase the amount of money in the bank by 100 SpaceBucks every 10 seconds; similar to the
 * MoneyBush, but is able to generate more SpaceBucks (but understandably also costs more to buy).
 */
public class MoneyTree extends IncomeTower {
	
	public MoneyTree() {
		super (HEALTH_MONEY_TREE, ATTACK_SPEED_MONEY_TREE, 
				DAMAGE_MONEY_TREE, new Image(MONEY_TREE_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_MONEY_TREE * Controller.costMultiplier;
	} 
	
	public int getTimeline() {
		return MONEY_TREE_GEN_TIMELINE;
	}
	
	public int getDepositAmount() {
		return MONEY_TREE_GEN_AMOUNT;
	}
	
	public String toString() {
		return "Money Tree\n" + super.infoCard();
	}

	@Override
	public Ammo shoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canShoot() {
		// TODO Auto-generated method stub
		return false;
	}
}
