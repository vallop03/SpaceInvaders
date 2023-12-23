package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class SuperLaserCommand extends NoParamsCommand{
	
	@Override
	public boolean execute(GameModel game) throws CommandExecuteException{
		if(game.enoughPoints())
		{
			if(game.shootSuperLaser())
			{
				game.decreasePoints();
				game.update();
				return ;
			}
			else
				return new ExecutionResult(Messages.LASER_ALREADY_SHOT);
		}
		else 
			return new ExecutionResult(Messages.SUPERLASER_ERROR);

	}

	@Override
	protected String getName() {
		return Messages.COMMAND_SUPERLASER_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SUPERLASER_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SUPERLASER_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SUPERLASER_HELP;
	}

}