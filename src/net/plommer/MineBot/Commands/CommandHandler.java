package net.plommer.MineBot.Commands;

import java.util.Arrays;

import net.plommer.MineBot.MineBot;
import net.plommer.MineBot.Users.User;

public class CommandHandler {
	
	public static boolean onCommand(String msg, User user, String channel) {
		if(msg != null) {
			if(msg.startsWith("!")) {
				String userCmd = msg.split(" ")[0].replaceFirst("!", "");
				String[] args = Arrays.copyOfRange(msg.split(" "), 1, msg.split(" ").length);
				for(Commands c : MineBot.cmds) {
					if(c.getCmd().contains(":")) {
						for(String cmd : c.getCmd().split(":")) {
							if(cmd.equalsIgnoreCase(userCmd)) {
								return c.run(user, args, channel);
							}
						}
					}
					if(userCmd.equalsIgnoreCase(c.getCmd())) {
						return c.run(user, args, channel);
					}
				}
			}
		}
		return false;
	}
	
}
