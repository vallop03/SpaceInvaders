package tp1.control.commands;


import tp1.control.ExecutionResult;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShockWaveCommand extends NoParamsCommand{
	
	@Override
	public ExecutionResult execute(GameModel game) {
		
		if(game.shootPower())
		{
			game.update();
			return new ExecutionResult(true);
		}
    	else
    		return new ExecutionResult(Messages.SHOCKWAVE_ERROR);
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_SHOCKWAVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SHOCKWAVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SHOCKWAVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SHOCKWAVE_HELP;
	}
}

