/**
 * 
 */
package com.demo.portlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author SUN
 *
 */
public class HelloActivator implements BundleActivator{

	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Started...");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Stopped...");
	}
	
}
