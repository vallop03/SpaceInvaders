package tp1.logic;

/**
 * Represents the allowed movements in the game
 *
 */
public enum Move {
	LEFT(-1,0), LLEFT(-2,0), RIGHT(1,0), RRIGHT(2,0), DOWN(0,1), UP(0,-1), NONE(0,0);
	
	private int x;
	private int y;
	
	private Move(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Move flip() {
		Move move;
		if(this == LEFT)
			move = RIGHT;
		else
			move = LEFT;
		
		return move;
	}
	
	public static boolean opVal(String op)
	{
		switch(op)
		{
		case "LEFT":
		case "RIGHT":
		case "UP":
		case "DOWN":
		case "RRIGHT":
		case "LLEFT":
		case "NONE":
			return true;
		default:
			return false;
		}
	}
	
	
}