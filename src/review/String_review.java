package review;

import java.util.Arrays;

/*
 * �ַ�����ϰ
 * 	1.�ַ���������������͵�ת��
 * 		������������ת�ַ�����
 * 			����+�Ž�������������תΪString
 * 			����String.valueOf()
 * 
 * 	2.�ַ����ڴ����
 * 		�ַ������ڳ������п��ٿռ�ģ��ַ�����������ǳ������е�ĳһ���ַ
 * 		�ַ����������¸�ֵ�Ļ�����ʵ�������޸��˳��������ַ�����ֵ
 * 
 * 	3.�ַ���ʵ���ڴ����
 * 		����new String("xxx")�ĳ�ʼ���ַ�����ʱ�򣬻��ڶ��Ͽ���һ���ڴ棬
 * 		Ȼ����ϵ��ڴ��д洢һ����ַ���õ�ַָ�������ж�Ӧ���ַ�����Ȼ���ڽ����Ͽ��ٵ��ڴ淵�ظ��ַ�������
 * 		������new String�ķ�ʽ����String����ʱ����ַ�ǲ�ͬ�ġ�
 * 
 * 	4.�ַ���ƴ���ڴ����
 * 		��ƴ�ӵ��ַ��������ַ�������ʱ����"hello"+"world"�������ڳ������н���ƴ�ӵ�
 * 		���Խ������"helloworld"����������"hello"+"world"=="helloworld"
 * 		����Ǻ����ַ���������ƴ�ӣ���ô�����ڶ��ڴ��Ͽ���һ��ռ䣬Ȼ��ÿռ����һ���ַ���õ�ַָ��������ƴ�Ӻú���ַ���
 * 		Ȼ���ڽ��ö��ڴ�ĵ�ַ���ظ�String��������ֻҪ�Ǻ����ַ����������ַ�ƴ�ӣ����������µĶ���
 * 
 * 	5.String�Ĺ��췽��
 * 		�޲ι��죺String()
 * 		�ַ������죺String("xxx")
 * 		�ַ����鹹�� String(char[] c)  String(char[] c,offset,length):�ӵ�offsetλ��ʼ��ȥlength�ַ������ַ���ʵ����
 * 		�ֽ����鹷��String(byte[] b)
 * 
 * 
 * 	6.String�ķǾ�̬����
 * 
 * 	7.String�ľ�̬����
 * 		
 * 				
 * */
public class String_review {

	public static void main(String[] args){
		//basicTostring();
		//stringTobasic();
		
		/*Person p=new Person();
		p.name="test";
		Person p2=p;
		p2.name="����";
		System.out.println(p.name);
		System.out.println(p2.name);
		String a="aaa";
		String b=a;
		b="bbb";
		System.out.println(a);
		System.out.println(b);*/
		
		String str1="hello";
		String str2=new String("hello");
		System.out.println(str1==str2);//false
		
		String str3="hello"+"world";
		String str4=str2+"world";
		String str5="helloworld";
		String str6=str2+"world";
		System.out.println(str3==str4);//false
		System.out.println(str3==str5);//true
		System.out.println(str4==str6);//false
		String str7=str1+str2;
		String str8=str1+str2;
		System.out.println(str7==str8);//false
		
		//Constractor();
		//Strmethod();
		Strstaticmethod();
	}
	
