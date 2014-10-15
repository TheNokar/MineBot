package net.plommer.MineBot.Commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import net.plommer.MineBot.MineBot;
import net.plommer.MineBot.Extensions.Votes.UserVote;
import net.plommer.MineBot.Extensions.Votes.Votes;
import net.plommer.MineBot.Extensions.Votes.UserVote.Type;
import net.plommer.MineBot.Utils.Utils;

public class VoteCommand extends Commands {

	public VoteCommand() {
		setCmd("vote");
		setPermissions("vote");
	}
	
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(getArgs().length > 2) {
			if(getArgs()[0].equalsIgnoreCase("create") || getArgs()[0].equalsIgnoreCase("c")) {
				if(getArgs()[1] != null && getArgs()[2] != null) {
					long how = Utils.addTimer(getArgs()[1]) + (System.currentTimeMillis()/1000L);
					StringBuilder b = new StringBuilder();
					for(int i = 2; i < getArgs().length; i++) {
						b.append(getArgs()[i] + " ");
					}
					b.setLength(b.length()- 1);
					Votes v = new Votes(b.toString(), getUser(), getChannel(), how);
					DateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm");
					long s = v.getDateExpire();
					Utils.sendMessage("&c{0} &ebjó til spurninguna &c{1}&e. Númer spurningunar er:&c {2}&e. Hún gildir til:&c {3}", new String[] {v.getUser().getName(), v.getVote_info(), ""+v.getVote_id(), fmt.format(new Date(s * 1000L))}, getChannel());
				}
			}
		} else if(getArgs().length > 1) {
			if(getArgs()[0].equalsIgnoreCase("check")) {
				int id = 0;
				try {
					id = Integer.parseInt(getArgs()[1]);
				} catch(NumberFormatException e) {
					System.out.println("Wrong time format");
				}
				Votes v = MineBot.db.getVote(id);
				if(v != null) {
					HashMap<Type, Integer> a = MineBot.db.getVoter(id);
					int yes = 0, no = 0;
					if(a.containsKey(Type.YES)) {
						yes = a.get(Type.YES);
					}
					if(a.containsKey(Type.NO)) {
						no = a.get(Type.NO);
					}
					Utils.sendMessage("&e{1}. &aJá[{2}] &cNei[{3}]", new String[] {id+"", v.getVote_info(), yes+"", no+""}, getChannel());
				}
				return false;
			}
			int id = 0;
			try {
				id = Integer.parseInt(getArgs()[0]);
			} catch(NumberFormatException e) {
				System.out.println("Wrong time format");
			}
			new UserVote(getUser(), getChannel(), id, getArgs()[1]);
		}
		return false;
	}

}
