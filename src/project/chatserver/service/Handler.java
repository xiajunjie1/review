package project.chatserver.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
}
