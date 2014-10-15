package net.plommer.MineBot.Connections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import net.plommer.MineBot.Commands.CommandHandler;
import net.plommer.MineBot.Configs.Config;
import net.plommer.MineBot.Utils.IrcParser;
import net.plommer.MineBot.Utils.Utils;

public class ServerHandler implements Runnable {

	public static Socket s;
	
	public ServerHandler() {
		try {
			s = new Socket(Config.serverip, Config.port);
			new ConnectionsReaders(new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8")), new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8")));
			if(s.isConnected()) {
				ConnectionsReaders.getWriter().write(Utils.getMessageString("PASS {0}:{1}", new String[] {"NBot", "plommer123"}));
				ConnectionsReaders.getWriter().write(Utils.getMessageString("NICK {0}", new String[] {Config.nick}));
				ConnectionsReaders.getWriter().write(Utils.getMessageString("USER {0} {1}", new String[] {Config.nick, "8 * : Bot"}));
				ConnectionsReaders.getWriter().flush();
				System.out.println("Connecting to server!");
				new ThreadConnection(this).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to connect to server!");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				String line = null;
		        while ((line = ConnectionsReaders.getReader().readLine( )) != null) {if (line.indexOf("004") >= 0) {break;} else if (line.indexOf("433") >= 0) {System.out.println("Nickname is already in use.");return;}}		
				while((line = ConnectionsReaders.getReader().readLine()) != null) {
					for(int i = 0; i < Config.channel.length; i++) {
						String channel = Config.channel[i];
						Utils.sendRawMessage("JOIN {0}", new String[] {channel});
					}
					if(line.startsWith("PING")) {Utils.sendRawMessage("PONG {0}", new String[] {line.substring(5)});} else {
						IrcParser pa = new IrcParser(line);
						if(pa.getChannel() != null && pa.getUser() != null) {
							Utils.printMessage("{0} - {1}: {2}", new String[] {pa.getChannel(), pa.getUser().getName(), pa.getMsg()});
							if(CommandHandler.onCommand(pa.getMsg(), pa.getUser(), pa.getChannel()) == true) {
								System.out.println(pa.getUser().getName() + " used a command...");
							}
						} else {
							System.out.println(pa.getMsg());
						}
					}
				}
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
