package review;

/*
 * 泛型在接口中的应用
 * 
 * 与在类中的应用相类似，子接口继承也和子类继承相同
 * */
public interface Review03_i<T> {

	public void compareTo(T o1,T o2);
}

class Re03achieve implements Review03_i<Integer>{
	public void compareTo(Integer o1,Integer o2){
		if(o1>o2){
			System.out.println("o1---"+o1);
		}
		
		else if(o1<o2){
			System.out.println("o2---"+o2);
		}
		else {
			System.out.println("equal---"+o1);
		}
	}
}