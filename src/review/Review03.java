package review;
/*
 * ����,��߳���İ�ȫ��
 * 
 * �����ж��巺�ͣ���Ȼ����ȷ��ʲô���ͣ������ڵ�ǰ�����ǿ���ʹ�õġ�
 * ��ʹ�õ�������ʱ�򣬱���Ҫָ�����͵����͡������ָ����Ĭ����object
 * ���ͣ�ֻ���ڵ�ǰ����ʹ�ã���������������ʹ�ã���������
 * 
 * ���෺�ͱ�ȷ�������еķ��ͳ�Ա����Ҳ�ᱻȷ��
 * 
 * 
 * 
 * */
public class Review03 {
public static void main(String[] args){
	//��ʹ�÷���
	Tool t1=new Tool();
	t1.setObj(new Student());
	//Worker w=(Worker)t1.getObj();//����׶β��������н׶α��� java.lang.ClassCastException
	
	//ʹ�÷���,�����뷺�͵�ֵ��E�ͱ�ȷ���ˣ�������ʹ�÷�������������׶β��������н׶η������ʹ���
	Tool2<Student> t2=new Tool2<Student>();
	t2.setObj(new Student());
	//Worker w2=(Worker)t2.getObj();����׶α���
	Student s=t2.getObj();
	t2.func("����");
	
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
	//����ʹ�÷���
	private E obj;
	public void setObj(E obj){
		this.obj=obj;
	}
	public E getObj(){
		return this.obj;
	}
	
	public <E> void func(E val){
		//��������ʹ�÷��ͣ���ʱ�������͵ı������Ͳ������Ӱ��
		//��̬�������ڲ���ʵ�������ɵ��ã����Ծ�̬����ֻ���Լ�ʹ�÷���
		System.out.println("value is : "+val);
		
	}
}

class Student{
	
}
class Worker{
	
}

//������ļ̳�1
class subTool1 extends Tool2<Student>{}

//������ļ̳�2
class subTool2<E> extends Tool2<E>{}

