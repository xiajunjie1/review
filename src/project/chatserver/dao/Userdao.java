package project.chatserver.dao;

import java.util.List;

import project.entry.Message;
import project.entry.User;

public interface Userdao {
	
	public Message Add(User u);
	public void deleteByid(int id);
	public void deleteByusername(String username);
	public List<User> getUsers();
	public List<User> getUsers(String uname);
	public User getUserbyId(int id);
	public User getUserbyUname(String username);
	public void UpdateUserbyId(int id);
	public void UpdateUserbyUname(String username,String sql);
	public boolean Login(String uname,String pwd)throws Exception;
}
