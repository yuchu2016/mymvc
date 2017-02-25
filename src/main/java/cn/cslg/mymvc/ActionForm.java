package cn.cslg.mymvc;

import java.beans.PropertyDescriptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

public abstract class ActionForm {

	public void fillData(HttpServletRequest request, ActionForm form) throws DataFillException {
		try {
			// ��ȡForm���е���������
			PropertyDescriptor pds[] = PropertyUtils.getPropertyDescriptors(form);
			for (PropertyDescriptor pd : pds) {
				if (!pd.getName().equals("class")) {
					
//					pd.getWriteMethod().invoke(form, request.getParameter(pd.getName()));
				
					/*
					 * ��������Ϊ����ȡ�û�������������δȡ������Ϊnull
					 * ��Ϊ���ύ�����������飨�縴ѡ�����ʽ����������request.getParameterValues
					 */
					String[] values = request.getParameterValues(pd.getName());
					/*
					 * ��Form���е����Ը�ֵ һ��FormBean�г��ֵ�����Ϊ
					 * String��int��float��double��String[],Date������
					 */
					if (values!=null && values.length == 1) {
						//�������ֻ��һ�ʹ��ConvertUtils�������ת����Form�����Ե�����
						String value = values[0];
						System.out.println(value);
						Class targetType = pd.getPropertyType();
						pd.getWriteMethod().invoke(form, ConvertUtils.convert(value, targetType));
					} else if (values!=null && values.length > 1){
						//�������ȷ�����飬����Ҫǿ��ת����Object���ͣ�����ᱨtoo much arguments����
						pd.getWriteMethod().invoke(form, (Object)values);
					}
				}
			}
		} catch (Exception e) {
			throw new DataFillException(e);
		}
	}
}
