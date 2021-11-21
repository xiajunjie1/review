package review;

/*
 * Lambda���ʽѧϰ
 * 
 * 	java8��������
 * 	�ӱ�����������lambda���ʽ��һ����������������ʹ���������������ʵ�ֽӿ��еķ������Խӿڽ��зǳ��򵥵�ʵ��
 * 	ͨ����˵��lambda���ʽ����Ϊ�˼򻯽ӿڶ�ʵ�ֵ�
 * 	ʵ�ֽӿڵķ���������ʵ���ࡢ�����ڲ��࣬��lambda���ʽ�������ַ�������
 * 
 * 	���������еĽӿڶ�����lambda���ʽʵ�ֵ�
 * 		�ӿڵ��б���Ҫʵ�ֵķ�������ֻ��һ���Ľӿڣ��ſ�����lambda���ʽʵ�֣���ֻ��lambda���ʽֻ��ʵ�ֺ���ʽ�ӿ�
 * 		
 * 	����ʽ�ӿ�
 * 		һ���ӿ���Ҫ�����ʵ�ֵĳ��󷽷�����ֻ��һ���������Ľӿڳ�֮Ϊ����ʽ�ӿ�
 * 		������@FunctionalInterfaceע�⣬��ȷ���ӿ���һ������ʽ�ӿ�
 * 
 * lambda���ʽ���﷨��
 * 		����lambda��ʾ�ı�����һ�������������������ǲ���Ҫ��ע�ӿ�����Ҫʵ�ֵķ��������֣�����ֻ��Ҫ��ע�����б�ͷ�����
 * 		(�����б�)->{	������...	};
 * 		�����б�Ҫ���ֺͽӿ��ж���ķ����β��б�һ��
 * 		����з���ֵ��Ҫ�ڷ�������return
 * 		->�����ָ������б�ͷ�����
 * 
 * lambda���ʽ���﷨���ף�
 * 		���Լ�lambda���ʽ�Ļ����﷨
 * 		�������ֵľ���
 * 			��дlambda���ʽ��ʱ�򣬲����б��еĲ������Ϳ��Բ�д(a,b)->{....}
 * 			�����б��еĲ���������ֻ��һ����ʱ��С���ſ���ʡ��
 * 		�����岿�ֵľ���
 * 			��������ֻ��һ�仰�������ſ���ʡ�ԣ�����һ�仰��return��䣬��ôʡ�Դ����ŵ�ͬʱ��returnҲ����ʡ��
 * 
 * lambda���ʽ���������ã�
 * 		��lambda���ʽ�в��˳��ֽ�Ϊ���ӵ��߼��������������������԰ѷ����嵥��дһ��������lambda���ʽֱ�����ø÷�������
 * 		����һ�����ڵķ���������lambda���ʽ��Ҫ��ɵ�ʵ��
 * 
 * ���󷽷�����������
 * 		
 * 
 * 	
 * */

public class Lambda_test {
public static void main(String[] args){
	
	Noparanoret noparanoret = ()->{
		System.out.println("�޲������޷���ֵ!");
	};
	noparanoret.noreply();
	
	//lambda���ʽ��д���������б�Ҳ����д��(a)����(int a)
	Oneparanoret oneparanoret = a->{
		System.out.println("һ���������޷���ֵ������Ϊ��"+a);
	};
	oneparanoret.oneparanoreply(10);
	
	//�������д
	Multparanoret multparanoret = (a,b)-> System.out.println("��������޷���ֵ������a: "+a+"����b: "+b);
	multparanoret.multparanoreply(10, 12);
	
	//��return�ķ������д
	Noparahasret noparahasret = ()->10;
	int res1=noparahasret.reply();
	System.out.println(res1);
	
	Oneparahasret oneparahasret = (a)->{
		
		System.out.println("һ���������з���ֵ��");
		return a;
	};
	int res2=oneparahasret.oneparareply(10);
	System.out.println(res2);
	
	Multparahasret multparahasret = (a,b)->{
		System.out.println("��������з���ֵ");
		return a+b;
	};
	int res3=multparahasret.multparahasret(10, 12);
	System.out.println(res3);
	
	//lambda���ʽ��̬����������
	Caculator caculator = LambdaCall::lambda1;//�ȼ���(x,y)->LambdaCall.lambda1(x,y)
	System.out.println(caculator.caculat(10, 12));
	
	//ʵ������������
	Caculator caculator2=new LambdaCall()::lambda2;
	System.out.println(caculator2.caculat(22, 24));
	
	//lambda�����޲ι���
	GetPerson getperson=myPerson::new;
	GetPerson2 getperson2=myPerson::new;
	GetPerson3 getperson3=myPerson::new;
	getperson.getPerson();
	getperson2.getPerson("xia");
	getperson3.getPerson("jun", 12);
	
	
	//lambda���ʽ���󷽷����������
	//myinter myint1=(mct)->mct.getName();�������д���ȼ�
	myinter myint1 = myclsTest::getName;
	
	//myinter2 myint2= (mct,a)->mct.setName(a);������д���ȼ�
	myinter2 myint2= myclsTest::setName;
	
	myclsTest mct=new myclsTest();
	myint2.set(mct, "jie");
	System.out.println(myint1.get(mct));
	
	
}

	
//����ʽ�ӿ�
@FunctionalInterface
interface funcint{
	
