package review;

//ѭ����ǩ
public class Review02 {
	
	//�����Ķ���
	//[����Ȩ�����η�][�������η�]�������� ��������([�����б�]){
	//������
	//}
	
	
	/*
	 * ����������Overload
	 * ����������
	 * 	1����������ͬ
	 * 	2��������ͬ�����͡�������
	 * 
	 * */
	public static void OverLoadM(){
		System.out.println("�����޲η���");
	}
	public static void OverLoadM(int num1,int num2){
		System.out.println("�ҵĲ���������������1: "+num1+"����2��"+num2);
	}
	public static void OverLoadM(String str1){
		System.out.println("�ҵĲ������ַ���: "+str1);
	}
	
	public static void main(String[] args){
		//�趨��ǩ�����breakʹ��
		OUTER:
		for(int i=0;i<5;i++){
			
			for(int j=0;j<5;j++){
				System.out.println("i: "+i+"j: "+j);
				if(j==3){
					//break;
					break OUTER;
				}
			}
		}
	
	OverLoadM();
	OverLoadM(1,2);
	OverLoadM("�ַ�������");
		
	}

}
