# Introduction #

The [Seaglass Look and Feel](http://code.google.com/p/seaglass/) is a modern Java look and feel with a slight bias (?) towards Mac OS X.

# Usage #

```
def ousia = new OusiaBuilder()
ousia.edt {
    lookAndFeel('seaglass')

    frame(title: 'SeaglassTest', pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {

        ....
    }
}
```