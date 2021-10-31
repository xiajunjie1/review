package review;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * �쳣��ϰ
 * 	�쳣�ǶԳ��������й��������ֲ��������������������������δ��������쳣���ᵼ����������޷����б��������
 * 
 * 	��java�У���Throwable�����ࣩ�����������в������������������������
 * 		Error�����󣩣�������jvm���������Ĵ�����Щ�����޷�������
 * 		Exception���쳣���������������������쳣���ǿ��Ա��������
 * 			IOException
 * 			RuntimeException
 * 
 * 		��RuntimeException�쳣�����޷�ͨ������ġ�
 * 
 * 	�쳣�Ĳ���
 * 		try{...}catch(�쳣���� e){...}
 * 
 * 	finally�־䣬���Ը���try-catch���棬�����Ƿ���֡������쳣������ִ��finally�еĴ���
 * 	����finally���ض���һ�����finally�н�����Դ���ͷţ���ر�IO����
 * 
 * 
 * 	throw
 * 		һ���쳣���󣬱�ʵ������ɺ�û���κ����塣����Ӱ�쵽����ı��������
 * 		�����Ҫһ���쳣��Ч����Ҫʹ�ùؼ���throw�����쳣�׳�
 * 
 * 	throws
 * 		д�ڷ��������е���public void func()throws{...}
 * 		��;1�����ߵ��÷������ñ�������ʱ��������Щ�쳣
 * 		��;2����������ʹ�õ�ʱ�򣬲��ᴦ��ǰ�쳣�������׳������÷����ɵ��÷�������
 * 
 * 	�Զ����쳣
 * 		ΪʲôҪ�Զ����쳣��
 * 			ʹ���쳣����Ϊ�˴���һЩ�ش���߼�bug����Щ�߼�bug���ܻᵼ�³������
 * 			��������Ҫ���쳣��ϵͳû���ṩ��ʱ�����ǾͿ��Խ����Զ����ճ���
 * 
 * 		�������ʱ�쳣------------�̳�Exception
 * 		��������ʱ�쳣------------�̳�RuntimeException
 * */
public class Exception_review {

	public static void main(String[] args){
		int[] arr={1,2,3,4};
		String str=null;
		try{
		//�����ܳ���Ĵ���ŵ�try...catch�У�����Ӱ�����ִ��	
		//int ele = arr[4];
		//�����쳣��λ��֮�����䲻����ִ��
		//System.out.println(ele);
		
		str.equals("test");
		
	}catch(ArrayIndexOutOfBoundsException e){
		//�˴���׽���쳣����Ҫ��ʵ���׳����쳣����ƥ�䣬������Ȼ�޷������쳣����Ӱ�����ִ��
		System.err.println("�����±�Խ�磡");
	}catch(NullPointerException ne){
		//���try������п��ܴ��ڶ���쳣����ô������Ը��϶��Catch
		System.err.println("��ָ�룡");
	}
		//���catch֮����쳣��û�м̳й�ϵ�Ļ����Ⱥ�˳������ν
		//����м̳й�ϵ���ñ�֤�����쳣�ں������쳣��ǰ
		//�������쳣�Ĵ���ʽ������ͬ�ģ�����ֱ��Catchһ�������쳣������ͳһ����
		//ExceptionTest(2);
		//System.out.println(finallyTest());
		//System.out.println(finallyTest2());
		//throwTest();
		System.out.println("end");
		
		List<String> list=new ArrayList<String>();
		list.add("abc");
		list.add("fff");
		list.add("defg");
		list.add("www");
		list.add("ooo");
		try {
			loop(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("�����׳�");
		}
		
		try {
			throw new MyException("�Զ��������Ϣ������");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("�Զ����쳣��"+e.getMessage());//��ȡ�쳣��Ϣ
		}
	}
	
	/*
	 * Exception ����
	 * */
	public static void ExceptionTest(int index){
		int[] arr={1,2,3,4};
		String str=null;
		try{
			
		int ele = arr[index];
		System.out.println(ele);
		str.equals("test");
		//һ��Catch�������쳣������ֱ��catch���࣬Ҳ���Խ����ɸ��޼̳й�ϵ���쳣��|����
	}catch(ArrayIndexOutOfBoundsException |NullPointerException e){
		//�˴���׽���쳣����Ҫ��ʵ���׳����쳣����ƥ�䣬������Ȼ�޷������쳣����Ӱ�����ִ��
		System.err.println("�����±�Խ����ָ���쳣��");
	}catch(RuntimeException re){
		System.err.println("�����쳣");
	}
	}
	
	/*
	 * finally ����
	 * */
	public static int finallyTest(){
		/*
		 * ִ�й��̣�
		 * 	��try�еĴ��룬ִ�е�return��ʱ��ϵͳ�ὫҪ���ص�ֵ������һ�ݵ���ʱ�ռ���
		 * 	Ȼ��ִ��finally�еĴ��룬���ʱ��finally�еĴ����޸ĵ���ԭ�ֲ��ı���������
		 * 	finally�еĴ���ִ����ɺ󣬻Ὣ��ʱ�ռ��е����ݷ��أ����ͷ���ʱ�ռ�
		 * 	���Է���ֵΪ20
		 * */
		int x=10;
		try{
			x=20;
		return x;
	}finally{
		x=100;
		System.out.println("finally����ִ��");
	}
	}
	/*
	 * finally ����
	 * */
	public static String finallyTest2(){
		String a="a";
		try{
			a="b";
			return a;
		}finally{
			a="c";
			System.out.println("finally����2ִ��");
		}
	}
	
	
	/*
	 * throw ����
	 * */
	private static void throwTest(){
		
		RuntimeException ex=new RuntimeException();
		int i=1;
		while(true){
			if(i>100)throw ex;//�׳��쳣
			System.out.println(i++);
			
		}
	}
	
	/*
	 * thorws ����
	 * */
	private static void loop(List<String> list)throws Exception{
		Exception e =new Exception();
		int i=0;
		Iterator<String> it=list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
			if(i==list.size()/2+1){
				throw e;//���û��throws Exception�Ļ������ڱ������쳣�޷�ͨ������
			}
			i++;
			System.out.println("---------"+i);
		}
	}
}

//����ʱ�쳣
class MyException extends Exception{
	public MyException(){
		
	}
	
	public MyException(String message){
		super(message);
	}
}
