# Introduction #

The [MiGLayout layout manager](http://miglayout.com/) provides an advanced layout manager for building complex Swing user interfaces.

# Usage #

```
new OusiaBuilder().edt {
 	frame(title: 'MigLayout Test', size: [320, 240], show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		migLayout(layoutConstraints: 'fill')
		label(text: 'Full width label', constraints: 'wrap')
 		button(text: 'Button 1', constraints: 'wrap')
 		label(text: 'Text Entry')
 		textField(columns: 15, constraints: 'wrap')
	}
}
```

# Further Information #

You can find more examples of usage in the [source repository](http://code.google.com/p/ousia/source/browse/#hg/src/test/groovy/org/mnode/ousia)