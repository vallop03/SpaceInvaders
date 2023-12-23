package tp1.logic;

import tp1.control.InitialConfiguration;

public interface GameModel {
	
	public boolean isFinished();
	public String infoToString();
	public void update();
	public boolean enoughPoints();
	public void decreasePoints();
	
	// PLAYER ACTIONS

	public boolean move(Move move);
	public boolean shootLaser();
	public void reset(InitialConfiguration conf);
	public void exit();
	public boolean shootPower();
	public boolean shootSuperLaser();
	
}	


