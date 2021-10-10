package review;
import java.util.*;
//collection���ӽӿ�
//ʵ����ArrayList,LinkedList
//ArrayList�ײ���ʹ�õ����飬��ַ����������Ч�ʸ�
//LinkedList�ײ�ʹ�õ���˫��������ɾЧ�ʸ���
//List�̳���Collection���еķ���
public class ListTest {
public static void main(String[] args){
	List<String> list=new ArrayList<String>();
	//list���еķ���
	list.add("abc");
	list.add("ppp");
	list.add("def");
	list.add(3,"gkj");//3������ĸ�Ԫ�أ�index����ڵ�ǰ�������һ��Ԫ�غ�һ������ǰһ��Ԫ��֮�䲻����null
	list.add("def");
	System.out.println(list);
	list.replaceAll(ele -> ele+".txt");
	System.out.println(list);
	int index = list.indexOf("ppp.txt");
	System.out.println("������"+index);
	int index2=list.lastIndexOf("def.txt");//����ж����ͬ��Ԫ�أ��������һ��������
	System.out.println("����2��"+index2);
	
	List<String> templist=new ArrayList<String>();
	templist.add("fff");
	templist.add("er");
	templist.add("fghjk");
	templist.add("a");
	//����
	//templist.sort(new mycompare());//��Ҫ����Comparator�ӿڵ�ʵ����
	templist.sort((ele1,ele2) -> ele1.length()-ele2.length());//ʹ��lambda���ʽʵ��
	System.out.println(templist);
	int len=templist.size();
	
	System.out.println(templist.get(len-1));
	
	//List���еĵ�������Ĭ�ϵĵ�����ֻ��ʵ�ֱ�����ɾ��Ԫ�أ����õ���������ʵ����ɾ��
	ListIterator<String> lit=templist.listIterator();
	while(lit.hasNext()){
		String ele=lit.next();
		if(ele.equals("er")){
			lit.set("ers");
			lit.add("jkl");
		}
		else if (ele.equals("fff")){
			lit.remove();
		}
	}
	System.out.println(templist);
}



}

class mycompare implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		// o1�����������һ��Ԫ�أ�o2����o1����һ��Ԫ�أ��÷���ֵ����������������
		//����ֵ�Ľ�����ڵ���0��ʾ������λ�ã�������Ҫ����λ��
		return o1.length()-o2.length();
	}
	
}
