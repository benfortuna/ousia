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
class HTMLEditorKitFactory extends AbstractFactory {

	/**
	 * {@inheritDoc}
	 */
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		return new HTMLEditorKitExt();
	}

	void setChild(FactoryBuilderSupport builder,  parent,  child) {
		parent.setStyleSheet(child)
	}
}
