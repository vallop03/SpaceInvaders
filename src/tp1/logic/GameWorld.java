package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.GameObject;


public interface GameWorld {

	public Random getRandom();
	public void addObject(GameObject object);
	public void increasePoints(int i);
	public Level getLevel();
	public void numShipsOnBorder();
	public void decreaseAliensManager();
	public void enableLaser(boolean activate);
	public void enableShockWave(boolean activate);
	public int getRemainingAliens();
	public boolean enoughPoints(); //la nave es el gestor de puntos
	public void receiveAttack(GameObject object);
	public void performAttack(GameObject weapon);
	public int getNumCyclesToMoveOneCell();
	
	

}
