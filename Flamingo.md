# Introduction #

The [Flamingo component suite](https://flamingo.dev.java.net/) provides additional widgets for building Swing user interfaces.

# Usage #

```
def ousia = new OusiaBuilder()
ousia.edt {
    frame(title: 'FlamingoTest', pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {

        fileBreadcrumbSelector(constraints: BorderLayout.NORTH, new File(System.getProperty('user.home')))
        resizableIcon(FlamingoTest.getResource('/find.svg'), new Dimension(48, 48))
    }
}
```

# Further Information #

You can find more examples of usage in the [source repository](http://code.google.com/p/ousia/source/browse/#hg/src/test/groovy/org/mnode/ousia)