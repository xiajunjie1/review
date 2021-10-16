package review;

public class Test {
	public static void main(String[] args){
		LinkedListRealize<String> llr=new LinkedListRealize<String>();
		llr.add("test");
		llr.add("test2");
		llr.add("test3");
		llr.add(0,"test0");
		llr.add(4,"test4");
		llr.show();
		//llr.revershow();
		//System.out.println(llr.remove(9));
		llr.set(1, "test1");
		llr.show();
		llr.revershow();
		
	}
}
