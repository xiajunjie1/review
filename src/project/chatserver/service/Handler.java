package project.chatserver.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import project.chatserver.dao.Userdao;
import project.chatserver.dao.Userdao_imp;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.User;

/**
 * 进行服务器端的各种处理
 * */
public class Handler {

	/*
	 * 处理登录请求
	 * */
	public Message login(User u){
		try {
			
			Userdao userdao=new Userdao_imp();
			System.out.println(u.getUsername()+"::"+u.getPassword());
			boolean isLogin=userdao.Login(u.getUsername(), u.getPassword());
			Message msg=new Message();
			if(isLogin){
				msg.setMsgType(MessageType.Login_Success);
				System.out.println("登录成功！");
			
				return msg;
			}else{
				msg.setMsgType(MessageType.Login_Failure);
				System.out.println("用户名或密码错误!");
				return msg;
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * 处理注册请求
	 * */
	public Message register(User u){
		Userdao userdao=new Userdao_imp();
		Message responseMsg=userdao.Add(u);
		return responseMsg;
	}
	
	public Message getUserlist(){
		Userdao userdao=new Userdao_imp();
		List<User> ulist=userdao.getUsers();
		Message msg;
		if(ulist!=null&&ulist.size()>0){
			msg=new Message();
			msg.setUlist(ulist);
			msg.setMsgType(MessageType.Get_Ulist_Success);
			msg.setContent("用户列表获取成功");
			return msg;
			
		}else{
			msg=new Message();
			msg.setMsgType(MessageType.Get_Ulist_Failure);
			msg.setContent("用户列表获取失败");
			return msg;
		}
		
	}
	
	public void send(Message msg,Socket s){
		try {
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(msg);
			System.out.println(msg.getContent());
			s.getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
