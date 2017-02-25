package cn.cslg.news;

import cn.cslg.mymvc.ActionForm;

public class ChangePasswordForm extends ActionForm {
	private String oldPassword;
	private String newPassword;
	private String newPassword2;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	@Override
	public String toString() {
		return "ChangePasswordForm [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", newPassword2="
				+ newPassword2 + "]";
	}

}
