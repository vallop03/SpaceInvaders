package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
//import tp1.view.Messages;

public class Explosive extends EnemyWeapon{
	
	public Explosive(GameWorld game, Position pos)
	{
		super(game, pos, 0, Move.UP);
	}
	
	@Override 
	public String getSymbol()
	{
		return "";
	}
	
	@Override
	protected int getDamage() {
		return 1;
	}

	@Override
	protected int getArmour() {
		return 0;
	}
	
	@Override
	public boolean performAttack(GameItem other)
	{
		if(other.isAlive() && other.isOnPosNextTo(this.pos))
		{
			other.looseLife(1);
			return true;
		}
		return false;
	}
	
	@Override
	public void automaticMove(){}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon)
	{
		return false;
	}	
	
	
	

}