	public abstract void absfunc();
}

interface funcint2 extends funcint{
	//��Ȼ�ýӿ���û�з������������̳еĸ��ӿ�����һ��������Ҳ����Ҫʵ�ֵģ�������Ҳ�����Ǻ���ʽ�ӿ�
}

interface funcint3{
	//�ýӿ��е�test2����default���Σ�������Ĭ�ϵ�ʵ�ַ����壬ʵ���൱�п���ʵ�ָ÷�����Ҳ���Բ�ʵ�ָ÷���
	//���Ա���ʵ�ֵķ�������ֻ��һ��test
	void test();
	default void test2(){}
}

interface funcint4{
	//�ýӿ��е�toString������Object����ʵ���˵ķ�����ʵ������Դ�Object���м̳У����Ǳ���ʵ��
	//���Ըýӿڵ��б���ʵ�ֵķ�������ֻ��һ��test3
	void test3();
	String toString();
}
//�޲��޷���ֵ�ĺ���ʽ�ӿ�
@FunctionalInterface
interface Noparanoret{
	void noreply();
}

//һ�������޷���ֵ�Ľӿ�
@FunctionalInterface
interface Oneparanoret{
	void oneparanoreply(int a);
}

//����������޷���ֵ
interface Multparanoret{
	void multparanoreply(int a,int b);
}

//�޲������з���ֵ
interface Noparahasret{
	int reply();
}
//һ���������з���ֵ
interface Oneparahasret{
	int oneparareply(int a);
}

interface Multparahasret{
	int multparahasret(int a,int b);
}
@FunctionalInterface
interface Caculator{
	public int caculat(int a,int b);
}
@FunctionalInterface
interface GetPerson{
	myPerson getPerson();
}

@FunctionalInterface
interface GetPerson2{
	myPerson getPerson(String a);
}
@FunctionalInterface
interface GetPerson3{
	myPerson getPerson(String a,int b);
}

@FunctionalInterface
interface myinter{
	String get(myclsTest mct);
}
@FunctionalInterface
interface myinter2{
	void set(myclsTest mct,String a);
}


//�Ǻ���ʽ�ӿ�
//@FunctionalInterface---�ᱨ��
interface unfuncint{
	public abstract void absfunc();
	public abstract void absfunc2();
}

interface unfuncint2{
	
}


}


/**
 * ���ڴ�ű����õķ���
 * */
class LambdaCall{
	
	public static int lambda1(int x,int y){
		if(x>y)return x-y;
		else if(x<y)return y-x;
		return x+y;
	}
	
	
	public int lambda2(int x,int y){
		if(x>y)return x-y;
		else if(x<y)return y-x;
		return x+y;
	}
	
}

class myPerson{
	String name;
	int age;
	
	public myPerson(){
		System.out.println("�޲ι���");
	}
	
	public myPerson(String name){
		this.name=name;
		System.out.println("һ�ι���"+name);
	}
	
	public myPerson(String name,int age){
		this.name=name;
		this.age=age;
		System.out.println("��ι���"+name+","+age);
		
	}
}

class myclsTest{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
