package net.plommer.MineBot.Datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import net.plommer.MineBot.MineBot;
import net.plommer.MineBot.Configs.Config;
import net.plommer.MineBot.Extensions.Votes.UserVote.Type;
import net.plommer.MineBot.Extensions.Votes.Votes;
import net.plommer.MineBot.Users.User;

public class Database {
	public Database() {
		try {
			this.db().prepareStatement(this.getSQLTables("vote.sql")).execute();
			this.db().prepareStatement(this.getSQLTables("voteusers.sql")).execute();
			this.db().prepareStatement(this.getSQLTables("permissions.sql")).execute();
			this.db().prepareStatement("SET GLOBAL max_connections=12000000;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection db() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/minecraft?autoReconnect=true&user="+Config.mysql_username+"&password="+Config.mysql_password);
		} catch (SQLException ex) {
			System.out.println("Failed to connect to mysql. Plugin is now shutting down!");
		}
		return null;
	}
	
	public int addVotes(Votes v) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("INSERT INTO `bot_votes` (vote, creator, channel, date_executed, date_expire) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, v.getVote_info());
			ps.setString(2, v.getUser().getName());
			ps.setString(3, v.getChannel());
			ps.setString(4, v.getDateExecuted().toString());
			ps.setString(5, v.getDateExpire().toString());
			ps.executeUpdate();
			PreparedStatement rs = db().prepareStatement("SELECT id FROM `bot_votes` order by id desc limit 1");
			rs.execute();
			ResultSet rs1 = rs.getResultSet();
			while(rs1.next()) {
				return rs1.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public boolean addVoteUser(int voteid, User user, String channel, Type type) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("INSERT INTO `bot_votes_user` (vote_id, username, channel, type) VALUES (?, ?, ?, ?)");
			ps.setInt(1, voteid);
			ps.setString(2, user.getName());
			ps.setString(3, channel);
			ps.setString(4, type.name());
			return ps.executeUpdate() != 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Votes getVote(int voteid) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("SELECT * from `bot_votes` WHERE id = (?)");
			ps.setInt(1, voteid);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				return new Votes(rs.getString(2), new User(rs.getString(3)), rs.getString(4), rs.getLong(5), rs.getLong(6), rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public HashMap<Type, Integer> getVoter(int voteid) {
		PreparedStatement ps = null;
		HashMap<Type, Integer> a = new HashMap<Type, Integer>();
		try {
			ps = this.db().prepareStatement("SELECT COUNT(*), type from `bot_votes_user` WHERE vote_id = (?) group by type");
			ps.setInt(1, voteid);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				System.out.print(rs.getInt(1));
				if(rs != null) {
					a.put(Type.valueOf(rs.getString(2)), rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}
	
	public boolean hasVoted(int voteid, User user, String channel) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("SELECT COUNT(*) from `bot_votes_user` WHERE username = (?) && vote_id = (?)");
			ps.setString(1, user.getName());
			ps.setInt(2, voteid);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				if(rs.getInt(1) > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean hasPermissions(User user, String permissions) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("SELECT count(*) FROM `bot_permissions` where username = (?) && permission = (?)");
			ps.setString(1, user.getName());
			ps.setString(2, permissions);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				if(rs.getInt(1) > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean addPermissions(String user, String permissions) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("INSERT INTO `bot_permissions` (username, permission) VALUES (?, ?)");
			ps.setString(1, user);
			ps.setString(2, permissions);
			return ps.executeUpdate() != 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private String getSQLTables(String table) {
		Scanner scan = new Scanner(MineBot.class.getResourceAsStream("sql/" + table));
		StringBuilder builder = new StringBuilder();
		while(scan.hasNextLine()) {
			builder.append(scan.nextLine());
		}
		scan.close();
		return builder.toString();
	}
	
}
