package org.mnode.ousia

import javax.swing.JFrame;

new OusiaBuilder().edt {
	frame(title: 'HTMLEditorKit Test', size: [320, 240], show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		htmlEditorKit(id: 'myEditorKit') {
			styleSheet {
				styleSheetRule 'body {background-color:#ffffff; color:#444b56; font-family:verdana,sans-serif; margin:8px; }'
				styleSheetRule 'a {color:#ffaaaa}'
			}
		}
		
		editorPane(editorKit: myEditorKit, text: 'HTMLEditorKit Test<br/><a href="http://java.com">Java</a>')
	}
}
