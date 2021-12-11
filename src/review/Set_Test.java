package review;

import java.util.*;
/*
 * set
 * 实现类：HashSet、TreeSet
 * HashSet是无序，不可重复的
 * HashSet实现原理：
 * 	哈希表（散列表）----数组+链表实现
 * 当向hashset集合添加值的时候，它会开辟一块儿连续的空间（数组a[n]）
 * 需要添加的数据，取hash值，再拿该hash值与数组长度(n)取余，余数i为数组的下标
 * 即需要添加的值，在a[i]处会指向一个单向链表，然后将需要添加的值放在链表后面即可
 * 余数相同的元素，放在同一个链表上，新元素在添加的时候，会逐个跟已有的元素对比hashcode
 * 若hashcode不同，则添加到链表中，若hashcode相同，那么需要调用该对象的equals方法再一次进行比较
 * 若equals方法返回true，则说明元素相同
 * */


/*
 * HashSet的应用
 * 当向集合当中添加类对象的时候，若对象中的成员变量相同，那么不能添加到集合中
 * 根据hashset的原理，为了实现该需求，需要重写hashcode方法和equals方法
 * 
 * TreeSet
 * 	无序，不可重复，但是可以对集合中的元素进行排序
 * 	TreeSet在添加元素的时候，就会自动对元素进行排序
 * 	排序的原理：实现Comparable<E>接口，若不实现该接口，往TreeSet中添加的对象会报错
 * TreeSet的底层是一个二叉树实现的，添加第一个元素的时候，将第一个元素作为根节点，继续添加元素时，调用compareto方法
 * 如果添加的元素大于根节点，则放在根节点的右侧，反之放到左侧
 * 
 * */

class User implements Comparable<User>{
	private String name;
	private int age;
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
	public  User(){
		
	}
	public User(String name,int age){
		this.name=name;
		this.age=age;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.name,this.age);//根据传入的参数计算哈希值
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj){
			//与其比较的对象就是其本身
			return true;
		}
		if(obj==null || this.getClass()!=obj.getClass()){
			//比较的对象为空或者不是同一个类
			return false;
		}
		User u=(User)obj;
		if(this.name.equals(u.getName()) && this.age==u.getAge()){
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{name:"+this.name+",age:"+this.age+"}";
	}
	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.age-o.getAge();//根据年龄排序，返回值大于0则说明当前对象大于比较对象，反之则是小于，若返回0则认为是重复元素
		//返回值大于0，说明当前对象要排在比较对象后面，反之则是前面
	}
	
	
}
public class Set_Test {
public static void main(String[] args){
	Set<Integer> set =new HashSet<Integer>();
	set.add(1);
	set.add(2);
	set.add(3);
	set.add(4);
	set.add(5);
	set.add(6);
	set.add(20);
	set.add(17);
	System.out.println(set);
	//由元素的顺序可知，连续空间的长度为16
	set.add(1);//不会添加进去
	//set.forEach(ele -> System.out.println(ele) );
	
	Set<User> set2=new HashSet<>();
	set2.add(new User("zhangsan",21));
	set2.add(new User("lisi",22));
	set2.add(new User("wangwu",21));
	set2.add(new User("zhangsan",30));
	set2.add(new User("lisi",22));
	System.out.println(set2);
	
	User u1=new User("xiajunjie",25);
	User u2=new User("xiajunjie",25);
	System.out.println(u1.equals(u2));
	
	Set<User> Tset=new TreeSet<>();
	Tset.add(new User("abc",20));
	Tset.add(new User("def",18));
	System.out.println(Tset);
	
	//利用Comparator函数式接口实现排序
	//有时候，往TreeSet中添加的对象已经实现了Comparable接口，但是排序规则不满足需求
	//这时候就可以利用Comparator来重新定义排序规则
	Set<String> Tset2 = new TreeSet<String>((str1,str2)-> str1.length()-str2.length());
	Tset2.add("aaaaaa");
	Tset2.add("ttt");
	System.out.println(Tset2);
	
	
}


}
