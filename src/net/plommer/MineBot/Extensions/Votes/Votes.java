package net.plommer.MineBot.Extensions.Votes;

import net.plommer.MineBot.MineBot;
import net.plommer.MineBot.Users.User;

public class Votes {

	private String vote_info;
	private int vote_id;
	private User user;
	private long dateExpire;
	private long dateExecuted;
	private String channel;
	
	public Votes(String vote_info, User user, String channel, long date_expire) {
		this.setVote_info(vote_info);
		this.setUser(user);
		this.setChannel(channel);
		this.setDateExecuted(System.currentTimeMillis()/1000L);
		this.setDateExpire(date_expire);
		this.setVote_id(MineBot.db.addVotes(this));
	}
	
	public Votes(String vote_info, User user, String channel, long date_executed, long date_expire, int id) {
		this.setVote_info(vote_info);
		this.setUser(user);
		this.setChannel(channel);
		this.setDateExecuted(date_executed);
		this.setDateExpire(date_expire);
		this.setVote_id(id);
	}
	
	public String getVote_info() {
		return vote_info;
	}
	
	public void setVote_info(String vote_info) {
		this.vote_info = vote_info;
	}
	
	public int getVote_id() {
		return vote_id;
	}
	
	public void setVote_id(int vote_id) {
		this.vote_id = vote_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getDateExpire() {
		return dateExpire;
	}

	public void setDateExpire(Long dateExpire) {
		this.dateExpire = dateExpire;
	}

	public Long getDateExecuted() {
		return dateExecuted;
	}

	public void setDateExecuted(Long dateExecuted) {
		this.dateExecuted = dateExecuted;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
