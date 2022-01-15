package project.chatclient.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import project.chatclient.service.LeaveReq;
import project.chatclient.service.UserRefresh;
import project.chatclient.util.Getconfig;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.Sms;
import project.entry.User;

/**
 * 好友列表框
 * */
public class FriendslistView extends JFrame {
	private String title;
	public FriendslistView(String title){
		this.title=title;
	}
	public void CreateFrame(List<User> ulist,Socket revSocket ){
		JPanel panel=new JPanel(new GridLayout(10,1,20,20));
		List<JLabel> labels=new ArrayList<>();
		if(revSocket==null){
			System.out.println("没有登陆用户登录Socket");
		}
		for(int i=0;i<ulist.size();i++){
			User u=ulist.get(i);
			if(u.getUsername().equals(this.title)){
				continue;
			}
			ImageIcon icon=new ImageIcon(FriendslistView.class.getClassLoader().getResource(u.getPhoto()));
			icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			String name=u.getNickname()+"【"+u.getUsername()+"】";
			JLabel label=new JLabel(name,icon,JLabel.LEFT);
			Color color=label.getBackground();
			label.setOpaque(true);
			label.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(e.getClickCount()==2){
						//双击
						//请求留言信息
						LeaveReq lq=new LeaveReq();
						List<Sms> slist=lq.getLevaes(u.getUsername(), FriendslistView.this.title);
						((JLabel)e.getSource()).setForeground(Color.BLACK);
						((JLabel)e.getSource()).setText(name);
						new ChatView(FriendslistView.this.title,((JLabel)e.getSource()).getText(),u.getUsername()).createFrame(slist);
					}
				
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					label.setBackground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					label.setBackground(color);
				}


			
				
			});
			if(u.getIsonline()==0){
				//不在线
				label.setEnabled(false);
			}
			if(u.getHasleave()==1){
				//有给登录用户的留言
				label.setText(name+"--(有留言)");
			}
			labels.add(label);
			panel.add(label);
		}
		JScrollPane jsp=new JScrollPane(panel);
		this.add(jsp);
		this.setBounds(600, 300, 350, 500);
		this.setTitle(this.title+"的好友列表");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//开启监听线程，为了接收登录、下线消息，及时更新
		UserRefresh ur=new UserRefresh(revSocket,labels,FriendslistView.this);
		new Thread(ur).start();
		this.addWindowListener(new WindowAdapter(){
			
			@Override
			public void windowClosing(WindowEvent e) {
			Message offmsg=new Message();
			offmsg.setMsgType(MessageType.U_offline);
			offmsg.setFromUser(FriendslistView.this.title);
			String server=Getconfig.getValue("server");
			int port=Integer.parseInt(Getconfig.getValue("port"));
			try {
				Socket socket=new Socket(server,port);
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(offmsg);
				oos.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// 关闭监听线程
				ur.shutdown();
			}
			
			
			
		});

	}
	
	
}
