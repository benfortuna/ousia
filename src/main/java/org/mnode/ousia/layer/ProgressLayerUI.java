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
package org.mnode.ousia.layer;

import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.AbstractLayerUI;

public class ProgressLayerUI extends AbstractLayerUI<JComponent> implements ComponentListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	private final JProgressBar progressBar;
	
	public ProgressLayerUI() {
		progressBar = new JProgressBar();
		progressBar.setVisible(false);
	}
	
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		@SuppressWarnings("unchecked")
		JXLayer<JComponent> l = (JXLayer<JComponent>) c;
        l.getGlassPane().setLayout(null);
        l.getGlassPane().add(progressBar);
        l.addComponentListener(this);
	}
    
    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        @SuppressWarnings("unchecked")
		JXLayer<JComponent> l = (JXLayer<JComponent>) c;
        l.getGlassPane().setLayout(new FlowLayout());
        l.getGlassPane().remove(progressBar);
        l.removeComponentListener(this);
    }
    
    public void addMonitor(ProgressMonitor monitor) {
		monitor.addListener(this);
    }
    
    public void removeMonitor(ProgressMonitor monitor) {
    	monitor.removeListener(this);
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final ProgressMonitor monitor = (ProgressMonitor) evt.getSource();
				progressBar.setMinimum(monitor.getMinimum());
				progressBar.setMaximum(monitor.getMaximum());
		    	progressBar.setValue(monitor.getValue());
		    	progressBar.setToolTipText(String.format("%s: %s/%s", monitor.getName(), monitor.getValue(), monitor.getMaximum()));
		    	if (monitor.getValue() > monitor.getMinimum() && monitor.getValue() < monitor.getMaximum()) {
		    		if (!progressBar.isVisible()) {
		        		progressBar.setVisible(true);
		    		}
		    	}
		    	else if (progressBar.isVisible()) {
		    		progressBar.setVisible(false);
		    	}
			}
		});
    }
	
	@Override
	public void componentShown(ComponentEvent e) {
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		progressBar.setLocation(e.getComponent().getWidth() - 220, 5);
		progressBar.setSize(200, e.getComponent().getHeight() - 10);
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
	}
	
	public static class ProgressMonitor {
		private final String name;
		private final int minimum;
		private final int maximum;
		private AtomicInteger value;
		
		private final PropertyChangeSupport propertyChangeHandler;
		
		public ProgressMonitor(String name, int minimum, int maximum) {
			this.name = name;
			this.minimum = minimum;
			this.maximum = maximum;
			this.value = new AtomicInteger(0);
			propertyChangeHandler = new PropertyChangeSupport(this);
		}

		public int getValue() {
			return value.get();
		}

		public void setValue(int value) {
			final int oldValue = this.value.getAndSet(value);
			propertyChangeHandler.firePropertyChange("value", oldValue, this.value.get());
		}

		public void addDelta(int delta) {
			final int oldValue = this.value.getAndAdd(delta);
			propertyChangeHandler.firePropertyChange("value", oldValue, this.value.get());
		}
		
		public String getName() {
			return name;
		}

		public int getMinimum() {
			return minimum;
		}

		public int getMaximum() {
			return maximum;
		}
		
		public void addListener(PropertyChangeListener listener) {
			propertyChangeHandler.addPropertyChangeListener(listener);
		}
		
		public void removeListener(PropertyChangeListener listener) {
			propertyChangeHandler.removePropertyChangeListener(listener);
		}
	}
}
