/**
 * Copyright (c) 2011, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/**
 * 
 */
package org.mnode.ousia

import groovy.util.AbstractFactory
import groovy.util.FactoryBuilderSupport

import java.awt.Frame
import java.util.Map

import javax.swing.JComponent
import javax.swing.JSplitPane

import org.jdesktop.jxlayer.JXLayer
import org.jdesktop.jxlayer.plaf.LayerUI
import org.jdesktop.swingx.JXStatusBar
import org.mnode.ousia.tracker.ComponentTracker
import org.mnode.ousia.tracker.FrameTracker
import org.mnode.ousia.tracker.JRibbonFrameTracker
import org.mnode.ousia.tracker.JSplitPaneTracker
import org.mnode.ousia.tracker.JXStatusBarTracker
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame

/**
 * @author fortuna
 *
 */
class JXLayerFactory extends AbstractFactory {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		FactoryBuilderSupport.checkValueIsType(value, name, LayerUI)
		JXLayer layer = new JXLayer<JComponent>(null, value)
		
		builder.context.trackingEnabled = attributes.remove('trackingEnabled')
		builder.context.id = attributes['id']
		
		layer
	}

	void setChild(FactoryBuilderSupport builder, parent, child) {
		parent.view = child
	}
	
	void onNodeCompleted(FactoryBuilderSupport builder, Object parent, Object node) {
		if (builder.context.trackingEnabled) {
			if (node.class.isAssignableFrom(JRibbonFrame.class)) {
				JRibbonFrameTracker tracker = [node, builder.context.id]
			}
			else if (node.class.isAssignableFrom(Frame.class)) {
				FrameTracker tracker = [node, builder.context.id]
			}
			else if (node.class.isAssignableFrom(JSplitPane.class)) {
				JSplitPaneTracker tracker = [node, builder.context.id]
			}
			else if (node.class.isAssignableFrom(JXStatusBar.class)) {
				JXStatusBarTracker tracker = [node, builder.context.id]
			}
			else {
				ComponentTracker tracker = [node, builder.context.id]
			}
		}
		super.onNodeCompleted builder, parent, node
	}
}
