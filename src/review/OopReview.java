package review;
/*
 * �������ϰ
 * 1.�����ڴ����
 * 	������ջ�ڴ��п���һ���ռ䣬����mycls���������ʵ�������ӳ���ַ
 * 	��Σ�ϵͳ����new�ؼ��ֺ󣬻��ڶ��ڴ��п���һ���ռ䣬�ÿռ����������еĳ�Ա����
 * 	Ȼ�󽫳�Ա������ֵ����nameĬ��ֵΪnull��ageĬ��ֵΪ0��Ȼ���ٽ��õ�ַ���ô�ŵ�mycls��
 * 
 * 2.��Ա����
 * 	�Ǿ�̬������Ҫʹ�ö��������ʣ�����֮ǰ��Ҫʵ��������
 * 	��̬��ʹ��static���ε����Ի򷽷�����̬���ԣ������һ�μ��ص��ڴ��ʱ�򣬿ռ�ͻᱻ���䡣����������ģ����ж�����Թ���ñ���
 * 		��̬���ԵĿռ��ھ�̬�����ٿռ䣬��̬�Ŀռ���Ψһ�ģ����еĶ����ǹ�������ռ��
 * 		��̬��Ա�����౾��Ϳ��Է����ˣ�����Ҳ���Է���
 * 
 * 3.this�ؼ��ֵ�ʡ��
 * 	����Ҫ���ʵ�������ֲ�����û��������ʱ��this�ؼ��ֿ���д��Ҳ���Բ�д
 * 	����Ҫ���ʵ�������ֲ�����������ʱ��this�ؼ��ֱ���д��������ʡ��
 * 
 * 4.���췽��
 * 	����ʵ����������ʵ���������ʱ�����
 * 	���췽������������������һ��
 * 	�޷���ֵ������ֵ���Ͳ��ֲ�д��
 * 	���췽���������޲�������Ϊ���޲ι��졢�вι���
 * 	���췽��ʹ�ã�����ֵ��ʼ������
 * 	���췽���е����������췽��---this(������
 * 		���췽���е����������췽�������뽫�������д�ڵ�һ��
 * 		����ѭ�����ù��췽��---�繹�췽��1�е��ù��췽��2�����췽��2���ֵ��ù��췽��1
 * 
 * 		
 * 
 * */
public class OopReview {
	public static void main(String[] args){
		//ʵ��������
		Mycls mycls = new Mycls();
		//ʵ�������ĵ���
		mycls.show();
		//��̬�����ĵ���
		Mycls.display();//1
		Mycls.display();//2
		Mycls.display();//3
		
	}
}

class Mycls{
	public String name;
	public int age;
	public static int count;
	public void show(){
		System.out.println("My name is "+this.name+" "+this.age+" years old");
		System.out.println("��̬������"+count);//0
		//�Ǿ�̬���������Է��ʱ����еľ�̬��Ա�ͷǾ�̬��Ա
	}
	//��̬����
	public static void display(){
		count++;
		//this.count++;//�﷨������̬�������޷�ʹ��this
		//age=100;//�﷨������̬�������޷�ʹ�÷Ǿ�̬����
		System.out.println("��̬������"+count);
		
	}
	
}


class StudentCls{
	public String name;
	public int age;
	public boolean gender;//true=male,false=female
	
	//�޲ι��췽��---Ĭ���Ǵ����޲ι��죬���������һ���вι��죬���޲θ�������д
	public StudentCls(){}
	//�вι��죬��������ʼ����Ա����
	public StudentCls(String name,int age,boolean gender){
		//���ھֲ������ͳ�Ա����ͬ�������Ա���ü���this�ؼ���
		//this.name=name;
		//his.age=age;
		this(name,age);
		this.gender=gender;
		
	}
	
	public StudentCls(String name,int age){
		//���ھֲ������ͳ�Ա����ͬ�������Ա���ü���this�ؼ���
		this.name=name;
		this.age=age;
	
		
	}
	
}