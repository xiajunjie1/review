package review;

public class Review01 {

	
	public static void getType(Object a){
		
		System.out.println(a.getClass().toString());
	}
	
	public static void main(String[] args){
		System.out.println("-----------复习01----------");
		//long l1=1000000000000;---会报错
		long l2=1000000000000L;
		//getType(l1);
		getType(l2);
		getType(100000);//Integer
		getType(100000L);//Long
	
		System.out.println(1+2*3+4/2);
	
		
	}
}
