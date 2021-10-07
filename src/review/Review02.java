package review;

//循环标签
public class Review02 {
	
	//方法的定义
	//[访问权限修饰符][其他修饰符]返回类型 方法名字([参数列表]){
	//方法体
	//}
	
	
	/*
	 * 方法的重载Overload
	 * 重载条件：
	 * 	1、方法名相同
	 * 	2、参数不同（类型、数量）
	 * 
	 * */
	public static void OverLoadM(){
		System.out.println("我是无参方法");
	}
	public static void OverLoadM(int num1,int num2){
		System.out.println("我的参数是整数，参数1: "+num1+"参数2："+num2);
	}
	public static void OverLoadM(String str1){
		System.out.println("我的参数是字符串: "+str1);
	}
	
	public static void main(String[] args){
		//设定标签，配合break使用
		OUTER:
		for(int i=0;i<5;i++){
			
			for(int j=0;j<5;j++){
				System.out.println("i: "+i+"j: "+j);
				if(j==3){
					//break;
					break OUTER;
				}
			}
		}
	
	OverLoadM();
	OverLoadM(1,2);
	OverLoadM("字符串方法");
		
	}

}
