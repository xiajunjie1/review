package review;
/*
 * �ڲ���
 * java����ڲ������Զ���һ���������࣬�����֮Ϊ�ڲ���
 * �ڲ���������Ҳ�������ֽ����ļ�
 * 		�ֽ����ļ������ⲿ����$�ڲ�����.class
 * �ⲿ�ඨ���˽�г�Ա���ڲ������ֱ�ӷ���
 * ����Ϊ�ⲿ���ṩһЩ�������
 * 
 * ����
 * 	��Ա�ڲ���
 * 	��̬�ڲ���
 * 	�ֲ��ڲ���
 * 		�������ⲿ��ķ����У����÷�Χ�ʹ�������ķ�Χ�����ڵ�ǰ������
 * 		����Ȩ�ޣ�û�У�ʲôȨ�����η�������д
 * 		������static������
 * 		����ھֲ��ڲ����У�ʹ�õ��˾ֲ���������ô����ֲ������ᱻ��ʽ�Ķ���Ϊfinal
 * 	�����ڲ���
 * 		û�����ֵľֲ��ڲ��࣬����������ֲ��ڲ�����ͬ
 * 		�����ڲ������̳�һ���������ʵ��һ���ӿ�
 * 		ֻ�ܴ���һ������Ķ���
 * 
 * 
 * */
public class Innercls_review {
	
	public String name="outter";
	public static void main(String[] args){
		//�ڲ����ʹ��---ʵ����
	Innercls_review ir=new Innercls_review();
	//�����ڲ���Ҳ���ⲿ��ĳ�Ա��������Ҫ���ⲿ�������newһ���ڲ������
	Innercls_review.innercls iri=ir.new innercls();
	iri.show("parameter");
	
	//��̬�ڲ���ֱ��ʵ�������ɣ�����Ҫ�õ��ⲿ��Ķ���
	Innercls_review.innercls2 iri2=new Innercls_review.innercls2();
	System.out.println("��̬�ڲ����Ա1��"+iri2.name);
	System.out.println("��̬�ڲ����Ա2��"+Innercls_review.innercls2.i);
		
		Person p =new Person(){
			@Override
			public void run(){
				System.out.println("�����ڲ��� run!");
			}
			
		};//���������ڲ��࣬�������ڲ������Person������
		
		p.run();
	}

	/**
	 * �ڲ��ඨ��---��Ա�ڲ���
	 * 	����Ȩ�ޣ�
	 * 		public���ⲿ����public���Σ�ֻ��д��һ��������ͬ�����ļ��У����ڲ��಻���ڸ����� 
	 * 		Ĭ�ϲ�д
	 * ---------�ⲿ��ֻ�������������η����Σ��ڲ����������η�������
	 * 		protected
	 * 		private
	 * */
	class innercls{
		public String a;
		int b;
		String name="inner";
		//static int c;//�����ڲ����޷����徲̬��Ա�;�̬����
		public void show(String name){
			System.out.println("����name��"+name);
			System.out.println("�ڲ���name��"+this.name);
			//�ڲ����з����ⲿ��ĳ�Ա�������ⲿ����.this.��Ա������
			System.out.println("�ⲿ��name��"+Innercls_review.this.name);
		}
		
		}
	
	public static class innercls2{
		
		public String name="����";
		public static int i=1;//���Զ��徲̬��Ա
		//��̬�ڲ������޷����ʵ��ⲿ�ڵĳ�Ա�ģ����ǿ��Է����ⲿ��ľ�̬��Ա
	}
	
	
}


class Person {
	String name;
	int age;
	
	public void run(){
		System.out.println("Person run!");
	}
}


