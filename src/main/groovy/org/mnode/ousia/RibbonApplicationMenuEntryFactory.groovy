/**
 * Copyright (c) 2010, Ben Fortuna
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

import java.lang.reflect.Constructor;
import java.util.Map;

import groovy.util.AbstractFactory;
import groovy.util.FactoryBuilderSupport;

import java.awt.event.ActionListener;

import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntry;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryFooter;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;


/**
 * @author fortuna
 *
 */
class RibbonApplicationMenuEntryFactory extends AbstractFactory {

	private final Constructor<?> withKindConstructor;
	
	private final Constructor<?> withoutKindConstructor;

	private final Class<?> clazz;
	
	RibbonApplicationMenuEntryFactory(Class clazz) {
		try {
			withKindConstructor = clazz.getConstructor([ResizableIcon, String, ActionListener, CommandButtonKind] as Class[])
		}
		catch (NoSuchMethodException e) {
		}
		
		try {
			withoutKindConstructor = clazz.getConstructor([ResizableIcon, String, ActionListener] as Class[])
		}
		catch (NoSuchMethodException e) {
		}

		this.clazz = clazz
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		ResizableIcon icon = attributes.remove('icon')
		String text = attributes.remove('text')
		ActionListener mainActionListener = attributes.remove('actionPerformed')
		CommandButtonKind entryKind = attributes.remove('kind')
		
		if (withKindConstructor && entryKind) {
			return withKindConstructor.newInstance(icon, text, mainActionListener, entryKind)
		}
		else {
			return withoutKindConstructor.newInstance(icon, text, mainActionListener)
		}
	}

	void setParent(FactoryBuilderSupport builder, parent, child) {
		if (parent instanceof RibbonApplicationMenu) {
			if (child instanceof RibbonApplicationMenuEntryPrimary) {
				parent.addMenuEntry child
			}
			else if (child instanceof RibbonApplicationMenuEntryFooter) {
				parent.addFooterEntry child
			}
		}
	}
	
	void setChild(FactoryBuilderSupport builder, parent, child) {
		if (parent instanceof RibbonApplicationMenuEntryPrimary && child instanceof Map) {
			parent.addSecondaryMenuGroup child['groupTitle'], child['entries']
		}
	}
}
