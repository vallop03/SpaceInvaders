package tp1.logic.gameobjects;

import tp1.view.Messages;
import tp1.exceptions.LaserIntFlightException;
import tp1.exceptions.NoShockWaveException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.OffWorldException;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;


public class UCMShip extends Ship{
	private boolean enableLaser;
	private boolean enableShockWave;
	
	//CTES
	public static final int COL_INI = 4; ////Final son ctes
	public static final int ROW_INI = 7;
	public static final int INI_HEALTH = 3;
	public static final int DAMAGE = 0;
	
	public UCMShip() {};
	
	public UCMShip(GameWorld game)
	{
		super(game, new Position(COL_INI, ROW_INI), INI_HEALTH);
		this.enableLaser = false;
		this.enableShockWave = false;
		game.addObject(this);
	}
	
	@Override
	public String getInfo()
	{
		return Messages.ucmShipDescription(Messages.UCMSHIP_DESCRIPTION, UCMLaser.DAMAGE, UCMShip.INI_HEALTH);
	}
	
	public int getLife()//para imprimir en game
	{
		return this.life;
	}
	
	@Override
	protected String getSymbol()
	{
		if(!this.isAlive())
			return Messages.UCMSHIP_DEAD_SYMBOL;
		else
			return Messages.UCMSHIP_SYMBOL;
	}
	
	public void enableLaser(boolean activate)
	{
		this.enableLaser = activate;
	}
	
	public void disableLaser()
	{
		this.enableLaser = false;
	}
	
	public void enableShockWave(boolean activate)
	{
		this.enableShockWave = activate;
	}
	
	public void disableShockWave()
	{
		this.enableShockWave = false;
	}
	
	public String shockWaveState() {
		if(this.enableShockWave)
			return "ON";
		else
			return "OFF";
	}
	
	public void shoot() throws LaserIntFlightException{
		if(!enableLaser)
		{
			UCMLaser laser = new UCMLaser(game, pos);
			this.game.addObject(laser);
			this.enableLaser = true;
		}
		else
			throw new LaserIntFlightException(Messages.LASER_ALREADY_SHOT);
			
	}

	@Override
	protected int getDamage() {
		return UCMShip.DAMAGE;
	}

	@Override
	protected int getArmour() {
		return UCMShip.INI_HEALTH;
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		return false;
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) 
	{
		if(weapon.isOnPosition(this.pos))
		{
			this.looseLife(weapon.getDamage());
			return true;
		}
		return false;
	}

	@Override
	public void automaticMove() {
		
	}

	@Override
	public void onDelete() 
	{
		
	}

	public void shootShockWave() throws NoShockWaveException{
		if(enableShockWave)
		{
			game.addObject(new ShockWave(game, game.getRemainingAliens()));
			enableShockWave = false;
		}
		else
			throw new NoShockWaveException();
	}

	public boolean death() {
		return this.life <= 0;
	}

	public void superLaser() throws LaserIntFlightException
	{
		if(!enableLaser)
		{
			UCMSuperLaser superLaser = new UCMSuperLaser(game, pos);
			this.game.addObject(superLaser);
			this.enableLaser = true;
		}
		else
			throw new LaserIntFlightException(Messages.LASER_ALREADY_SHOT);
	}

	public static String allowedMoves(String string) {
		return String.valueOf(Move.LEFT) + string +  String.valueOf(Move.LLEFT) + string + String.valueOf(Move.RIGHT) + string + String.valueOf(Move.RRIGHT);
	}

	public void performMove(Move move) throws NotAllowedMoveException, OffWorldException
	{
		if(move != Move.UP && move != Move.DOWN)
		{
			if(validPos(move))
			{
				performMovement(move);				
			}
			else
			{
				throw new OffWorldException(Messages.OFF_WORLD_MESSAGE.formatted(move, this.pos));
			}
		}
		else
		{
			throw new NotAllowedMoveException(Messages.ALLOWED_UCMSHIP_MOVES);
		}
	}
	
}
