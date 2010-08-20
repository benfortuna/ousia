package org.mnode.ousia

import javax.swing.JFrame;

new OusiaBuilder().edt {
	lookAndFeel('seaglass')
	
	frame(title: 'BreadcrumbFileSelector Test', size: [320, 240], show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		breadcrumbFileSelector(path: new File(System.getProperty('user.home')))
	}
}