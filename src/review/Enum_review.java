package review;

/*ö��
 * һ���Զ����������������
 * 	��������һЩȡֵ���޵Ķ���
 * 
 * ö����Object���������࣬��ö�ٲ����Ա��̳�
 * ���Ե�ö�ٶ���Enum������
 * 
 * ö��ֵ
 * 	������ö�ٶ����ڵ�ö��ֵ����ʵ�����ɸ���̬����
 * 	Male�൱��public static final Gender Male=new Gender()
 * 
 * ö���еķ�������
 * 	�������ͨ������һ����
 * 
 * ö�ٷ�����д
 * 	��дObject�ķ���
 * 	ö��Ҳ����ʵ�ֽӿڣ���д�ӿڵķ���
 * 
 * */

public class Enum_review {
	
	public static void main(String[] args){
		Human2 h2=new Human2();
		h2.setAge(22);
		h2.setGender(Gender.Male);//��ö�ٶ���ֵ��ö������.ö��ֵ
		h2.setName("xia");
		System.out.println(h2);
		
		//��ȡö�ٶ���
		Gender female=Gender.Female;
		//ͨ��������ʳ�Ա
		female.name="test";
		System.out.println(female+"----"+female.name);
		female.show();
		System.out.println(++Gender.count);
	}
}

enum Gender{
	//�����ö�ٶ����У����п��ܵ�ֵ���оٳ���
	//ö���е�Ԫ��Ҳ�Ǳ�ʶ������ѭ���շ�������
	//��ö����������ԣ�����Ҫ��ö��ֵĩβ���Ϸֺ�
	//��ӵĳ�Ա��������ö��ֵ���·�
	Male,Female,other("������");//��Ԫ���൱�ڵ��ù��췽��
	public String name;
	public void show(){
		System.out.println("ö�ٷ���");
	}
	public static int count;//ö���еľ�̬����
	
	Gender(){
		//ö���еĹ��췽����Ĭ����˽�����εģ�Ҳֻ����private����
		//�ж��ٸ�ö��Ԫ�أ��÷����ͻᱻ���ö��ٴΣ���ʼ����
		System.out.println("һ��ö�ٶ���ʵ����");
	}
	
	Gender(String name){
		this.name=name;
	}
}

class Human2{
	private String name;
	private int age;
	//�����Ա�ֻ��������Ϊ�����ƴ�ֵ��������ö����Ϊ��Ա����
	private Gender gender;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name: "+this.name+" age: "+this.age+" gender: "+this.gender;
	}
	
	
}