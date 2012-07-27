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
package org.mnode.ousia.glazedlists;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;

import ca.odell.glazedlists.Filterator;
import ca.odell.glazedlists.matchers.AbstractMatcherEditor;
import ca.odell.glazedlists.matchers.Matcher;

/**
 * @author fortuna
 *
 */
public class JCheckboxMatcherEditor<E> extends AbstractMatcherEditor<E> {

	private final Filterator<Boolean, E> filterator;

	private final Matcher<E> matcher;
	
	private final JCheckBox component;
	
	JCheckboxMatcherEditor(JCheckBox component, Filterator<Boolean, E> filterator) {
		this.filterator = filterator;
		this.component = component;
		matcher = new BooleanMatcher();
		
		component.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				refilter();
			}
		});
	}
	
	private void refilter() {
		Logger.getLogger("Global").info("Refiltering..");
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				if (!component.isSelected()) {
					fireMatchAll();
				}
				else {
					fireChanged(matcher);
				}
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matcher<E> getMatcher() {
		return matcher;
	}
	
	private class BooleanMatcher implements Matcher<E> {
		
		/**
		 * {@inheritDoc}
		 */
		public boolean matches(E paramE) {
			if (component.isSelected()) {
				List<Boolean> matches = new ArrayList<Boolean>();
				filterator.getFilterValues(matches, paramE);
				if (!matches.isEmpty()) {
					return matches.get(0);
				}
			}
			return true;
		};
	}
}
