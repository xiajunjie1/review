package review;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * ��̬
 * 	������������ָ�����࣬�Ӷ������˶�����̬�����γ��˶�̬
 * 
 * ����ת��
 * 	����ת�ͣ���������תΪ��������
 * 	����ת�ͣ���������ת��δ��������
 * 	����ת�͵Ķ���ֻ�ܷ��ʸ����еĹ��г�Ա�����ܷ��������еĶ��г�Ա
 * 	����ת�ͺ�Ķ��󣬽����Է��������еĶ��г�Ա
 * 
 * instanceof
 * 	����ת�ͣ�����ʧ�ܵĿ����ԣ�������õ�ʵ�ʶ�����Ҫת�͵����ͣ�����׳��쳣
 * 	������ת��֮ǰ������instanceof�����м��
 * */
public class Poly_review {
	
	private static void makesay(Human h){
		h.say();
	}
	public static void main(String[] args){
		Adults adult=new Adults();
		Human human=adult;//����ת�ͣ���ת�ͷ�ʽ�϶���ɹ�������һ����ʽת��
		//Workers work=(Workers)adult;//����ת�ͣ�����ת�ʹ���ʧ�ܵĿ����ԣ���Ҫ����ǿ������ת��
		//����ת�����ʧ�ܣ������ClassCastException
		//���ַ�ʽ��Ҫת�ͳɹ�����ҪAdults adult=new Workers();Workers worker=(Workers)adult
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
				System.out.println("Worker ��ʼ����");
			}
		}
		//��̬��д
		makesay(human);//Adults say,����ת�ͣ����õ�����д������෽��
		makesay(adult);
		makesay(worker);//Workers say������ת�ͣ����õ�����д������෽��
		//�ܽ᣺�����ĸ���ķ�������Ҫ���ö�������ָ��Ķ�������ônew������
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