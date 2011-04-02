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

import java.awt.event.ComponentEvent;

import org.jdesktop.swingx.JXStatusBar;

/**
 * @author Ben
 * 
 */
public class JXStatusBarTracker extends ComponentTracker {

    JXStatusBar statusBar;

    /**
     * @param statusBar the status bar to track
     */
    public JXStatusBarTracker(JXStatusBar statusBar) {
        super(statusBar);
    }

    /**
     * @param statusBar the status bar to track
     * @param id an identifier for this tracker
     */
    public JXStatusBarTracker(JXStatusBar statusBar, String id) {
        super(statusBar, id);
        this.statusBar = statusBar;
        statusBar.setVisible(isVisible());
    }

    /**
     * {@inheritDoc}
     */
    public void componentHidden(final ComponentEvent e) {
        getPreferences().putBoolean(getUniqueId() + ".visible", false);
    }

    /**
     * {@inheritDoc}
     */
    public void componentShown(final ComponentEvent e) {
        getPreferences().putBoolean(getUniqueId() + ".visible", true);
    }

    /**
     * @return the saved visibility of the status bar.
     */
    public boolean isVisible() {
        return getPreferences().getBoolean(getUniqueId() + ".visible", statusBar.isVisible());
    }

}
