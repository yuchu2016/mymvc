package cn.cslg.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cslg.mymvc.Action;
import cn.cslg.mymvc.ActionForm;

public class ChangePasswordAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		//TODO ����޸��������
		System.out.println("�޸�����Action������");
		return "success";
	}

}
