package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.LaserIntFlightException;
import tp1.exceptions.NoShockWaveException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.NotEnoughPointsException;
import tp1.exceptions.OffWorldException;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.UCMLaser;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.Ufo;
import tp1.view.Messages;

import java.util.Random;


public class Game implements GameStatus, GameModel, GameWorld{
	private int cycles;
	private int points;
	private Level level;
	private long seed;
	private Random random;
	private AlienManager alienMan;
	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	private boolean exit;
	private UCMShip player;
	

	private GameObjectContainer container;

	public Game(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		this.reset(InitialConfiguration.NONE);
	}
	
	@Override
	public void reset(InitialConfiguration conf) { ////reinicia el juego
		
		this.alienMan = new AlienManager(this, level);
		this.container = this.alienMan.initialize(conf);
		this.player = new UCMShip(this); 
		this.random = new Random(this.seed);
		this.cycles = 0;
		this.points = 0;
		this.exit = false;
	}
	
	
	
	
	@Override
	public void shootSuperLaser()throws LaserIntFlightException, NotEnoughPointsException
	{
		if(this.points >= 5)
		{
			player.superLaser();
			this.points -= 5;
		}
		else
			throw new NotEnoughPointsException(Messages.NOT_ENOUGH_POINTS_ERROR.formatted(this.points, 5));
	}
	
	
	@Override
	public void enableLaser(boolean activate)
	{
		player.enableLaser(activate);
	}
	
	@Override
	public void enableShockWave(boolean activate)
	{
		player.enableShockWave(activate);
	}
	
	
	public void update() {
	    this.cycles++;
	    this.container.computerActions();
	    this.container.automaticMoves();
	}
	
	@Override
	public int getNumCyclesToMoveOneCell()
	{
		return level.getNumCyclesToMoveOneCell();
	}
	

	@Override
	public String stateToString() {//imprime vidas, puntos y shockWave //CAMBIAR Y USAR LISTA DE MENSAJES
		return this.player.Lifes() + System.lineSeparator() + "Points: " + this.points + System.lineSeparator() + "ShockWave: " + player.shockWaveState() + System.lineSeparator(); 
	}
	
	@Override
	public int getCycle() {
		
		return this.cycles;
	}

	@Override
	public int getRemainingAliens() {
		
		return alienMan.remainingAliens();
	}

	public void increaseCycles()
	{
		this.cycles++;
	}

	public boolean playerWin() {
		
		return alienMan.noAliens();
	}

	public boolean aliensWin() {
		
		return player.death() || this.alienMan.squadFinalRow();
	}
	
	@Override
	public void exit()
	{
		this.exit = true;
	}
	
	@Override
	public boolean isFinished()
	{
		return this.exit || aliensWin() || playerWin();
	}

	@Override
	public Random getRandom() {
		return random;
	}

	@Override
	public Level getLevel() {
		return this.level;
	}
	
	
	@Override
	public void move(Move move) throws NotAllowedMoveException, OffWorldException{ //permite el movimiento de la nave
		player.performMove(move);
	}
	
	private final String[] LIST_LINES = new String[] //CONSTANTES MAYUSC. Hacer con factoria (esto privado)
	{
		Messages.ucmShipDescription(Messages.UCMSHIP_DESCRIPTION, UCMLaser.damage, UCMShip.ini_health),
		Messages.alienDescription(Messages.REGULAR_ALIEN_DESCRIPTION, RegularAlien.points, RegularAlien.damage, RegularAlien.ini_resistance),
		Messages.alienDescription(Messages.DESTROYER_ALIEN_DESCRIPTION, DestroyerAlien.points, DestroyerAlien.damage, DestroyerAlien.ini_resistance),
		Messages.alienDescription(Messages.UFO_DESCRIPTION, 25, Ufo.damage, Ufo.resistance),
		" "};
	
	@Override  //////PONER OVERRIDE EN FUNCIONES REPES
	public String infoToString()
	{
		return String.join(System.lineSeparator(), LIST_LINES);
	}
	
	public String positionToString(int col, int row) {//pide a nave simbolo
		return container.toString(new Position(col, row));
	}

	@Override
	public void shootLaser() throws LaserIntFlightException{//crea el laser en ship porque necesita posNave
		 player.shoot();
	}
	
	@Override
	public void shootPower() throws NoShockWaveException
	{
		player.shootShockWave();
	}
	
	
	@Override
	public void numShipsOnBorder()
	{
		this.alienMan.numShipsOnBorder();
	}
	
	@Override 
	public void decreaseAliensManager()
	{
		this.alienMan.decreaseAliensManager();
	}
	
	@Override
	public void increasePoints(int i) {
		this.points += i;
	}
	

	@Override
	public void addObject(GameObject object) {
		container.add(object);
	}

	@Override
	public void receiveAttack(GameObject object) {
		this.container.receiveAttack(object);
	}
	
	@Override
	public void performAttack(GameObject weapon)
	{
		this.container.performAttack(weapon);
	}

	
}