package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;
	private String invalMove;

	public MoveCommand() {}

	protected MoveCommand(Move move, String error) {
		this.move = move;
		this.invalMove = error;
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
	public ExecutionResult execute(GameModel game) {
		if(this.move == Move.UP || this.move == Move.DOWN)
		{
			return new ExecutionResult(Messages.DIRECTION_ERROR + invalMove);
		}
		else if(this.move == null)
			return new ExecutionResult(Messages.COMMAND_PARAMETERS_MISSING);
		
		if(!game.move(move))
			return new ExecutionResult(Messages.MOVEMENT_ERROR);
		else
		{
			game.update();
			return new ExecutionResult(true);
		}
	}


	@Override
	public Command parse(String[] commandWords) { 
		Command c = null;
		Move move = null;
		if(matchCommandName(commandWords[0]))
		{
			c = this;
			if(commandWords.length == 2)// quitar strings y opVal con excepciones
			{
				if(Move.opVal(commandWords[1].toUpperCase()))
				{
					move = Move.valueOf(commandWords[1].toUpperCase());
					c = new MoveCommand(move, "");
				}
				else
				{
					c = new MoveCommand(Move.UP, commandWords[1]);
				}
				
			}
		}
	    return c;
	}

}
