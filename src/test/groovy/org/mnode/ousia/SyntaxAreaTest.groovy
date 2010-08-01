package org.mnode.ousia

import javax.swing.JFrame;

class SyntaxAreaTest {

    public static void main(String[] args) {
        def ousia = new OusiaBuilder()
        
        ousia.edt {
            lookAndFeel('substance-nebula')
            
            frame(title: 'SyntaxAreaTest', pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
                syntaxScrollPane(id: 'sp') {
                    sp.gutter.bookmarkingEnabled = true
                    sp.gutter.bookmarkIcon = imageIcon('/bookmark.png')
                    
                    syntaxTextArea('This is a test', marginLineEnabled: true, id: 'ta') {
                        ta.addHyperlinkListener(new HyperlinkBrowser())
                        ta.syntaxEditingStyle = 'text/groovy'
                    }
                }
            }
        }
    }
}
