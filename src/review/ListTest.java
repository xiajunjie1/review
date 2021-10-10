package review;
import java.util.*;
//collection的子接口
//实现类ArrayList,LinkedList
//ArrayList底层是使用的数组，地址连续，查找效率高
//LinkedList底层使用的是双向链表，增删效率更高
//List继承了Collection所有的方法
public class ListTest {
public static void main(String[] args){
	List<String> list=new ArrayList<String>();
	//list特有的方法
	list.add("abc");
	list.add("ppp");
	list.add("def");
	list.add(3,"gkj");//3代表第四个元素，index最大在当前集合最后一个元素后一个，与前一个元素之间不能有null
	list.add("def");
	System.out.println(list);
	list.replaceAll(ele -> ele+".txt");
	System.out.println(list);
	int index = list.indexOf("ppp.txt");
	System.out.println("索引："+index);
	int index2=list.lastIndexOf("def.txt");//如果有多个相同的元素，返回最后一个的索引
	System.out.println("索引2："+index2);
	
	List<String> templist=new ArrayList<String>();
	templist.add("fff");
	templist.add("er");
	templist.add("fghjk");
	templist.add("a");
	//排序
	//templist.sort(new mycompare());//需要传入Comparator接口的实现类
	templist.sort((ele1,ele2) -> ele1.length()-ele2.length());//使用lambda表达式实现
	System.out.println(templist);
	int len=templist.size();
	
	System.out.println(templist.get(len-1));
	
	//List特有的迭代器，默认的迭代器只能实现遍历中删除元素，而该迭代器可以实现增删改
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
		// o1代表集合中最后一个元素，o2代表o1的上一个元素，该返回值代表按长度升序排序
		//返回值的结果大于等于0表示不交换位置，否则需要交换位置
		return o1.length()-o2.length();
	}
	
}
