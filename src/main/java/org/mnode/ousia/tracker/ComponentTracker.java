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
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.prefs.Preferences;

/**
 * Tracks component changes.
 * 
 * @author Ben Fortuna
 */
public class ComponentTracker implements ComponentListener {

    private Preferences preferences;

    private Component component;

    private String id;

    /**
     * Constructor made protected to ensure only instantiated by PrefsManager.
     * 
     * @param component
     *            a component to monitor for preference changes
     */
    public ComponentTracker(final Component component) {
        this(component, component.getName());
    }

    /**
     * Constructor made protected to ensure only instantiated by PrefsManager.
     * 
     * @param component
     *            a component to monitor for preference changes
     * @param id
     *            the identifier of a specific component
     */
    public ComponentTracker(final Component component, final String id) {
        this.component = component;
        this.id = id;

        preferences = Preferences.userNodeForPackage(component.getClass());

        component.setLocation(getLocation());
        component.setSize(getSize());
        // component.setVisible(isVisible());

        component.addComponentListener(this);
    }

    /**
     * @return a unique identifier for the tracker
     */
    public String getUniqueId() {
        if (id != null) {
            return component.getClass().getName() + "." + id;
        }
        return component.getClass().getName();
    }

    /**
     * {@inheritDoc}
     */
    public void componentHidden(final ComponentEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void componentMoved(final ComponentEvent e) {
        // save preferences on component location..
        getPreferences().putInt(getUniqueId() + ".x", component.getX());
        getPreferences().putInt(getUniqueId() + ".y", component.getY());
    }

    /**
     * {@inheritDoc}
     */
    public void componentResized(final ComponentEvent e) {
        // save preferences on component size..
        getPreferences().putInt(getUniqueId() + ".width", component.getWidth());
        getPreferences().putInt(getUniqueId() + ".height", component.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    public void componentShown(final ComponentEvent e) {
    }

    /**
     * Returns the saved location preferences for the component.
     * 
     * @return a point specifying a location
     */
    public final Point getLocation() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point(Math.min(screenSize.width - getSize().width, getPreferences().getInt(getUniqueId() + ".x",
                component.getLocation().x)), Math.min(screenSize.height - getSize().height, getPreferences().getInt(
                getUniqueId() + ".y", component.getLocation().y)));
    }

    /**
     * Returns the saved size preferences for the component.
     * 
     * @return a dimension specifying a size
     */
    public final Dimension getSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(Math.min(screenSize.width, getPreferences().getInt(getUniqueId() + ".width",
                component.getSize().width)), Math.min(screenSize.height, getPreferences().getInt(
                getUniqueId() + ".height", component.getSize().height)));
    }

    /**
     * @return Returns the component.
     */
    public final Component getComponent() {
        return component;
    }

    /**
     * @return Returns the id.
     */
    public final String getId() {
        return id;
    }

    /**
     * @return Returns the preferences.
     */
    public final Preferences getPreferences() {
        return preferences;
    }
}
