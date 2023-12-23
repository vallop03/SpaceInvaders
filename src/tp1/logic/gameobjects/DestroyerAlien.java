package tp1.logic.gameobjects;
import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip {
	
	private boolean enableBomb;
	
	//CTES
	public static final int INI_RESISTANCE = 1;
	public static final int POINTS = 10;
	public static final int DAMAGE = 1;
	
	public DestroyerAlien() 
	{
		super();
	}
	
	public DestroyerAlien(Position posAlien, AlienManager alienManager, GameWorld game)
	{
		super(posAlien, alienManager,  game, INI_RESISTANCE, POINTS);
		this.enableBomb = false;
	}
	
	@Override
	public String getInfo()
	{
		return Messages.alienDescription(Messages.DESTROYER_ALIEN_DESCRIPTION, DestroyerAlien.POINTS, DestroyerAlien.DAMAGE, DestroyerAlien.INI_RESISTANCE);
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
		return Messages.status(Messages.DESTROYER_ALIEN_SYMBOL, getLife());
	}
	
	@Override
	public void computerAction()
	{
		if(!this.cycleIsZero() && !this.enableBomb && canGenerateBomb())
		{
			game.addObject(shootBomb(game));
		}
	}
	
	public boolean canGenerateBomb()
	{
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
	}
	

	public Bomb shootBomb(GameWorld game) {
		Bomb bomb = new Bomb(game, pos, Move.DOWN, this);
		this.enableBomb = true;
		return bomb;
	}

	public void enableBomb() {
		this.enableBomb = false;
	}

	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new DestroyerAlien(pos, am, game);
	}
	
	@Override
	protected String getLetter()
	{
		return Messages.DESTROYER_ALIEN_SYMBOL;
	}

}