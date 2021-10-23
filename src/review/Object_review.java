package review;

/*
 * Object��
 * 	Java��������Ļ���
 * 
 * Object���еķ���
 * 	toString()
 * 		Object��toStringĬ�ϵ�ʵ���Ƿ���getClass().getName()+"@"+Integer.toHexString(hashCode())
 * 		����Ҫ�ѵ�ǰ����ת�����ַ�������ʽ�����Զ����ø÷���
 * 
 * 	equals()
 * 		���������������ıȽϣ�==�ǱȽϱ����ĵ�ַ
 * 
 * 	Objects.euqals(obj1,obj2)
 * 	ObjectsΪ�����࣬�����ù������е�euqals������������������ıȽϣ��÷������Ա���obj1.equals(obj2),obj1Ϊnull�����
 * 
 * 	getClass()
 * 	hashCode()
 * 
 * 
 * */

public class Object_review {
	public static void main(String[] args){
		System.out.println(new Subobject("test"));
		String str=new Subobject("test")+"2";
		System.out.println(str);
		Subobject sub1=new Subobject(new String("abc"));
		Subobject sub2=new Subobject(new String("abc"));
		System.out.println(sub1.equals(sub2));
	}
	
}

class Subobject{
	
	String name;
	public Subobject(String name){
		this.name=name;
	}
	@Override
	public String toString(){
		return this.name;
	}
	
	
	//��дequals,����Ա������������ͬ���������
	/*
	 * �Զ���Ƚϵ�ʱ����Ҫ���صĹ淶:
	 * 	�����ַ��ͬ����һ������true
	 * 	���o��null����һ������false
	 * 	��������������Ͳ�ͬ����һ������false
	 * 	���a.equals(b)��������b.equals(a)����
	 * 	���a.equals(b)������b.equals(c)��������a.equals(c)����
	 * 
	 * */
	@Override
	public boolean equals(Object o){
		if(this==o)return true;
		if(o==null || this.getClass()!=o.getClass())return false;
		
		Subobject sub=(Subobject)o;
		return this.name.equals(sub.name);
		
		
		
	}
}
