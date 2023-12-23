package tp1.logic;


/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	@Override
	public String toString() {
		return String.valueOf(this.col) + ", " + String.valueOf(this.row);
	}

	private int col;
	private int row;

	public Position(int col, int row) //Constructor de pos
	{
		this.col = col;
		this.row = row;
	}
	
	public boolean equals(Position pos) {
		
		return this.col == pos.col && this.row == pos.row;
	}

	public Position nuevaPos(Move move) {
		
		return new Position(col + move.getX(), row + move.getY());
	}
	

	public boolean validPos(Move move) { 
		this.nuevaPos(move);
		return !outOfWall() || !out();
	}
	
	public boolean outOfWall()
	{
		return this.col < 0 || this.col >= Game.DIM_X;
	}
	
	public boolean out()
	{
		return this.row < 0 || this.row >= Game.DIM_Y;
	}

	public boolean border() {
		
		return col == 0 || col == Game.DIM_X - 1;
	}

	public boolean down() {
		return this.row == Game.DIM_Y - 1;
	}

	public boolean isOnPosNextTo(Position pos) { //Hacer con distancias
		boolean find = pos.equals(this);
		int i = pos.row - 1;
		int j = pos.col - 1;
		while(!find && i < pos.row + 2)
		{
			while(!find && j < pos.col + 2)
			{
				Position findPos = new Position(j, i);
				find = findPos.equals(this);
				j++;
			}
			i++;
			j = pos.col - 1;
		}
		return find;
	}

	
	
}