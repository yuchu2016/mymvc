package cn.cslg.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cslg.mymvc.Action;
import cn.cslg.mymvc.ActionForm;

public class RegisterAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		// TODO ���ע�����
		System.out.println("ע��Action������");
		return "success";
	}

}
