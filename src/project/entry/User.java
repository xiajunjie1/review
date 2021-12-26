package project.entry;

import java.io.Serializable;

public class User implements Serializable{
private int id;
private String username;
private String password;
private String nickname;
private String photo;
public int getId() {
	return id;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return this.username+this.password+this.nickname;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
}
