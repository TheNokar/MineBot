package net.plommer.MineBot.Commands;

import net.plommer.MineBot.MineBot;
import net.plommer.MineBot.Utils.Utils;

public class AddPermission extends Commands {

	public AddPermission() {
		setCmd("permission:perm");
		setPermissions("permission");
	}
	
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(getArgs().length > 2) {
			if(getArgs()[0].equalsIgnoreCase("add") || getArgs()[0].equalsIgnoreCase("a")) {
				MineBot.db.addPermissions(getArgs()[1], getArgs()[2]);
				Utils.sendMessage("&e{0} &ahefur verið gefið leyfi á &e{1}", new String[] {getArgs()[1], getArgs()[2]}, getChannel());
			}
		}
		return false;
	}

}
