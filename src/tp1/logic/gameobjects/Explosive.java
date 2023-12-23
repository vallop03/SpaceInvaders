package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;


public class Explosive extends EnemyWeapon{
	public static final int DAMAGE = 1;
	public static final int INI_RESISTANCE = 0;
	
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
		return Explosive.DAMAGE;
	}

	@Override
	protected int getArmour() {
		return this.life;
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
