package com.servicetest.portlet;

import com.servicetest.constants.ServiceTestPortletKeys;
import com.employee.model.Employee;
import com.employee.service.EmployeeLocalService;
import com.employee.service.EmployeeLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.awt.List;
import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author SUN
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=categorysunny",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=empservicetest Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ServiceTestPortletKeys.ServiceTest,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
		
		
	},
	service = Portlet.class
)


public class ServiceTestPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		DynamicQuery dynamicQuery = EmployeeLocalServiceUtil.dynamicQuery();//DynamicQueryFactoryUtil.forClass(Employee.class);
		//dynamicQuery.add(RestrictionsFactoryUtil.eq("name", "farhan"));
		dynamicQuery.add(RestrictionsFactoryUtil.like("email", "sun%"));
		java.util.List<Employee> dynEmpList = EmployeeLocalServiceUtil.dynamicQuery(dynamicQuery);
		System.out.println("From Dynamic Query ....."+dynamicQuery.toString());
		dynEmpList.forEach(System.out::println);
		String fromEmpSubmitAction = ParamUtil.getString(renderRequest, "fromEmpSubmitAction");
		String msg = ParamUtil.getString(renderRequest, "msg");
		System.out.println("fromEmpSubmitAction: "+fromEmpSubmitAction);
		//System.out.println("the employee name by id: "+EmployeeLocalServiceUtil.getEmployee(2).getName());
		System.out.println("From finder...........");
		java.util.List<Employee> empList = EmployeeLocalServiceUtil.findByName("sunny");
		empList.forEach(System.out::println);
		empList.forEach(emp->System.out.println(emp.getEmail()));
		empList.stream().filter(emp->emp.getEmail().startsWith("sun")).forEach(System.out::println);
		renderRequest.setAttribute("msg", msg);
		if(fromEmpSubmitAction.equalsIgnoreCase("success"))
			include("/META-INF/resources/greeetings.jsp", renderRequest, renderResponse);
		else
			super.doView(renderRequest, renderResponse);
		
	}
	
	//@ProcessAction(name="employeeSubmitMethod")
	public void employeeSubmit(ActionRequest request, ActionResponse response){
			String name = ParamUtil.getString(request, "name");
			String email = ParamUtil.getString(request, "email");
			String mobile = ParamUtil.getString(request, "mobile");
			
			System.out.println("name: "+name+" email: "+email+" mobile: "+mobile);
			
			try {
				Employee employee = EmployeeLocalServiceUtil.createEmployee((int)CounterLocalServiceUtil.increment());
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
	
	
	
	//@ProcessAction(name="employeeDeleteMethod")
	public void employeeDelete(ActionRequest request, ActionResponse response){
			String name = ParamUtil.getString(request, "name");
			String email = ParamUtil.getString(request, "email");
			String mobile = ParamUtil.getString(request, "mobile");
			
			System.out.println("name: "+name+" email: "+email+" mobile: "+mobile);
			
			try {
				/*Employee employee = EmployeeLocalServiceUtil.createEmployee((int)CounterLocalServiceUtil.increment());
				employee.setName(name);
				employee.setEmail(email);
				employee.setMobile(mobile);*/
				
				java.util.List<Employee> empList = EmployeeLocalServiceUtil.findByName(name);
//				for(Employee emp : empList){
//					System.out.println("empId: "+emp.getEmpId());
//					EmployeeLocalServiceUtil.deleteEmployee(emp.getEmpId());
//				}
				
				empList.forEach(emp->{
					try {
						EmployeeLocalServiceUtil.deleteEmployee(emp.getEmpId());
					} catch (PortalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				
				//EmployeeLocalServiceUtil.deleteEmployee(employee);
				
				System.out.println("DATA is Deleted successfully....");
				response.setRenderParameter("msg", name+" is successfully deleted from DB..");
				response.setRenderParameter("fromEmpSubmitAction", "success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}