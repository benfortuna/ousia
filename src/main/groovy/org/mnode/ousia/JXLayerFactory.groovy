/**
 * 
 */
package org.mnode.ousia

import java.awt.Component;
import java.util.Map;

import javax.swing.JComponent;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.LayerUI;
import org.jdesktop.jxlayer.plaf.ext.LockableUI;


import groovy.util.AbstractFactory;
import groovy.util.FactoryBuilderSupport;

/**
 * @author fortuna
 *
 */
class JXLayerFactory extends AbstractFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		FactoryBuilderSupport.checkValueIsType(value, name, LayerUI)
		JXLayer layer = new JXLayer<JComponent>()
		layer.ui = value
		return layer
	}

	void setChild(FactoryBuilderSupport builder, parent, child) {
		parent.view = child
	}
}
