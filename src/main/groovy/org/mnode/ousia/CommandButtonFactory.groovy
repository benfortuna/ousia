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
package org.mnode.ousia

import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Action;
import javax.swing.Icon;

import org.pushingpixels.flamingo.api.common.AbstractCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

class CommandButtonFactory extends AbstractFactory {

	@SuppressWarnings("rawtypes")
	static final Class<?>[] ICON_ARGS = [ResizableIcon];
	@SuppressWarnings("rawtypes")
	static final Class<?>[] STRING_ARGS = [String];
	@SuppressWarnings("rawtypes")
	static final Class<?>[] STRING_ICON_ARGS = [String, ResizableIcon];
	
	final Constructor<?> iconCtor;
	final Constructor<?> stringCtor;
	final Constructor<?> stringIconCtor;
	final Class klass;

	CommandButtonFactory(Class klass) {
        try {
            iconCtor = klass.getConstructor(ICON_ARGS);
            stringCtor = klass.getConstructor(STRING_ARGS);
            stringIconCtor = klass.getConstructor(STRING_ICON_ARGS);
            this.klass = klass;
        } catch (NoSuchMethodException ex) {
            Logger.getLogger("global").log(Level.INFO, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }
	
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		AbstractCommandButton button
        try {
            if (value instanceof GString) value = value as String
            if (value instanceof ResizableIcon) {
                button = iconCtor.newInstance(value);
            } else if (value instanceof String) {
                button = stringCtor.newInstance(value);
            } else if (klass.isAssignableFrom(value.getClass())) {
                button = value;
            } else {
                throw new RuntimeException("$name can only have a value argument of type org.pushingpixels.flamingo.api.common.icon.ResizableIcon, java.lang.String, or $klass.name");
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to create component for '$name' reason: $e", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to create component for '$name' reason: $e", e);
        }
		
		def selected = attributes.remove('selected')
		if (button && selected) {
			button.actionModel.selected = selected
		}
		
		ActionListener actionPerformed = attributes.remove('actionPerformed')
		if (button && actionPerformed) {
			button.addActionListener actionPerformed
		}
		return button
	}

}
