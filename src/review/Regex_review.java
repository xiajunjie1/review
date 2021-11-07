package review;

/*
 * 正则表达式
 * 	进行字符串的校验匹配工作
 * */
public class Regex_review {
	public static void main(String[] args){
		System.out.println(checkQQid("12345"));
	}
	
	/**
	 * 验证一个字符串是否是合法的QQ号码
	 * 	1、不能以0开头
	 * 	2、纯数字组成
	 * 	3、长度[6,11]
	 * */
	private static boolean checkQQid(String qq){
		
		return qq.matches("[1-9]\\d{5,10}");
	}
}
