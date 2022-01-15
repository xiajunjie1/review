package project.chatclient.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import project.chatclient.util.Getconfig;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.Sms;

public class LeaveReq {
	/**
	 * 获取留言
	 * */
	public List<Sms> getLevaes(String From,String To){
		List<Sms> slist=null;
		Message reqMsg=new Message();
		reqMsg.setMsgType(MessageType.Get_Leave);
		reqMsg.setFromUser(From);
		reqMsg.setToUser(To);
		String server=Getconfig.getValue("server");
		int port=Integer.parseInt(Getconfig.getValue("port"));
		try {
			Socket socket=new Socket(server,port);
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(reqMsg);
			ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			Message repMsg=(Message)ois.readObject();
			slist=repMsg.getSlist();
			ois.close();
			oos.close();
			socket.close();
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return slist;
	}
}
