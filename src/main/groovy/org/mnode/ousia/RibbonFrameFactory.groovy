/**
 * 
 */
package org.mnode.ousia

import javax.swing.JFrame;

import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;

import groovy.swing.factory.FrameFactory;

/**
 * @author fortuna
 *
 */
class RibbonFrameFactory extends FrameFactory {
	
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		JFrame frame
		if (FactoryBuilderSupport.checkValueIsType(value, name, JFrame.class)) {
			frame = value
		} else {
			frame = new JRibbonFrame()
		}

		handleRootPaneTasks(builder, frame, attributes)

		return frame;
	}

}
