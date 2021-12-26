package project.chatclient.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import project.chatclient.util.Getconfig;
import project.entry.Message;

public class RegisterReq {
	private Message msg;
	public RegisterReq(Message msg){
		this.msg=msg;
	}
	
	public Message Register(){
		Message responseMsg=null;
		String server=Getconfig.getValue("server");
		int port=Integer.parseInt(Getconfig.getValue("port"));
		try(Socket client=new Socket(server,port);) {
			
			ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(this.msg);
			ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
			responseMsg=(Message) ois.readObject();
			//client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseMsg;
	}
}
