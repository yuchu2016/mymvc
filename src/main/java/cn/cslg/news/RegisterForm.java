package cn.cslg.news;

import java.util.Arrays;
import java.sql.Date;

import cn.cslg.mymvc.ActionForm;

public class RegisterForm extends ActionForm{
	private String username;
	private String password;
	private Date birthday;
	private String[] fav;
	private int receiveMail;

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String[] getFav() {
		return fav;
	}

	public void setFav(String[] fav) {
		this.fav = fav;
	}

	public int getReceiveMail() {
		return receiveMail;
	}

	public void setReceiveMail(int receiveMail) {
		this.receiveMail = receiveMail;
	}

	@Override
	public String toString() {
		return "RegisterForm [username=" + username + ", password=" + password + ", birthday=" + birthday + ", fav="
				+ Arrays.toString(fav) + ", receiveMail=" + receiveMail + "]";
	}

}
