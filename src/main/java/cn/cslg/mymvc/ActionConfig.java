package cn.cslg.mymvc;

import java.util.HashMap;

public class ActionConfig {

    private String actionClassname; //业务方法execute所在类，是Action的子类
    private String formClassname;  //表单数据提交后要自动填充的Form类，是ActionForm的子类
    private final HashMap<String, String> fowards = new HashMap<>(); //不同情况下的转发 （"success","/admin/index.jsp"）,("error","/login.jsp")

    public String getActionClassname() {
        return actionClassname;
    }

    public void setActionClassname(String actionClassname) {
        this.actionClassname = actionClassname;
    }

    public String getFormClassname() {
        return formClassname;
    }

    public void setFormClassname(String formClassname) {
        this.formClassname = formClassname;
    }

    /**
     * 添加转发
     * @param name 结果名
     * @param url  转发url地址
     */
    public void addForward(String name, String url) {
        this.fowards.put(name, url);
    }

    /**
     * 
     * @param name 转发时的名字，如 "success","error","failure","input"
     * @return
     */
    public String findForward(String name) {
        return this.fowards.get(name); //返回转发的地址
    }

    public ActionConfig(String actionClassname, String formClassname) {
        super();
        this.actionClassname = actionClassname;
        this.formClassname = formClassname;
    }

}
