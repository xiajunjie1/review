package project.chatserver.dao;

import java.util.List;

import project.entry.Sms;

public interface Smsdao {
	public boolean insert(Sms sms);
	public List<Sms> getList(String hostname,String friendname);
}
