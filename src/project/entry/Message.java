package project.entry;

import java.io.Serializable;

public class Message implements Serializable{
private int msgType;
private User user;
private String content;//消息提示内容

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

}
