package review;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.*;

/*
 * 集合的流式编程
 * 	JDK1.8的新特性
 * 	Stream是操作集合的增强，流不是集合的元素，不是一种数据结构，不负责数据的存储的。
 * 	流更像一个迭代器，可以单向遍历集合中的每一个元素，并且不可循环
 * 
 * 	流可以将数据源（集合、数组...)中的数据读到了Stream中，在对流中的数据进行一些其他的处理
 * 	处理的结果，依然是一个流
 * 
 * 	使用流式变成的步骤
 * 		1、获取数据源，将数据源中的数据读到流中
 * 		2、对流中的数据进行各式各样的处理
 * 		3、对流中的数据进行整合处理
 * 
 * 	最终操作
 * 		通过最终操作，可以从流中提取出来我们想要的信息
 * 		最终操作 执行结束后，会自动关闭这个流，流中的数据都会销毁
 * 		collect
 * 			将流中的数据存到一个集合里
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
	 * 集合数据源读取，获取一个流
	 * */
	private static void getStreamColl(){
		//读取集合的数据到流中
		List<Integer> list=new ArrayList<>();
		Collections.addAll(list, 0,1,2,3,4,5,6,7,8,9);
		//通过集合自带的方法获取流----同步流
		Stream<Integer> stream=list.stream();
		System.out.println(stream);
		//通过集合自带的方法获取流----并发流
		Stream<Integer> stream2=list.parallelStream();
		System.out.println(stream2);
		//使用并发流效率要更高一些
		
	}
	
	
	/**
	 * 数组数据源读取，获取一个流
	 * */
	private static void getStreamArr(){
		Integer[] intarrys={1,2,3,4,5,6,7,8,9};
		//读取数组中的数据获取一个流
		Stream<Integer> stream=Arrays.stream(intarrys);
		System.out.println(stream);
		
		//基本类型的数组
		int[] arrys={11,12,13,14,15,16,17,18,19};
		IntStream intstream=Arrays.stream(arrys);
		System.out.println(intstream);
	}
	
	
	/**
	 * 最终操作-collect
	 * 	聚合
	 * */
	private static void finalOpration(){
		//获取数据源
		List<Integer> list=new ArrayList<Integer>();
		Collections.addAll(list, 1,2,3,4,5);
		Stream<Integer> stream=list.stream();
		
		//调用流的collect方法，将流中的数据存入到相应的集合当中
		//List<Integer> intlist=stream.collect(Collectors.toList());
		//System.out.println(intlist);
		//Set<Integer> intset=stream.collect(Collectors.toSet());
		
		
		Map<String,Integer> map=stream.collect(Collectors.toMap(i->i.toString(), i->i));
		System.out.println(map);
		
		//如果上面将流存入集合当中的代码没有注掉，那么会抛出异常：stream has already been operated upon or closed
		//最终操作完成后会关闭流，调用关闭的流会抛出异常
		
		
	} 
	
	
	/**
	 * 最终操作--reduce，将流中的数据按照一定的规则聚合起来
	 * */
	private static void finalOpration2(){
		List<Integer> intlist=new ArrayList<>();
		Collections.addAll(intlist, 1,2,3,4,5);
		Stream<Integer> stream=intlist.stream();
		
		
		//reduce的参数是一个BinaryOperator<T>接口，该接口是一个函数式接口
		//BinaryOperator接口，继承自BiFunction<T, U, R>接口，它必须实现的方法是R apply(T t, U u);
		Optional<Integer> optional=stream.reduce((p1,p2)->{
			System.out.println("Test!");
			return p1+p2;});
		//reduce方法，会从第一个元素开始，遍历流，p1为第一个元素，p2为后一个元素
		//然后，reduce会在遍历的过程当中，调用传入的函数接口的当中实现的方法，得到该方法的返回值，令p1等于该值，p2为下一个元素，继续调用该函数式接口中的实现方法
		//直到遍历到倒数第二个元素，最后一次调用该方法，在将该方法的返回值作为结果。
		
		Integer res=optional.get();
		System.out.println(res);
		
	}
	
	
	/**最终操作.
	 * count---统计流中的数据的数量
	 * forEach---遍历流中的数据
	 * 
	 * */
	private static void finalOpration3(){
		List<Integer> intlist=new ArrayList<>();
		Collections.addAll(intlist, 1,2,3,4,5);
		Stream<Integer> stream=intlist.stream();
		System.out.println(stream.count());
		
		Stream<Integer> stream2=intlist.stream();
		//forEach的参数是一个函数式接口，必须实现的方法为一个参数，无返回值的方法
		stream2.forEach(e1->System.out.println(e1));//可以写成System::println
	}
	
	
	/**
	 * 最终操作
	 * 	max----按照指定的对象比较规则，找出流中最大的元素
	 * 	min----按照指定的对象比较规则，找出流中最小的元素
	 * 
	 * */
	private static void finalOpration4(){
		List<Integer> intlist=new ArrayList<>();
		Collections.addAll(intlist, 1,2,3,4,5);
		Stream<Integer> stream=intlist.stream();
		System.out.println(stream.max((e1,e2) -> e1-e2).get());//Optional对象，用get获得结果
		//Integer对象直接实现了compareto方法，所以可以直接引用Integer对象的compareTo方法，所以lambda表达式也能直接写成Integer::compareTo
		
		Stream<Integer> stream2=intlist.stream();
		System.out.println(stream2.min(Integer::compareTo).get());
		
		
		
	}
	
	
	/**
	 * 最终操作：Matching
	 * 	allMatch：只有当流中所有的元素都匹配指定的规则，才返回true
	 * 	anyMatch：只要流中任意数据满足匹配指定的规则，就返回true
	 * 	noneMatch：只有当流中所有的数据都不满足匹配指定的规则，才返回true
	 * 
	 * */
	private static void finalOpration5(){
		List<Integer> intlist=new ArrayList<>();
		Collections.addAll(intlist, 1,2,3,4,5);
		Stream<Integer> stream=intlist.stream();
		boolean res1=stream.allMatch(e1->e1>1);//该方法中的参数是一个函数式接口Predicate，必须实现的方法为boolean test(T t)
		System.out.println(res1);//false
		
		Stream<Integer> stream2=intlist.stream();
		boolean res2=stream2.anyMatch(e1->e1>4);
		System.out.println(res2);//true
		
		Stream<Integer> stream3=intlist.stream();
		boolean res3=stream3.noneMatch(e1->e1>5);
		System.out.println(res3);//true
		
	}
	
	
	/**
	 * 最终操作：find
	 * 	findFirst：从流中获取一个元素（获取的开头元素）
	 * 	findAny：从流中获取一个元素（一般情况下是获取的开头元素）,在并行流中可能不是首元素，串行流当中一定是首元素
	 * 这两个方法绝大部分情况下是相同的，但是在多线程的环境下，这两个方法的结果有可能不一样
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
	 * IntStream的最终处理
	 * */
	private static void IntStreamfinalOpration(){
		int[] intarry={1,2,3,4,5,6};
		//获取IntStream对象
		IntStream intstream=Arrays.stream(intarry);
		/*
		IntStream intstream2=Arrays.stream(intarry);
		IntStream intstream3=Arrays.stream(intarry);
		IntStream intstream4=Arrays.stream(intarry);
		IntStream intstream5=Arrays.stream(intarry);
		
		
		System.out.println(intstream.max().getAsInt());//获取最大值
		System.out.println(intstream2.min().getAsInt());//获取最小值
		System.out.println(intstream3.sum());//获取求和
		System.out.println(intstream4.count());//获取流中元素的个数
		System.out.println(intstream5.average().getAsDouble());//获取平均值
		*/
		
		//利用一个int流获取到以上多个数据
		//获取流的一个数据分析
		IntSummaryStatistics iss=intstream.summaryStatistics();
		System.out.println(iss.getMax());
		System.out.println(iss.getMin());
		System.out.println(iss.getSum());
		System.out.println(iss.getCount());
		System.out.println(iss.getAverage());
		
	}
}
