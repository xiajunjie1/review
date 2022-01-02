package project.chatclient.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import project.chatclient.service.UserRefresh;
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
			System.out.println("this is test");
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
						new ChatView(FriendslistView.this.title,((JLabel)e.getSource()).getText(),u.getUsername()).createFrame();;
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
	}
	
	
}
