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
package org.mnode.ousia.flamingo

import groovy.util.logging.Log
import groovy.util.logging.Slf4j;

import java.awt.event.ActionListener
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.util.logging.Level

import javax.swing.Action

import org.pushingpixels.flamingo.api.common.AbstractCommandButton
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon

@Slf4j
class CommandButtonFactory extends AbstractFactory {

	@SuppressWarnings('rawtypes')
	static final Class<?>[] ICON_ARGS = [ResizableIcon]
	@SuppressWarnings('rawtypes')
	static final Class<?>[] STRING_ARGS = [String]
	@SuppressWarnings('rawtypes')
	static final Class<?>[] STRING_ICON_ARGS = [String, ResizableIcon]
	
	final Constructor<?> iconCtor
	final Constructor<?> stringCtor
	final Constructor<?> stringIconCtor
	final Class klass

	CommandButtonFactory(Class klass) {
        this.klass = klass
		
        try {
            iconCtor = klass.getConstructor(ICON_ARGS)
        } catch (NoSuchMethodException ex) {
            log.debug(null, ex)
        } catch (SecurityException ex) {
            log.error(null, ex)
        }

        try {
            stringCtor = klass.getConstructor(STRING_ARGS)
        } catch (NoSuchMethodException ex) {
            log.debug(null, ex)
        } catch (SecurityException ex) {
            log.error(null, ex)
        }
		
        try {
            stringIconCtor = klass.getConstructor(STRING_ICON_ARGS)
        } catch (NoSuchMethodException ex) {
            log.debug(null, ex)
        } catch (SecurityException ex) {
            log.error(null, ex)
        }
    }
	
	@Override
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {

		AbstractCommandButton button
		
		def updateFromAction = { action ->
			button.text = action.getValue('Name')
			button.addActionListener action
			if (action.getValue(Action.SMALL_ICON) instanceof ResizableIcon) {
				button.icon = action.getValue(Action.SMALL_ICON)
			}
		}
		
        try {
            if (value instanceof GString) {
				value = value as String
            }
			
            if (value instanceof ResizableIcon) {
                button = (iconCtor) ? iconCtor.newInstance(value) : stringIconCtor.newInstance(null, value)
            }
			else if (value instanceof Action) {
                button = (stringCtor) ? stringCtor.newInstance('') : stringIconCtor.newInstance('', null)
				updateFromAction(value)
			}
			else if (value instanceof String) {
                button = (stringCtor) ? stringCtor.newInstance(value) : stringIconCtor.newInstance(value, null)
            }
			else if (klass.isAssignableFrom(value.getClass())) {
                button = value
            }
			else if (value == null) {
                button = (stringCtor) ? stringCtor.newInstance(null) : stringIconCtor.newInstance(null, null)
			}
			else {
                throw new RuntimeException("$name can only have a value argument of type org.pushingpixels.flamingo.api.common.icon.ResizableIcon, java.lang.String, or $klass.name")
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to create component for '$name' reason: $e", e)
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to create component for '$name' reason: $e", e)
        }
		
		def action = attributes.remove('action')
		if (action) {
			updateFromAction(action)
		}
		
		def selected = attributes.remove('selected')
		if (button && selected) {
			button.actionModel.selected = selected
		}
		
		ActionListener actionPerformed = attributes.remove('actionPerformed')
		if (button && actionPerformed) {
			button.addActionListener actionPerformed
		}
		button
	}
}
