package review;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * 多态
 * 	父类对象的引用指向子类，从而产生了多种形态，则形成了多态
 * 
 * 对象转型
 * 	向上转型：子类类型转为父类类型
 * 	向下转型：父类类型转类未子类类型
 * 	向上转型的对象，只能访问父类中的共有成员，不能访问子类中的独有成员
 * 	向下转型后的对象，将可以访问子类中的独有成员
 * 
 * instanceof
 * 	向下转型，存在失败的可能性，如果引用的实际对象不是要转型的类型，则会抛出异常
 * 	所以在转型之前可以用instanceof来进行检查
 * */
public class Poly_review {
	
	private static void makesay(Human h){
		h.say();
	}
	public static void main(String[] args){
		Adults adult=new Adults();
		Human human=adult;//向上转型，该转型方式肯定会成功，它是一个隐式转型
		//Workers work=(Workers)adult;//向下转型，向下转型存在失败的可能性，需要进行强制类型转换
		//向下转型如果失败，会出现ClassCastException
		//该种方式想要转型成功，需要Adults adult=new Workers();Workers worker=(Workers)adult
		Workers worker=null;
		Adults ad=new Workers();
		List<Adults> Adlist=new ArrayList<>();
		Adlist.add(adult);
		Adlist.add(ad);
		Iterator<Adults> it =Adlist.iterator();
		while(it.hasNext()){
			Adults a=it.next();
			boolean check=(a instanceof Workers);
			System.out.println(check);
			if(check){
				worker=(Workers)a;
				System.out.println("Worker 初始化！");
			}
		}
		//多态重写
		makesay(human);//Adults say,向上转型，调用的是重写后的子类方法
		makesay(adult);
		makesay(worker);//Workers say，向下转型，调用的是重写后的子类方法
		//总结：调用哪个类的方法，主要看该对象引用指向的对象，是怎么new出来的
	}
}


class Human{
	public String name;
	public int age;
	public String gender;
	
	public void say(){
		System.out.println("Human say");
	}
	
}

class Adults extends Human{
	public String career;
	@Override
	public void say(){
		System.out.println("Adults say");
	}
}

class Workers extends Adults{
	public int salry;
	public void say(){
		System.out.println("Workers say");
	}
}