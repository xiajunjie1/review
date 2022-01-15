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
	public static final int Get_Userlist=7;
	public static final int Get_Ulist_Success=8;
	public static final int Get_Ulist_Failure=9;
	public static final int Talk_Connect=10;//一创建聊天的窗口就发送该信息给服务器，服务器记录聊天的Socket
	public static final int Talk_Normal=11;//发送聊天信息
	public static final int U_online=12;//上线
	public static final int U_offline=13;//下线
	public static final int Have_Leave=14;//有留言，传给客户端
	public static final int Get_Leave=15;//获取留言
	public static final int Close_Chat=16;//关闭聊天窗口
}


