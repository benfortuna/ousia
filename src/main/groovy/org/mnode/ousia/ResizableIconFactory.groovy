/**
 * Copyright (c) 2012, Ben Fortuna
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

import java.awt.Dimension
import java.util.Map

import javax.swing.ImageIcon;

import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;


/**
 * @author fortuna
 *
 */
class ResizableIconFactory extends AbstractFactory {

	/* (non-Javadoc)
	 * @see groovy.util.Factory#newInstance(groovy.util.FactoryBuilderSupport, java.lang.Object, java.lang.Object, java.util.Map)
	 */
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes)
                throws InstantiationException, IllegalAccessException {

		URL url
		if (FactoryBuilderSupport.checkValueIsTypeNotString(value, name, URL)) {
			url = value
		}
		else {
			url = ResizableIconFactory.getResource(value)
		}
		Dimension size = attributes.remove('size')
		// XXX: replace with svg salamander
//		SvgBatikResizableIcon.getSvgIcon(url, size)
		ImageWrapperResizableIcon.getIcon(url, size)
	}

}
