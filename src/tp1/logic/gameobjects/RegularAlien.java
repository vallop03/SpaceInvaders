package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
//import tp1.logic.Game;
import tp1.logic.GameWorld;
//import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;
//import tp1.logic.Level;


/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien extends AlienShip{
	
	//CTES
	public static final int points = 5;
	public static final int ini_resistance = 2;
	public static final int damage = 0;
	

	public RegularAlien(Position posAlien, AlienManager alienManager, GameWorld game)
	{
		super(posAlien, alienManager,  game, ini_resistance, points);
		
	}
	
	
	public RegularAlien() {
		super();
	}
	
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am){
		return new RegularAlien(pos, am, game);
	}
	
	
	@Override
	public int getDamage()
	{
		return damage;
	}
	
	@Override
	public int getArmour()
	{
		return ini_resistance;
	}

	@Override
	protected String getSymbol()
	{
		return Messages.status(Messages.REGULAR_ALIEN_SYMBOL, getLife());
	}
	
	@Override
	protected String getLetter()
	{
		return Messages.REGULAR_ALIEN_SYMBOL;
	}

	

}