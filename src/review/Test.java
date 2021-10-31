package review;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Test {
	public static void main(String[] args){
		LinkedListRealize<String> llr=new LinkedListRealize<String>();
		llr.add("test");
		llr.add("test2");
		llr.add("test3");
		llr.add(0,"test0");
		llr.add(4,"test4");
		//llr.show();
		//llr.revershow();
		//System.out.println(llr.remove(9));
		llr.set(1, "test1");
		//llr.show();
		//llr.revershow();
		
		
		List<String> strList =new ArrayList<String>();
		strList.add("abc");
		strList.add("defg");
		strList.add("b");
		Comparator<String> c=new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.length()-o2.length();
			}
			
		};
		for(String str : strList){
			System.out.println(str);
		}
		strList.sort(c);
		for(String str : strList){
			System.out.println(str);
		}
		
	}
}
