package review;

public class Sort {

	
	public static void main(String[] args){
		int[] arr={3,1,4,5,9,6,7,0};
		//sort1(arr);
		sort2(arr);
		for(int e : arr){
			System.out.print(e+",");
		}
	}
	
	/*
	 * ѡ������
	 * ����˼�룺ÿһ��ѭ��������Сֵ�ƶ�����ǰ��
	 * ��һ��ѭ���������е���Сֵ�ƶ�����һλ
	 * �ڶ���ѭ���������д�Сֵ�ƶ�������ĵڶ�λ
	 * */
	
	public static void sort1(int[] arr){
		for(int i=0;i<arr.length-1;i++){
			int min =i;//������ǰѭ������Сֵ���±꣬����ָ��i
			
			for(int j=i+1;j<arr.length;j++){
				//j��i��һλ��ʼ����������Сֵ
				if(arr[min]>arr[j])
				{
					min=j;
					
				}
	
			}
			//��ǰѭ���У���С��ֵ�Ѿ��ҵ�����arr[i]���佻��
			int tmp=arr[i];
			arr[i]=arr[min];
			arr[min]=tmp;
		}
		
	}
	
	/*
	 * ð������
	 * ����˼�룺�Ƚ���������Ԫ�أ��ϴ��Ԫ�����ϸ�����һ��ѭ��������Ԫ���Ƶ����һλ
	 * */
	public static void sort2(int[] arr){
		
		for(int i=0;i<arr.length;i++){
			//���ѭ������ѭ��������
			int flag=0;//��flagû�б仯����˵��һ��ѭ��δ���������������Ѿ��ź���
			for(int j=0;j<arr.length-i-1;j++){

				//����iѭ��һ�Σ�����ǰ���ֵ�Ѱ��źã�������󳤶�Ҫ��i
				if(arr[j]>arr[j+1]){
					int tmp=arr[j+1];
					arr[j+1]=arr[j];
					arr[j]=tmp;
					 flag=1;
				}
				
			}
		
		if(flag==0)return;
	}
		}
	
}
