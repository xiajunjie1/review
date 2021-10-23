package review.test;

/*
 * 包：起到了组织代码的作用，可以将功能相关的类，放入到一个包中
 * 包名是一个标识符，需要遵循小驼峰命名法
 * 包与包之间，如果存在嵌套（包含）的关系，需要用电进行分隔
 * 
 * 
 * 关键字：
 * 	package:写在一个文件的最上方，表示这个文件属于哪一个包中
 * 		在非IDE中，创建一个java文件，并指定包，可以在命令行中进入到当前java文件
 * 		所在目录并执行javac -d .*.java
 * 	import :导入包中的类
 * 
 * 	java.lang:基础语法，这个包中的类，可以直接使用，不需要导包（已经默认被导入了）
 * 	
 * 	
 * */

public class PackageUsage {
	public static void main(String[] args){
		//跨包访问类：类的全限定名（从最外层的包开始，一层层向内查询，直到找到这个类
		//review.Caculator c=new review.Caculator();
		//跨包访问类：通过import导入包
		
	}
}
