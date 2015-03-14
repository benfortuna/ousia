# Introduction #

The [JXLayer component](https://jxlayer.dev.java.net/) provides support for overlaying Swing components with additional painting and controls.

# Usage #

```
import javax.swing.JFrame;
import java.awt.color.ColorSpace;
import java.awt.image.ColorConvertOp;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;
import org.jdesktop.jxlayer.plaf.ext.LockableUI;

new OusiaBuilder().edt {
 	frame(title: 'JXLayer Test', size: [320, 240], show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		def ui = new LockableUI()
 		ColorConvertOp grayScale = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null)
 		// wrap it with the jxlayer's BufferedImageOpEffect
 		BufferedImageOpEffect effect = new BufferedImageOpEffect(grayScale);
 		// set it as the locked effect
 		ui.lockedEffects = effect
		layer(ui) {
			button(text: 'Click Me', actionPerformed: {ui.locked = true; println 'ui locked'})
		}
	}
} 
```

# Further Information #

You can find more examples of usage in the [source repository](http://code.google.com/p/ousia/source/browse/#hg/src/test/groovy/org/mnode/ousia)