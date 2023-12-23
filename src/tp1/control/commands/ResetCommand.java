package tp1.control.commands;

import tp1.control.InitialConfiguration;
import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.Messages;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResetCommand extends Command{
	private InitialConfiguration conf;
	
	protected ResetCommand() {};
	
	protected ResetCommand(InitialConfiguration conf)
	{
		this.conf = conf;
	}
	
	@Override
	public boolean execute(GameModel game) {
		if(this.conf != null)
		{
			game.reset(this.conf);
			return new ExecutionResult(true);
		}
		else
			return new ExecutionResult(Messages.INITIAL_CONFIGURATION_ERROR);
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException
	{
		Command c = null;
		if(matchCommandName(commandWords[0]))
		{
			c = this;
			if(commandWords.length == 2)
			{
				try
				{
					InitialConfiguration config = InitialConfiguration.readFromFile(commandWords[1]);
					c = new ResetCommand(config);					
				}
				catch(FileNotFoundException e)
				{
					throw new CommandParseException(Messages.FILE_NOT_FOUND.formatted(commandWords[1]));
				}
				catch(IOException e)
				{
					throw new CommandParseException(Messages.READ_ERROR.formatted(commandWords[1]));
				}
			}
			else if(commandWords.length == 1)
				c = new ResetCommand(InitialConfiguration.NONE);
			else
			{
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
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