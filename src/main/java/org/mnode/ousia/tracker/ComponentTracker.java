/**
 * This file is part of Base Modules.
 *
 * Copyright (c) 2009, Ben Fortuna [fortuna@micronode.com]
 *
 * Base Modules is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Base Modules is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Base Modules.  If not, see <http://www.gnu.org/licenses/>.
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
