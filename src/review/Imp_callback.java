package review;

/*
 * �ýӿ�ʵ�ֻص�
 * ����Щʱ��һ�����ڵ�����һ����ִ��ĳЩ�ϳ�ʱ��Ĳ�����ʱ����һ����ִ������Ҫ���ñ���ķ�����֪ͨ�����Ѿ���ɲ���
 * 
 * 
 * */

public class Imp_callback {
	public static void main(String[] args){
		Human_Counter hc=new Human_Counter("xia");
		hc.count(5);
	}
}

class Human_Counter implements Counter_rule{
	public String name;
	
	public Human_Counter(String name){
		this.name=name;
	}
	//���㣬������ù�������ɼ���
	public void count(int end){
		Computer_Counter cc=new Computer_Counter();
		cc.count(end, this);//����this�����ڼ�����ɺ�ص��ӿ���д������
	}
	@Override
	public void Countcomplete(int result) {
		System.out.println(this.name+"�ļ������ǣ�"+result);
		
	}

	
}


//������
//Ҫ���ø���ķ�������Ҫ�������������������Զ���ӿ�
class Computer_Counter{
	public void count(int end,Counter_rule cr){
		int result;
		if(end<=0){
			result=0;
		}
		if(end==1)result=1;
		result=(1+end)*end/2;
		//�ص�
		cr.Countcomplete(result);
	}
}


interface Counter_rule{
	//��ɼ����֪ͨ�Ѿ���ɼ���
	public abstract void Countcomplete(int result);
}