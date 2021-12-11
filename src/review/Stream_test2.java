package review;

import java.util.stream.*;
import java.util.*;
import java.util.function.Function;
/*
 * 流式编程，中间操作
 * 
 * */
public class Stream_test2 {
	public static void main(String[] args){
		//filterUsage();
		//Object o=new myStudent("test",11);
		//System.out.println(o instanceof myStudent);
		//distinctUsage();
		//sortedUsage();
		//limtskipUsage();
		//mapUsage();
		flatMapUsage();

	}
	
	/**
	 * 获取流对象数据源
	 * 
	 * */
	private static Stream<myStudent> getDataSourece(){
		List<myStudent> list =new ArrayList<>();
		Collections.addAll(list, 
				new myStudent("xiaoming",17),
				new myStudent("xiaohong",18),
				new myStudent("xiaohuang",22),
				new myStudent("xiaohei",25),
				new myStudent("xiaobai",19),
				new myStudent("xiaohong",18)
				);
		return list.stream();
	}
	
	/**中间操作，filter---过滤数据，返回满足条件的数据流
	 * */
	private static void filterUsage(){
		//获取数据源
		Stream<myStudent> stream=getDataSourece();
		//过滤，只返回年龄大于18岁的元素
		Stream<myStudent> targetStream=stream.filter(e1->e1.getAge()>18);//返回一个数据过滤后的流对象
		targetStream.forEach(System.out::println);
	}
	
	/**
	 * 中间操作，distinct。
	 * 这个方法没有参数，去重规则和HashSet一样
	 * 1.比较两个对象的hashcode
	 * 2.比较两个对象的equals
	 * 
	 * */
	
	private static void distinctUsage(){
		Stream<myStudent> students=getDataSourece();
		students.distinct().forEach(System.out::println);;
	}
	
	/**
	 * 中间操作，sorted--排序
	 * sorted():按照元素实现的Comparable接口的大小规则进行排序
	 * sort(Comparator<T>)：给定排序规则
	 * 	
	 * */
	private static void sortedUsage(){
		Stream<myStudent> students=getDataSourece();
		//按照升序进行排序
		students.sorted((e1,e2)->e1.getAge()-e2.getAge()).forEach(System.out::println);
		//student.sorted()如果用该方法，那么元素对象必须实现了Comparable接口
	}
	
	
	/**
	 * 中间操作，limit&skip
	 * 	limit，限制，表示截取流中的指定数量的数据
	 * 	skip，跳过，表示跳过指定数量的数据，截取剩余的部分
	 * 
	 * */
	private static void limtskipUsage(){
		Stream<myStudent> students=getDataSourece();
		//获取年龄最大的三个人
		//students.sorted((e1,e2)->e2.getAge()-e1.getAge()).limit(3).forEach(System.out::println);
		//获取年龄最大的第二人、第三人
		students.sorted((e1,e2)->e2.getAge()-e1.getAge()).limit(3).skip(1).forEach(System.out::println);
	}
	
	/**
	 * 中间操作，map元素映射，提供一个映射规则，将流中每一个元素替换成指定的元素
	 * 
	 * */
	private static void mapUsage(){
		Stream<myStudent> students=getDataSourece();
		Stream<myStudent> students2=getDataSourece();
		Stream<myStudent> students3=getDataSourece();
		//获取所有学生的姓名
		Stream<String> target=students.map(m->m.getName());//参数为函数式接口，Function<? super T, ? extends R> 
		//必须实现的方法为R apply(T t);
		//可以简写成myStudent::getName()
		target.forEach(System.out::println);
		//获取所有学生的年龄
		Stream<Integer> target2=students2.map(myStudent::getAge);
		target2.forEach(System.out::println);
		//另一种获取基本数据类型流的方法
		IntStream target3=students3.mapToInt(myStudent::getAge);
		IntSummaryStatistics iss=target3.summaryStatistics();
		System.out.println(iss.getSum());
		
		
		
	}
	
	/**
	 * 中间操作flatMap，扁平化映射
	 * 一般用在map方法映射完后，流中的数据是容器（数组、集合等），而我们需要对流中的容器进行处理
	 * 它可以将容器中的数据直接读取到流中。
	 * 
	 * */
	private static void flatMapUsage(){
		String[] arrays=new String[]{"abc","efg","hij","xyz"};
		
		Stream<String> stream=Arrays.stream(arrays);
		//统计字符串数组中，所有出现的字符
		//stream.map(e1->new String(e1).toCharArray()).forEach(e1->System.out.println(Arrays.toString(e1)));
		stream.map(e1->e1.split("")).flatMap(e1->Arrays.stream(e1)).forEach(System.out::println);
	}
}


/*
 * 数据源元素对象
 * */
class myStudent{
	private String name;
	private int age;
	
	public myStudent(String name,int age){
		this.name=name;
		this.age=age;
	}
	
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name: "+this.name+"  age: "+this.age;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.name,this.age);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==null)return false;
		if(obj instanceof myStudent){
			myStudent ms=(myStudent)obj;
			return (this.name.equals(ms.getName()))&&(this.age==ms.getAge());
		}
		return false;
		
	}
	
}
