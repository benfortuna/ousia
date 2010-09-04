package org.mnode.ousia

import javax.swing.JButton;
import javax.swing.JFrame;

import org.noos.xing.mydoggy.ToolWindowAnchor;

new OusiaBuilder().edt {
	lookAndFeel('substance-mariner')
	frame(title: 'ToolWindow Test', size: [320, 240], show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		toolWindowManager(id: 'windowManager') {
			windowManager.registerToolWindow "Test", "Test Tool", null, new JButton('Click Me'), ToolWindowAnchor.BOTTOM
		}
		windowManager.getToolWindow("Test").available = true
		windowManager.getToolWindow("Test").active = true
	}
}
