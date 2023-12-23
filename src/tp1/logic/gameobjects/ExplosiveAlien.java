package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplosiveAlien extends AlienShip{
	
	//CTES
	public static final int POINTS = 12;
	public static final int INI_RESISTANCE = 2;
	public static final int DAMAGE = 0;

	public ExplosiveAlien(Position posAlien, AlienManager alienManager, GameWorld game)
	{
		super(posAlien, alienManager, game, INI_RESISTANCE, POINTS);
		
	}
	
	public ExplosiveAlien() {
		super();
	}
	
	@Override
	public String getInfo()
	{
		return Messages.alienDescription(Messages.EXPLOSIVE_ALIEN_DESCRIPTION, ExplosiveAlien.POINTS, Explosive.DAMAGE, ExplosiveAlien.INI_RESISTANCE);
	}
	
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am){
		return new ExplosiveAlien(pos, am, game);
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
