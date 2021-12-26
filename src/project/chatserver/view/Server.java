package project.chatserver.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import project.entry.User;

public class Server {
	public static void main(String[] args){
		try {
			System.out.println("服务器开始监听....");
			ServerSocket ss=new ServerSocket(9999);
			while(true){
				handlerServer ls=new handlerServer(ss.accept());
				new Thread(ls).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
}


