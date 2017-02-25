package cn.cslg.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cslg.mymvc.Action;
import cn.cslg.mymvc.ActionForm;

public class ChangePasswordAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		//TODO 添加修改密码代码
		System.out.println("修改密码Action被调用");
		return "success";
	}

}
