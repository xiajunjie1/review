package project.chatclient.service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import project.entry.Message;
import project.entry.MessageType;

public class ChatSend {
private Socket s;
private String hostname;
private String friendname;

public ChatSend(Socket s,String hostname,String friendname){
	this.s=s;
	this.hostname=hostname;
	this.friendname=friendname;
	
	
}

public void Send(String content){
	Message reqMsg=new Message();
	reqMsg.setContent(content);
	reqMsg.setFromUser(this.hostname);
	reqMsg.setToUser(this.friendname);
	reqMsg.setMsgType(MessageType.Talk_Normal);
	ObjectOutputStream oos;
	
	try {
		System.out.println("客户端发送："+this.s);
		oos = new ObjectOutputStream(this.s.getOutputStream());
		oos.writeObject(reqMsg);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
}
