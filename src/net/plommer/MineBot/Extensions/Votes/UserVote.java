package net.plommer.MineBot.Extensions.Votes;

import java.util.HashMap;

import net.plommer.MineBot.MineBot;
import net.plommer.MineBot.Users.User;
import net.plommer.MineBot.Utils.Utils;

public class UserVote {

	public UserVote(User u, String channel, int id, String t) {
		Type type = null;
		if(t.equalsIgnoreCase("yes") || t.equalsIgnoreCase("já") || t.equalsIgnoreCase("j") || t.equalsIgnoreCase("y")) {
			t = "já";
			type = Type.YES;
		} else if(t.equalsIgnoreCase("nei") || t.equalsIgnoreCase("no") || t.equalsIgnoreCase("n")) {
			t = "nei";
			type = Type.NO;
		}
		if(MineBot.db.hasVoted(id, u, channel) == false) {
			Votes v = MineBot.db.getVote(id);
			if(type != null && v != null) {
				if(v.getDateExpire() * 1000L > System.currentTimeMillis()) {
					System.out.println(v.getDateExecuted() + ":" + v.getDateExpire());
					MineBot.db.addVoteUser(id, u, channel, type);
					HashMap<Type, Integer> a = MineBot.db.getVoter(id);
					int yes = 0, no = 0;
					if(a.containsKey(Type.YES)) {
						yes = a.get(Type.YES);
					}
					if(a.containsKey(Type.NO)) {
						no = a.get(Type.NO);
					}
					Utils.sendMessage("&c{0} &esvaraði spurninguni&c {2}&e með&c {1}&e. &aJá[{3}] &cNei[{4}]", new String[] {u.getName(), t, v.getVote_info(), yes+"", no+""}, channel);
				} else {
					Utils.sendMessage("&4Þessi könnun er útrunnin...", channel);
				}
			} else {
				Utils.sendMessage("&2Hmmm", channel);
			}
		} else {
			Utils.sendMessage("&4Þú ert nú þegar búin að taka þátt í þessari könnun!", channel);
		}
	}
	
	public static enum Type {
		YES,
		NO
	}
	
}
