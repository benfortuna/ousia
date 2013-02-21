package org.mnode.ousia.util


class ResourceBundleExtension {

	static String toLocalizedString(String self, String bundleName = 'messages', Locale locale = Locale.default) {
		ResourceBundle rb = ResourceBundle.getBundle(bundleName, locale)
		try {
			return rb.getString(self)
		}
		catch (MissingResourceException e) {
//			log.warn "Resource for key: ${key} not found in bundle: ${bundleName}"
		}
		self

	}
}
