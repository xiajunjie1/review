package review;
/*
 * 实现类似LinkedList的集合工具类
 * */
public class LinkedListRealize<E> {
	
	private class Node {
		E value;
		Node pre;//前节点
		Node next;//后节点
	}
	
	private Node head;
	private Node last;
	private int count;
	
	public LinkedListRealize(){
		this.head=new Node();
		this.last=new Node();
		this.head.pre=last;
		this.head.next=last;
		this.last.next=head;
		this.last.pre=head;
		count=0;
	}
	
	public E getHead(){
		return this.head.next.value;
		
	}
	
	public E getLast(){
		return this.last.pre.value;
	}
	
	public void add(E value){
		Node newEle=new Node();
		newEle.value=value;
		Node temp=this.head;
		while(!(temp.next==last)){
			temp=temp.next;
		}
		temp.next=newEle;
		newEle.pre=temp;
		newEle.next=last;
		this.last.pre=newEle;
		this.count++;
		
	}
	
	public void add(int index,E value){
		if(index<0 || index >this.count){
			System.out.println("下标不合法");
			return;
		}
		Node newEle=new Node();
		newEle.value=value;
		Node temp=head;
		for(int i=0;i<index;i++){
			temp=temp.next;
		}
		//Node temp2=temp.pre;
		Node temp3=temp.next;
		temp.next=newEle;
		newEle.pre=temp;
		newEle.next=temp3;
		temp3.pre=newEle;
		count++;
	}
	
	public void show(){
		Node ele=this.head;
		Node temp=this.head;
		System.out.print("[");
		for(int i=0;i<this.count;i++){
			temp=temp.next;
			System.out.print(temp.value);
			if(i<count-1){
				System.out.print(",");
			}
			
		}
		System.out.println("]");
	}
	
	public void revershow(){
		Node ele=this.last;
		Node temp=this.last;
		System.out.print("[");
		for(int i=0;i<this.count;i++){
			temp=temp.pre;
			System.out.print(temp.value);
			if(i<count-1){
				System.out.print(",");
			}
			
		}
		System.out.println("]");
	}
	
	public int remove(E ele){
		int i=-1;
		if(this.count==0){
			System.out.println("空列表");
		
		}
		Node temp=this.head;
		for(int j=0;j<this.count;j++){
			temp=temp.next;
			if(ele.equals(temp.value)){
				temp.pre.next=temp.next;
				temp.next.pre=temp.pre;
				this.count--;
				return j;
			}
		}
		return i;
	}
	
	public E remove(int index){
		E target;
		if(index<0 || index>=count){
			System.out.println("下标不合法！");
			return null;
		}
		Node temp=this.head;
		for(int i=0;i<index+1;i++){
			temp=temp.next;
		}
		temp.pre.next=temp.next;
		temp.next.pre=temp.pre;
		target=temp.value;
		this.count--;
		return target;
	}
	
	public void set(int index,E ele){
		if(index<0 || index>=count){
			System.out.println("下标不合法！");
			return;
		}
		Node temp=this.head;
		for(int i=0;i<index+1;i++){
			temp=temp.next;
		}
		temp.value=ele;
	}
	
	public int size(){
		return this.count;
	}
	
	public void clear(){
		this.head.next=this.last;
		this.last.pre=this.head;
		this.count=0;
	}

}

