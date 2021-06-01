package com.servicetest.portlet;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.language.UTF8Control;

@Component(
	    immediate = true,
	    property = {
	        "language.id=en_US"
	    },
	    service = ResourceBundle.class
	)

public class MyLanguageProperties extends ResourceBundle {

	private final ResourceBundle resource = ResourceBundle.getBundle("content.Language", UTF8Control.INSTANCE);
	
	@Override
	public Enumeration<String> getKeys() {
		// TODO Auto-generated method stub
		return resource.getKeys();
	}

	@Override
	protected Object handleGetObject(String key) {
		// TODO Auto-generated method stub
		return resource.getObject(key);
	}

}
