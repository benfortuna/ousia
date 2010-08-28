package org.mnode.ousia

import javax.swing.JFrame;

new OusiaBuilder().edt {
	frame(title: 'MigLayout Test', size: [320, 240], show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		migLayout(layoutConstraints: 'fill')
		
		label(text: 'Full width label', constraints: 'wrap')
		button(text: 'Button 1', constraints: 'wrap')
		label(text: 'Text Entry')
		textField(columns: 15, constraints: 'wrap')
	}
}