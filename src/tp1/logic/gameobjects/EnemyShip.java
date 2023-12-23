package tp1.logic.gameobjects;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship{
		protected Move dir;
		protected int cyclesToMove;
		protected int speed;
		protected int points;
		
		
		
		public EnemyShip() {
			super();
		}
		
		
		
		protected EnemyShip(GameWorld game, Position pos, Move move, int resist, int speed, int points)
		{
			super(game, pos, resist);
			this.dir = move;
			this.speed = speed;
			this.cyclesToMove = speed; 
			this.points = points;
		}
		

		@Override
		public boolean receiveAttack(UCMWeapon weapon) {
			this.looseLife(weapon.getDamage());
			
			return true;
		}
		
		@Override
		public void onDelete() {
			game.increasePoints(this.points);
		}
		
		
}
