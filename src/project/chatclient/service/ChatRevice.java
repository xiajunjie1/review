package project.chatclient.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JTextArea;

import project.entry.Message;

/**
 * 该线程用来接收消息
 * */
public class ChatRevice implements Runnable {

	private Socket socket;
	private JTextArea msgArea;
	private boolean isrun=true;
	public ChatRevice(Socket socket,JTextArea msgArea){
		this.socket=socket;
		this.msgArea=msgArea;
	}
	
	public void shutdown(){
		this.isrun=false;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			InputStream input=null;
			int count=0;
			System.out.println("等待读取...");
			ObjectInputStream ois;
			while(isrun){
				
				ois=new ObjectInputStream(this.socket.getInputStream());
				Message msg=(Message)ois.readObject();
				System.out.println(msg.getContent());
				msgArea.append(msg.getContent()+"\n");
				}
				
			
			
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
