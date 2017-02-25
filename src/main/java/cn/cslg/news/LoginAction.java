package cn.cslg.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cslg.mymvc.Action;
import cn.cslg.mymvc.ActionForm;

public class LoginAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		System.out.println(form);
		LoginForm lf=(LoginForm) form;
		//这里可以改用数据库来实现
		if(lf.getUsername().equals("kate") && lf.getPassword().equals("123")){
			request.setAttribute("LOGIN_USER", lf.getUsername());
			return "success";
		}else{
			request.setAttribute("LOGIN_ERROR", "登录失败！错误的用户名或密码！");
			return "error";
		}
	}

}
