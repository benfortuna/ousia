package org.mnode.ousia

import javax.swing.JFrame;

def ousia = new OusiaBuilder()

ousia.edt {
    lookAndFeel('substance-nebula')

	frame(title: 'SyntaxAreaTest', pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
	    rSyntaxScrollPane(id: 'sp') {
	        sp.gutter.bookmarkingEnabled = true
	        sp.gutter.bookmarkIcon = imageIcon('/bookmark.png')
	        
	        rSyntaxTextArea('This is a test', marginLineEnabled: true, id: 'ta') {
	            ta.addHyperlinkListener(new HyperlinkBrowser())
	            ta.syntaxEditingStyle = 'text/groovy'
	        }
	    }
	}
}