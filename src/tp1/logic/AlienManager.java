package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.InitializationException;
import tp1.logic.gameobjects.DestroyerAlien;
//import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.ShipFactory;
import tp1.logic.gameobjects.Ufo;
//import tp1.logic.lists.DestroyerAlienList;
//import tp1.logic.lists.RegularAlienList;
import tp1.view.Messages;

/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */
public class AlienManager {
	
	private Level level;
	private int remainingAliens;
	private Game game;
	private boolean squadInFinalRow;
	private int shipsOnBorder;
	private boolean onBorder;

	public AlienManager(Game game, Level level) {
		this.game = game;
		this.level = level;
		this.remainingAliens = 0;
		this.shipsOnBorder = 0;
		this.onBorder = false;
		this.squadInFinalRow = false;
	}
		
	// INITIALIZER METHODS
	
	
	public  GameObjectContainer initialize(InitialConfiguration conf) throws InitializationException{
		this.remainingAliens = 0;
		GameObjectContainer container = new GameObjectContainer();
		
		initializeOvni(container);
		
		if(conf == InitialConfiguration.NONE)
		{
			initializeRegularAliens(container);
			initializeDestroyerAliens(container);
		}
		else
			costumedInitialization(container, conf);
			
		return container;
	}
	
	
	private void costumedInitialization(GameObjectContainer container, InitialConfiguration conf) throws InitializationException{
		for (String shipDescription : conf.getShipDescription()) 
		{
			String[] words = shipDescription.toLowerCase().trim().split("\\s+");
			if(words.length != 3)
			{
				throw new InitializationException(Messages.INCORRECT_ENTRY.formatted(shipDescription));
			}
			try
			{
				Position pos = new Position(Integer.valueOf(words[1]), Integer.valueOf(words[2]));
				if(pos.outOfWall())
				{
					throw new InitializationException(Messages.OFF_WORLD_POSITION.formatted(words[1], words[2]));
				}
				container.add(ShipFactory.spawnAlienShip(words[0], game, pos, this));
			}
			catch(NumberFormatException e)
			{
				throw new InitializationException(Messages.INVALID_POSITION.formatted(words[1], words[2]));
			}
			this.remainingAliens++;
		}
	}
	
	private void initializeOvni(GameObjectContainer container) {
		container.add(new Ufo(game));
	}
	
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	private void initializeRegularAliens (GameObjectContainer container) {
		for(int i = 0; i < this.level.getNumRowsRegularAliens(); i++)
		{
			for(int j = 0; j < this.level.getNumRegularAliens()/this.level.getNumRowsRegularAliens(); j++)
			{
				container.add(new RegularAlien(new Position(j + 2, i + 1), this, game));
				this.remainingAliens++;
			}
		}
	}
	
	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
	
	private void initializeDestroyerAliens(GameObjectContainer container) {
		for(int i = 0; i < this.level.getNumDestroyerAliens(); i++)
		{
			container.add(new DestroyerAlien(new Position((4/level.getNumDestroyerAliens()) + 1 + i, 1 + this.level.getNumRowsRegularAliens()), this, game));
			this.remainingAliens++;
		}
	}

//	private void costumedInitialization(GameObjectContainer container, InitialConfiguration conf) {
//		for (String shipDescription : conf.getShipDescription()) {
//			String[] words = shipDescription.toLowerCase().trim().split("\\s+");
//			//AlienShip ship = ...
//			container.add(ship);
//			this.remainingAliens++;
//		}
//	}

	
	// CONTROL METHODS
		
	public void shipOnBorder() {
		if(!onBorder) {
			onBorder = true;
			shipsOnBorder = remainingAliens;
		}
	}
	

	public void ShipOnBorder()
	{
		this.numShipsOnBorder();
		this.onBorder = true;
		
	}


	public boolean noAliens() {
		return this.remainingAliens == 0;
	}

	public void decreaseAliens(int numAliensBaja) {
		this.remainingAliens = this.remainingAliens - numAliensBaja;
		
	}
	
	public void numShipsOnBorder()
	{
		this.shipsOnBorder = this.remainingAliens;
	}
	

	public int remainingAliens() {
		return remainingAliens;
	}

	public void decreaseOnBorder() {
		this.shipsOnBorder--;
		if(this.shipsOnBorder == 0)
				this.onBorder = false;
	}
	
	public boolean onBorder()
	{
		return this.onBorder;
	}
	
	public void aliensOnFinal()
	{
		this.squadInFinalRow = true;
	}
	
	public boolean squadFinalRow()
	{
		return this.squadInFinalRow;
	}

	public void decreaseAliensManager() 
	{
		this.remainingAliens--;
	}

}