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
package org.mnode.ousia.flamingo



import javax.swing.JComponent;

import groovy.util.FactoryBuilderSupport;

import org.pushingpixels.flamingo.api.common.AbstractCommandButton;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonComponent;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;


/**
 * @author fortuna
 *
 */
class RibbonBandFactory extends AbstractFactory {
	
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
		def title
		def icon
		
		if (value instanceof GString) value = value as String
		
		if (value instanceof String) {
			title = value
		}
		else {
			title = attributes.remove('title')
		}
		icon = attributes.remove('icon')
		def ribbonBand = new JRibbonBand(title, icon, null)
		
		def resizePolicy = attributes.remove('resizePolicy')
		if (resizePolicy) {
			if ('permissive' == resizePolicy) {
				ribbonBand.resizePolicies = CoreRibbonResizePolicies.getCorePoliciesPermissive(ribbonBand)
			}
			if ('restrictive' == resizePolicy) {
				ribbonBand.resizePolicies = CoreRibbonResizePolicies.getCorePoliciesRestrictive(ribbonBand)
			}
			if ('none' == resizePolicy) {
				ribbonBand.resizePolicies = CoreRibbonResizePolicies.getCorePoliciesNone(ribbonBand)
			}
		}
		else {
			def rp = []
			
			def resizePolicies = attributes.remove('resizePolicies')
			resizePolicies?.each {
				if ('mirror' == it) {
					rp << new CoreRibbonResizePolicies.Mirror(ribbonBand.controlPanel)
				}
				else if ('mid2low' == it) {
					rp << new CoreRibbonResizePolicies.Mid2Low(ribbonBand.controlPanel)
				}
			}
			ribbonBand.resizePolicies = rp
		}
		return ribbonBand
	}
	
	/**
	 * {@inheritDoc}
	 */
	/*
	void setChild(FactoryBuilderSupport builder, parent, child) {
		if (child.component instanceof AbstractCommandButton) {
			parent.addCommandButton child.component, child.priority
		}
		else if (child.component instanceof JComponent) {
			parent.addRibbonComponent new JRibbonComponent(child.component), child.rowSpan
		}
	}
	*/
}
