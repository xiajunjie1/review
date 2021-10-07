package review;
/*
 * Arrays������
 * �����а��������ɸ���������в����Ĺ��߷���
 * 
 * 
 * 	
 * */

import java.util.Arrays;
public class ArrayTools {

	public static void main(String[] args){
		 
		int[] arrtest={3,6,10,2,22,7,99,0,12};
		
		//���߷���-����:��ԭ�����п���һ��������Ԫ�أ��������µ�����
		//��arrtest�п���ǰ���Ԫ�أ�������Ϊ�µ�������з���
		//��������ĸ���������ԭ����ĳ��ȣ���Ȼ���Կ�����ֻ�������Ľ���в�������Ĭ��ֵ����int��0��String��null
		int[] arrtest2=Arrays.copyOf(arrtest, 5);
		
		//���߷���-����ת�ַ���Arrays.toString(����)�������������ƴ�ӳ��ַ���������
		System.out.println(Arrays.toString(arrtest2));
		
		//���߷���-����
		//Arrays.copyOfRange(original, from, to)
		//��ԭ�����д��±�from���±�to-1��Ԫ�ؿ�������һ���µ�����
		//to����Խ�磬Խ����ں��������Ĭ��ֵ����from�ǲ���Խ��ģ�fromԽ����׳�����Խ���쳣
		
		//���߷���-equals���Ƚ��������������Ƿ���ͬ
		int[] arrtestbak={3,6,10,2,22,7,99,0,12};
		System.out.println(Arrays.equals(arrtest, arrtestbak));
		
		//���߷���-��䣺��ֵ�������
		int[] testarr=new int[4];
		Arrays.fill(testarr, 10);
		System.out.println(Arrays.toString(testarr));
		
		//���߷���-����
		Arrays.sort(arrtestbak);
		System.out.println(Arrays.toString(arrtestbak));
		
		//���߷���-�ö��ֲ��ң�������ӦԪ���������е�λ�ã����ҷ����±�
		//���ֲ��ҵ�ǰ�������������Ѿ��ź���˳��
		int index = Arrays.binarySearch(arrtestbak, 99);
		System.out.println("�±�Ϊ��"+index);
		
		
		//System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length);
		//��һ������(src)��ָ��λ��srcPos)��ʼ��������Ŀ������(dest)��ָ��λ��(destPos)������length��Ԫ��
		//ע�������Ҫ��������Խ������
		
		int[] src={1,2,3,4,5,6};
		src=remove(src,5);
		System.out.println("ɾ������: "+Arrays.toString(src));
	
	}
	
	public static int[] remove(int[] src,int index){
		if(index<0||index>=src.length){
		System.out.println("�������Ϸ���");	
		return src;
		}
		if(index==src.length-1){
			src=Arrays.copyOf(src, src.length-1);
			return src;
		}
		for(int i=index;i<src.length-1;i++){
			src[i]=src[i+1];
		}
		src=Arrays.copyOf(src, src.length-1);
		
		return src;
	}
}
