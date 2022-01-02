package project.chatserver.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import project.entry.User;

public class Server {
	/**
	 * 如果单纯的用名字来作为key，那么当一个用户在开多个聊天窗口的时候，它的之前的
	 * Socket则会被最新开的窗口给覆盖掉，所以key值应该使用hostname-friendname这样的形式来唯一标记一个聊天窗口
	 * */
	private static Map<String,Socket> sMap=new HashMap<>();
	/**
	 * 用于存储登录成功的用户的Socket
	 * */
	private static Map<String,Socket> LoginMap=new HashMap<>();
	
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
	public static Map<String,Socket> getsMap() {
		return sMap;
	}
	public static void setsMap(Map<String,Socket> sMap) {
		Server.sMap = sMap;
	}
	public static Map<String,Socket> getLoginMap() {
		return LoginMap;
	}
	public static void setLoginMap(Map<String,Socket> loginMap) {
		LoginMap = loginMap;
	}
	

	
}


