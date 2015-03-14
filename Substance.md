# Introduction #

The [Substance Look and Feel](https://substance.dev.java.net/) is a modern Java look and feel with many enhancements for standard Swing components.

# Usage #

```
def ousia = new OusiaBuilder()
ousia.edt {
    lookAndFeel('substance-nebula')

    frame(title: 'SubstanceTest', pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {

        ....
    }
}
```

**NOTE:** Substance requires GUI building to be performed on the Event Dispatch Thread (EDT), so ensure you use the _edt_ closure when building.

## Available Look and Feels ##

The following is a list of available Substance Look and Feel aliases:

  * substance-autumn
  * substance-business
  * substance-business-black-steel
  * substance-business-blue-steel
  * substance-challenger-deep
  * substance-creme
  * substance-creme-coffee
  * substance-dust
  * substance-dust-coffee
  * substance-emerald-dusk
  * substance-gemini
  * substance-graphite
  * substance-graphite-aqua
  * substance-graphite-glass
  * substance-magellan
  * substance-mariner
  * substance-mist-aqua
  * substance-mist-silver
  * substance-moderate
  * substance-nebula
  * substance-nebula-brick-wall
  * substance-office-blue-2007
  * substance-office-silver-2007
  * substance-raven
  * substance-sahara
  * substance-twilight

# Further Information #

You can find more examples of usage in the [source repository](http://code.google.com/p/ousia/source/browse/#hg/src/test/groovy/org/mnode/ousia)