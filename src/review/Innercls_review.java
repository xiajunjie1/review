package review;
/*
 * 内部类
 * java类的内部，可以定义一个完整的类，该类称之为内部类
 * 内部类编译完后也会生成字节码文件
 * 		字节码文件名：外部类名$内部类名.class
 * 外部类定义的私有成员，内部类可以直接访问
 * 可以为外部类提供一些功能组件
 * 
 * 分类
 * 	成员内部类
 * 	静态内部类
 * 	局部内部类
 * 		定义在外部类的方法中，作用范围和创建对象的范围仅限于当前方法中
 * 		访问权限：没有，什么权限修饰符都不能写
 * 		不能用static来修饰
 * 		如果在局部内部类中，使用到了局部变量，那么这个局部变量会被隐式的定义为final
 * 	匿名内部类
 * 		没有名字的局部内部类，所有特征与局部内部类相同
 * 		匿名内部类必须继承一个父类或者实现一个接口
 * 		只能创建一个该类的对象
 * 
 * 
 * */
public class Innercls_review {
	
	public String name="outter";
	public static void main(String[] args){
		//内部类的使用---实例化
	Innercls_review ir=new Innercls_review();
	//由于内部类也是外部类的成员，还是需要由外部类对象来new一个内部类对象
	Innercls_review.innercls iri=ir.new innercls();
	iri.show("parameter");
	
	//静态内部类直接实例化即可，不需要得到外部类的对象
	Innercls_review.innercls2 iri2=new Innercls_review.innercls2();
	System.out.println("静态内部类成员1："+iri2.name);
	System.out.println("静态内部类成员2："+Innercls_review.innercls2.i);
		
		Person p =new Person(){
			@Override
			public void run(){
				System.out.println("匿名内部类 run!");
			}
			
		};//定义匿名内部类，该匿名内部类代表Person的子类
		
		p.run();
	}

	/**
	 * 内部类定义---成员内部类
	 * 	访问权限：
	 * 		public，外部类用public修饰，只能写在一个和类名同名的文件中，而内部类不存在该限制 
	 * 		默认不写
	 * ---------外部类只有上面两个修饰符修饰，内部类四种修饰符都可以
	 * 		protected
	 * 		private
	 * */
	class innercls{
		public String a;
		int b;
		String name="inner";
		//static int c;//报错，内部类无法定义静态成员和静态方法
		public void show(String name){
			System.out.println("参数name："+name);
			System.out.println("内部类name："+this.name);
			//内部类中访问外部类的成员变量：外部类名.this.成员变量名
			System.out.println("外部类name："+Innercls_review.this.name);
		}
		
		}
	
	public static class innercls2{
		
		public String name="测试";
		public static int i=1;//可以定义静态成员
		//静态内部类是无法访问到外部内的成员的，但是可以访问外部类的静态成员
	}
	
	
}


class Person {
	String name;
	int age;
	
	public void run(){
		System.out.println("Person run!");
	}
}


