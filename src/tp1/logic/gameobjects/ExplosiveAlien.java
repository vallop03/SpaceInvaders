package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplosiveAlien extends AlienShip{
	
	//CTES
	public static final int points = 12;
	public static final int ini_resistance = 2;
	public static final int damage = 0;

	public ExplosiveAlien(Position posAlien, AlienManager alienManager, GameWorld game)
	{
		super(posAlien, alienManager, game, ini_resistance, points);
		
	}
	
	public ExplosiveAlien() {
		super();
	}
	
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am){
		return new ExplosiveAlien(pos, am, game);
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
		return Messages.status(Messages.EXPLOSIVE_ALIEN_SYMBOL, getLife());
	}
	
	@Override
	protected String getLetter()
	{
		return Messages.EXPLOSIVE_ALIEN_SYMBOL;
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon)
	{
		super.receiveAttack(weapon);
		if(!this.isAlive())
			game.addObject(new Explosive(this.game, this.pos));
		
		return true;
	}
	
	
}