	/**
	 * ��������תString
	 * 
	 * */
	private static void basicTostring(){
		//����String��valueOf����
		String str1=String.valueOf(10);
		System.out.println(str1);
		System.out.println(str1.getClass().getName());
		
		//���ð�װ���toString����
		String str2=Integer.toString(10);
		System.out.println(str2);
		System.out.println(str2.getClass().getName());
	}
	
	
	private static void stringTobasic(){
		//1.����valueOf
		int n1=Integer.valueOf("123");
		System.out.println(n1);
		
		//2.����parseXXX
		int n2=Integer.parseInt("456");
		System.out.println(n2);
		
		/*�Ƽ�ʹ�÷���2������1�ײ�����parseInt���ַ���ת�ɻ������ͣ�Ȼ���ٽ���װ�䷵��Integer
		���ڸ�ֵ��ʱ��������Զ����䣬�����ֽ�Integer����������int
		*/
		
		
		//���⣺�ַ���ת�ַ�����Ϊ�ַ����Ƕ���ַ��ļ��ϣ����Բ���ֱ���������������ַ���ת���ַ�
		char c="hello world".charAt(0);
		System.out.println(c);
	}
	
	
	private static void Constractor(){
		String str1=new String(new char[]{'a','b','c','d','e','f'});
		String str2=new String(new char[]{'a','b','c','d','e','f','g','h'},3,3);
		String str3=new String(new byte[]{49,89,90,91,92});
		String str4=new String(new byte[]{49,89,90,91,92},2,2);
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);//1YZ[\�ַ�������Զ�ת��ASCII������ʾ
		System.out.println(str4);
		
	}
	
	/**
	 * �ַ����ķǾ�̬����
	 * */
	private static void Strmethod(){
		//�ַ�����ƴ��
		String str1="adadad".concat("ooo");
		System.out.println(str1);
		//�ַ����Ľ�ȡ
		String str2="ffedddd".substring(2);
		System.out.println(str2);
		String str3="ffedddd".substring(2,4);//����ҿ�
		System.out.println(str3);
		//�ַ����н�ȡ������ֵ��һ���ӿ�
		CharSequence s4="ffedddd".subSequence(2, 4);
		System.out.println(s4);
		//�ַ������滻
		String str5 ="hello world".replace('l', 'L');//�����������ַ�
		System.out.println(str5);
		String str6="HELLO WORLD".replace("HELLO", "CHANGE");//������CharSequence�ӿڶ��󣬿��Դ���String
		System.out.println(str6);
		//��ȡ�ض�λ�õ��ַ�
		char c1="HAHAHHA----FFF".charAt(10);
		System.out.println(c1);
		//���ַ���ת���ַ�����
		char[] chs="Go To Hell".toCharArray();
		System.out.println(Arrays.toString(chs));
		//���ַ���ת�ֽ�����
		byte[] bytes="Test Test".getBytes();//�������ָ���ַ�������ͬ���ַ������ַ���Ӧ�����ֿ��ܲ�ͬ
		System.out.println(Arrays.toString(bytes));
		
		//��ѯĳһ���ַ���һ�����ַ����г��ֵ��±�
		int index="Hello,World".indexOf('l');
		System.out.println(index);
		//��ѯĳһ���ַ����һ�����ַ����г��ֵ��±�
		int lindex="Hello,World".lastIndexOf('l');
		System.out.println(lindex);
		//���ַ���ָ��λ�ÿ�ʼ����һ���ַ����ֵ��±�
		int index2="Hello,World".indexOf('l',5);
		System.out.println(index2);
		//���ַ���ָ��λ�ÿ�ʼ�����һ���ַ����ֵ��±�
		//lastIndexOfԭ���Ӻ���ǰ��ѯ���������ĵ�һ���ַ�����
		//���ϵ����ֺ󣬴����5��ʼ��ǰ��ѯ
		int index3="Hello,World".lastIndexOf('l', 5);
		System.out.println(index3);
		//Сдת��д
		String str7="hello world".toUpperCase();
		System.out.println(str7);
		//��дתСд
		String str8=str7.toLowerCase();
		System.out.println(str8);
		//��ȡ�ַ����ĳ���
		int len="Hello World!".length();
		System.out.println(len);
		
		//����
		System.out.println("Hello World".contains("ll"));
		//��...��ͷ
		System.out.println("Hello World".startsWith("He"));
		//��...��β
		System.out.println("Hello World".endsWith("ld"));
		//ȥ��һ���ַ�������β�ո�
		System.out.println("    Hello World    ".trim());
		
		//�ַ����Ƚϴ�С
		//���αȽ�ÿһλ�ַ��Ĵ�С�����ĳһ���ַ���С�ȽϿ��Էֳ���С�����Ըôδ�С����
		//����ֵΪ���֣�����0����ǰ����ַ������ں�����ַ�����С��0����ǰ����ַ���С�ں�����ַ���������0���������
		System.out.println("Hello".compareTo("World"));
		
	
	}
	
	private static void Strstaticmethod(){
		//��һ������Ϊ�ָ���������������Ҫƴ�ӵ��ַ�����ÿ���ַ���֮ǰ�÷ָ����ָ�
		String join=String.join("-", "wo","cao","ni","ma","!");
		System.out.println(join);
		String[] param={"abc","bef","ghl"};
		String join2=String.join(",", param);
		System.out.println(join2);
		//�ַ�����ʽ�� %s--�ַ���  %d---����  %f---������   %c---�ַ�  
		//%ns������nλ�ַ���������������ո�	%nd������nλ���֣��������ո�	%.nf������nλС��
		String name="xiaoming";
		int age=18;
		String gender="male";
		float weight=50.1f;
		String format=String.format("name:%s,age:%d,gender:%s,weight:%.2f", name,age,gender,weight);
		System.out.println(format);
	}

}

