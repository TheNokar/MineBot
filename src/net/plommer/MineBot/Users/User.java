package net.plommer.MineBot.Users;

import net.plommer.MineBot.MineBot;

public class User {

	public String name;
	
	public User(String name) {
		setName(name);
	}
	
	//Player names
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	//Has permission
	public boolean hasPermissions(String permission) {
		return MineBot.db.hasPermissions(this, permission);
	}
	
}
