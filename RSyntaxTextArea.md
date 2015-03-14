# Introduction #

[RSyntaxTextArea](http://fifesoft.com/rsyntaxtextarea) is a syntax highlighting text area component, with many advanced features.

# Usage #

```
def ousia = new OusiaBuilder()
ousia.edt {
    lookAndFeel('substance-nebula')
    frame(title: 'SyntaxAreaTest', pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
        syntaxScrollPane(id: 'sp') {
            sp.gutter.bookmarkingEnabled = true
            sp.gutter.bookmarkIcon = imageIcon('/bookmark.png')
            syntaxTextArea('This is a test', marginLineEnabled: true, id: 'ta') {
                ta.addHyperlinkListener(new HyperlinkBrowser())
                ta.syntaxEditingStyle = 'text/groovy'
            }
        }
    }
}
```

# Further Information #

You can find more examples of usage in the [source repository](http://code.google.com/p/ousia/source/browse/#hg/src/test/groovy/org/mnode/ousia)