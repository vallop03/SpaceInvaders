package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.OffWorldException;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;

	public MoveCommand() {}

	protected MoveCommand(Move move) {
		this.move = move;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException{
		try {
			game.move(move);
			game.update();
			return true;
		}
		catch(OffWorldException e) {
			throw new CommandExecuteException(Messages.MOVEMENT_ERROR, e);
		}
		catch(NotAllowedMoveException e)
		{
			throw new CommandExecuteException(Messages.DIRECTION_ERROR + move.toString() , e);
		}
		
	}


	@Override
	public Command parse(String[] commandWords) throws CommandParseException
	{ 
		Command c = null;
		Move move = null;
		if(matchCommandName(commandWords[0]))
		{
			c = this;
			if(commandWords.length == 2)
			{
				try
				{
					move = Move.valueOf(commandWords[1].toUpperCase());
					c = new MoveCommand(move);
				}
				catch(IllegalArgumentException e)
				{
					throw new CommandParseException(Messages.DIRECTION_ERROR + commandWords[1]);
				}
				
			}
			else
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
	    return c;
	}

}
