package review;

import java.util.*;
/*
 * Collection是单列集合，它有List和Set两个子接口
 * */
public class CollectionTest {
	
	public static void main(String[] args){
		Collection<Integer> coll=new ArrayList<Integer>();
	Collection<Integer> temp=new ArrayList<Integer>();
	//新增
	coll.add(1);
	coll.add(2);
	coll.add(3);
	
	temp.add(4);
	temp.add(5);
	temp.add(6);
	//一次新增一个集合的元素
	coll.addAll(temp);
	System.out.println(coll);
	//删除
	temp.remove(5);
	System.out.println(temp);
	//包含
	System.out.println(coll.contains(2));
	//是否包含该集合
	System.out.println(coll.containsAll(temp));
	//一般在遍历集合的时候没办法对集合进行删除操作，可以用该方法，在满足某条件的情况下，删除掉满足条件的元素
	coll.removeIf(ele->ele>5);
	System.out.println(coll);
	
	//集合的遍历
	//iterator默认指向初始位置的前一个
	Iterator<Integer> it=temp.iterator();
	while(it.hasNext()){
		int value=it.next();//得到当前指向的下一个元素
		System.out.println(value);
	}
	coll.add(10);
	coll.add(11);
	//coll.toArray();转数组，默认为object数组
	Integer[] a=coll.toArray(new Integer[3]);
	System.out.println(Arrays.toString(a));
	//JDK1.8后提供了forEach方法
	coll.forEach(val -> System.out.print(val+"--"));
	
	}
	
	
	
	

	

}
