package net.plommer.MineBot.Commands;

import net.plommer.MineBot.Utils.Utils;

public class TestCommand extends Commands {

	public TestCommand() {
		setCmd("load:l");
		setPermissions("load");
	}
	
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		Utils.sendMessage("Loaded", getChannel());
		return false;
	}

}
