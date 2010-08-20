/**
 * Copyright (c) 2010, Ben Fortuna
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
package org.mnode.ousia.substance;

import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

/**
 * @author Ben
 *
 */
public class EditListener extends UndoManager implements DocumentListener {

    private static final long serialVersionUID = 1L;
    
    private final JComponent component;
    
    public EditListener(JComponent component) {
        this.component = component;
    }
    
    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        component.putClientProperty(SubstanceLookAndFeel.WINDOW_MODIFIED, true);
        super.undoableEditHappened(e);
    }
    
    /**
     * {@inheritDoc}
     */
    public void changedUpdate(DocumentEvent e) {
        if (canUndoAny()) {
            component.putClientProperty(SubstanceLookAndFeel.WINDOW_MODIFIED, true);
        }
        else {
            component.putClientProperty(SubstanceLookAndFeel.WINDOW_MODIFIED, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void insertUpdate(DocumentEvent e) {
        if (canUndoAny()) {
            component.putClientProperty(SubstanceLookAndFeel.WINDOW_MODIFIED, true);
        }
        else {
            component.putClientProperty(SubstanceLookAndFeel.WINDOW_MODIFIED, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeUpdate(DocumentEvent e) {
        if (canUndoAny()) {
            component.putClientProperty(SubstanceLookAndFeel.WINDOW_MODIFIED, true);
        }
        else {
            component.putClientProperty(SubstanceLookAndFeel.WINDOW_MODIFIED, false);
        }
    }
    
    private boolean canUndoAny() {
        for (UndoableEdit edit : edits) {
            if (edit.canUndo()) {
                return true;
            }
        }
        return false;
    }

}
