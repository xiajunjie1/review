package project.entry;

/**
 * 作为登录成功失败的标识，给Message中的登录标志赋值
 * */
public interface MessageType {
	public static final int Login_Success=1;
	public static final int Login_Failure=0;
	public static final int Register=2;
	public static final int Login=3;
	public static final int Register_Succeess=4;
	public static final int Register_Failure=5;
	public static final int Uname_Duplicate=6;
	
}


