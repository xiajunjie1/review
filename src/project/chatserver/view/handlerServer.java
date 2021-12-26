package project.chatserver.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import project.chatserver.dao.Userdao;
import project.chatserver.dao.Userdao_imp;
import project.chatserver.service.Handler;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.User;

/**
 * 处理登录请求的线程
 * */
public class handlerServer implements Runnable{
private Socket socket;
	
	public handlerServer(Socket socket){
		this.socket=socket;
	}
	
	@Override
	public void run() {
		try {
			InputStream input=this.socket.getInputStream();
			ObjectInputStream ois=new ObjectInputStream(input);
			Message reqmsg=(Message)ois.readObject();
			User u=reqmsg.getUser();
			Handler handler=new Handler();
			switch(reqmsg.getMsgType())
			{
			case MessageType.Login:{
				//进行登录的判断，并将登录结果包装成一个Message返回给客户端
				Message msg=handler.login(u);
				//未获取到登录是否成功的信息
				if(msg==null)
				{
					msg=new Message();
					msg.setMsgType(MessageType.Login_Failure);
				}
				ObjectOutputStream oos=new ObjectOutputStream(this.socket.getOutputStream());
				oos.writeObject(msg);
				
				break;
				}
			case MessageType.Register:{
				Message msg=handler.register(u);
				if(msg==null){
				
					msg=new Message();
					msg.setMsgType(MessageType.Register_Failure);
				}
				ObjectOutputStream oos=new ObjectOutputStream(this.socket.getOutputStream());
				oos.writeObject(msg);
				break;
				
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				this.socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
