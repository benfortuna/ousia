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
package org.mnode.ousia.tracker;

import java.awt.Frame;
import java.awt.event.ComponentEvent;

/**
 * @author Ben
 *
 */
public class FrameTracker<T extends Frame> extends ComponentTracker<T> {
    
    /**
     * @param frame the frame to track
     */
    public FrameTracker(T frame) {
        super(frame);
    }

    /**
     * @param frame the frame to track
     * @param id an identifier for this tracker
     */
    public FrameTracker(T frame, String id) {
        super(frame, id);
//        if (isMaximised()) {
//            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void componentMoved(ComponentEvent e) {
    	try {
    		// Micro-sleep may be needed to ensure frame extended state
    		// is set correctly (???? - seems to work)
        	Thread.sleep(10);
    	}
    	catch (InterruptedException ie) {
    	}
        if (Frame.MAXIMIZED_BOTH == getComponent().getExtendedState()) {
            // if frame is maximised don't update the saved location..
            return;
        }
        super.componentMoved(e);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void componentResized(ComponentEvent e) {
        if (Frame.MAXIMIZED_BOTH == getComponent().getExtendedState()) {
            getPreferences().putBoolean(getUniqueId() + ".maximised", true);
            // if frame is maximised don't update the saved dimensions..
            return;
        }
        else {
            getPreferences().putBoolean(getUniqueId() + ".maximised", false);
            super.componentResized(e);
        }
    }
    
    /**
     * Returns the saved maximised state of the component.
     * @return true if the last state of the frame was maximised
     */
    public boolean isMaximised() {
        return getPreferences().getBoolean(getUniqueId() + ".maximised",
                Frame.MAXIMIZED_BOTH == getComponent().getExtendedState());
    }
}
