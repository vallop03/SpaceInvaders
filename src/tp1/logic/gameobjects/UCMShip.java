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
	public static final int col_ini = 4; ////Final son ctes
	public static final int row_ini = 7;
	public static final int ini_health = 3;
	public static final int damage = 0;
	
	public UCMShip(GameWorld game)
	{
		super(game, new Position(col_ini, row_ini), ini_health);
		this.enableLaser = false;
		this.enableShockWave = false;
		game.addObject(this);
	}
	
	@Override
	protected String getSymbol()
	{
		if(!super.isAlive())
			return Messages.UCMSHIP_DEAD_SYMBOL;
		else
			return Messages.UCMSHIP_SYMBOL;
	}
	
	public void enableLaser(boolean activate)
	{
		this.enableLaser = activate;
	}
	
	public void enableShockWave(boolean activate)
	{
		this.enableShockWave = activate;
	}
	
	public String shockWaveState() {
		if(this.enableShockWave)
			return "ON";
		else
			return "OFF";
	}
	
	@Override
	public boolean isAlive()
	{
		return this.life >= 0;
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

	public String Lifes() {
		
		return "Lifes: " + getLife();
	}

	@Override
	protected int getDamage() {
		return UCMShip.damage;
	}

	@Override
	protected int getArmour() {
		return UCMShip.ini_health;
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
