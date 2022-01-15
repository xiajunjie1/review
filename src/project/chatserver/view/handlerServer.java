package project.chatserver.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import project.chatserver.dao.Userdao;
import project.chatserver.dao.Userdao_imp;
import project.chatserver.service.Handler;
import project.chatserver.service.ServerSend;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.Sms;
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
			String socketKey=reqmsg.getSocketKey();
			
			Handler handler=new Handler();
			System.out.println("请求参数："+reqmsg.getMsgType());
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
				//oos.close();//会造成Socket被close
				if(msg.getMsgType()==MessageType.Login_Success){
		
				
				//通知所有在线用户自己在线
				Map<String,Socket> loginMaps=Server.getLoginMap();
				if(loginMaps.size()>0){
				Message responseMsg=new Message();
				responseMsg.setMsgType(MessageType.U_online);
				responseMsg.setFromUser(u.getUsername());
				loginMaps.forEach((k,v)->{
					try {
						
						ObjectOutputStream out=new ObjectOutputStream(v.getOutputStream());
						out.writeObject(responseMsg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				}
				
				//将请求登录的线程放入到map当中
				Server.getLoginMap().put(u.getUsername(), this.socket);
				System.out.println(Server.getLoginMap());	
				}
				
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
				oos.close();
				break;
				
			}
			
			case MessageType.Get_Userlist:{
				Message msg=handler.getUserlist(reqmsg.getLoginUname());
				if(msg==null){
					msg=new Message();
					msg.setMsgType(MessageType.Get_Ulist_Failure);
					msg.setContent("服务器内部出错");
					
				}
				ObjectOutputStream oos=new ObjectOutputStream(this.socket.getOutputStream());
				oos.writeObject(msg);
				oos.close();
				break;
			}
			
			case MessageType.Talk_Connect:{
				Server.getsMap().put(socketKey, this.socket);
				
				System.out.println(Server.getsMap());
				break;
				
			}
			
			case MessageType.Close_Chat:{
				Server.getsMap().remove(socketKey);
				break;
			}
			
			case MessageType.Talk_Normal:{
				System.out.println("进入聊天处理");
				String hostname=reqmsg.getFromUser();
				String friendname=reqmsg.getToUser();
				System.out.println("Test:xxx--"+friendname);
				
				Socket from=Server.getsMap().get(hostname+"-"+friendname);
				Socket to=Server.getsMap().get(friendname+"-"+hostname);
				System.out.println("聊天："+from);
				System.out.println("聊天："+to);
				if(from!=null){
					System.out.println("得到发送者socket");
					Message RepMsg=new Message();
					
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
					
					StringBuffer bf=new StringBuffer(sdf.format(new Date()));
					bf.append("\n");
					bf.append(reqmsg.getFromUser());
					bf.append("对");
					bf.append(reqmsg.getToUser());
					bf.append("说：");
					bf.append(reqmsg.getContent());
					RepMsg.setContent(bf.toString());
					RepMsg.setFromUser(reqmsg.getFromUser());
					RepMsg.setToUser(reqmsg.getToUser());
					new Thread(new ServerSend(from,RepMsg)).start();;
				}
				if(to!=null){
					//接收消息的用户，打开了聊天窗口，直接发送消息到聊天窗口
					System.out.println("得到接收者线程");
					Message RepMsg=new Message();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
					
					StringBuffer bf=new StringBuffer(sdf.format(new Date()));
					bf.append("\n");
					bf.append(reqmsg.getFromUser());
					bf.append("对");
					bf.append(reqmsg.getToUser());
					bf.append("说：");
					bf.append(reqmsg.getContent());
					RepMsg.setContent(bf.toString());
					//RepMsg.setContent(reqmsg.getContent());
					RepMsg.setFromUser(reqmsg.getFromUser());
					RepMsg.setToUser(reqmsg.getToUser());
					new Thread(new ServerSend(to,RepMsg)).start();
				}else {
					//接收消息的用户未打开聊天窗口，获取登录的Socket，判断它是否登录
					Socket s=Server.getLoginMap().get(reqmsg.getToUser());
					if(s!=null){
						//接收消息的用户登录了，发送一个消息提示
						ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
						Message msg=new Message();
						msg.setMsgType(MessageType.Have_Leave);
						msg.setFromUser(reqmsg.getFromUser());
						oos.writeObject(msg);
					}
					//用户未打开聊天窗口，所以将消息存放到数据库中
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
					StringBuffer bf=new StringBuffer(sdf.format(new Date()));
					bf.append("\n");
					bf.append(reqmsg.getFromUser());
					bf.append("对");
					bf.append(reqmsg.getToUser());
					bf.append("说：");
					bf.append(reqmsg.getContent());
					Sms sms=new Sms();
					sms.setContent(bf.toString());
					sms.setHostname(reqmsg.getFromUser());
					sms.setFriendname(reqmsg.getToUser());
					if(handler.saveSms(sms)){
						System.out.println("消息存储成功");
					}else{
						System.out.println("消息存储失败");
					}
				}
				break;
			}
			case MessageType.U_offline:{
				String hostname=reqmsg.getFromUser();
			
				//移除掉登录线程
				Server.getLoginMap().remove(hostname);
				//获取到登录的Socket
				Map<String,Socket> logins=Server.getLoginMap();
				Message infomsg=reqmsg;
				//修改数据库中该用户的状态
				handler.offline(hostname);
				logins.forEach((k,v)->{
					ObjectOutputStream out;
					try {
						out = new ObjectOutputStream(v.getOutputStream());
						out.writeObject(infomsg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				});
				break;
			}
			
			case MessageType.Get_Leave:{
				//请求留言
				String from=reqmsg.getFromUser();
				String to=reqmsg.getToUser();
				List<Sms> slist=handler.getSms(from, to);
				System.out.println("留言信息:####"+slist);
				Message repMsg=new Message();
				repMsg.setSlist(slist);
				ObjectOutputStream out=new ObjectOutputStream(this.socket.getOutputStream());
				out.writeObject(repMsg);
				out.close();
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
