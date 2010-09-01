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
package org.mnode.ousia

import static org.jdesktop.swingx.JXStatusBar.Constraint.ResizeBehavior.*

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.fife.ui.rsyntaxtextarea.FileLocation;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.DecreaseFontSizeAction;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.IncreaseFontSizeAction;
import org.jdesktop.swingx.JXStatusBar;

import eu.medsea.mimeutil.MimeUtil;

MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.ExtensionMimeDetector")

new OusiaBuilder().edt {
//    lookAndFeel('substance-mariner')
	lookAndFeel('gtk')
	
    actions {
        action(id: 'openFileAction', name: 'Open', accelerator: shortcut('O'), closure: {
                 if (chooser.showOpenDialog() == JFileChooser.APPROVE_OPTION) {
                     doLater {
                        editor.load(FileLocation.create(chooser.selectedFile), null)
                        editor.syntaxEditingStyle = MimeUtil.getMimeTypes(chooser.selectedFile).iterator().next()
						syntaxStatus.text = editor.syntaxEditingStyle
                        editor.caretPosition = 0
                        frame.title = "${editor.fileFullPath} - Text Editor"
                     }
                 }
             })
        
        action(id: 'exitAction', name: 'Exit', accelerator: shortcut('Q'), closure: {
                System.exit(0)
            })
        
        action(new IncreaseFontSizeAction(), id: 'increaseFontAction', name: 'Increase Font Size', accelerator: shortcut(KeyEvent.VK_EQUALS))
        action(new DecreaseFontSizeAction(), id: 'decreaseFontAction', name: 'Decrease Font Size', accelerator: shortcut(KeyEvent.VK_MINUS))

    }
    
    fileChooser(id: 'chooser')
    
    frame(title: 'Text Editor', size: [640, 480], show: true, locationRelativeTo: null, defaultCloseOperation: JFrame.EXIT_ON_CLOSE, id: 'frame') {
        menuBar {
            menu(text: "File", mnemonic: 'F') {
                menuItem(openFileAction)
                separator()
                menuItem(exitAction)
            }
            menu(text: "View", mnemonic: 'V') {
                menuItem(increaseFontAction)
                menuItem(decreaseFontAction)
            }
        }
        
        rSyntaxScrollPane(id: 'sp') {
            sp.gutter.bookmarkingEnabled = true
            sp.gutter.bookmarkIcon = imageIcon('/bookmark.png')
            textEditorPane(marginLineEnabled: true, id: 'editor') {
                editor.addHyperlinkListener(new HyperlinkBrowser())
            }
        }
		
		statusBar(constraints: BorderLayout.SOUTH) {
			label(text: 'Ready', constraints: new JXStatusBar.Constraint(FILL))
			label(text: 'text/plain', id: 'syntaxStatus')
		}
    }
}
