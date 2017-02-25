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
		// ��������޸�������ת�򣬲������ص���ͼ
		// TODO ...
		map.put("changePassword", changePasswordConfig);

		ActionConfig registerConfig = new ActionConfig("cn.cslg.news.RegisterAction", "cn.cslg.news.RegisterForm");
		// �������ע����ת�򣬲������ص���ͼ
		// TODO ...
		map.put("register", registerConfig);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ǰ�˿�������I'm called!\tURL��" + req.getRequestURI());
		// ��ȡ�û������URL�����з����.mvc֮ǰ�Ĳ�����
		String uri = req.getRequestURI();
		int pos_end = uri.indexOf(".mvc");
		int pos_start = uri.lastIndexOf("/", pos_end) + 1;
		String pat = uri.substring(pos_start, pos_end);
		System.out.println("������ :" + pat);

		try {
			// ���ݲ������õ���Ӧ��Ҫ����Form����������������
			Class cf = Class.forName(map.get(pat).getFormClassname());
			ActionForm formbean = (ActionForm) cf.newInstance();
			// �Զ����Form����
			formbean.fillData(req, formbean);
			System.out.println("�Զ�����Form����:" + formbean);

			// ���ݲ������õ���Ӧ��Ҫ����Action����������������
			Class ac = Class.forName(map.get(pat).getActionClassname());
			Action action = (Action) ac.newInstance();

			// ����Action��ķ������õ�����ֵ
			String result = action.execute(req, resp, formbean);

			// ���ݷ���ֵ�ҵ�Ҫת��������ͼ��ַ
			if (result != null) {
				String fp = map.get(pat).findForward(result);
				System.out.println(fp);
				// ת���������ͼ
				this.getServletContext().getRequestDispatcher(fp).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
