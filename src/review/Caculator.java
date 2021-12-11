package review;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * 实现先乘除后加减的功能
 * 思路，利用两个栈存放数字，先消去括号和乘方，在消去乘除，最后消去加减
 * 该程序目前使用整型测试，稍后可换成double类型尝试
 * 
 * 2021.10.10-----已将所有整形转换成了double类型
 */
public class Caculator
{
  public static void main(String[] args){
      Caculator ca=new Caculator();
      System.out.println("请输入需要计算的表达式：");
      Scanner sc=new Scanner(System.in);
      String exp="";
      if(sc.hasNextLine())
      {
    	  exp+=sc.next();
      }
      sc.close();
      LinkedList<String> list=ca.caculator(exp);
      System.out.println("计算"+exp+"的值----");
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
        StringBuilder sb=new StringBuilder(exp);//接收表达式
        StringBuilder num=new StringBuilder();//接收数字字符
        LinkedList<String> explist=new LinkedList<String>();
        int len=sb.length();
        for(int i=0;i<len;i++){
          // System.out.println("okokok---->"+sb.charAt(i)+"boolean value is:"+isSymbol(sb.charAt(i)));
            if(isSymbol(sb.charAt(i))){
            //该字符为数字或小数点
            num.append(sb.charAt(i));
            if(i==len-1){
            explist.push(num.toString());//否则最后一个数字加不进去，因为没有其他符号了
            }
            }
            else{
            if(num.toString()!=null&&!num.toString().equals("")){
                //由于括号前后也是字符，所以若不加入该操作，会出现插入空字符的情况
                explist.push(num.toString());//将拼凑好的数字字符入栈
        }
            //System.out.println("jfjskfjsk---->"+num);
            num.setLength(0);//清空StringBuilder
            
            if(sb.charAt(i)==')'){
                //括号结束，将括号当中的内容出栈，并计算
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
            //+ -符号或者普通的数字
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
                //乘方先算
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
            //(、+、-、*、/等普通符号
            explist.push(sb.charAt(i)+"");
            }
            
            
            }
    }
    if(explist.size()>1){
    //将()中的内容和乘方计算完成，且表达式所有字符插入到栈后，若还有1个以上的元素，则还需要计算
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
//乘除已经算完，在算一次加减
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
explist.push(str);//数字或小数点

}
}
    
    }
    return explist;
    }
    
}