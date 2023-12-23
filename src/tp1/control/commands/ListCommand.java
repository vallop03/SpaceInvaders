package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ListCommand extends NoParamsCommand{
		  		
		@Override
		public ExecutionResult execute(GameModel game) {
			System.out.println(game.infoToString());
			return new ExecutionResult(false);
		}

		@Override
		protected String getName() {
			return Messages.COMMAND_LIST_NAME;
		}

		@Override
		protected String getShortcut() {
			return Messages.COMMAND_LIST_SHORTCUT;
		}

		@Override
		protected String getDetails() {
			return Messages.COMMAND_LIST_DETAILS;
		}

		@Override
		protected String getHelp() {
			return Messages.COMMAND_LIST_HELP;
		}

	}
