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
	public static final int POINTS = 5;
	public static final int INI_RESISTANCE = 2;
	public static final int DAMAGE = 0;
	

	public RegularAlien(Position posAlien, AlienManager alienManager, GameWorld game)
	{
		super(posAlien, alienManager,  game, INI_RESISTANCE, POINTS);
		
	}
	
	
	public RegularAlien() {
		super();
	}
	
	@Override
	public String getInfo()
	{
		return Messages.alienDescription(Messages.REGULAR_ALIEN_DESCRIPTION, RegularAlien.POINTS, RegularAlien.DAMAGE, RegularAlien.INI_RESISTANCE);
	}
	
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am){
		return new RegularAlien(pos, am, game);
	}
	
	
	@Override
	public int getDamage()
	{
		return DAMAGE;
	}
	
	@Override
	public int getArmour()
	{
		return INI_RESISTANCE;
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