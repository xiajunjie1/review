package review;
/*
 * 泛型,提高程序的安全性
 * 
 * 在类中定义泛型，虽然不明确是什么类型，但是在当前类中是可以使用的。
 * 在使用到这个类的时候，必须要指定泛型的类型。如果不指定，默认是object
 * 泛型，只能在当前类中使用，不能在其他类中使用，包括子类
 * 
 * 当类泛型被确定后，类中的泛型成员类型也会被确认
 * 
 * 
 * 
 * */
public class Review03 {
public static void main(String[] args){
	//不使用泛型
	Tool t1=new Tool();
	t1.setObj(new Student());
	//Worker w=(Worker)t1.getObj();//编译阶段不报错，运行阶段报错： java.lang.ClassCastException
	
	//使用泛型,当传入泛型的值，E就被确定了，不会像不使用泛型那样，编译阶段不报错，运行阶段发现类型错误
	Tool2<Student> t2=new Tool2<Student>();
	t2.setObj(new Student());
	//Worker w2=(Worker)t2.getObj();编译阶段报错
	Student s=t2.getObj();
	t2.func("测试");
	
}
}

class Tool{
	private Object obj;
	
	public void setObj(Object obj){
		this.obj=obj;
	}
	
	public Object getObj(){
		return this.obj;
	}
}

class Tool2<E>{
	//对类使用泛型
	private E obj;
	public void setObj(E obj){
		this.obj=obj;
	}
	public E getObj(){
		return this.obj;
	}
	
	public <E> void func(E val){
		//方法单独使用泛型，此时方法泛型的变量类型不受类的影响
		//静态方法由于不用实例化即可调用，所以静态方法只能自己使用泛型
		System.out.println("value is : "+val);
		
	}
}

class Student{
	
}
class Worker{
	
}

//泛型类的继承1
class subTool1 extends Tool2<Student>{}

//泛型类的继承2
class subTool2<E> extends Tool2<E>{}

