package tp1.logic.gameobjects;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Ship extends GameObject{
	
	protected Move move;
	
	public Ship() {
		super();
	}
	
	
	protected Ship(GameWorld game, Position pos, int resist)
	{
		super(game, pos, resist);
	}
	

	public boolean validPos(Move move) {
		
		return pos.validPos(move);
	}
	
	@Override
	public boolean isOnPosNextTo(Position pos) {
		return this.pos.isOnPosNextTo(pos);
	}
	
}
