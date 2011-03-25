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
package org.mnode.ousia;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.jdesktop.swingx.JXErrorPane;

/**
 * @author Ben
 *
 */
public class HyperlinkBrowser implements HyperlinkListener {

	private HyperlinkFeedback feedback;
	
    /**
     * {@inheritDoc}
     */
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            try {
            	final URI uri = e.getURL().toURI();
            	if ("mailto".equalsIgnoreCase(uri.getScheme())) {
            		Desktop.getDesktop().mail(uri);
            	}
            	else {
                    Desktop.getDesktop().browse(uri);
            	}
            } catch (Exception ex) {
                JXErrorPane.showDialog(ex);
            }
        }
        else if (e.getEventType() == HyperlinkEvent.EventType.ENTERED) {
        	if (feedback != null) {
        		feedback.show(e.getURL());
        	}
        }
        else if (e.getEventType() == HyperlinkEvent.EventType.EXITED) {
        	if (feedback != null) {
        		feedback.hide();
        	}
        }
    }

    /**
	 * @return the feedback
	 */
	public HyperlinkFeedback getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(HyperlinkFeedback feedback) {
		this.feedback = feedback;
	}

	public static interface HyperlinkFeedback {
    	
    	void show(URL url);
    	
    	void hide();
    }
}
