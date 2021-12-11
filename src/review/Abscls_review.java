package review;
/*抽象类
 *	抽象类不能被实例化，它只是提供所有子类的公有部分 
 *
 *应用场景：
 *	抽象类可以用来做一些简单的规则制定，在抽象类中制定一些规则，要求子类必须实现，约束所有子类的行为
 *
 *局限性：
 *	由于java中，类是单继承的，所以当一个类受到多种规则的约束时，就无法满足要求。此时可以使用接口进行这样复杂规则的制定
 *
 * */

public class Abscls_review {
	public static void main(String[] args){
		Basecls basecls=new Soncls();
		Soncls soncls=new Soncls();
	}
}

//定义抽象类
//abstract放在class前，但是放在权限修饰符后如public abstract class
abstract class Basecls{
	public String field1;
	public int field2;
	
	public void func1(){}
	//它内部能定义的内容和普通类相同，如成员变量，成员方法等
	//它内部也可以定义构造方法或者实例方法，但是它们都是给子类用的
	
	//由于父类的方法对数会在子类中重写覆盖，所以定义类显得有些多余
	//引入抽象方法
	//抽象方法只能包含在抽象类中
	public abstract void absfunc1();
	
}

//子类
class Soncls extends Basecls{

	//父类中定义了抽象方法的话，子类要不也定义为抽象类，要不就必须要实现该抽象方法
	@Override
	public void absfunc1() {
		
		System.out.println("子类重写");
	}
	
}