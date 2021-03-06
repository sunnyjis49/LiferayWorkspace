package com.servicetest.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.employee.model.Employee;
import com.employee.service.EmployeeLocalService;
import com.employee.service.EmployeeLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.servicetest.constants.ServiceTestPortletKeys;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + ServiceTestPortletKeys.ServiceTest, 
	        "javax.portlet.name=" + ServiceTestPortletKeys.StudentPortlet,
	        "mvc.command.name=employeeSubmitMethod"
	    },
	    service = MVCActionCommand.class
	)

public class EmpSubmitActionCommand extends BaseMVCActionCommand {
	
	@Reference
	EmployeeLocalService employeeLocalService;
	
	@Reference
	CounterLocalService counterLocalService;

	@Override
	protected void doProcessAction(ActionRequest request, ActionResponse response) throws Exception {
		String name = ParamUtil.getString(request, "name");
		String email = ParamUtil.getString(request, "email");
		String mobile = ParamUtil.getString(request, "mobile");
		
		System.out.println("name: "+name+" email: "+email+" mobile: "+mobile);
		
		try {
			//Employee employee = EmployeeLocalServiceUtil.createEmployee((int)CounterLocalServiceUtil.increment());
			Employee employee = employeeLocalService.createEmployee((int) counterLocalService.increment());
			employee.setName(name);
			employee.setEmail(email);
			employee.setMobile(mobile);
			
			EmployeeLocalServiceUtil.addEmployee(employee);
			System.out.println("DATA is saved successfully....");
			response.setRenderParameter("msg", employee.getName()+" is successfully added into the DB...");
			response.setRenderParameter("fromEmpSubmitAction", "success");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
