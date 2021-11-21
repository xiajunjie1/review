package review;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 正则表达式
 * 	进行字符串的校验匹配工作
 * 
 * 	元字符：
 * 		^：匹配一个字符串的开头,java中可省略
 * 		$：匹配一个字符串的结尾，java中可省略
 * 		[]:匹配一位字符，如[abc]表示可以是a、b或者c
 * 			[a,z]:表示该位字符是a-z范围内的任意字符
 * 			[^abc]:表示非，除了a,b,c的任意字符，^写在括号内的开头才表示取非，若写在中间则表示一个普通的字符
 * 			[^a-z[hk]]:表示匹配不是小写字母的字符，hk除外。必须得用中括号括起来hk，否则前面的^会作用于整体
 * 			Java正则表达式转义字符\\---一个\是java转意，将第二\转义成普通的斜杠，然后普通斜杠在正则表达式中表示转义
 * 		.：通配符，表示匹配任意一位字符
 * 		+：前面的一位或一组字符出现了一次或多次
 * 		?:前面的一位或一组字符出现了0次或1次
 * 		*：前面的一位或一组字符出现了任意次数
 * 		{n}：匹配前面的一位或一组字符出现了n次
 * 		{n,}:匹配前面的一位或一组字符至少出现了n次
 * 		{n,m}:匹配前面的一位或一组字符至少出现了n次，最多出现了m次
 * 
 * 		\d:表示所有数字[0-9]
 * 		\D:表示所有非数字[^0-9]
 * 		\w:表示所有单词字符[a-zA-Z0-9_]
 * 		\W:表示所有的非单词字符[^a-zA-Z0-9_]
 * 		上面四种一位匹配，在java中均要加一个\做java转义
 * 
 * 	Pattern和Matcher类
 * 		它们均在java.util.regex包中
 * 		Pattern:在java中，正则表达式的载体。使用正则表达式对字符串进行校验，分组，切割，替换都需要用到它，String中的matches底层也是用它实现的
 * 		Matcher：在java中对一个正则校验的结果描述
 * 
 * 
 * 
 * 注意：正则表达式不要随意加空格
 * */
public class Regex_review {
	public static void main(String[] args){
		//System.out.println(checkQQid("12345"));
		//basicSyntax();
		//example2();
		//PatternUsage();
		MathcerUsage();
	}
	
	/**
	 * 验证一个字符串是否是合法的QQ号码
	 * 	1、不能以0开头
	 * 	2、纯数字组成
	 * 	3、长度[6,11]
	 * */
	private static boolean checkQQid(String qq){
		//使用字符串和正则表达式进行匹配
		return qq.matches("[1-9]\\d{5,10}");
		
		
	}
	
	
	private static void basicSyntax(){
		//匹配以h开头任意长度和内容的字符串
		System.out.println("hEllo".matches("^h.*"));
		//匹配以o结尾任意长度和内容的字符串
		System.out.println("hello".matches(".*o$"));
		//匹配空字符
		System.out.println("".matches("^$"));
		
		//转义字符的使用,转义字符将^转成普通的字符
		System.out.println("hello".matches("\\^h.*"));//匹配^h后任意字符任意长度
		
		//+符号
		System.out.println("hehehehe".matches("(he)+"));//true
		//?符号
		System.out.println("hehehehe".matches("(he)？"));//false
		
		//{n}
		System.out.println("hello".matches("hel{2}o"));//匹配l出现了2次
		
		
	}
	
	private static void example(){
		//验证一个邮箱是否是QQ邮箱
		//QQ邮箱=QQ号+qq.com
		System.out.println("953669965@qq.com".matches("[1-9]\\d{5,10}@qq\\.com"));//.是一个通配符，要对它进行转义
		
		//验证一个手机号是否是合法的手机号
		System.out.println("18062091348".matches("1[356789]\\d{9}"));
		
		//验证一个固定电话号码
		//3-4位数字-7为数字
		System.out.println("027-8885414".matches("\\d{3,4}-\\d{7}"));
	}
	
	//正则表达式案例2，切割、替换、分组
	private static void example2(){
		//将一个字符串，按照指定的规则，切割成若干段
		String name = "leon  chris dante  ada  lily";
		//按照空格切割
		String regex=" +";//表示空格出现一次或多次
		String[] names=name.split(regex);
		System.out.println(Arrays.toString(names));
		
		String replace="hello world".replaceAll(regex, "@");//将空格替换成@
		System.out.println(replace);
		String repFirs=name.replaceFirst(regex, "@");
		System.out.println(repFirs);
		
		//实现手机号码中间四位替换成****
		//结合分组实现该功能--将若干的字符，用()括起来，即构成了分组
		String tarNum="18062091348".replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1****$3");//$1指代分组1，$3指代分组3，$0指代整个字符串
		System.out.println(tarNum);
		
		
	}
	
	private static void PatternUsage(){
		//静态的校验
		boolean res=Pattern.matches("[1-9]\\d{5,10}", "123456");
		System.out.println("校验结果为："+res);
		
		//将字符串编译为一个正则表达式
		Pattern pattern=Pattern.compile("[1-9]\\d{5,10}");
		
		//通过pattern返回正则表达式的写法
		String regx=pattern.pattern();
		System.out.println(regx);
		
		//字符串的切割
		String[] strs=pattern.split("A111111 BC123456 E");//该方法还可以加第二个int参数。表示分成几段，即分好后数组中有几个元素
	
		System.out.println(Arrays.toString(strs));
		
	}
	
	
	private static void MathcerUsage(){
		//Matcher是对正则校验的一个描述
		//获取Matcher对象
		Pattern pattern=Pattern.compile("\\d+");
		String data="hello1234world5678";
		Matcher matcher=pattern.matcher(data);
		//获取校验结果，对字符串进行整体校验，依然会将校验的浮标向后移
		boolean result=matcher.matches();
		System.out.println(result);
		boolean result2=matcher.find();//从字符串当前校验下标开始查询是否有满足正则的子部分
		//如果再次调用find，会从上一次查找到的位置开始往后查找
		System.out.println(result2+"--匹配到的字符子串："+matcher.group()+"---浮标范围："+matcher.start()+","+matcher.end());//true
		//利用Matcher对象中group、start、end方法可以获取到匹配到的子串内容和开始，结束的浮标
		//Matcher对象当中，维护着一个浮标，find、matches等方法都是从当前的浮标开始往后匹配
		//所以如果去掉字符串中的hello，先调用matches方法，它会从第一位开始匹配，直到w处停止匹配
		//再调用find，浮标会从w处开始往后查找，所以第一个匹配到的子串会是5678
		
		result2=matcher.find();
		System.out.println(result2);//true
		result2=matcher.find();
		System.out.println(result2);//false
		result2=matcher.find(0);//从第0位开始查找
		System.out.println(result2);
	
		matcher.lookingAt();//从0位开始查询字符串，返回true
		
		Pattern pattern2=Pattern.compile("(\\d{3})(\\w{3})(.*)");
		Matcher matcher2=pattern2.matcher("123aaaokdfdskjfsdjk");
		//获取分组数量
		System.out.println(matcher2.groupCount());
		//获取分组值之前，需要先匹配一次
		System.out.println(matcher2.matches());
		//获取分组值
		System.out.println(matcher2.group(1));
		
	}
}
