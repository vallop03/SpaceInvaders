package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.InitializationException;
import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ShipFactory {
	
	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
			new RegularAlien(),
			new DestroyerAlien(),
			new ExplosiveAlien()
		);
	
	public static AlienShip spawnAlienShip(String input, GameWorld game, Position pos, AlienManager am) throws InitializationException
	{
		for(AlienShip alien: AVAILABLE_ALIEN_SHIPS)
		{
				if(input.equalsIgnoreCase(alien.getLetter()))
					return alien.copy(game, pos, am);

		}
		throw new InitializationException(Messages.UNKNOWN_SHIP.formatted(input));
	}

}
