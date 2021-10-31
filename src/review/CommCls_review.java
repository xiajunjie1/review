package review;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/*
 * ������
 * 	Math
 * 	Random
 * 	BigInteger��BigDecimal�����������ǳ��ǳ��������
 * 	Date����java.util�У�
 * 	SimpleDateFormat:������ʱ����и�ʽ��
 * 		��date����ת��ָ����ʽ��ʱ���ַ���
 * 		��һ��ָ����ʽ��ʱ���ַ�����ת��Date����
 * 	Calendar:����ʱ�䡢���ڵ��࣬��������ȡ��Date�б������ķ���
 * 
 * */
public class CommCls_review {
	
	public static void main(String[] args){
		//MathTest();
		//randomTest();
		//randomSeed();//ÿ�����г������ɵ�10�����������ͬ�ģ���Ϊ��������ͬ�ģ�����һ�ּ����
		//BignumberTest();
		//DateTest();
		//SimpleDateFormatTest();
		CalendarTest();
	}
	
	/**
	 * Math���ʹ��
	 * �����ѧ�������
	 * */
	private static void MathTest(){
		System.out.println(Math.PI);//3.14159265...
		System.out.println(Math.E);//����������2.7....
		
		//���÷���
		int val=Math.abs(-20);//�����ֵ,�м������ط���������������int��float��long��double
		System.out.println(val);
		System.out.println(Math.max(9.8, 10));//���ؽϴ��ֵ��.min(a,b)�Ƿ��ؽ�С��ֵ
		System.out.println(Math.round(3.6415926));//��������
		System.out.println(Math.ceil(3.14));//����ȡ��
		System.out.println(Math.floor(3.14));//����ȡ��
		System.out.println(Math.pow(2, 3));//2^3
		System.out.println(Math.sqrt(4));//����һ�����ֵ�ƽ����
		System.out.println(Math.random());//����0-1֮�ڵ������������
		//����0-100��Χ�ڵ�������Math.random()*100
		//����50-100��Χ�ڵ�������Math.random()*50+50
		
	}
	
	/**
	 * Random��
	 * 	ʹ��֮ǰ����Ҫ�ȵ������İ�java.util.Random
	 * 	���������
	 * */
	
	private static void randomTest(){
		Random r=new Random();
		System.out.println(r.nextInt());//����һ��[-2^31,2^32-1]��Χ�ڵ�����
		System.out.println(r.nextInt(100));//����[0,100)��Χ������
		System.out.println(r.nextFloat());//����[0,1)��Χ�ڵĸ�����
		System.out.println(r.nextDouble());//����[0,1)��Χ�ڵ�double���͵������
		System.out.println(r.nextBoolean());//����һ�������Boolean������
	} 
	
	/**
	 * ������������ӵ����������
	 * ���������һ���̶��㷨���ɵģ��������һ�����ӵ��������㷨���У��ܹ�����һ��������С�
	 * ��������㷨����ͬ�ģ��ʻ����һ����bug������������ͬ�����ӣ���ô���ɵ��������Ҳ����ͬ��
	 * */
	private static void randomSeed(){
		//ͨ��������ӣ�ʵ����Random����
		Random r = new Random(10);
		for(int i=0;i<10;i++){
			System.out.print(r.nextInt(100)+",");
		}
		
		//�÷������޲ι��죬�����õ�ǰϵͳʱ������һ�����ӣ���ÿ�����г������Ӷ���ͬ
	}
	
	/**
	 * �����ַ�������
	 * */
	private static void BignumberTest(){
		BigInteger num1=new BigInteger("1234567891234567891234567891234");
		BigInteger num2=new BigInteger("2345697034502304924123834345566");
		
		System.out.println(num1);
		//��������
		//�ӷ�
		System.out.println(num1.add(num2));
		//����
		System.out.println(num2.subtract(num1));
		//�˷�
		System.out.println(num1.multiply(num2));
		//����
		System.out.println(num2.divide(num1));
		//�����̺�����
		BigInteger[] res=num2.divideAndRemainder(num1);
		System.out.println("�̣�"+res[0]+",������"+res[1]);
		
		
	}
	
	
	/**
	 * ���������
	 * */
	private static void DateTest(){
		Date d=new Date();
		System.out.println(d);
		//��ȡʱ���---��1970.1.1 0:0:0�����ڵ�һ��������
		long timestamp=d.getTime();
		System.out.println(timestamp);
		//����ʱ���
		d.setTime(1625671858619l);
		System.out.println(d);
		
		//ʱ��Ƚ�
		Date d2=new Date(1635671947065L);
		System.out.println(d.before(d2));
		System.out.println(d.after(d2));
		System.out.println(d.equals(d2));
	}
	
	/**
	 * SimpleDateFormat�����
	 * */
	private static void SimpleDateFormatTest(){
		//Dateת��String
		Date d= new Date();
		//ָ����ʽ����ʱ���ַ�����ת������2021��10��31�� 17:33:33����ʽ
		String format="yyyy��MM��dd�� HH:mm:ss";
		//ͨ������õĸ�ʽ����ʼ��SimpleDateFormat����
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		//��Date��ʽ��������String
		String now=sdf.format(d);
		System.out.println(now);
		
		//��ָ����ʽ���ַ���ת��һ��Date����
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
	 * Calendar����
	 * */
	private static void CalendarTest(){
		//Calendar��һ�������࣬����Ļ�ȡ��ͨ������Calendar�ľ�̬����
		//�þ�̬������ʵ������һ��Calendar��һ�����࣬Ȼ������ת�ͳ���Calendar
		Calendar cal=Calendar.getInstance();
		//��ȡʱ���
		long timestamp=cal.getTimeInMillis();
		System.out.println(timestamp);
		//��ȡDate����
		Date date=cal.getTime();
		System.out.println(date);
		
		//��ȡ�꣬ͬ��ɻ�ȡ�£���
		int year = cal.get(cal.YEAR);
		System.out.println(year);
		//��ȡ�����ǽ���ĵڼ��죬ͬ��ɻ�ȡ�����Ǳ��µĵڼ��죬���ܵĵڼ����
		int day_of_year=cal.get(cal.DAY_OF_YEAR);
		System.out.println(day_of_year);
		
		//�޸�ָ������ֵ
		cal.set(cal.YEAR, 2022);
		System.out.println(cal.getTime());
		
		cal.set(2025, 10,1);//���������� 2025-11-1,�·��Ǵ�0��ʼ�����
		System.out.println(cal.getTime());
		
		//calendarҲ֧��Before after equals
		
		//�����ڵ�ĳЩ����ֵ���ӷ�
		cal.add(cal.YEAR, 3);
		cal.add(cal.MONTH,-1);
		cal.add(cal.DAY_OF_MONTH, 5);
		System.out.println(cal.getTime());
		
	}
}
