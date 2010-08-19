package org.mnode.ousia

import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.fife.ui.rsyntaxtextarea.FileLocation;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.DecreaseFontSizeAction;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.IncreaseFontSizeAction;

import eu.medsea.mimeutil.MimeUtil;

MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.ExtensionMimeDetector")

new OusiaBuilder().edt {
    lookAndFeel('substance-mariner')
    
    actions {
        action(id: 'openFileAction', name: 'Open', accelerator: shortcut('O'), closure: {
                 if (chooser.showOpenDialog() == JFileChooser.APPROVE_OPTION) {
                     doLater {
                        editor.load(FileLocation.create(chooser.selectedFile), null)
                        editor.syntaxEditingStyle = MimeUtil.getMimeTypes(chooser.selectedFile).iterator().next()
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
    }
}
