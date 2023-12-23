package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo extends EnemyShip{

	private boolean enable;
	private final static int SPEED = 0;
	
	//CTES
	public final static int RESISTANCE = 1;
	public final static int POINTS = 25;
	public final static int DAMAGE = 0;

	public Ufo(){}
	
	public Ufo(GameWorld game)
	{
		super(game, new Position(9, 0), Move.LEFT, RESISTANCE, SPEED, POINTS);
		this.enable = false;
		
	}
	
	@Override
	public String getInfo()
	{
		return Messages.alienDescription(Messages.UFO_DESCRIPTION, Ufo.POINTS, Ufo.DAMAGE, Ufo.RESISTANCE);
	}

	@Override
	public void computerAction() {
		if(!enable && canGenerateRandomUfo()) 
		{
			enable();
		}
	}
	
	@Override
	public void automaticMove() 
	{
		if(this.enable)
		{
			this.pos = pos.nuevaPos(Move.LEFT);
			if(pos.out() || pos.outOfWall())
			{
				this.life = RESISTANCE;
				this.enable = false;
				this.pos = new Position(9, 0);
			}
		}
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		if(this.enable)
		{
			this.onDelete();
			return true;
		}
		return false;
	}
	
	
	private void enable() {
		this.enable = true; 
	}

	@Override
	public void onDelete() {
		game.increasePoints(POINTS);
		this.enable = false;
		this.life = RESISTANCE;
		this.pos = new Position(9, 0);
		game.enableShockWave(true);
	}
	
	public boolean ufoEnable()
	{
		return this.enable;
	}

	/**
	 * Checks if the game should generate an ufo.
	 * 
	 * @return <code>true</code> if an ufo should be generated.
	 */
	private boolean canGenerateRandomUfo(){
		return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}

	@Override
	protected String getSymbol()
	{
		return Messages.status(Messages.UFO_SYMBOL, getLife());
	}

	@Override
	protected int getDamage() {
		return 0;
	}

	@Override
	protected int getArmour() {
		return RESISTANCE;
	}
	
}