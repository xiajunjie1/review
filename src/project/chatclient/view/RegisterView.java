package project.chatclient.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.chatclient.service.RegisterReq;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.User;

public class RegisterView extends JFrame {
	
	private JPanel centerPanel=null;
	private JLabel unameLabel=null;//用户名
	private JTextField unameTextField=null;
	private JLabel pwdLabel=null;//密码
	private JPasswordField pwdTextField=null;
	private JLabel checkLabel=null;//密码确认
	private JPasswordField checkTextField=null;
	private JLabel nicknameLabel=null;//昵称
	private JTextField nicknameTextField=null;
	private JLabel photoLabel=null;//头像
	private JTextField photoTextField=null;
	
	private JPanel southPanel=null;
	private JButton confirmBtn=null;
	private JButton resetBtn=null;
	
	/*
	public static void main(String[] args){
		RegisterView rv=new RegisterView();
		rv.createFrame();
	}
	*/
	
	public void createFrame(){
		unameLabel=new JLabel("用户名");
		unameTextField=new JTextField(33);
		pwdLabel=new JLabel("密码");
		pwdTextField=new JPasswordField(33);
		checkLabel=new JLabel("确认密码");
		checkTextField=new JPasswordField(33);
		nicknameLabel=new JLabel("昵称");
		nicknameTextField=new JTextField(33);
		photoLabel=new JLabel("头像");
		photoTextField=new JTextField(33);
		
		centerPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p5=new JPanel();
		p1.add(unameLabel);
		p1.add(unameTextField);
		p2.add(pwdLabel);
		p2.add(pwdTextField);
		p3.add(checkLabel);
		p3.add(checkTextField);
		p4.add(nicknameLabel);
		p4.add(nicknameTextField);
		p5.add(photoLabel);
		p5.add(photoTextField);
		centerPanel.add(p1);
		centerPanel.add(p2);
		centerPanel.add(p3);
		centerPanel.add(p4);
		centerPanel.add(p5);
		this.add(centerPanel,BorderLayout.CENTER);
		
		southPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		confirmBtn=new JButton("确定");
		resetBtn=new JButton("重置");
		southPanel.add(confirmBtn);
		southPanel.add(resetBtn);
		confirmBtn.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String pwd=new String(pwdTextField.getPassword());
				String cpwd=new String(checkTextField.getPassword());
				if(!pwd.equals(cpwd)){
					//两次输入密码不一致
					JOptionPane.showMessageDialog(RegisterView.this, "确认密码与密码不一致","提示",JOptionPane.WARNING_MESSAGE);
				}
				else{
				User u=new User();
				u.setUsername(unameTextField.getText());
				u.setPassword(pwd);
				u.setNickname(nicknameTextField.getText());
				u.setPhoto(photoTextField.getText());
				Message msg=new Message();
				msg.setMsgType(MessageType.Register);
				msg.setUser(u);
				RegisterReq reg=new RegisterReq(msg);
				int msgType=reg.Register().getMsgType();
				switch(msgType){
				case MessageType.Register_Failure:{
					
					JOptionPane.showMessageDialog(RegisterView.this, "注册失败，服务器异常","错误",JOptionPane.ERROR_MESSAGE);
					break;
					}
				case MessageType.Uname_Duplicate:{
					JOptionPane.showMessageDialog(RegisterView.this, "用户名重复","提示",JOptionPane.WARNING_MESSAGE);
					break;}
				case MessageType.Register_Succeess:{
					JOptionPane.showMessageDialog(RegisterView.this, "注册成功","提示",JOptionPane.INFORMATION_MESSAGE);
					RegisterView.this.dispose();
					break;
					}
				default:{
					System.out.println("返回信息异常");
				}
				}
			}
				}
			
		});
		resetBtn.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				RegisterView.this.unameTextField.setText("");
				RegisterView.this.pwdTextField.setText("");
				RegisterView.this.checkTextField.setText("");
				RegisterView.this.nicknameTextField.setText("");
				RegisterView.this.photoTextField.setText("");
			}
			
		});
		this.add(southPanel,BorderLayout.SOUTH);
		
		this.setTitle("chat register");
		this.setBounds(400, 300, 450, 350);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
