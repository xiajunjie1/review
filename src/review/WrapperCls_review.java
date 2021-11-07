package review;
/*
 * ��װ�ิϰ
 * �ڻ������������������һ�������ʹ������������͵Ĺ���
 * byte---Byte
 * short--Short
 * int----Integer
 * long---Long
 * float--Float
 * double-Double
 * char---Character
 * boolean-Boolean
 * 
 * װ��
 * ����
 * �Զ�װ����Զ����䣺��JDK1.5֮��java֧���Զ�װ����Զ�����
 * 	�Զ�װ�䣺��һ������������һ����Ӧ�İ�װ����и�ֵ
 * 	�Զ����䣺��һ����װ�������һ����Ӧ�Ļ����������и�ֵ
 * 
 * ��ĳЩ����£����޷������Զ���װ����Զ��Ĳ����
 * */
public class WrapperCls_review {
	public static void main(String[] args){
		Integer int1=Integer.valueOf(10);
		test(int1);
		test(10);
	}
	
	/**
	 * �ֶ���װ�䣺�ɻ���������ɶ԰�װ���͵�ת��
	 * 1.ͨ����װ��Ĺ��췽��
	 * 2.ͨ����װ��ľ�̬����valueOf
	 * �Ƽ�ʹ�÷���2,��Ϊ������Ԫԭ��
	 * ��new һ����װ������ʱ�򣬻Ὺ��һ���µ��ڴ�ռ䣬�����valueOf�Ļ����������ö�����ָ���ͬһ����ռ�
	 * */
	private static void boxing(){
		//1.ͨ����װ��Ĺ��췽�����
		Byte b1=new Byte((byte)10);//��byte���͵�10װ���ΪByte����
		Integer inte1=new Integer(20);
		
		//2.ͨ����װ��ľ�̬����valueOf
		Byte b2=Byte.valueOf((byte)30);
		Integer inte2=Integer.valueOf(35);
		
		
	}
	
	/**
	 * �ֶ����䣺�ɰ�װ������ɶ�Ӧ�������͵�ת��
	 * 1.ʹ��ÿһ����װ���xxxValue��������ʵ��
	 * 
	 * */
	private static void unboxing(){
		Integer int1=Integer.valueOf(100);
		int baseint1=int1.intValue();
		System.out.println(baseint1);
	}
	
	
	/**
	 * �ֶ�װ����ֶ�����ʹ�ó���
	 * �ڷ������صĳ����£����޷������Զ���װ����Զ��Ĳ����
	 * */
	private static void test(int i){
		System.out.println("�������ͣ�"+i);
	}
	
	private static void test(Integer i){
		System.out.println("�������ͣ�"+i);
	}
}
