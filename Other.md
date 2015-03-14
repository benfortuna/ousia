# Introduction #

Ousia also provides extensions to core Swing features.

## HTMLEditorKitExt ##

The default HTMLEditorKit shares style across all components. This implementation supports associating different styles with each instance.

## Usage ##

```
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
```

## Resource Strings ##

Convenience methods provide support for i18n via resource bundles.

## Usage ##

```
new OusiaBuilder().edt {
    frame(title: 'ResourceString Test', size: [320, 240], show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		migLayout(layoutConstraints: 'fill')
		label(text: resourceString('label1'), constraints: 'wrap')
		button(text: rs('button1', 'messages', Locale.FRANCE), constraints: 'wrap')
		label(text: resourceString('label2'))
		textField(columns: 15, constraints: 'wrap')
	}
}
```

Messages are configued in the _messages.properties_ file:

```
label1=Test Label
button1=Test Button
```

# Further Information #

You can find more examples of usage in the [source repository](http://code.google.com/p/ousia/source/browse/#hg/src/test/groovy/org/mnode/ousia)