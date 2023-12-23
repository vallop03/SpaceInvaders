package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),
		new MoveCommand(),
		new ExitCommand(),
		new NoneCommand(),
		new ListCommand(),
		new ShockWaveCommand(),
		new ShootCommand(),
		new ResetCommand(),
		new SuperLaserCommand()
	);

	public static Command parse(String[] commandWords) throws CommandParseException {		
		Command command = null;
		for (Command c: availableCommands) {
			command = c.parse(commandWords);
			if(command != null)
				return command;
		}
		throw new CommandParseException(Messages.UNKNOWN_COMMAND);
	}
		
	public static String commandHelp() { //stringBuilder de help está aquí porque help necesita 
		StringBuilder commands = new StringBuilder();//info de cada comando
		for (Command c: availableCommands) {
			 commands.append(c.helpText());
		}
		return commands.toString();
	}

}
