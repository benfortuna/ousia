package org.mnode.ousia

import groovy.util.AbstractFactory
import groovy.util.FactoryBuilderSupport

import java.util.Map

class FileTreePanelFactory extends AbstractFactory {

	@Override
	Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		new FileTreePanel()
	}

}
