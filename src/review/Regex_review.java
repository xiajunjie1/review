package review;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * ������ʽ
 * 	�����ַ�����У��ƥ�乤��
 * 
 * 	Ԫ�ַ���
 * 		^��ƥ��һ���ַ����Ŀ�ͷ,java�п�ʡ��
 * 		$��ƥ��һ���ַ����Ľ�β��java�п�ʡ��
 * 		[]:ƥ��һλ�ַ�����[abc]��ʾ������a��b����c
 * 			[a,z]:��ʾ��λ�ַ���a-z��Χ�ڵ������ַ�
 * 			[^abc]:��ʾ�ǣ�����a,b,c�������ַ���^д�������ڵĿ�ͷ�ű�ʾȡ�ǣ���д���м����ʾһ����ͨ���ַ�
 * 			[^a-z[hk]]:��ʾƥ�䲻��Сд��ĸ���ַ���hk���⡣�������������������hk������ǰ���^������������
 * 			Java������ʽת���ַ�\\---һ��\��javaת�⣬���ڶ�\ת�����ͨ��б�ܣ�Ȼ����ͨб����������ʽ�б�ʾת��
 * 		.��ͨ�������ʾƥ������һλ�ַ�
 * 		+��ǰ���һλ��һ���ַ�������һ�λ���
 * 		?:ǰ���һλ��һ���ַ�������0�λ�1��
 * 		*��ǰ���һλ��һ���ַ��������������
 * 		{n}��ƥ��ǰ���һλ��һ���ַ�������n��
 * 		{n,}:ƥ��ǰ���һλ��һ���ַ����ٳ�����n��
 * 		{n,m}:ƥ��ǰ���һλ��һ���ַ����ٳ�����n�Σ���������m��
 * 
 * 		\d:��ʾ��������[0-9]
 * 		\D:��ʾ���з�����[^0-9]
 * 		\w:��ʾ���е����ַ�[a-zA-Z0-9_]
 * 		\W:��ʾ���еķǵ����ַ�[^a-zA-Z0-9_]
 * 		��������һλƥ�䣬��java�о�Ҫ��һ��\��javaת��
 * 
 * 	Pattern��Matcher��
 * 		���Ǿ���java.util.regex����
 * 		Pattern:��java�У�������ʽ�����塣ʹ��������ʽ���ַ�������У�飬���飬�и�滻����Ҫ�õ�����String�е�matches�ײ�Ҳ������ʵ�ֵ�
 * 		Matcher����java�ж�һ������У��Ľ������
 * 
 * 
 * 
 * ע�⣺������ʽ��Ҫ����ӿո�
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
	 * ��֤һ���ַ����Ƿ��ǺϷ���QQ����
	 * 	1��������0��ͷ
	 * 	2�����������
	 * 	3������[6,11]
	 * */
	private static boolean checkQQid(String qq){
		//ʹ���ַ�����������ʽ����ƥ��
		return qq.matches("[1-9]\\d{5,10}");
		
		
	}
	
	
	private static void basicSyntax(){
		//ƥ����h��ͷ���ⳤ�Ⱥ����ݵ��ַ���
		System.out.println("hEllo".matches("^h.*"));
		//ƥ����o��β���ⳤ�Ⱥ����ݵ��ַ���
		System.out.println("hello".matches(".*o$"));
		//ƥ����ַ�
		System.out.println("".matches("^$"));
		
		//ת���ַ���ʹ��,ת���ַ���^ת����ͨ���ַ�
		System.out.println("hello".matches("\\^h.*"));//ƥ��^h�������ַ����ⳤ��
		
		//+����
		System.out.println("hehehehe".matches("(he)+"));//true
		//?����
		System.out.println("hehehehe".matches("(he)��"));//false
		
		//{n}
		System.out.println("hello".matches("hel{2}o"));//ƥ��l������2��
		
		
	}
	
	private static void example(){
		//��֤һ�������Ƿ���QQ����
		//QQ����=QQ��+qq.com
		System.out.println("953669965@qq.com".matches("[1-9]\\d{5,10}@qq\\.com"));//.��һ��ͨ�����Ҫ��������ת��
		
		//��֤һ���ֻ����Ƿ��ǺϷ����ֻ���
		System.out.println("18062091348".matches("1[356789]\\d{9}"));
		
		//��֤һ���̶��绰����
		//3-4λ����-7Ϊ����
		System.out.println("027-8885414".matches("\\d{3,4}-\\d{7}"));
	}
	
	//������ʽ����2���и�滻������
	private static void example2(){
		//��һ���ַ���������ָ���Ĺ����и�����ɶ�
		String name = "leon  chris dante  ada  lily";
		//���տո��и�
		String regex=" +";//��ʾ�ո����һ�λ���
		String[] names=name.split(regex);
		System.out.println(Arrays.toString(names));
		
		String replace="hello world".replaceAll(regex, "@");//���ո��滻��@
		System.out.println(replace);
		String repFirs=name.replaceFirst(regex, "@");
		System.out.println(repFirs);
		
		//ʵ���ֻ������м���λ�滻��****
		//��Ϸ���ʵ�ָù���--�����ɵ��ַ�����()���������������˷���
		String tarNum="18062091348".replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1****$3");//$1ָ������1��$3ָ������3��$0ָ�������ַ���
		System.out.println(tarNum);
		
		
	}
	
	private static void PatternUsage(){
		//��̬��У��
		boolean res=Pattern.matches("[1-9]\\d{5,10}", "123456");
		System.out.println("У����Ϊ��"+res);
		
		//���ַ�������Ϊһ��������ʽ
		Pattern pattern=Pattern.compile("[1-9]\\d{5,10}");
		
		//ͨ��pattern����������ʽ��д��
		String regx=pattern.pattern();
		System.out.println(regx);
		
		//�ַ������и�
		String[] strs=pattern.split("A111111 BC123456 E");//�÷��������Լӵڶ���int��������ʾ�ֳɼ��Σ����ֺú��������м���Ԫ��
	
		System.out.println(Arrays.toString(strs));
		
	}
	
	
	private static void MathcerUsage(){
		//Matcher�Ƕ�����У���һ������
		//��ȡMatcher����
		Pattern pattern=Pattern.compile("\\d+");
		String data="hello1234world5678";
		Matcher matcher=pattern.matcher(data);
		//��ȡУ���������ַ�����������У�飬��Ȼ�ὫУ��ĸ��������
		boolean result=matcher.matches();
		System.out.println(result);
		boolean result2=matcher.find();//���ַ�����ǰУ���±꿪ʼ��ѯ�Ƿ�������������Ӳ���
		//����ٴε���find�������һ�β��ҵ���λ�ÿ�ʼ�������
		System.out.println(result2+"--ƥ�䵽���ַ��Ӵ���"+matcher.group()+"---���귶Χ��"+matcher.start()+","+matcher.end());//true
		//����Matcher������group��start��end�������Ի�ȡ��ƥ�䵽���Ӵ����ݺͿ�ʼ�������ĸ���
		//Matcher�����У�ά����һ�����꣬find��matches�ȷ������Ǵӵ�ǰ�ĸ��꿪ʼ����ƥ��
		//�������ȥ���ַ����е�hello���ȵ���matches����������ӵ�һλ��ʼƥ�䣬ֱ��w��ֹͣƥ��
		//�ٵ���find��������w����ʼ������ң����Ե�һ��ƥ�䵽���Ӵ�����5678
		
		result2=matcher.find();
		System.out.println(result2);//true
		result2=matcher.find();
		System.out.println(result2);//false
		result2=matcher.find(0);//�ӵ�0λ��ʼ����
		System.out.println(result2);
	
		matcher.lookingAt();//��0λ��ʼ��ѯ�ַ���������true
		
		Pattern pattern2=Pattern.compile("(\\d{3})(\\w{3})(.*)");
		Matcher matcher2=pattern2.matcher("123aaaokdfdskjfsdjk");
		//��ȡ��������
		System.out.println(matcher2.groupCount());
		//��ȡ����ֵ֮ǰ����Ҫ��ƥ��һ��
		System.out.println(matcher2.matches());
		//��ȡ����ֵ
		System.out.println(matcher2.group(1));
		
	}
}
