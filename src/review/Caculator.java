package review;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * ʵ���ȳ˳���Ӽ��Ĺ���
 * ˼·����������ջ������֣�����ȥ���źͳ˷�������ȥ�˳��������ȥ�Ӽ�
 * �ó���Ŀǰʹ�����Ͳ��ԣ��Ժ�ɻ���double���ͳ���
 * 
 * 2021.10.10-----�ѽ���������ת������double����
 */
public class Caculator
{
  public static void main(String[] args){
      Caculator ca=new Caculator();
      System.out.println("��������Ҫ����ı��ʽ��");
      Scanner sc=new Scanner(System.in);
      String exp="";
      if(sc.hasNextLine())
      {
    	  exp+=sc.next();
      }
      sc.close();
      LinkedList<String> list=ca.caculator(exp);
      System.out.println("����"+exp+"��ֵ----");
      System.out.println(list.pop());
    }
    
    public boolean isSymbol(char ch){
       // System.out.println("aosfsj:---"+ch);
    if(ch =='+'){
    return false;
    }
    if(ch == '-'){
    return false;
    }
    if(ch=='*'){
    return false;
    }
    if(ch=='/'){
    return false;
    }
    if(ch=='('||ch==')'){
    return false;
    }
    if(ch=='^'){
    return false;
    }
    return true;
    }
    
    public LinkedList<String> caculator(String exp){
        StringBuilder sb=new StringBuilder(exp);//���ձ��ʽ
        StringBuilder num=new StringBuilder();//���������ַ�
        LinkedList<String> explist=new LinkedList<String>();
        int len=sb.length();
        for(int i=0;i<len;i++){
          // System.out.println("okokok---->"+sb.charAt(i)+"boolean value is:"+isSymbol(sb.charAt(i)));
            if(isSymbol(sb.charAt(i))){
            //���ַ�Ϊ���ֻ�С����
            num.append(sb.charAt(i));
            if(i==len-1){
            explist.push(num.toString());//�������һ�����ּӲ���ȥ����Ϊû������������
            }
            }
            else{
            if(num.toString()!=null&&!num.toString().equals("")){
                //��������ǰ��Ҳ���ַ���������������ò���������ֲ�����ַ������
                explist.push(num.toString());//��ƴ�պõ������ַ���ջ
        }
            //System.out.println("jfjskfjsk---->"+num);
            num.setLength(0);//���StringBuilder
            
            if(sb.charAt(i)==')'){
                //���Ž����������ŵ��е����ݳ�ջ��������
              LinkedList<String> templist=new LinkedList<String>();
            while(true){
            String str=explist.pop();
            if(str.equals("*")){
            String str1=templist.pop();
            String str2=explist.pop();
            double val1=Double.parseDouble(str1);
            double val2=Integer.parseInt(str2);
            double val3=val1*val2;
            templist.push(val3+"");
            }
            else if(str.equals("/")){
            String str1=templist.pop();
            String str2=explist.pop();
            double val1=Double.parseDouble(str1);
            double val2=Double.parseDouble(str2);
            double val3=val2/val1;
            templist.push(val3+"");
            }
            else{
            //+ -���Ż�����ͨ������
            if(!str.equals("("))
            templist.push(str);
            
            }
            if(str.equals("(")){
                while(templist.size()>0){
                String str1=templist.pop();
                if(str1.equals("+")){
                    String str2=explist.pop();
                    String str3=templist.pop();
                    double val1=Double.parseDouble(str2);
                    double val2=Double.parseDouble(str3);
                    double val3=val1+val2;
                    explist.push(val3+"");
                }
                else if(str1.equals("-")){
                    String str2=explist.pop();
                    String str3=templist.pop();
                    double val1=Double.parseDouble(str2);
                    double val2=Double.parseDouble(str3);
                    double val3=val1-val2;
                    explist.push(val3+"");
                }
                else{
                explist.push(str1);
                }
                }
                break;
            }
            
            }
        }
            else if(sb.charAt(i)=='^'){
                //�˷�����
            String val=explist.pop();
                double val1=Double.parseDouble(val);
                int time=Integer.parseInt(sb.charAt(i+1)+"");
               double val2=1;
                for(int j=0;j<time;j++){
                val2=val2*val1;
                }
                i++;
                explist.push((val2+""));
            }
            else{
            //(��+��-��*��/����ͨ����
            explist.push(sb.charAt(i)+"");
            }
            
            
            }
    }
    if(explist.size()>1){
    //��()�е����ݺͳ˷�������ɣ��ұ��ʽ�����ַ����뵽ջ��������1�����ϵ�Ԫ�أ�����Ҫ����
    LinkedList<String> templist=new LinkedList<String>();
    //System.out.println(explist.toString());
    while(explist.size()>0){
    String str=explist.pop();
    //System.out.println()
    if(str.equals("*")){
    String str1=explist.pop();
    String str2=templist.pop();
   // System.out.println("what the hell"+str1);
    double val1=Double.parseDouble(str1);
    double val2=Double.parseDouble(str2);
    double val3=val1*val2;
    templist.push(val3+"");
    }
    else if(str.equals("/")){
    String str1=explist.pop();
    String str2=templist.pop();
    double val1=Double.parseDouble(str1);
    double val2=Double.parseDouble(str2);
    double val3=val1/val2;
    templist.push(val3+"");
    }
    
    else{
    templist.push(str);
    }
}
//�˳��Ѿ����꣬����һ�μӼ�
while(templist.size()>0){
String str=templist.pop();
if(str.equals("+")){
String str1=explist.pop();
String str2=templist.pop();
double val1=Double.parseDouble(str1);
double val2=Double.parseDouble(str2);
double val3=val1+val2;
explist.push(val3+"");
}
else if(str.equals("-")){
String str1=explist.pop();
String str2=templist.pop();
double val1=Double.parseDouble(str1);
double val2=Double.parseDouble(str2);
double val3=val1-val2;
explist.push(val3+"");
}
else{
explist.push(str);//���ֻ�С����

}
}
    
    }
    return explist;
    }
    
}