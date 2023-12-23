package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class UCMSuperLaser extends UCMWeapon{
	
	public static final int damage = 2;
	public static final int life = 1;
	
	public UCMSuperLaser(GameWorld game, Position pos)
	{
		super(game, pos, UCMSuperLaser.life, Move.UP);
	}

	@Override
	protected String getSymbol() {
		return Messages.SUPERLASER_SYMBOL;
	}

	@Override
	protected int getDamage() {
		return UCMSuperLaser.damage;
	}

	@Override
	protected int getArmour() {
		return UCMSuperLaser.life;
	}
	
	@Override
	protected void enable()
	{
		this.game.enableLaser(false);
	}
}
