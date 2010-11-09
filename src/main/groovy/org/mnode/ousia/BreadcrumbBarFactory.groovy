/**
 * 
 */
package org.mnode.ousia

import java.util.Map;

import org.pushingpixels.flamingo.api.bcb.BreadcrumbBarCallBack;
import org.pushingpixels.flamingo.api.bcb.JBreadcrumbBar;

import groovy.util.AbstractFactory;
import groovy.util.FactoryBuilderSupport;

/**
 * @author fortuna
 *
 */
class BreadcrumbBarFactory extends AbstractFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		FactoryBuilderSupport.checkValueIsType(value, name, BreadcrumbBarCallBack)
		return new JBreadcrumbBar<?>(value);
	}

}
