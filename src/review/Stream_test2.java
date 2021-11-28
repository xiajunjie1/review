package review;

import java.util.stream.*;
import java.util.*;
import java.util.function.Function;
/*
 * ��ʽ��̣��м����
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
	 * ��ȡ����������Դ
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
	
	/**�м������filter---�������ݣ���������������������
	 * */
	private static void filterUsage(){
		//��ȡ����Դ
		Stream<myStudent> stream=getDataSourece();
		//���ˣ�ֻ�����������18���Ԫ��
		Stream<myStudent> targetStream=stream.filter(e1->e1.getAge()>18);//����һ�����ݹ��˺��������
		targetStream.forEach(System.out::println);
	}
	
	/**
	 * �м������distinct��
	 * �������û�в�����ȥ�ع����HashSetһ��
	 * 1.�Ƚ����������hashcode
	 * 2.�Ƚ����������equals
	 * 
	 * */
	
	private static void distinctUsage(){
		Stream<myStudent> students=getDataSourece();
		students.distinct().forEach(System.out::println);;
	}
	
	/**
	 * �м������sorted--����
	 * sorted():����Ԫ��ʵ�ֵ�Comparable�ӿڵĴ�С�����������
	 * sort(Comparator<T>)�������������
	 * 	
	 * */
	private static void sortedUsage(){
		Stream<myStudent> students=getDataSourece();
		//���������������
		students.sorted((e1,e2)->e1.getAge()-e2.getAge()).forEach(System.out::println);
		//student.sorted()����ø÷�������ôԪ�ض������ʵ����Comparable�ӿ�
	}
	
	
	/**
	 * �м������limit&skip
	 * 	limit�����ƣ���ʾ��ȡ���е�ָ������������
	 * 	skip����������ʾ����ָ�����������ݣ���ȡʣ��Ĳ���
	 * 
	 * */
	private static void limtskipUsage(){
		Stream<myStudent> students=getDataSourece();
		//��ȡ��������������
		//students.sorted((e1,e2)->e2.getAge()-e1.getAge()).limit(3).forEach(System.out::println);
		//��ȡ�������ĵڶ��ˡ�������
		students.sorted((e1,e2)->e2.getAge()-e1.getAge()).limit(3).skip(1).forEach(System.out::println);
	}
	
	/**
	 * �м������mapԪ��ӳ�䣬�ṩһ��ӳ����򣬽�����ÿһ��Ԫ���滻��ָ����Ԫ��
	 * 
	 * */
	private static void mapUsage(){
		Stream<myStudent> students=getDataSourece();
		Stream<myStudent> students2=getDataSourece();
		Stream<myStudent> students3=getDataSourece();
		//��ȡ����ѧ��������
		Stream<String> target=students.map(m->m.getName());//����Ϊ����ʽ�ӿڣ�Function<? super T, ? extends R> 
		//����ʵ�ֵķ���ΪR apply(T t);
		//���Լ�д��myStudent::getName()
		target.forEach(System.out::println);
		//��ȡ����ѧ��������
		Stream<Integer> target2=students2.map(myStudent::getAge);
		target2.forEach(System.out::println);
		//��һ�ֻ�ȡ���������������ķ���
		IntStream target3=students3.mapToInt(myStudent::getAge);
		IntSummaryStatistics iss=target3.summaryStatistics();
		System.out.println(iss.getSum());
		
		
		
	}
	
	/**
	 * �м����flatMap����ƽ��ӳ��
	 * һ������map����ӳ��������е����������������顢���ϵȣ�����������Ҫ�����е��������д���
	 * �����Խ������е�����ֱ�Ӷ�ȡ�����С�
	 * 
	 * */
	private static void flatMapUsage(){
		String[] arrays=new String[]{"abc","efg","hij","xyz"};
		
		Stream<String> stream=Arrays.stream(arrays);
		//ͳ���ַ��������У����г��ֵ��ַ�
		//stream.map(e1->new String(e1).toCharArray()).forEach(e1->System.out.println(Arrays.toString(e1)));
		stream.map(e1->e1.split("")).flatMap(e1->Arrays.stream(e1)).forEach(System.out::println);
	}
}


/*
 * ����ԴԪ�ض���
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
