package review;

/*
 * ������ʽ
 * 	�����ַ�����У��ƥ�乤��
 * */
public class Regex_review {
	public static void main(String[] args){
		System.out.println(checkQQid("12345"));
	}
	
	/**
	 * ��֤һ���ַ����Ƿ��ǺϷ���QQ����
	 * 	1��������0��ͷ
	 * 	2�����������
	 * 	3������[6,11]
	 * */
	private static boolean checkQQid(String qq){
		
		return qq.matches("[1-9]\\d{5,10}");
	}
}
