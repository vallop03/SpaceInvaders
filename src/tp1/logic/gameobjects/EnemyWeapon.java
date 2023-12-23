package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon
{
	public EnemyWeapon(GameWorld game, Position pos, int life, Move dir)
	{
		super(game, pos, life, dir);
	}
	
	@Override
	public boolean performAttack(GameItem other)
	{
		if(this.isAlive())
		{
			
			if(other.isAlive() && other.isOnPosition(this.pos))
			{
				if(other.receiveAttack(this))
				{
					this.looseLife(1);
					return true;
				}	
			}
			
		}
		return false;
	}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) 
	{
		if(weapon.isOnPosition(this.pos))
		{
			this.looseLife(weapon.getDamage());
			return true;
		}
		return false;
	}
	
}
