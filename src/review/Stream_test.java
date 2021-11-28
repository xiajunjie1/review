package review;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.*;

/*
 * ���ϵ���ʽ���
 * 	JDK1.8��������
 * 	Stream�ǲ������ϵ���ǿ�������Ǽ��ϵ�Ԫ�أ�����һ�����ݽṹ�����������ݵĴ洢�ġ�
 * 	������һ�������������Ե�����������е�ÿһ��Ԫ�أ����Ҳ���ѭ��
 * 
 * 	�����Խ�����Դ�����ϡ�����...)�е����ݶ�����Stream�У��ڶ����е����ݽ���һЩ�����Ĵ���
 * 	����Ľ������Ȼ��һ����
 * 
 * 	ʹ����ʽ��ɵĲ���
 * 		1����ȡ����Դ��������Դ�е����ݶ�������
 * 		2�������е����ݽ��и�ʽ�����Ĵ���
 * 		3�������е����ݽ������ϴ���
 * 
 * 	���ղ���
 * 		ͨ�����ղ��������Դ�������ȡ����������Ҫ����Ϣ
 * 		���ղ��� ִ�н����󣬻��Զ��ر�����������е����ݶ�������
 * 		collect
 * 			�����е����ݴ浽һ��������
 * 
 * 		reduce
 * 		count
 * 		
 * */
public class Stream_test {
	public static void main(String[] args){
		//getStreamColl();
		//getStreamArr();
		//finalOpration();
		//finalOpration2();
		//finalOpration3();
		//finalOpration4();
		//finalOpration5();
		//finalOpration6();
		IntStreamfinalOpration();
	
	}
	
	
	/**
	 * ��������Դ��ȡ����ȡһ����
	 * */
	private static void getStreamColl(){
		//��ȡ���ϵ����ݵ�����
		List<Integer> list=new ArrayList<>();
		Collections.addAll(list, 0,1,2,3,4,5,6,7,8,9);
		//ͨ�������Դ��ķ�����ȡ��----ͬ����
		Stream<Integer> stream=list.stream();
		System.out.println(stream);
		//ͨ�������Դ��ķ�����ȡ��----������
		Stream<Integer> stream2=list.parallelStream();
		System.out.println(stream2);
		//ʹ�ò�����Ч��Ҫ����һЩ
		
	}
	
	
	/**
	 * ��������Դ��ȡ����ȡһ����
	 * */
	private static void getStreamArr(){
		Integer[] intarrys={1,2,3,4,5,6,7,8,9};
		//��ȡ�����е����ݻ�ȡһ����
		Stream<Integer> stream=Arrays.stream(intarrys);
		System.out.println(stream);
		
		//�������͵�����
		int[] arrys={11,12,13,14,15,16,17,18,19};
		IntStream intstream=Arrays.stream(arrys);
		System.out.println(intstream);
	}
	
	
	/**
	 * ���ղ���-collect
	 * 	�ۺ�
	 * */
	private static void finalOpration(){
		//��ȡ����Դ
		List<Integer> list=new ArrayList<Integer>();
		Collections.addAll(list, 1,2,3,4,5);
		Stream<Integer> stream=list.stream();
		
		//��������collect�����������е����ݴ��뵽��Ӧ�ļ��ϵ���
		//List<Integer> intlist=stream.collect(Collectors.toList());
		//System.out.println(intlist);
		//Set<Integer> intset=stream.collect(Collectors.toSet());
		
		
		Map<String,Integer> map=stream.collect(Collectors.toMap(i->i.toString(), i->i));
		System.out.println(map);
		
		//������潫�����뼯�ϵ��еĴ���û��ע������ô���׳��쳣��stream has already been operated upon or closed
		//���ղ�����ɺ��ر��������ùرյ������׳��쳣
		
		
	} 
	
	
	/**
	 * ���ղ���--reduce�������е����ݰ���һ���Ĺ���ۺ�����
	 * */
	private static void finalOpration2(){
		List<Integer> intlist=new ArrayList<>();
		Collections.addAll(intlist, 1,2,3,4,5);
		Stream<Integer> stream=intlist.stream();
		
		
		//reduce�Ĳ�����һ��BinaryOperator<T>�ӿڣ��ýӿ���һ������ʽ�ӿ�
		//BinaryOperator�ӿڣ��̳���BiFunction<T, U, R>�ӿڣ�������ʵ�ֵķ�����R apply(T t, U u);
		Optional<Integer> optional=stream.reduce((p1,p2)->{
			System.out.println("Test!");
			return p1+p2;});
		//reduce��������ӵ�һ��Ԫ�ؿ�ʼ����������p1Ϊ��һ��Ԫ�أ�p2Ϊ��һ��Ԫ��
		//Ȼ��reduce���ڱ����Ĺ��̵��У����ô���ĺ����ӿڵĵ���ʵ�ֵķ������õ��÷����ķ���ֵ����p1���ڸ�ֵ��p2Ϊ��һ��Ԫ�أ��������øú���ʽ�ӿ��е�ʵ�ַ���
		//ֱ�������������ڶ���Ԫ�أ����һ�ε��ø÷������ڽ��÷����ķ���ֵ��Ϊ�����
		
		Integer res=optional.get();
		System.out.println(res);
		
	}
	
	
	/**���ղ���.
	 * count---ͳ�����е����ݵ�����
	 * forEach---�������е�����
	 * 
	 * */
	private static void finalOpration3(){
		List<Integer> intlist=new ArrayList<>();
		Collections.addAll(intlist, 1,2,3,4,5);
		Stream<Integer> stream=intlist.stream();
		System.out.println(stream.count());
		
		Stream<Integer> stream2=intlist.stream();
		//forEach�Ĳ�����һ������ʽ�ӿڣ�����ʵ�ֵķ���Ϊһ���������޷���ֵ�ķ���
		stream2.forEach(e1->System.out.println(e1));//����д��System::println
	}
	
	
	/**
	 * ���ղ���
	 * 	max----����ָ���Ķ���ȽϹ����ҳ���������Ԫ��
	 * 	min----����ָ���Ķ���ȽϹ����ҳ�������С��Ԫ��
	 * 
	 * */
	private static void finalOpration4(){
		List<Integer> intlist=new ArrayList<>();
		Collections.addAll(intlist, 1,2,3,4,5);
		Stream<Integer> stream=intlist.stream();
		System.out.println(stream.max((e1,e2) -> e1-e2).get());//Optional������get��ý��
		//Integer����ֱ��ʵ����compareto���������Կ���ֱ������Integer�����compareTo����������lambda���ʽҲ��ֱ��д��Integer::compareTo
		
		Stream<Integer> stream2=intlist.stream();
		System.out.println(stream2.min(Integer::compareTo).get());
		
		
		
	}
	
	
	/**
	 * ���ղ�����Matching
	 * 	allMatch��ֻ�е��������е�Ԫ�ض�ƥ��ָ���Ĺ��򣬲ŷ���true
	 * 	anyMatch��ֻҪ����������������ƥ��ָ���Ĺ��򣬾ͷ���true
	 * 	noneMatch��ֻ�е��������е����ݶ�������ƥ��ָ���Ĺ��򣬲ŷ���true
	 * 
	 * */
	private static void finalOpration5(){
		List<Integer> intlist=new ArrayList<>();
		Collections.addAll(intlist, 1,2,3,4,5);
		Stream<Integer> stream=intlist.stream();
		boolean res1=stream.allMatch(e1->e1>1);//�÷����еĲ�����һ������ʽ�ӿ�Predicate������ʵ�ֵķ���Ϊboolean test(T t)
		System.out.println(res1);//false
		
		Stream<Integer> stream2=intlist.stream();
		boolean res2=stream2.anyMatch(e1->e1>4);
		System.out.println(res2);//true
		
		Stream<Integer> stream3=intlist.stream();
		boolean res3=stream3.noneMatch(e1->e1>5);
		System.out.println(res3);//true
		
	}
	
	
	/**
	 * ���ղ�����find
	 * 	findFirst�������л�ȡһ��Ԫ�أ���ȡ�Ŀ�ͷԪ�أ�
	 * 	findAny�������л�ȡһ��Ԫ�أ�һ��������ǻ�ȡ�Ŀ�ͷԪ�أ�,�ڲ������п��ܲ�����Ԫ�أ�����������һ������Ԫ��
	 * �������������󲿷����������ͬ�ģ������ڶ��̵߳Ļ����£������������Ľ���п��ܲ�һ��
	 * 	
	 * */
	
