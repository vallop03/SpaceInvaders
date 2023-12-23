package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.InitialConfiguration;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ResetCommand extends Command{
	private InitialConfiguration conf;
	
	protected ResetCommand() {};
	
	protected ResetCommand(InitialConfiguration conf)
	{
		this.conf = conf;
	}
	
	@Override
	public ExecutionResult execute(GameModel game) {
		if(this.conf != null)
		{
			game.reset(this.conf);
			return new ExecutionResult(true);
		}
		else
			return new ExecutionResult(Messages.INITIAL_CONFIGURATION_ERROR);
	}

	@Override
	public Command parse(String[] commandWords) 
	{
		Command c = null;
		if(matchCommandName(commandWords[0]))
		{
			c = this;
			if(commandWords.length == 2) //reset con 3 long es none
			{
				if(InitialConfiguration.opVal(commandWords[1].toUpperCase()))
				{
					InitialConfiguration config = InitialConfiguration.valueOfIgnoreCase(commandWords[1]);
					c = new ResetCommand(config);
				}
			}
			else
				c = new ResetCommand(InitialConfiguration.NONE);
		}
		return c;
	}
	
	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

}