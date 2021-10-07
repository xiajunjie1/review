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
	 * 选择排序
	 * 核心思想：每一趟循环，将最小值移动到最前面
	 * 第一趟循环将数组中的最小值移动到第一位
	 * 第二趟循环将数组中次小值移动到数组的第二位
	 * */
	
	public static void sort1(int[] arr){
		for(int i=0;i<arr.length-1;i++){
			int min =i;//创建当前循环中最小值的下标，让它指向i
			
			for(int j=i+1;j<arr.length;j++){
				//j从i下一位开始计数，找最小值
				if(arr[min]>arr[j])
				{
					min=j;
					
				}
	
			}
			//当前循环中，最小的值已经找到，让arr[i]和其交换
			int tmp=arr[i];
			arr[i]=arr[min];
			arr[min]=tmp;
		}
		
	}
	
	/*
	 * 冒泡排序法
	 * 核心思想：比较相邻两个元素，较大的元素往上浮，第一轮循环将最大的元素移到最后一位
	 * */
	public static void sort2(int[] arr){
		
		for(int i=0;i<arr.length;i++){
			//外层循环代表循环的趟数
			int flag=0;//若flag没有变化，则说明一趟循环未交换，即该数组已经排好序
			for(int j=0;j<arr.length-i-1;j++){

				//外层的i循环一次，代表当前最大值已安排好，所以最大长度要减i
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
