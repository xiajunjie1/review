package review;
import java.util.*;
/*
 * Map<K,V>接口：双列集合
 * Map实现类：HashMap,TreeMap,LinkedHashMap
 * TreeMap支持键值对的排序，它是根据键来进行排序的
 * LinkedHashMap底层维护了一个链表，故，它可以保证元素的顺序和插入的顺序相同
 * Hashtable
 * 	HashMap是线程不安全的，Hashtable是线程安全的。
 * 	HashMap允许出现null的键值，Hashtable是不允许的
 * 	HashMap的父类是AbstractMap，Hashtable的父类是Dictionary
 * 	HashMap底层算法效率优于Hashtable
 * 
 * 
 * collections工具类中，有方法可以得到对应线程安全的集合，详情可见api
 * Collections.synchronizedMap(m),传入map返回线程安全的map
 * Collections.synchronizedList(list),传入list返回线程安全的list
 * Collections.synchronizedSet(s),传入set返回线程安全的set
 * 
 * 
 * 
 * */

public class MapTest {
	public static void main(String[] args){
		Map<String,String> hashmap=new HashMap<>();
		String value;
		value=hashmap.put("name", "张三");//该方法有返回值，它的返回值是，被覆盖掉的上一个name的值，因为集合本来是空，没有name，所以返回null
		System.out.println(value);
		value=hashmap.put("name", "李四");//李四会覆盖张三，该次执行的结果的返回值是张三
		System.out.println(value);
		//由上面的执行结果可以看出来，键是唯一的。
		
		value=hashmap.putIfAbsent("name", "王五");//该方法表示，如果集合中已经存在相同的键，该键是不会被添加出去的
		System.out.println(value);
		System.out.println(hashmap);
		
		hashmap.put("address","china");
		//删除键
		System.out.println("before del"+hashmap);
		System.out.println("del"+hashmap.remove("address"));//该方法参数为键名,返回值为被删除的键值
		System.out.println("after del"+hashmap);
		
		//修改键值对的值
		hashmap.replace("address", "beijing");//返回被替换的键值对的值
		hashmap.replace("address","beijing","shanghai");//返回布尔值，是否替换成功
		hashmap.replaceAll((k,v)->{
			//map集合中，所以的键值对，会一次传入键和传入值
			//k代表键，v代表键值
			//下列例子实现的是，如果键为name，则值替换为xiajunjie，其他的维持不变
			if(k.equals("name")){
				return "xiajunjie";
			}
			return v;
		});
		
		//map获取值
		String name=hashmap.get("name");//传入键，获得值
		System.out.println(name);
		//获得键值，如果传入的键不存在，则返回传入的默认值
		String exvalue=hashmap.getOrDefault("newkey", "defaultvalue");
		System.out.println(exvalue);
		
		//判断集合是否包含键,返回值为布尔
		System.out.println(hashmap.containsKey("name"));
		//判断集合是否包含值，返回值为布尔
		System.out.println(hashmap.containsValue("xiajunjie"));
		
		hashmap.clear();
		//map的遍历
		hashmap.put("key1", "value1");
		hashmap.put("key2", "value2");
		hashmap.put("key3", "value3");
		hashmap.put("key4", "value4");
		hashmap.put("key5", "value5");
		//1.通过keySet来遍历
		Set<String> keys=hashmap.keySet();
		System.out.println("keySet遍历：");
		keys.forEach(key -> {
			
			System.out.println(key+"="+hashmap.get(key));
			
		});
		
		//利用map自带的forEach进行遍历
		System.out.println("foreach遍历：");
		hashmap.forEach((k,v)->{
			
			System.out.println(k+"="+v);
			
		});
		
		//利用map的EntrySet进行遍历
		Set<Map.Entry<String,String>> entryset=hashmap.entrySet();
		System.out.println("EntrySet遍历:");
		entryset.forEach(ens->{
			System.out.println(ens.getKey()+"="+ens.getValue());
		});
		
		
	}
}
