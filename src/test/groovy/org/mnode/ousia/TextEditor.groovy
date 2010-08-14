package org.mnode.ousia

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.fife.ui.rsyntaxtextarea.FileLocation;

import eu.medsea.mimeutil.MimeUtil;

class TextEditor {

    public static void main(String[] args) {
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.ExtensionMimeDetector")
        
        new OusiaBuilder().edt {
            lookAndFeel('substance-nebula')
            
            actions {
                action(id: 'openFileAction', name: 'Open', accelerator: shortcut('O'), closure: {
                         if (chooser.showOpenDialog() == JFileChooser.APPROVE_OPTION) {
                             doLater {
                                editor.load(FileLocation.create(chooser.selectedFile), null)
                                editor.syntaxEditingStyle = MimeUtil.getMimeTypes(chooser.selectedFile).iterator().next()
                                frame.title = "${editor.fileFullPath} - Text Editor"
                             }
                         }
                     })
                
                action(id: 'exitAction', name: 'Exit', accelerator: shortcut('Q'), closure: {
                        System.exit(0)
                    })
            }
            
            fileChooser(id: 'chooser')
            
            frame(title: 'Text Editor', pack: true, show: true, locationRelativeTo: null, defaultCloseOperation: JFrame.EXIT_ON_CLOSE, id: 'frame') {
                menuBar {
                    menu(text: "File", mnemonic: 'F') {
                        menuItem(openFileAction)
                        separator()
                        menuItem(exitAction)
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
    }
}
