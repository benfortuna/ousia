/**
 * 
 */
package org.mnode.ousia

import java.util.Map;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.SortedList;

import groovy.util.AbstractFactory;
import groovy.util.FactoryBuilderSupport;

/**
 * @author fortuna
 *
 */
class SortedListFactory extends AbstractFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		FactoryBuilderSupport.checkValueIsType(value, name, EventList)
		Comparator<?> comparator = attributes.remove('comparator')

		return new SortedList<?>(value, comparator);
	}

}
