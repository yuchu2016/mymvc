package cn.cslg.mymvc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "*.mvc", initParams = { @WebInitParam(name = "config", value = "mvc.xml") })
public class MvcControllerServlet extends HttpServlet {

	private HashMap<String, ActionConfig> map = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		setConfig();
		//loadXmlConfig(config);
	}

	private void loadXmlConfig(ServletConfig config) throws ServletException {
		String configFile = config.getInitParameter("config");
		String mvcConfigFile = this.getServletContext().getRealPath("/WEB-INF/" + configFile);

		try {
			File f = new File(mvcConfigFile);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			for (Iterator actions = root.elementIterator("action"); actions.hasNext();) {
				Element action = (Element) actions.next();
				String actionName = action.attributeValue("name");
				String actionClassName = action.attributeValue("actionClass");
				String formClassName = action.attributeValue("formClass");
				ActionConfig acfg = new ActionConfig(actionClassName, formClassName);
				for (Iterator forwards = action.elementIterator("forward"); forwards.hasNext();) {
					Element forward = (Element) forwards.next();
					String forwardName = forward.attributeValue("name");
					String forwardValue = forward.getText();
					acfg.addForward(forwardName, forwardValue);
				}
				map.put(actionName, acfg);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	

	private void setConfig() {
		ActionConfig loginConfig = new ActionConfig("cn.cslg.news.LoginAction", "cn.cslg.news.LoginForm");
		loginConfig.addForward("success", "/admin/index.jsp");
		loginConfig.addForward("error", "/login.jsp");
		map.put("login", loginConfig);

		ActionConfig changePasswordConfig = new ActionConfig("cn.cslg.news.ChangePasswordAction",
				"cn.cslg.news.ChangePasswordForm");
		// 这里添加修改密码后的转向，并添加相关的视图
		// TODO ...
		map.put("changePassword", changePasswordConfig);

		ActionConfig registerConfig = new ActionConfig("cn.cslg.news.RegisterAction", "cn.cslg.news.RegisterForm");
		// 这里添加注册后的转向，并添加相关的视图
		// TODO ...
		map.put("register", registerConfig);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("前端控制器：I'm called!\tURL：" + req.getRequestURI());
		// 获取用户请求的URL，从中分离出.mvc之前的操作名
		String uri = req.getRequestURI();
		int pos_end = uri.indexOf(".mvc");
		int pos_start = uri.lastIndexOf("/", pos_end) + 1;
		String pat = uri.substring(pos_start, pos_end);
		System.out.println("操作名 :" + pat);

		try {
			// 根据操作名得到对应需要填充的Form类名，并创建对象
			Class cf = Class.forName(map.get(pat).getFormClassname());
			ActionForm formbean = (ActionForm) cf.newInstance();
			// 自动填充Form对象
			formbean.fillData(req, formbean);
			System.out.println("自动填充的Form对象:" + formbean);

			// 根据操作名得到对应需要填充的Action类名，并创建对象
			Class ac = Class.forName(map.get(pat).getActionClassname());
			Action action = (Action) ac.newInstance();

			// 调用Action类的方法并得到返回值
			String result = action.execute(req, resp, formbean);

			// 根据返回值找到要转发到的视图地址
			if (result != null) {
				String fp = map.get(pat).findForward(result);
				System.out.println(fp);
				// 转发到结果视图
				this.getServletContext().getRequestDispatcher(fp).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
