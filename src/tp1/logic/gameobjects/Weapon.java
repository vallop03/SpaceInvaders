package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
	protected Move dir;
	
	public Weapon(GameWorld game, Position pos, int life, Move dir)
	{
		super(game, pos, life);
		this.dir = dir;
	}
	
	@Override
	public void automaticMove () {
		game.receiveAttack(this);
		performMovement(dir);
		if(isOut())
			this.looseLife(this.getArmour());;
	}
	
	private boolean isOut() 
	{
		return this.pos.out();
	}
	
	protected void enable()
	{
		
	}
	
	@Override
	public void onDelete() {
		enable();
	}
	
	@Override
	public boolean isOnPosNextTo(Position pos) {
		return this.pos.isOnPosNextTo(pos);
	}

}
