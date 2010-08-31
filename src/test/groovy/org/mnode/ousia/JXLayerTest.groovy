package org.mnode.ousia

import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.ColorConvertOp;

import org.jdesktop.jxlayer.JXLayer;


import org.jdesktop.jxlayer.plaf.AbstractLayerUI;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;
import org.jdesktop.jxlayer.plaf.ext.LockableUI;

new OusiaBuilder().edt {
	frame(title: 'JXLayer Test', size: [320, 240], show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		def ui = new LockableUI(lockedCursor: Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR))
		ColorConvertOp grayScale = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null)
		// wrap it with the jxlayer's BufferedImageOpEffect
		BufferedImageOpEffect effect = new BufferedImageOpEffect(grayScale);
		// set it as the locked effect
		ui.setLockedEffects(effect);
		
		layer(ui) {
			button(text: 'Click Me', actionPerformed: {ui.locked = true; println 'ui locked'})
		}
	}
}
