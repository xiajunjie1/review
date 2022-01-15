package project.chatclient.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.chatclient.service.ChatRevice;
import project.chatclient.service.ChatSend;
import project.chatclient.util.Getconfig;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.Sms;

public class ChatView extends JFrame {
	private String hostname;
	private String friendname;
	private String fname;//传给服务器的参数，为朋友的用户名
	private JTextArea msgArea;
	private JScrollPane jscrollpane;
	
	private JPanel southPanel;
	private JTextField sendMsg;
	private JButton sendBtn;
	private Socket s=null;
	public ChatView(String hostname,String friendname,String fname){
		this.hostname=hostname;
		this.friendname=friendname;
		this.fname=fname;
		
		
		//JPanel centerPanel;
	
	}
	
	public void createFrame(List<Sms> slist){
		//centerPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		String server=Getconfig.getValue("server");
		int port=Integer.parseInt(Getconfig.getValue("port"));
		
		//创建客户端连接
		try {
			this.s=new Socket(server,port);
			Message msg=new Message();
			msg.setMsgType(MessageType.Talk_Connect);//通知服务器记录聊天消息Socket
			//为了唯一标识一个聊天窗口的Socket对象
			msg.setSocketKey(hostname+"-"+fname);
			ObjectOutputStream oos =new ObjectOutputStream(this.s.getOutputStream());
			oos.writeObject(msg);
			
			msgArea=new JTextArea();
			msgArea.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
			//System.out.println("留言内容####："+slist);
			if(slist!=null){
				for(int i=0;i<slist.size();i++){
					msgArea.append(slist.get(i).getContent()+"\n");
				}
			}
			jscrollpane=new JScrollPane(msgArea);
			this.add(jscrollpane,BorderLayout.CENTER);
			
			southPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
			sendMsg=new JTextField(30);
			sendMsg.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
			sendBtn=new JButton("发送");
			sendBtn.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("点击发送按钮！");
					Message msg=new Message();
					msg.setMsgType(MessageType.Talk_Normal);
					msg.setFromUser(hostname);
					msg.setToUser(fname);
					msg.setContent(sendMsg.getText());
					sendMsg.setText("");
					
					//System.out.println(s.isConnected());
					try {
						String server=Getconfig.getValue("server");
						int port=Integer.parseInt(Getconfig.getValue("port"));
						Socket socket=new Socket(server,port);
						ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
						out.writeObject(msg);
						//out.writeObject("\n");
						out.flush();
						socket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//ChatSend chatSend=new ChatSend(s,hostname,friendname);
					//chatSend.Send(sendMsg.getText());
				}
				
			});
			southPanel.add(sendMsg);
			southPanel.add(sendBtn);
			this.add(southPanel,BorderLayout.SOUTH);
			//接收服务器穿过来的消息
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		ChatRevice revicer=new ChatRevice(s,msgArea);
		new Thread(revicer).start();
		this.setTitle(this.hostname+"-和-"+this.friendname+"的聊天窗口");
		this.setBounds(300, 200, 600, 450);
		this.setVisible(true);
		this.setResizable(false);
		
		/*
		 * 当聊天窗口关闭时，通知服务器，移除掉map中的Socket
		 * 
		 * */
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				//向服务器发送请求，移除掉map中的chat Socket
				Message reqMsg=new Message();
				reqMsg.setMsgType(MessageType.Close_Chat);
				reqMsg.setSocketKey(hostname+"-"+fname);
				String server=Getconfig.getValue("server");
				int port=Integer.parseInt(Getconfig.getValue("port"));
				try {
					Socket s=new Socket(server,port);
					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(reqMsg);
					oos.close();
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/*
			 * 关闭监听线程
			 * */
			@Override
			public void windowClosed(WindowEvent e) {
				
				revicer.shutdown();
				
			}
			
			
			
		});
		
		
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getFriendname() {
		return friendname;
	}

	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
}
