package project.entry;

import java.io.Serializable;

public class Sms implements Serializable {
private int id;
private String hostname;
private String friendname;
private String content;
private int isread;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getHostname() {
	return hostname;
}
public void setHostname(String hostname) {
	this.hostname = hostname;
}
public String getFriendname() {
	return friendname;
}
public void setFriendname(String friendname) {
	this.friendname = friendname;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getIsread() {
	return isread;
}
public void setIsread(int isread) {
	this.isread = isread;
}
}
