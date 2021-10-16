package review;
import java.util.*;
/*
 * Map<K,V>�ӿڣ�˫�м���
 * Mapʵ���ࣺHashMap,TreeMap,LinkedHashMap
 * TreeMap֧�ּ�ֵ�Ե��������Ǹ��ݼ������������
 * LinkedHashMap�ײ�ά����һ�������ʣ������Ա�֤Ԫ�ص�˳��Ͳ����˳����ͬ
 * Hashtable
 * 	HashMap���̲߳���ȫ�ģ�Hashtable���̰߳�ȫ�ġ�
 * 	HashMap�������null�ļ�ֵ��Hashtable�ǲ������
 * 	HashMap�ĸ�����AbstractMap��Hashtable�ĸ�����Dictionary
 * 	HashMap�ײ��㷨Ч������Hashtable
 * 
 * 
 * collections�������У��з������Եõ���Ӧ�̰߳�ȫ�ļ��ϣ�����ɼ�api
 * Collections.synchronizedMap(m),����map�����̰߳�ȫ��map
 * Collections.synchronizedList(list),����list�����̰߳�ȫ��list
 * Collections.synchronizedSet(s),����set�����̰߳�ȫ��set
 * 
 * 
 * 
 * */

public class MapTest {
	public static void main(String[] args){
		Map<String,String> hashmap=new HashMap<>();
		String value;
		value=hashmap.put("name", "����");//�÷����з���ֵ�����ķ���ֵ�ǣ������ǵ�����һ��name��ֵ����Ϊ���ϱ����ǿգ�û��name�����Է���null
		System.out.println(value);
		value=hashmap.put("name", "����");//���ĻḲ���������ô�ִ�еĽ���ķ���ֵ������
		System.out.println(value);
		//�������ִ�н�����Կ�����������Ψһ�ġ�
		
		value=hashmap.putIfAbsent("name", "����");//�÷�����ʾ������������Ѿ�������ͬ�ļ����ü��ǲ��ᱻ��ӳ�ȥ��
		System.out.println(value);
		System.out.println(hashmap);
		
		hashmap.put("address","china");
		//ɾ����
		System.out.println("before del"+hashmap);
		System.out.println("del"+hashmap.remove("address"));//�÷�������Ϊ����,����ֵΪ��ɾ���ļ�ֵ
		System.out.println("after del"+hashmap);
		
		//�޸ļ�ֵ�Ե�ֵ
		hashmap.replace("address", "beijing");//���ر��滻�ļ�ֵ�Ե�ֵ
		hashmap.replace("address","beijing","shanghai");//���ز���ֵ���Ƿ��滻�ɹ�
		hashmap.replaceAll((k,v)->{
			//map�����У����Եļ�ֵ�ԣ���һ�δ�����ʹ���ֵ
			//k�������v�����ֵ
			//��������ʵ�ֵ��ǣ������Ϊname����ֵ�滻Ϊxiajunjie��������ά�ֲ���
			if(k.equals("name")){
				return "xiajunjie";
			}
			return v;
		});
		
		//map��ȡֵ
		String name=hashmap.get("name");//����������ֵ
		System.out.println(name);
		//��ü�ֵ���������ļ������ڣ��򷵻ش����Ĭ��ֵ
		String exvalue=hashmap.getOrDefault("newkey", "defaultvalue");
		System.out.println(exvalue);
		
		//�жϼ����Ƿ������,����ֵΪ����
		System.out.println(hashmap.containsKey("name"));
		//�жϼ����Ƿ����ֵ������ֵΪ����
		System.out.println(hashmap.containsValue("xiajunjie"));
		
		hashmap.clear();
		//map�ı���
		hashmap.put("key1", "value1");
		hashmap.put("key2", "value2");
		hashmap.put("key3", "value3");
		hashmap.put("key4", "value4");
		hashmap.put("key5", "value5");
		//1.ͨ��keySet������
		Set<String> keys=hashmap.keySet();
		System.out.println("keySet������");
		keys.forEach(key -> {
			
			System.out.println(key+"="+hashmap.get(key));
			
		});
		
		//����map�Դ���forEach���б���
		System.out.println("foreach������");
		hashmap.forEach((k,v)->{
			
			System.out.println(k+"="+v);
			
		});
		
		//����map��EntrySet���б���
		Set<Map.Entry<String,String>> entryset=hashmap.entrySet();
		System.out.println("EntrySet����:");
		entryset.forEach(ens->{
			System.out.println(ens.getKey()+"="+ens.getValue());
		});
		
		
	}
}
