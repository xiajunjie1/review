package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Socket_test {
	public static void main(String[] args){
		try {
			//connServer();
			//EchoClient();
			UdpClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void connServer() throws UnknownHostException, IOException{
		Socket client=new Socket("localhost",9999);
		
		Scanner sc=new Scanner(client.getInputStream());
		sc.useDelimiter("\n");
		while(sc.hasNext()){
			System.out.println("【Hello Client】："+sc.next());
		}
		sc.close();
		client.shutdownInput();
		client.close();
	}
	
	private static void EchoClient() throws UnknownHostException, IOException{
		Socket client=new Socket("localhost",9999);
		Scanner in=new Scanner(client.getInputStream());//接收服务器返回的信息
		in.useDelimiter("\n");
		PrintStream out=new PrintStream(client.getOutputStream());//向服务器发送信息
		boolean flag=true;
		BufferedReader keyborad=new BufferedReader(new InputStreamReader(System.in));
		while(flag){
			String value=keyborad.readLine();//获取键盘上的输入
			
			out.println(value);
			if(in.hasNext()){
				System.out.println("【服务器返回的数据为：】"+in.next());
			}
			if(value.equalsIgnoreCase("exit")){
				flag=false;
				
			}
		}
		in.close();
		out.close();
		keyborad.close();
		client.close();
	}
	
	private static void UdpClient() throws IOException{
		DatagramSocket client=new DatagramSocket(9999);//监听客户端端口
		byte[] b =new byte[1024];//接收数据的数组
		DatagramPacket packet=new DatagramPacket(b,b.length);//创建数据报，接收数据报
		System.out.println("接收服务器发送的数据...");
		client.receive(packet);//等待接收数据
		System.out.println("接收到的数据："+new String(b,0,packet.getLength(),"UTF-8"));
		
		
		
	}
}
