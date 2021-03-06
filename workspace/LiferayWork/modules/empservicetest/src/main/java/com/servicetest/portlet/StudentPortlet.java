package com.servicetest.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.servicetest.constants.ServiceTestPortletKeys;

/**
 * @author SUN
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=categorysunny",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=studentportlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ServiceTestPortletKeys.StudentPortlet,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
		
		
	},
	service = Portlet.class
)

public class StudentPortlet extends MVCPortlet {
	
	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		System.out.println("Inside StudentPortlet Testing for ServiceWrapper: "+UserLocalServiceUtil.getUsersCount());
		include(viewTemplate, renderRequest, renderResponse);
	}

}
