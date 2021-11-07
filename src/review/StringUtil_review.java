package review;

/*
 * StringBuffer��StringBuilder
 * 	���Ƕ���String�Ĳ����࣬����ά����һ���ַ����������
 * 
 * StringBuilder�ṩ�ķ�����StringBuffer��һ���ġ�
 * ����
 * 		StringBuffer���̰߳�ȫ��
 * 		StringBuilder�����̵߳İ�ȫ��
 * 		���������Ƕ��̲߳��������ʱ���ʱ����StringBuilderЧ�ʸ���
 * 		�����߳�ʹ�ö����ʱ�򣬾���StringBuffer
 * 
 * �ַ�������Ч�ʶԱȣ�
 * 		�����漰���ַ���������ʹ�ó�������������ѭ���ж��ַ������в�����һ����Ҫʹ���ַ�����������StringBuffer��StringBuilder�ķ�������
 * 		�����ַ����ǲ��ɱ�ģ�����String������еĲ��������Ὺ��һ�����ڴ�ռ������µ��ַ���
 * 		StringBuffer��StringBuilder�����ڲ�ά���ַ����飬���е��޸Ķ���Χ�Ƹ��������ģ�����Ч�ʸ���
 * 
 * */

public class StringUtil_review {
	public static void main(String[] args){
		StringbufferTest();
	}
	
	private static void StringbufferTest(){
		//�޲ι��죬����һ�����ַ���
		StringBuffer sb=new StringBuffer();
		//���ι��죬����һ��������ַ���
		StringBuffer sb2=new StringBuffer("test");
		
		//����ƴ���ַ���
		sb.append("abc");//�÷����ķ���ֵҲ��һ��StringBuffer����
		System.out.println(sb);
		//������ָ��λ�ò����ַ���
		sb.insert(1, "ToT");//���±�1������
		System.out.println(sb);
		
		//ɾ
		sb.delete(2, 4);//ɾ���±��2-4������ҿ�
		System.out.println(sb);
		sb.deleteCharAt(3);//ɾ���±�Ϊ3���ַ�
		System.out.println(sb);
		//�ģ��滻
		sb2.replace(1, 3, "ooo");//�޸��±�1-3���ַ���Ϊ"ooo"
		System.out.println(sb2);
		sb2.setCharAt(2, 'I');
		System.out.println(sb2);
		//��ת
		System.out.println(sb2.reverse());
	}
}

