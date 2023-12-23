package tp1.logic.gameobjects;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;


public class ShockWave extends UCMWeapon{
	public static final int damage = 1;
	public static final int life = 1;
	
	public ShockWave(GameWorld game, int aliensToKill)
	{
		super(game, new Position(-1, 0), aliensToKill, Move.NONE);
	}
	
	@Override
	public String getSymbol()
	{
		return "";
	}
	
	@Override
	protected void enable()
	{
		game.enableShockWave(false);
	}

	@Override
	protected int getDamage() {
		return ShockWave.damage;
	}

	@Override
	protected int getArmour() {
		return ShockWave.life;
	}

	@Override
	public boolean performAttack(GameItem other)
	{
		if(this.isAlive())
		{
			if(other.receiveAttack(this))
				this.looseLife(1);
			return true;
		}
		return false;
	}
	
	@Override
	public void automaticMove() {}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) 
	{
		return false;
	}
	
	
}
