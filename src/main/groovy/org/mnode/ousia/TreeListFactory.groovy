/**
 * 
 */
package org.mnode.ousia

import java.util.Map;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.TreeList;
import ca.odell.glazedlists.TreeList.Format;
import ca.odell.glazedlists.TreeList.ExpansionModel;

import groovy.util.AbstractFactory;
import groovy.util.FactoryBuilderSupport;

/**
 * @author fortuna
 *
 */
class TreeListFactory extends AbstractFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		FactoryBuilderSupport.checkValueIsType(value, name, EventList)
		ExpansionModel<?> expansionModel = attributes.remove('expansionModel')
		Format<?> format = attributes.remove('format')
		
		return new TreeList<?>(value, format, expansionModel)
	}

}
