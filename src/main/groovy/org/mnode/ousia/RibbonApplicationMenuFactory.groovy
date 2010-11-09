/**
 * 
 */
package org.mnode.ousia

import groovy.util.AbstractFactory;
import groovy.util.FactoryBuilderSupport;

import java.util.Map;

import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryFooter;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;

/**
 * @author fortuna
 *
 */
class RibbonApplicationMenuFactory extends AbstractFactory {
	
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		return new RibbonApplicationMenu()
	}

	/**
	 * {@inheritDoc}
	 */
	void setChild(FactoryBuilderSupport builder,  parent,  child) {
		if (child instanceof RibbonApplicationMenuEntryPrimary) {
			parent.addMenuEntry(child)
		}
		else if (child instanceof RibbonApplicationMenuEntryFooter) {
			parent.addFooterEntry(child)
		}
	}

}
