package review;

/*枚举
 * 一种自定义的引用数据类型
 * 	经常描述一些取值有限的对象
 * 
 * 枚举是Object的最终子类，即枚举不可以被继承
 * 所以的枚举都是Enum的子类
 * 
 * 枚举值
 * 	定义在枚举对象内的枚举值，其实是若干个静态常量
 * 	Male相当于public static final Gender Male=new Gender()
 * 
 * 枚举中的方法定义
 * 	定义和普通的类是一样的
 * 
 * 枚举方法重写
 * 	重写Object的方法
 * 	枚举也可以实现接口，重写接口的方法
 * 
 * */

public class Enum_review {
	
	public static void main(String[] args){
		Human2 h2=new Human2();
		h2.setAge(22);
		h2.setGender(Gender.Male);//给枚举对象赋值，枚举类型.枚举值
		h2.setName("xia");
		System.out.println(h2);
		
		//获取枚举对象
		Gender female=Gender.Female;
		//通过对象访问成员
		female.name="test";
		System.out.println(female+"----"+female.name);
		female.show();
		System.out.println(++Gender.count);
	}
}

enum Gender{
	//将这个枚举对象中，所有可能的值都列举出来
	//枚举中的元素也是标识符，遵循大驼峰命名法
	//在枚举中添加属性，必须要在枚举值末尾加上分号
	//添加的成员，必须在枚举值的下方
	Male,Female,other("阴阳人");//该元素相当于调用构造方法
	public String name;
	public void show(){
		System.out.println("枚举方法");
	}
	public static int count;//枚举中的静态变量
	
	Gender(){
		//枚举中的构造方法，默认是私有修饰的，也只能用private修饰
		//有多少个枚举元素，该方法就会被调用多少次（初始化）
		System.out.println("一个枚举对象被实例化");
	}
	
	Gender(String name){
		this.name=name;
	}
}

class Human2{
	private String name;
	private int age;
	//由于性别只有两个，为了限制传值，可以用枚举作为成员变量
	private Gender gender;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name: "+this.name+" age: "+this.age+" gender: "+this.gender;
	}
	
	
}