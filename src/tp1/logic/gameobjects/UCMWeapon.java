package tp1.logic.gameobjects;


import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon {
	
	
	public UCMWeapon(GameWorld game, Position pos, int life, Move move)
	{
		super(game, pos, life, move);

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
	public boolean receiveAttack(EnemyWeapon weapon) 
	{
		this.looseLife(weapon.getDamage());
		return true;

	}
	
}
