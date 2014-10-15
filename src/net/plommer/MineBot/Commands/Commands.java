package net.plommer.MineBot.Commands;

import net.plommer.MineBot.Users.User;
import net.plommer.MineBot.Utils.Utils;

public abstract class Commands {

	private String cmd;
	private User user;
	private String[] args;
	private String permissions;
	private String channel;
	
	public boolean run(User user, String[] args, String channel) {
		if(!user.hasPermissions(getPermissions())) {
			Utils.sendMessage("&cÞú hefur ekki leyfi á að gera þetta!", channel);
			return false;
		}
		this.setUser(user);
		this.setArgs(args);
		this.setChannel(channel);
		return execute();
	}

	public abstract boolean execute();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
