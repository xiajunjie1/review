package project.chatclient.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取properties配置文件的属性值
 * 
 * */
public class Getconfig {
	/*
	public static void main(String[] args){
		Getconfig gc=new Getconfig();
		System.out.println(gc.getValue("server"));
	}
*/
	public static String getValue(String key){
		Properties prop=new Properties();
		InputStream input=Getconfig.class.getClassLoader().getResourceAsStream("info.properties");
		String result=null;
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result=prop.getProperty(key);
		return result;
	}
}
