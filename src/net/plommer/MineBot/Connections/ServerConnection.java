package net.plommer.MineBot.Connections;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import net.plommer.MineBot.Configs.Config;

public class ServerConnection extends Socket {

	public ServerConnection() throws UnknownHostException, IOException {
		super(Config.serverip, Config.port);
	}
	
}
