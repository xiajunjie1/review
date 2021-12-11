package review;

/*
 * 用接口实现回调
 * 当有些时候，一个类在调用另一个类执行某些较长时间的操作的时候，另一个类执行完了要调用本类的方法来通知本类已经完成操作
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
	//计算，该类调用工具类完成计算
	public void count(int end){
		Computer_Counter cc=new Computer_Counter();
		cc.count(end, this);//传入this，便于计算完成后回调接口重写方法。
	}
	@Override
	public void Countcomplete(int result) {
		System.out.println(this.name+"的计算结果是："+result);
		
	}

	
}


//工具类
//要调用该类的方法，需要满足条件，该条件可以定义接口
class Computer_Counter{
	public void count(int end,Counter_rule cr){
		int result;
		if(end<=0){
			result=0;
		}
		if(end==1)result=1;
		result=(1+end)*end/2;
		//回调
		cr.Countcomplete(result);
	}
}


interface Counter_rule{
	//完成计算后，通知已经完成计算
	public abstract void Countcomplete(int result);
}