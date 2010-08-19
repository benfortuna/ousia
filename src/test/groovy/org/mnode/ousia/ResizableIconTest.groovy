package org.mnode.ousia

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;


new OusiaBuilder().edt {
	frame(title: 'ResizableIcon Test', pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		resizableIcon(ResizableIconTest.getResource('/find.svg'), size: new Dimension(48, 48), id: 'findIcon')
		
		label(icon: findIcon, horizontalAlignment: JLabel.CENTER)
	}
}