package review;
/*������
 *	�����಻�ܱ�ʵ��������ֻ���ṩ��������Ĺ��в��� 
 *
 *Ӧ�ó�����
 *	���������������һЩ�򵥵Ĺ����ƶ����ڳ��������ƶ�һЩ����Ҫ���������ʵ�֣�Լ�������������Ϊ
 *
 *�����ԣ�
 *	����java�У����ǵ��̳еģ����Ե�һ�����ܵ����ֹ����Լ��ʱ�����޷�����Ҫ�󡣴�ʱ����ʹ�ýӿڽ����������ӹ�����ƶ�
 *
 * */

public class Abscls_review {
	public static void main(String[] args){
		Basecls basecls=new Soncls();
		Soncls soncls=new Soncls();
	}
}

//���������
//abstract����classǰ�����Ƿ���Ȩ�����η�����public abstract class
abstract class Basecls{
	public String field1;
	public int field2;
	
	public void func1(){}
	//���ڲ��ܶ�������ݺ���ͨ����ͬ�����Ա��������Ա������
	//���ڲ�Ҳ���Զ��幹�췽������ʵ���������������Ƕ��Ǹ������õ�
	
	//���ڸ���ķ�������������������д���ǣ����Զ������Ե���Щ����
	//������󷽷�
	//���󷽷�ֻ�ܰ����ڳ�������
	public abstract void absfunc1();
	
}

//����
class Soncls extends Basecls{

	//�����ж����˳��󷽷��Ļ�������Ҫ��Ҳ����Ϊ�����࣬Ҫ���ͱ���Ҫʵ�ָó��󷽷�
	@Override
	public void absfunc1() {
		
		System.out.println("������д");
	}
	
}