package tp1.logic.gameobjects;
import tp1.view.Messages;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;


/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */

public class UCMLaser extends UCMWeapon{
	public static final int damage = 1;
	public static final int life = 1;
	
	
	public UCMLaser(GameWorld game, Position pos)
	{
		super(game, pos, UCMLaser.life, Move.UP);
		
	}
	
	@Override
	public String getSymbol()
	{
		return Messages.LASER_SYMBOL;  //////imprime laser
	}
	
	@Override
	protected int getDamage() {
		return damage;
	}

	@Override
	protected int getArmour() {
		return 1;
	}
	
	@Override
	protected void enable()
	{
		game.enableLaser(false);
	}

}