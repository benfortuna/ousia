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
package groovy.runtime.metaclass.javax.swing.text

import java.awt.Component;
import java.awt.Window;

import javax.swing.Icon;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import groovy.lang.MetaClass;
import groovy.lang.DelegatingMetaClass;

class DefaultStyledDocumentMetaClass extends DelegatingMetaClass {
	
	DefaultStyledDocumentMetaClass(MetaClass delegate) {
		super(delegate)
	}
	
	void append(Document doc, String text, String style){
		doc.insertString(doc.length, text, doc.getStyle(style))
//        ensureNoDocLengthOverflow(doc)
	}

//	void append(Document doc, Window window, AttributeSet style) {
//		append(doc, window.toString(), style)
//	}

	void append(Document doc, Object object, String style) {
		append(doc, object.toString(), style)
	}

	void append(Document doc, Component component, String style) {
		SimpleAttributeSet sas = new SimpleAttributeSet();
		sas.addAttribute(StyleConstants.NameAttribute, "component")
		StyleConstants.setComponent(sas, component)
		append(doc, component.toString(), sas)
	}

	void append(Document doc, Icon icon, String style) {
		SimpleAttributeSet sas = new SimpleAttributeSet();
		sas.addAttribute(StyleConstants.NameAttribute, "icon")
		StyleConstants.setIcon(sas, icon)
		append(doc, icon.toString(), sas)
	}
	
	public Object invokeMethod(Object a_object, String a_methodName, Object[] a_arguments) {
		try {
			super.invokeMethod(a_object, a_methodName, a_arguments)
		}
		catch (MissingMethodException e) {
			if (a_methodName == 'append') {
				append(a_object, a_arguments[0], a_arguments[1])
			}
			else {
				throw e
			}
		}
	}

}