	private static void finalOpration6(){
		List<Integer> intlist=new ArrayList<>();
		Collections.addAll(intlist, 1,2,3,4,5);
		Stream<Integer> stream=intlist.parallelStream();
		Optional<Integer> opt=stream.findFirst();
		System.out.println(opt.get());
		Stream<Integer> stream2=intlist.parallelStream();
		Optional<Integer> opt2=stream2.findAny();
		System.out.println(opt2.get());
	}
	
	/**
	 * IntStream�����մ���
	 * */
	private static void IntStreamfinalOpration(){
		int[] intarry={1,2,3,4,5,6};
		//��ȡIntStream����
		IntStream intstream=Arrays.stream(intarry);
		/*
		IntStream intstream2=Arrays.stream(intarry);
		IntStream intstream3=Arrays.stream(intarry);
		IntStream intstream4=Arrays.stream(intarry);
		IntStream intstream5=Arrays.stream(intarry);
		
		
		System.out.println(intstream.max().getAsInt());//��ȡ���ֵ
		System.out.println(intstream2.min().getAsInt());//��ȡ��Сֵ
		System.out.println(intstream3.sum());//��ȡ���
		System.out.println(intstream4.count());//��ȡ����Ԫ�صĸ���
		System.out.println(intstream5.average().getAsDouble());//��ȡƽ��ֵ
		*/
		
		//����һ��int����ȡ�����϶������
		//��ȡ����һ�����ݷ���
		IntSummaryStatistics iss=intstream.summaryStatistics();
		System.out.println(iss.getMax());
		System.out.println(iss.getMin());
		System.out.println(iss.getSum());
		System.out.println(iss.getCount());
		System.out.println(iss.getAverage());
		
	}
}
