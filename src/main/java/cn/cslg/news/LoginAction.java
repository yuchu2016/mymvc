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
		//������Ը������ݿ���ʵ��
		if(lf.getUsername().equals("kate") && lf.getPassword().equals("123")){
			request.setAttribute("LOGIN_USER", lf.getUsername());
			return "success";
		}else{
			request.setAttribute("LOGIN_ERROR", "��¼ʧ�ܣ�������û��������룡");
			return "error";
		}
	}

}
