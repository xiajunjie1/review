package review;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/*
 * 常用类
 * 	Math
 * 	Random
 * 	BigInteger、BigDecimal：用来描述非常非常大的数字
 * 	Date（在java.util中）
 * 	SimpleDateFormat:对日期时间进行格式化
 * 		将date对象，转成指定格式的时间字符串
 * 		将一个指定格式的时间字符串，转成Date对象
 * 	Calendar:描述时间、日期的类，可用它来取代Date中被废弃的方法
 * 
 * */
public class CommCls_review {
	
	public static void main(String[] args){
		//MathTest();
		//randomTest();
		//randomSeed();//每次运行程序，生成的10个随机数是相同的，因为种子是相同的，这是一种假随机
		//BignumberTest();
		//DateTest();
		//SimpleDateFormatTest();
		CalendarTest();
	}
	
	/**
	 * Math类的使用
	 * 相关数学计算操作
	 * */
	private static void MathTest(){
		System.out.println(Math.PI);//3.14159265...
		System.out.println(Math.E);//对数常量，2.7....
		
		//常用方法
		int val=Math.abs(-20);//求绝对值,有几个重载方法，参数可以是int，float，long，double
		System.out.println(val);
		System.out.println(Math.max(9.8, 10));//返回较大的值，.min(a,b)是返回较小的值
		System.out.println(Math.round(3.6415926));//四舍五入
		System.out.println(Math.ceil(3.14));//向上取整
		System.out.println(Math.floor(3.14));//向下取整
		System.out.println(Math.pow(2, 3));//2^3
		System.out.println(Math.sqrt(4));//计算一个数字的平方根
		System.out.println(Math.random());//生成0-1之内的随机浮点数字
		//生成0-100范围内的整数：Math.random()*100
		//生成50-100范围内的整数：Math.random()*50+50
		
	}
	
	/**
	 * Random类
	 * 	使用之前，需要先导入该类的包java.util.Random
	 * 	生成随机数
	 * */
	
	private static void randomTest(){
		Random r=new Random();
		System.out.println(r.nextInt());//生成一个[-2^31,2^32-1]范围内的整数
		System.out.println(r.nextInt(100));//生成[0,100)范围内整数
		System.out.println(r.nextFloat());//生成[0,1)范围内的浮点数
		System.out.println(r.nextDouble());//生成[0,1)范围内的double类型的随机数
		System.out.println(r.nextBoolean());//生成一个随机的Boolean的数据
	} 
	
	/**
	 * 带有随机数种子的生成随机数
	 * 随机数是有一个固定算法生成的，如果带入一个种子到这个随机算法当中，能够生成一个随机数列。
	 * 由于随机算法是相同的，故会产生一个“bug”，即带入相同的种子，那么生成的随机数列也是相同的
	 * */
	private static void randomSeed(){
		//通过随机种子，实例化Random对象
		Random r = new Random(10);
		for(int i=0;i<10;i++){
			System.out.print(r.nextInt(100)+",");
		}
		
		//该方法的无参构造，会利用当前系统时间生成一个种子，即每次运行程序，种子都不同
	}
	
	/**
	 * 大数字方法测试
	 * */
	private static void BignumberTest(){
		BigInteger num1=new BigInteger("1234567891234567891234567891234");
		BigInteger num2=new BigInteger("2345697034502304924123834345566");
		
		System.out.println(num1);
		//四则运算
		//加法
		System.out.println(num1.add(num2));
		//减法
		System.out.println(num2.subtract(num1));
		//乘法
		System.out.println(num1.multiply(num2));
		//除法
		System.out.println(num2.divide(num1));
		//保留商和余数
		BigInteger[] res=num2.divideAndRemainder(num1);
		System.out.println("商："+res[0]+",余数："+res[1]);
		
		
	}
	
	
	/**
	 * 日期类测试
	 * */
	private static void DateTest(){
		Date d=new Date();
		System.out.println(d);
		//获取时间戳---从1970.1.1 0:0:0到现在的一个毫秒数
		long timestamp=d.getTime();
		System.out.println(timestamp);
		//设置时间戳
		d.setTime(1625671858619l);
		System.out.println(d);
		
		//时间比较
		Date d2=new Date(1635671947065L);
		System.out.println(d.before(d2));
		System.out.println(d.after(d2));
		System.out.println(d.equals(d2));
	}
	
	/**
	 * SimpleDateFormat类测试
	 * */
	private static void SimpleDateFormatTest(){
		//Date转成String
		Date d= new Date();
		//指定格式化的时间字符串，转成形如2021年10月31日 17:33:33的形式
		String format="yyyy年MM月dd日 HH:mm:ss";
		//通过定义好的格式，初始化SimpleDateFormat对象
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		//将Date格式化并返回String
		String now=sdf.format(d);
		System.out.println(now);
		
		//将指定格式的字符串转成一个Date对象
		String timestr="2021-10-31 17:44:44";
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d2=sdf2.parse(timestr);
			System.out.println(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Calendar测试
	 * */
	private static void CalendarTest(){
		//Calendar是一个抽象类，对象的获取是通过调用Calendar的静态方法
		//该静态方法是实例化了一个Calendar的一个子类，然后向上转型成了Calendar
		Calendar cal=Calendar.getInstance();
		//获取时间戳
		long timestamp=cal.getTimeInMillis();
		System.out.println(timestamp);
		//获取Date对象
		Date date=cal.getTime();
		System.out.println(date);
		
		//获取年，同理可获取月，日
		int year = cal.get(cal.YEAR);
		System.out.println(year);
		//获取今天是今年的第几天，同理可获取今天是本月的第几天，本周的第几天等
		int day_of_year=cal.get(cal.DAY_OF_YEAR);
		System.out.println(day_of_year);
		
		//修改指定属性值
		cal.set(cal.YEAR, 2022);
		System.out.println(cal.getTime());
		
		cal.set(2025, 10,1);//设置年月日 2025-11-1,月份是从0开始算起的
		System.out.println(cal.getTime());
		
		//calendar也支持Before after equals
		
		//对日期的某些属性值做加法
		cal.add(cal.YEAR, 3);
		cal.add(cal.MONTH,-1);
		cal.add(cal.DAY_OF_MONTH, 5);
		System.out.println(cal.getTime());
		
	}
}
