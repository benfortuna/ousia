# Introduction #

The [MyDoggy docking framework](http://mydoggy.sourceforge.net/) provides a light-weight docking framework for Swing user interfaces.

# Usage #

```
def ousia = new OusiaBuilder()
ousia.edt {
    frame(title: 'MyDoggyTest', pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {

        toolWindowManager(id: 'windowManager') {
		windowManager.registerToolWindow "Test", "Test Tool", null, new JButton('Click Me'), ToolWindowAnchor.BOTTOM
	}
	windowManager.getToolWindow("Test").available = true
	windowManager.getToolWindow("Test").active = true 
    }
}
```

# Further Information #

You can find more examples of usage in the [source repository](http://code.google.com/p/ousia/source/browse/#hg/src/test/groovy/org/mnode/ousia)