package review;

/*
 * StringBuffer和StringBuilder
 * 	它们都是String的操作类，里面维护了一个字符数组的属性
 * 
 * StringBuilder提供的方法和StringBuffer是一样的。
 * 区别：
 * 		StringBuffer是线程安全的
 * 		StringBuilder不是线程的安全的
 * 		即当不考虑多线程操作对象的时候的时候，用StringBuilder效率更高
 * 		当多线程使用对象的时候，就用StringBuffer
 * 
 * 字符串操作效率对比：
 * 		但凡涉及到字符串操作的使用场景，尤其是在循环中对字符串进行操作。一定不要使用字符串方法，用StringBuffer或StringBuilder的方法来做
 * 		由于字符串是不可变的，所以String类的所有的操作，都会开辟一个新内存空间生成新的字符串
 * 		StringBuffer和StringBuilder是在内部维护字符数组，所有的修改都是围绕该数组来的，所以效率更高
 * 
 * */

public class StringUtil_review {
	public static void main(String[] args){
		StringbufferTest();
	}
	
	private static void StringbufferTest(){
		//无参构造，操作一个空字符串
		StringBuffer sb=new StringBuffer();
		//带参构造，操作一个传入的字符串
		StringBuffer sb2=new StringBuffer("test");
		
		//增：拼接字符串
		sb.append("abc");//该方法的返回值也是一个StringBuffer对象
		System.out.println(sb);
		//增：在指定位置插入字符串
		sb.insert(1, "ToT");//在下标1处插入
		System.out.println(sb);
		
		//删
		sb.delete(2, 4);//删除下标从2-4，左闭右开
		System.out.println(sb);
		sb.deleteCharAt(3);//删除下标为3的字符
		System.out.println(sb);
		//改，替换
		sb2.replace(1, 3, "ooo");//修改下标1-3的字符串为"ooo"
		System.out.println(sb2);
		sb2.setCharAt(2, 'I');
		System.out.println(sb2);
		//反转
		System.out.println(sb2.reverse());
	}
}

