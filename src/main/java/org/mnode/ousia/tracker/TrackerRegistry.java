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

import java.awt.Component;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JSplitPane;

import org.jdesktop.swingx.JXStatusBar;

/**
 * Manages the monitoring of user interface preferences.
 * 
 * @author benfortuna
 */
public final class TrackerRegistry {

    private static TrackerRegistry instance = new TrackerRegistry();

    private Map<String, ComponentTracker> componentTrackers;

    private Map<String, JSplitPaneTracker> jSplitPaneTrackers;

    private Map<String, FrameTracker> frameTrackers;

    private Map<String, JXStatusBarTracker> jxStatusBarTrackers;

    /**
     * Constructor made private to enforce singleton.
     */
    private TrackerRegistry() {
        componentTrackers = new HashMap<String, ComponentTracker>();
        jSplitPaneTrackers = new HashMap<String, JSplitPaneTracker>();
        frameTrackers = new HashMap<String, FrameTracker>();
        jxStatusBarTrackers = new HashMap<String, JXStatusBarTracker>();
    }

    /**
     * @return Returns the instance.
     */
    public static TrackerRegistry getInstance() {
        return instance;
    }

    /**
     * Registers a component for preferences monitoring.
     * 
     * @param component
     *            a component to monitor
     * @param id
     *            the id of a specific component
     */
    public void register(final Component component, final String id) {
        ComponentTracker tracker = new ComponentTracker(component, id);
        componentTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a JSplitPane for preferences monitoring.
     * 
     * @param pane
     *            a JSplitPane to monitor
     * @param id
     *            the id of a specific JSplitPane
     */
    public void register(final JSplitPane pane, final String id) {
        JSplitPaneTracker tracker = new JSplitPaneTracker(pane, id);
        jSplitPaneTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a Frame for preferences monitoring.
     * 
     * @param frame
     *            a Frame to monitor
     * @param id
     *            the id of a specific Frame
     */
    public void register(final Frame frame, final String id) {
        FrameTracker tracker = new FrameTracker(frame, id);
        frameTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a JXStatusBar for preferences monitoring.
     * @param statusBar
     *            a status bar to monitor
     * @param id
     *            the id of a specific status bar
     */
    public void register(final JXStatusBar statusBar, final String id) {
        JXStatusBarTracker tracker = new JXStatusBarTracker(statusBar, id);
        jxStatusBarTrackers.put(tracker.getUniqueId(), tracker);
    }
}
