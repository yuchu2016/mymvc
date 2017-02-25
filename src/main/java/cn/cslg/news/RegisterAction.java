package cn.cslg.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cslg.mymvc.Action;
import cn.cslg.mymvc.ActionForm;

public class RegisterAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		// TODO 添加注册代码
		System.out.println("注册Action被调用");
		return "success";
	}

}
