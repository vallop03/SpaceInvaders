package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
//import tp1.logic.Level;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip{
	
	protected AlienManager alienMan;
	protected abstract String getLetter();
	
	public AlienShip()
	{
		super();
	}
	
	protected AlienShip(Position posAlien, AlienManager alienManager, GameWorld game, int iniResistance, int points) //CAMBIAR GAME A GAMEWORLD
	{
		super(game, posAlien, Move.LEFT, iniResistance, game.getNumCyclesToMoveOneCell(), points);
		this.alienMan = alienManager;
	}
	
	protected abstract AlienShip copy(GameWorld game, Position pos, AlienManager am);
	
	@Override
	public void automaticMove()
	{
		if(this.cycleIsZero())
		{
			performMovement(dir);
			cyclesToMove = speed; 
			if(this.isInBorder())
			{
				alienMan.shipOnBorder(); //onBorder es true y igualar a remaining aliens
			}
		}
		else if(alienMan.onBorder()) //booleano que devuelve onBorder
		{
			descent();
			game.receiveAttack(this);
		}
		else
			cyclesToMove--;
	}
	
	
	
	private void descent() {
		this.performMovement(Move.DOWN);
		dir = dir.flip();
		alienMan.decreaseOnBorder(); //baja num las naves que tienen que bajar y si ya llega a 0 on border es false
		if(pos.down())
			alienMan.aliensOnFinal();
	}
	
	private boolean isInBorder() {
		
		return pos.border();
	}
	
	public boolean down() { //comprueba si su pos está abajo de todo
		return this.pos.down();
	}
	
	public boolean cycleIsZero() {
		return this.cyclesToMove == 0;
	}
	
	@Override
	public void onDelete()
	{
		super.onDelete();
		game.decreaseAliensManager();
		game.numShipsOnBorder();
	}
	
	
}
