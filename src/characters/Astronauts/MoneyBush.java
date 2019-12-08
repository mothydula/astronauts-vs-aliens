package characters.Astronauts;

import ammo.Ammo;
import characters.IncomeTowers.IncomeTower;
import game.Controller;
import javafx.scene.image.Image;

/*
 * This class contains methods related to the MoneyBush's properties (a.k.a. SpaceBucks Printer);
 * this 'defender' is one of three non-shooting board characters. The purpose of this character is 
 * to increase the amount of money in the bank by 50 SpaceBucks every 10 seconds.
 */
public class MoneyBush extends IncomeTower {
	
	public MoneyBush() {
		super(HEALTH_MONEY_BUSH, ATTACK_SPEED_MONEY_BUSH, 
				DAMAGE_MONEY_BUSH, new Image(MONEY_BUSH_GIF, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
	
	public int getCost() {
		return COST_MONEY_BUSH * Controller.costMultiplier;
	}
	
	public int getTimeline() {
		return MONEY_BUSH_GEN_TIMELINE;
	}
	
	public int getDepositAmount() {
		return MONEY_BUSH_GEN_AMOUNT;
	}
	
	public String toString() {
		return "Money Bush\n" + super.infoCard();
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
