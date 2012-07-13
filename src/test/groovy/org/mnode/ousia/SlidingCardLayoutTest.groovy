package org.mnode.ousia

import java.awt.BorderLayout;

import groovy.swing.SwingBuilder

import javax.swing.JFrame


def swing = new SwingBuilder()

swing.edt {
    frame(title: 'Test Image View', visible: true, pack: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
        panel(id: 'testPanel') {
            cardLayout(new SlidingCardLayout(), id: 'slideLayout')
            
			panel(constraints: 'button1') {
				borderLayout()
				textArea()
				button(text: 'Button 1', constraints: BorderLayout.SOUTH, actionPerformed: {
	                slideLayout.show(testPanel, 'button2')
	            })
            }
			panel(constraints: 'button2') {
				borderLayout()
				textArea()
	            button(text: 'Button 2', constraints: BorderLayout.SOUTH, actionPerformed: {
	                slideLayout.show(testPanel, 'button1')
	            })
			}
        }
    }
}
