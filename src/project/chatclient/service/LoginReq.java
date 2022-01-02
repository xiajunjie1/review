package project.chatclient.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import project.chatclient.util.Getconfig;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.User;

/**
 * 进行登录请求的类
 * */
public class LoginReq {
	private Message msg;
	public LoginReq(Message msg){
		this.msg=msg;
	}
	

	
	public boolean Login(Socket s){
		
		try{
			
			OutputStream out=s.getOutputStream();
			ObjectOutputStream oout=new ObjectOutputStream(out);
			oout.writeObject(this.msg);
			InputStream in=s.getInputStream();
			ObjectInputStream ois=new ObjectInputStream(in);
			Message reponsemsg=(Message)ois.readObject();
			if(reponsemsg.getMsgType()==MessageType.Login_Success){
			
				
				return true;
				
			}else{
				
			
				return false;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return false;
	}
	
	public Message getUsers(){
		String server=Getconfig.getValue("server");
		int port=Integer.parseInt(Getconfig.getValue("port"));
		Message responseMsg=null;
		try(Socket s=new Socket(server,port)){
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(this.msg);
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			responseMsg=(Message)ois.readObject();
			oos.close();
			ois.close();
			return responseMsg;
		}catch(Exception e){
			System.out.println("与服务器通讯异常...");
			e.printStackTrace();
		}
		return responseMsg;
	}
}
