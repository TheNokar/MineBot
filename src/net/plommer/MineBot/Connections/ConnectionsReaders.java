package net.plommer.MineBot.Connections;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class ConnectionsReaders {
	
	public static BufferedWriter w = null;
	public static BufferedReader r = null;

	public ConnectionsReaders(BufferedWriter w) {
		ConnectionsReaders.w = w;
	}
	public ConnectionsReaders(BufferedReader r) {
		ConnectionsReaders.r = r;
	}
	public ConnectionsReaders(BufferedWriter w, BufferedReader r) {
		ConnectionsReaders.w = w;
		ConnectionsReaders.r = r;
	}
	
	public static BufferedWriter getWriter() {
		return w;
	}
	
	public static BufferedReader getReader() {
		return r;
	}
	
}
