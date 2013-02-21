package org.mnode.ousia.util

import spock.lang.Specification

class ResourceBundleExtensionSpec extends Specification {

	def 'test extension module: toLocalizedString'() {
		expect:
		'button1'.toLocalizedString() == 'Test Button'
		'button1'.toLocalizedString('messages', Locale.FRANCE) == 'Un bouton'
		'button2'.toLocalizedString() == 'button2'
	}
}
