package review;

/*
 * �ӿ�
 * 	��۸���ӿ���һ�ֱ�׼
 * 	΢�۸���ӿ���һ��������Լ��
 * 		�ӿڵĶ��壺����ĳ������
 * 		�����Ķ��壺�����ľ���Ҫ��
 * 
 * �ӿڵĶ���
 * 	�ӿڵĶ����������ƣ�ֻ�ǹؼ���class�����interface
 * 	�ӿ��п��Զ��壺
 * 		����
 * 			�ӿ��еķ���Ĭ�����η���public abstract,��Ĭ�ϵĶ��ǳ��󷽷�
 * 			
 * 		����
 * 			�ӿ��е����ԣ�Ĭ�����η���public static final----����
 * 
 * �ӿ��������Ƚϣ�
 * 	��ͬ��
 * 		�ӿںͳ��������󶼻�����.class�ֽ����ļ�
 * 		�����ܴ�������
 * 		��������������
 * 		���߱�Object����������ķ���
 * 		������д���󷽷�
 * 
 * ��ͬ�㣺
 * 	�ӿ������е����ԣ����ǹ�����̬������Ĭ��Ϊpublic static final
 * 	�ӿ������еķ��������ǹ������󷽷���ȱʡ��public abstract
 * 	�ӿ���û�й��췽�����������Ρ���̬�����
 * 
 * 
 * ʵ�ֶ���ӿ�
 * 	ÿ���ӿ�֮���ö��Ÿ������ӿ�֮����Ⱥ�˳����Ӱ��
 * 	һ����ʵ���˶���ӿڣ�������д���нӿ��еĳ�����
 * ��������ӿ��У���ͬ�������Ƿ���ֵ��ͬ�ķ��������������ӿ��ǻ���ӿڣ��޷�ͬʱʵ�ֵ�
 * 
 * 
 * �ӿ�֮��ļ̳�
 * 	�ӿ�֮��ļ̳��Ƕ�̳У���һ���ӿڿ����ж�����ӿڡ�
 * 	�ӿڶ�̳е�ʱ��Ҳ����ͬʱ�̳���������ӿ�
 * 
 * �ӿڵĶ�̬
 * 	����ת�ͣ�ʵ�������ת�ͳɽӿ����͡���ʽת��
 * 	����ת�ͣ��ӿ�ת��Ϊʵ����---��ʾת����������ת��֮ǰ��Ҳ��instanceof����һ���ж�
 * 
 * 
 * java8�нӿڵ�������
 * 	static�����������ڽӿ��ж��徲̬�����������̬�������ǳ���ģ�����ʵ�ֵġ���ֻ���ɽӿ������е���
 * 	default���������νӿ��еķ�����default���εķ����������Ĭ�ϵ�ʵ�ֲ��֡���ʱʵ������ʵ�ֽӿڵ�ʱ�򣬿��Զ���Щ����������д��Ҳ���Բ���д
 * 		
 * */
public class Interface_review {
	public static void main(String[] args){
		myfirstInt mfi=new Impcls();//����ת��
		mysecondInt msi=new Impcls2();
		Impcls2 imp2;
		if(msi instanceof Impcls2){
			System.out.println("����ת��");
			imp2=(Impcls2)msi;
		}
		
		
		NewInt newInt=new Newcls();
		NewInt newInt2=new Newcls2();
		NewInt.Intfunc();
		newInt.Intfunc2();
		newInt2.Intfunc2();
		
		
	}
}

//
interface myfirstInt{
	  int a=10;//�����ʼ������Ϊ�ӿ��е������Ǿ�̬����
	 
	 void basefunc();//�����з����壬Ĭ���ǳ��󷽷�
	
}

interface mysecondInt{
	public  abstract void basefunc2();//�����������ӿ��еĸ÷���ͬ��������ʵ����ֻ��ʵ��һ��
	
}

//�ӿ�������
interface NewInt{
	public static void Intfunc(){
		System.out.println("�ӿھ�̬������");
	}
	
	public default void Intfunc2(){
		System.out.println("�ӿ�default������");
	}
}

//����ӿ�ʵ����
//ʵ���������д�ӿ������еĳ��󷽷������߶���Ϊ������
class Impcls implements myfirstInt
{

	@Override
	public void basefunc() {
		System.out.println("ʵ����ʵ�ֽӿڷ�����");
		
	}
		
		}

//ʵ�ֶ���ӿڣ��ӿ�֮���ö��ŷָ�
class Impcls2 implements myfirstInt,mysecondInt{

	@Override
	public void basefunc() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void basefunc2() {
		// TODO Auto-generated method stub
		
	}
	
	

	
}

class Newcls implements NewInt{
	
}

class Newcls2 implements NewInt{

	@Override
	public void Intfunc2() {
		// TODO Auto-generated method stub
		System.out.println("ʵ����ʵ��default����");
	}
	
}

