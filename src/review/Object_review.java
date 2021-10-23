package review;

/*
 * Object类
 * 	Java是所有类的基类
 * 
 * Object类中的方法
 * 	toString()
 * 		Object中toString默认的实现是返回getClass().getName()+"@"+Integer.toHexString(hashCode())
 * 		当需要把当前对象转换成字符串的形式，会自动调用该方法
 * 
 * 	equals()
 * 		可以用来做对象间的比较，==是比较变量的地址
 * 
 * 	Objects.euqals(obj1,obj2)
 * 	Objects为工具类，可以用工具类中的euqals方法来进行两个对象的比较，该方法可以避免obj1.equals(obj2),obj1为null的情况
 * 
 * 	getClass()
 * 	hashCode()
 * 
 * 
 * */

public class Object_review {
	public static void main(String[] args){
		System.out.println(new Subobject("test"));
		String str=new Subobject("test")+"2";
		System.out.println(str);
		Subobject sub1=new Subobject(new String("abc"));
		Subobject sub2=new Subobject(new String("abc"));
		System.out.println(sub1.equals(sub2));
	}
	
}

class Subobject{
	
	String name;
	public Subobject(String name){
		this.name=name;
	}
	@Override
	public String toString(){
		return this.name;
	}
	
	
	//重写equals,若成员变量的名字相同，视作相等
	/*
	 * 自定义比较的时候需要遵守的规范:
	 * 	如果地址相同，则一定返回true
	 * 	如果o是null，则一定返回false
	 * 	如果两个对象类型不同，则一定返回false
	 * 	如果a.equals(b)成立，则b.equals(a)成立
	 * 	如果a.equals(b)成立，b.equals(c)成立，则a.equals(c)成立
	 * 
	 * */
	@Override
	public boolean equals(Object o){
		if(this==o)return true;
		if(o==null || this.getClass()!=o.getClass())return false;
		
		Subobject sub=(Subobject)o;
		return this.name.equals(sub.name);
		
		
		
	}
}
