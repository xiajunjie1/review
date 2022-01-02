package project.entry;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable{
private int msgType;
private User user;
private String content;//消息提示内容
private List<User> Ulist;
private String socketKey;//存储Socket的map的键
private String fromUser;//发送人
private String toUser;//接收人

public int getMsgType() {
	return msgType;
}

public void setMsgType(int msgType) {
	this.msgType = msgType;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public List<User> getUlist() {
	return Ulist;
}

public void setUlist(List<User> ulist) {
	Ulist = ulist;
}

public String getSocketKey() {
	return socketKey;
}

public void setSocketKey(String socketKey) {
	this.socketKey = socketKey;
}

public String getFromUser() {
	return fromUser;
}

public void setFromUser(String fromUser) {
	this.fromUser = fromUser;
}

public String getToUser() {
	return toUser;
}

public void setToUser(String toUser) {
	this.toUser = toUser;
}

}
