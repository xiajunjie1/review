package review;

import java.util.*;

public class CollectionTest {
	
	public static void main(String[] args){
		Collection<Integer> coll=new ArrayList<Integer>();
	Collection<Integer> temp=new ArrayList<Integer>();
	//����
	coll.add(1);
	coll.add(2);
	coll.add(3);
	
	temp.add(4);
	temp.add(5);
	temp.add(6);
	//һ������һ�����ϵ�Ԫ��
	coll.addAll(temp);
	System.out.println(coll);
	//ɾ��
	temp.remove(5);
	System.out.println(temp);
	//����
	System.out.println(coll.contains(2));
	//�Ƿ�����ü���
	System.out.println(coll.containsAll(temp));
	//һ���ڱ������ϵ�ʱ��û�취�Լ��Ͻ���ɾ�������������ø÷�����������ĳ����������£�ɾ��������������Ԫ��
	coll.removeIf(ele->ele>5);
	System.out.println(coll);
	
	//���ϵı���
	//iteratorĬ��ָ���ʼλ�õ�ǰһ��
	Iterator<Integer> it=temp.iterator();
	while(it.hasNext()){
		int value=it.next();//�õ���ǰָ�����һ��Ԫ��
		System.out.println(value);
	}
	coll.add(10);
	coll.add(11);
	//coll.toArray();ת���飬Ĭ��Ϊobject����
	Integer[] a=coll.toArray(new Integer[3]);
	System.out.println(Arrays.toString(a));
	//JDK1.8���ṩ��forEach����
	coll.forEach(val -> System.out.print(val+"--"));
	
	}
	
	
	
	

	

}
