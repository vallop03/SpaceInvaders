package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;


public abstract class GameObject implements GameItem {

	protected Position pos;
	protected int life;
	protected GameWorld game;
	
	public GameObject() {}
	
	protected GameObject(GameWorld game, Position pos, int life) {	
		this.pos = pos;
		this.game = game;
		this.life = life;
	}
	

	@Override
	public boolean isAlive() {
		return this.life > 0;
	}
	
	public void performMovement(Move dir) {
		this.pos = this.pos.nuevaPos(dir);
	}
	
	public void looseLife(int damage) {
		this.life -= damage;
	}

	protected int getLife() {
		return this.life;
	}
	
	@Override
	public boolean isOnPosition(Position position)
	{
		return position.equals(this.pos);
	}
	
	@Override
	public boolean isOnPosNextTo(Position pos) {
		return this.pos.isOnPosNextTo(pos);
	}
	
	public void die()
	{
		onDelete();
	}
	
	public String toString()
	{
		return getSymbol();
	}

	
	protected abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmour();
	
			
	public abstract void onDelete();
	public abstract void automaticMove();
	public void computerAction() {}; //las funciones de arriba como est�n sin {} no funcionan, esta si no se hace override no hace nada
	
	@Override
	public boolean performAttack(GameItem other) {return false;}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {return false;}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {return false;}

}