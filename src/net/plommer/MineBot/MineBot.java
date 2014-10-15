package net.plommer.MineBot;

import java.io.File;
import java.util.ArrayList;

import net.plommer.MineBot.Commands.*;
import net.plommer.MineBot.Connections.ServerHandler;
import net.plommer.MineBot.Datasource.Database;
import net.plommer.MineBot.Utils.Startup;
import net.plommer.MineBot.Utils.UrlDownload;

public class MineBot {
	
	public static ArrayList<Commands> cmds = new ArrayList<Commands>();
	public static Database db;
	
	public static void main(String[] args) {
		new Startup();
		File f = new File(MineBot.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		File file = new File(f.getParent() + "/mysql-connector-java-5.1.18.jar");
		if(!file.exists()) {
			System.out.println("Downloading mysql...");
			new UrlDownload("http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.33/mysql-connector-java-5.1.33.jar", "mysql-connector-java-5.1.18.jar");
		}
		new ServerHandler();
		cmds.add(new AddPermission());
		cmds.add(new TestCommand());
		cmds.add(new VoteCommand());
		db = new Database();
	}
	
}