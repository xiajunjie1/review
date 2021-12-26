package project.chatclient.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import project.chatclient.service.LoginReq;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.User;

public class Test {
public static void main(String[] args){
	User u=new User();
	u.setUsername("xiaoming");
	u.setNickname("小明");
	u.setPassword("111");
	u.setPhoto("project/chatclient/image/icon.png");
	Message msg=new Message();
	msg.setMsgType(MessageType.Register);
	msg.setUser(u);
	//LoginReq login =new LoginReq(u);
	//System.out.println(login.Login());
	try {
		Socket s=new Socket("localhost",9999);
		ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
		//代码1和代码2不可交换位置，否则会引起死锁
	/*1*/oos.writeObject(msg);
	/*2*/ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
		Message responseMsg=(Message)ois.readObject();
		s.close();
		System.out.println("【信息类型】："+responseMsg.getMsgType());
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
