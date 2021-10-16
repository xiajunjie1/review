package review;

import java.util.*;
/*
 * set
 * ʵ���ࣺHashSet��TreeSet
 * HashSet�����򣬲����ظ���
 * HashSetʵ��ԭ��
 * 	��ϣ��ɢ�б�----����+����ʵ��
 * ����hashset�������ֵ��ʱ�����Ὺ��һ��������Ŀռ䣨����a[n]��
 * ��Ҫ��ӵ����ݣ�ȡhashֵ�����ø�hashֵ�����鳤��(n)ȡ�࣬����iΪ������±�
 * ����Ҫ��ӵ�ֵ����a[i]����ָ��һ����������Ȼ����Ҫ��ӵ�ֵ����������漴��
 * ������ͬ��Ԫ�أ�����ͬһ�������ϣ���Ԫ������ӵ�ʱ�򣬻���������е�Ԫ�ضԱ�hashcode
 * ��hashcode��ͬ������ӵ������У���hashcode��ͬ����ô��Ҫ���øö����equals������һ�ν��бȽ�
 * ��equals��������true����˵��Ԫ����ͬ
 * */


/*
 * HashSet��Ӧ��
 * ���򼯺ϵ������������ʱ���������еĳ�Ա������ͬ����ô������ӵ�������
 * ����hashset��ԭ��Ϊ��ʵ�ָ�������Ҫ��дhashcode������equals����
 * 
 * TreeSet
 * 	���򣬲����ظ������ǿ��ԶԼ����е�Ԫ�ؽ�������
 * 	TreeSet�����Ԫ�ص�ʱ�򣬾ͻ��Զ���Ԫ�ؽ�������
 * 	�����ԭ��ʵ��Comparable<E>�ӿڣ�����ʵ�ָýӿڣ���TreeSet����ӵĶ���ᱨ��
 * TreeSet�ĵײ���һ��������ʵ�ֵģ���ӵ�һ��Ԫ�ص�ʱ�򣬽���һ��Ԫ����Ϊ���ڵ㣬�������Ԫ��ʱ������compareto����
 * �����ӵ�Ԫ�ش��ڸ��ڵ㣬����ڸ��ڵ���Ҳ࣬��֮�ŵ����
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
		return Objects.hash(this.name,this.age);//���ݴ���Ĳ��������ϣֵ
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj){
			//����ȽϵĶ�������䱾��
			return true;
		}
		if(obj==null || this.getClass()!=obj.getClass()){
			//�ȽϵĶ���Ϊ�ջ��߲���ͬһ����
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
		return this.age-o.getAge();//�����������򣬷���ֵ����0��˵����ǰ������ڱȽ϶��󣬷�֮����С�ڣ�������0����Ϊ���ظ�Ԫ��
		//����ֵ����0��˵����ǰ����Ҫ���ڱȽ϶�����棬��֮����ǰ��
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
	//��Ԫ�ص�˳���֪�������ռ�ĳ���Ϊ16
	set.add(1);//������ӽ�ȥ
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
	
	//����Comparator����ʽ�ӿ�ʵ������
	//��ʱ����TreeSet����ӵĶ����Ѿ�ʵ����Comparable�ӿڣ��������������������
	//��ʱ��Ϳ�������Comparator�����¶����������
	Set<String> Tset2 = new TreeSet<String>((str1,str2)-> str1.length()-str2.length());
	Tset2.add("aaaaaa");
	Tset2.add("ttt");
	System.out.println(Tset2);
	
	
}


}
