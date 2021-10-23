package review;

/*
 * �������ģʽ
 * һ��������ֻ��һ�������ڳ��������ģ�飬��ȡ�������Ķ�������ͬ�Ķ���
 * ��������ģʽ
 * ��������ģʽ
 * 
 * ��Ƚ϶��ԣ���������ģʽ��һ���Ŀؼ��˷�
 * */
public class SingletonTest {
	public static void main(String[] args){
		for(int i=0;i<20;i++){
			Boss b=Boss.getInstance();
			System.out.println(i+":"+b);
			BigBoss bb=BigBoss.getInstance();
			System.out.println(i+":"+bb);
		}
	}
}
/*
 * �����࣬�������κεط�������Ķ�������ֻ��һ��
 * 	1.˽�л����췽�����ž�������ʵ��������Ŀ�����
 * 	2.�ṩһ��˽�еġ���̬�ġ���ǰ��Ķ��󣬲�ʵ����
 * 	3.�ṩһ��publicȨ�޵ľ�̬��������������һ����ǰ��Ķ���
 * 	����ʽ����ģʽ�������Ƿ��õ���ʵ�������ȳ�ʼ���ö���
 * */
class Boss{
	private static Boss boss=new Boss();//��ʵ������ֻ�������һ�μ��ص�ʱ��Ż����С�֮���ǲ��������е�
	
	private Boss(){
		System.out.println("ʵ������һ������");//���ö�� Boss.getInstance(),�����ֻ���ӡһ�Σ�˵��ֻ������һ�γ�ʼ��
	}
	
	public static Boss getInstance(){

		return boss;
	}
}
/*
 * ����ʽ����
 * ��������������ʱ���ж�������Ƿ�Ϊ�գ���Ϊ���ڽ���ʵ����
 * */
class BigBoss{
	private static BigBoss bigboss;
	private BigBoss(){
		System.out.println("ʵ����һ������");
	}
	
	public static BigBoss getInstance(){
		if(bigboss==null){
			bigboss=new BigBoss();
		}
		
		return bigboss;
	}
}
