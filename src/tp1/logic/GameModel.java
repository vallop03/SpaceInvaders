package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.LaserIntFlightException;
import tp1.exceptions.NoShockWaveException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.NotEnoughPointsException;
import tp1.exceptions.OffWorldException;


public interface GameModel {
	
	public boolean isFinished();
	public String infoToString();
	public void update();
	
	// PLAYER ACTIONS

	public void move(Move move) throws NotAllowedMoveException, OffWorldException;
	public void shootLaser() throws LaserIntFlightException;
	public void reset(InitialConfiguration conf);
	public void exit();
	public void shootPower() throws NoShockWaveException;
	public void shootSuperLaser() throws LaserIntFlightException, NotEnoughPointsException;
	
}	


