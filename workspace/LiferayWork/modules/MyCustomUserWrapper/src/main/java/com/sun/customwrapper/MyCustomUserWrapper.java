package com.sun.customwrapper;

import com.liferay.portal.kernel.service.UserLocalServiceWrapper;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author SUN
 */
@Component(
	immediate = true,
	property = {
	},
	service = ServiceWrapper.class
)
public class MyCustomUserWrapper extends UserLocalServiceWrapper {

	public MyCustomUserWrapper() {
		super(null);
	}
	
	@Override
	public int getUsersCount() {
		System.out.println("MyCustomUserWrapper method is called.......");
		return 10;
	}
}