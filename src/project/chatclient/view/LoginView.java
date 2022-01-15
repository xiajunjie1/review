package project.chatclient.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.chatclient.service.LoginReq;
import project.chatclient.util.Getconfig;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.User;

/**
 * 客户端登录主界面
 * */
public class LoginView extends JFrame {
	//上
	private JPanel northPanel=null;//放在上方的容器
	private JLabel photoLabel=null;//图片组件
	
	//中
	private JPanel centerPanel=null;
	private JLabel usernameLabel=null;
	private JTextField usernameTextField=null;
	private JLabel pwdLabel=null;
	private JPasswordField pwdPasswordField=null;
	
	//下
	private JPanel southPanel=null;
	private JButton loginButton=null;
	private JButton registerButton=null;
	
	public static void main(String[] args){
		LoginView lv=new LoginView();
		lv.createFram();
	}
	
	
	/*
	 * 创建主界面
	 * */
	public void createFram(){
		ImageIcon ic=new ImageIcon(LoginView.class.getClassLoader().getResource("project/chatclient/image/chat.png"));
		ic.setImage(ic.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		photoLabel=new JLabel("",ic,JLabel.CENTER);
		northPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		northPanel.add(photoLabel);
		this.add(northPanel,BorderLayout.NORTH);
		
		centerPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		usernameLabel=new JLabel("账号");
		usernameLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
		usernameTextField=new JTextField(24);//长度为24的文本框
		pwdLabel=new JLabel("密码");
		pwdLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
		pwdPasswordField=new JPasswordField(24);
		centerPanel.add(usernameLabel);
		centerPanel.add(usernameTextField);
		centerPanel.add(pwdLabel);
		centerPanel.add(pwdPasswordField);
		this.add(centerPanel,BorderLayout.CENTER);
		
		
		southPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
		loginButton=new JButton("登录");
		loginButton.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// 监听点击事件
				String server=Getconfig.getValue("server");
				int port=Integer.parseInt(Getconfig.getValue("port"));
				Socket reqSocket;
				try {
					reqSocket = new Socket(server,port);
					User u=new User();
					Message msg=new Message();
					msg.setUser(u);
					msg.setMsgType(MessageType.Login);
					u.setUsername(usernameTextField.getText());
					u.setPassword(new String(pwdPasswordField.getPassword()));
					LoginReq req=new LoginReq(msg);
					if(req.Login(reqSocket)){
						//如果登录验证成功
						msg=new Message();
						msg.setMsgType(MessageType.Get_Userlist);
						msg.setLoginUname(usernameTextField.getText().trim());
						req=new LoginReq(msg);
						Message responseMsg=req.getUsers();
						if(responseMsg!=null && responseMsg.getMsgType()==MessageType.Get_Ulist_Success){
							new FriendslistView(u.getUsername()).CreateFrame(responseMsg.getUlist(),reqSocket);
							LoginView.this.dispose();//关闭窗口
						}else if(responseMsg!=null && responseMsg.getMsgType()==MessageType.Get_Ulist_Failure){
							JOptionPane.showMessageDialog(LoginView.this, responseMsg.getContent(),"错误",JOptionPane.ERROR_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(LoginView.this, "服务器通讯异常","错误",JOptionPane.ERROR_MESSAGE);
						}
						
					}else{
						//弹出警告信息
						JOptionPane.showMessageDialog(LoginView.this, "账号或密码错误","提示",JOptionPane.WARNING_MESSAGE);
					}
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
			
		});//添加鼠标监听事件
		registerButton=new JButton("注册");
		registerButton.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterView().createFrame();
			}
			
		});
		southPanel.add(loginButton);
		southPanel.add(registerButton);
		this.add(southPanel,BorderLayout.SOUTH);
		
		
		
		
		
		
		this.setTitle("Chat Login");
		this.setBounds(300,300,320,270);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
