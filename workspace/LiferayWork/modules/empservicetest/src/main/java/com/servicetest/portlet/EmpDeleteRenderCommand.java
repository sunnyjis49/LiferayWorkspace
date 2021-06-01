package com.servicetest.portlet;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.employee.model.Employee;
import com.employee.service.EmployeeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.servicetest.constants.ServiceTestPortletKeys;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + ServiceTestPortletKeys.ServiceTest, 
	        "javax.portlet.name=" + ServiceTestPortletKeys.StudentPortlet,
	        "mvc.command.name=employeeDeleteMethod"
	    },
	    service = MVCRenderCommand.class
	)

public class EmpDeleteRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest request, RenderResponse response) throws PortletException {
		String name = ParamUtil.getString(request, "name");
		String email = ParamUtil.getString(request, "email");
		String mobile = ParamUtil.getString(request, "mobile");
		
		System.out.println("name: "+name+" email: "+email+" mobile: "+mobile);
		
		try {
			
			java.util.List<Employee> empList = EmployeeLocalServiceUtil.findByName(name);
	
			empList.forEach(emp->{
				try {
					EmployeeLocalServiceUtil.deleteEmployee(emp.getEmpId());
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			
			System.out.println("DATA is Deleted successfully....");
			//response.setRenderParameter("msg", name+" is successfully deleted from DB..");
			//response.setRenderParameter("fromEmpSubmitAction", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/META-INF/resources/greeetings.jsp";
	}

}
