package net.plommer.MineBot.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.plommer.MineBot.Configs.Config;

public class Startup {

	public Startup() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Server ip: ");
			Config.serverip = isEmpty(bf.readLine(), Config.serverip);
			System.out.print("Port: ");
			Config.port = isEmpty(bf.readLine(), Config.port);
			System.out.print("Nick: ");
			Config.nick = isEmpty(bf.readLine(), Config.nick);
			System.out.print("Channel name: ");
			Config.channel = isChannel(bf.readLine());
			System.out.print("MySQL User: ");
			Config.mysql_username = isEmpty(bf.readLine(), Config.mysql_username);
			System.out.print("MySQL Pass: ");
			Config.mysql_password = isEmpty(bf.readLine(), Config.mysql_password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String[] isChannel(String input) {
		String[] st = new String[] {};
		if(input.contains(",")) {
			for(String s : input.split(",")) {
				s = s.replace(" ", "");
				st[st.length+1] = s;
			}
		}
		return st;
	}
	
	private String isEmpty(String input, String def) {
		if(input.length() <= 0) {
			return def;
		}
		return input;
	}
	
	private int isEmpty(String input, int def) {
		if(input.length() <= 0) {
			return def;
		}
		return Integer.parseInt(input);
	}
	
}
