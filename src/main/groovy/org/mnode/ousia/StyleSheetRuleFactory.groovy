/**
 * 
 */
package org.mnode.ousia

import java.util.Map;

import groovy.util.AbstractFactory;
import groovy.util.FactoryBuilderSupport;

/**
 * @author fortuna
 *
 */
class StyleSheetRuleFactory extends AbstractFactory {

	/**
	 * {@inheritDoc}
	 */
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	void setParent(FactoryBuilderSupport builder,  parent,  child) {
		parent.addRule(child)
	}
}
