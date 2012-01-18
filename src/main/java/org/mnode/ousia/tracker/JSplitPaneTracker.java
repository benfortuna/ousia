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
package org.mnode.ousia.tracker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JSplitPane;

/**
 * Preferences for JSplitPanes
 * 
 * @author Ben Fortuna
 */
public class JSplitPaneTracker extends ComponentTracker<JSplitPane> implements PropertyChangeListener {

//    private static final LogAdapter LOG = new AsyncLogAdapter(new JclAdapter(LogFactory.getLog(JSplitPaneTracker.class)));

//    private static final LogEntry PROPERTY_CHANGED_LOG = new FormattedLogEntry(Level.Debug, "Property changed: %s");

    /**
     * Constructor made protected to ensure only instantiated by PrefsManager.
     * 
     * @param pane the split pane to track
     */
    protected JSplitPaneTracker(JSplitPane pane) {
        this(pane, null);
    }

    /**
     * Constructor made protected to ensure only instantiated by PrefsManager.
     * 
     * @param pane the split plane to track
     * @param id an identifier for this tracker
     */
    protected JSplitPaneTracker(JSplitPane pane, String id) {
        super(pane, id);
        pane.setDividerLocation(getDividerLocation());
        pane.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    public void propertyChange(PropertyChangeEvent e) {

//        LOG.log(PROPERTY_CHANGED_LOG, e.getPropertyName());

        if ("dividerLocation".equals(e.getPropertyName())) {
            getPreferences().putInt(getUniqueId() + "." + e.getPropertyName(), getComponent().getDividerLocation());
        }
    }

    /**
     * @return the last known divider location for the split pane
     */
    public int getDividerLocation() {
        return getPreferences().getInt(getUniqueId() + ".dividerLocation", getComponent().getDividerLocation());
    }
}
