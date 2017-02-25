package cn.cslg.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cslg.mymvc.Action;
import cn.cslg.mymvc.ActionForm;

public class UserExistsAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		LoginForm fr=(LoginForm) form;
		try(PrintWriter out=response.getWriter()){
			if(fr.getUsername().equals("jack"))
				out.print("1");
			else
				out.print("0");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}

}
