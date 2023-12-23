package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Bomb extends EnemyWeapon{
	//private Game game;
	//private Position posBomb;
	private DestroyerAlien destroyer;
	//private Move dir;
	
	
	
	public Bomb(GameWorld game, Position pos, Move dir, DestroyerAlien destroyer)
	{
		super(game, pos, 1, Move.DOWN);
		this.destroyer = destroyer;
	}
	
	@Override 
	public String getSymbol()
	{
		return Messages.BOMB_SYMBOL;
	}
	
	
	public boolean isOut() {
		
		return this.pos.out();
	}
	
	public boolean canGenerateRandomBomb(){
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
	}

	@Override
	protected int getDamage() {
		return 1;
	}

	@Override
	protected int getArmour() {
		return 1;
	}
	
	@Override
	protected void enable()
	{
		destroyer.enableBomb();
	}

}
