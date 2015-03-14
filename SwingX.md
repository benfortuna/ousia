# Introduction #

The [SwingX component suite](https://swingx.dev.java.net/) provides additional widgets for building Swing user interfaces.

# Usage #

```
def ousia = new OusiaBuilder()
ousia.edt {
    frame(title: 'SwingXTest', pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {

        statusBar() {
                label(text: 'Ready', constraints: new JXStatusBar.Constraint(FILL))
                label(text: 'text/plain')
        }
    }
}
```

# Further Information #

You can find more examples of usage in the [source repository](http://code.google.com/p/ousia/source/browse/#hg/src/test/groovy/org/mnode/ousia)