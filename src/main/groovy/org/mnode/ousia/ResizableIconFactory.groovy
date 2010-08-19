/**
 * 
 */
package org.mnode.ousia

import java.awt.Dimension;
import java.util.Map;

import org.apache.batik.svggen.SVGRescaleOp;
import org.pushingpixels.flamingo.api.svg.SvgBatikResizableIcon;

import groovy.util.AbstractFactory;
import groovy.util.FactoryBuilderSupport;

/**
 * @author fortuna
 *
 */
class ResizableIconFactory extends AbstractFactory {

	/* (non-Javadoc)
	 * @see groovy.util.Factory#newInstance(groovy.util.FactoryBuilderSupport, java.lang.Object, java.lang.Object, java.util.Map)
	 */
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes)
                throws InstantiationException, IllegalAccessException {

		URL url
		if (FactoryBuilderSupport.checkValueIsTypeNotString(value, name, URL)) {
			url = value
		}
		Dimension size = attributes.remove('size')
		return SvgBatikResizableIcon.getSvgIcon(url, size);
	}

}
