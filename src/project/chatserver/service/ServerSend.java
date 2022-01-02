package project.chatserver.service;

import java.net.Socket;

import project.entry.Message;

public class ServerSend implements Runnable {

	private Socket s;
	private Message msg;
	public ServerSend(Socket s,Message msg){
		this.s=s;
		this.msg=msg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Handler handler=new Handler();
		handler.send(this.msg, this.s);

	}

}
