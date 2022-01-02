package project.chatclient.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;

import project.chatclient.view.FriendslistView;
import project.entry.Message;
import project.entry.MessageType;

public class UserRefresh implements Runnable {
	private Socket socket;
	private List<JLabel> jLabels; 
	private FriendslistView fl;
	
	public UserRefresh(Socket socket,List<JLabel> jLabels,FriendslistView fl){
		this.socket=socket;
		this.jLabels=jLabels;
		this.fl=fl;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				ObjectInputStream input=new ObjectInputStream(this.socket.getInputStream());
				Message msg=(Message)input.readObject();
				if(msg.getMsgType()==MessageType.U_online){
					Iterator<JLabel> it=this.jLabels.iterator();
					while(it.hasNext()){
						JLabel label=it.next();
						StringBuffer sb=new StringBuffer(label.getText());
						System.out.println("组件名："+sb);
						System.out.println("登录人名："+msg.getFromUser());
						int start=sb.indexOf("【");
						int end=sb.indexOf("】");
						String uname=sb.subSequence(start+1, end).toString();
						
						if(uname.equals(msg.getFromUser())){
							System.out.println("匹配到登陆者头像...");
							label.setEnabled(true);
							fl.validate();
						}
					}
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

}
