package cn.cslg.mymvc;

import java.beans.PropertyDescriptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

public abstract class ActionForm {

	public void fillData(HttpServletRequest request, ActionForm form) throws DataFillException {
		try {
			// 获取Form类中的所有属性
			PropertyDescriptor pds[] = PropertyUtils.getPropertyDescriptors(form);
			for (PropertyDescriptor pd : pds) {
				if (!pd.getName().equals("class")) {
					
//					pd.getWriteMethod().invoke(form, request.getParameter(pd.getName()));
				
					/*
					 * 以属性名为名字取用户请求参数，如果未取到，则为null
					 * 因为有提交的数据是数组（如复选框的形式），所以用request.getParameterValues
					 */
					String[] values = request.getParameterValues(pd.getName());
					/*
					 * 对Form类中的属性赋值 一般FormBean中出现的类型为
					 * String，int，float，double，String[],Date等类型
					 */
					if (values!=null && values.length == 1) {
						//如果数据只有一项，使用ConvertUtils类把数据转换成Form中属性的类型
						String value = values[0];
						System.out.println(value);
						Class targetType = pd.getPropertyType();
						pd.getWriteMethod().invoke(form, ConvertUtils.convert(value, targetType));
					} else if (values!=null && values.length > 1){
						//如果数据确是数组，则需要强制转换成Object类型，否则会报too much arguments错误
						pd.getWriteMethod().invoke(form, (Object)values);
					}
				}
			}
		} catch (Exception e) {
			throw new DataFillException(e);
		}
	}
}
