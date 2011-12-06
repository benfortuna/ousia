/**
 * Copyright (c) 2011, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mnode.ousia

import groovy.lang.Closure
import groovy.swing.LookAndFeelHelper
import groovy.swing.SwingBuilder
import groovy.swing.factory.LayoutFactory
import groovy.swing.factory.ScrollPaneFactory
import groovy.swing.factory.TextArgWidgetFactory
import groovy.util.logging.Slf4j

import java.awt.Component
import java.awt.Cursor
import java.awt.Insets
import java.util.Locale
import java.util.prefs.Preferences

import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.text.html.StyleSheet

import net.miginfocom.swing.MigLayout

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea
import org.fife.ui.rsyntaxtextarea.TextEditorPane
import org.fife.ui.rtextarea.RTextArea
import org.fife.ui.rtextarea.RTextScrollPane
import org.jdesktop.swingx.JXFormattedTextField
import org.jdesktop.swingx.JXFrame
import org.jdesktop.swingx.JXStatusBar
import org.jdesktop.swingx.JXTreeTable
import org.mnode.ousia.flamingo.BreadcrumbBarFactory
import org.mnode.ousia.flamingo.CommandButtonFactory
import org.mnode.ousia.flamingo.JCommandButtonStripFactory
import org.mnode.ousia.flamingo.JCommandPopupMenuFactory
import org.mnode.ousia.flamingo.RibbonApplicationMenuEntryFactory
import org.mnode.ousia.flamingo.RibbonBandFactory
import org.mnode.ousia.flamingo.RibbonComponentFactory
import org.mnode.ousia.flamingo.RibbonTaskFactory
import org.mnode.ousia.glazedlists.FilterListFactory
import org.mnode.ousia.glazedlists.SortedListFactory
import org.mnode.ousia.glazedlists.TreeListFactory
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager
import org.pushingpixels.flamingo.api.bcb.core.BreadcrumbFileSelector
import org.pushingpixels.flamingo.api.common.JCommandButton
import org.pushingpixels.flamingo.api.common.JCommandMenuButton
import org.pushingpixels.flamingo.api.common.JCommandToggleButton
import org.pushingpixels.flamingo.api.common.JCommandToggleMenuButton
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryFooter
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntrySecondary

@Slf4j
class OusiaBuilder extends SwingBuilder {
	
    static {
        // remove padding from tabbed panes..
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0))

        // register substance look and feels..        
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-autumn', 'org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-business', 'org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-business-black-steel', 'org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-business-blue-steel', 'org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-challenger-deep', 'org.pushingpixels.substance.api.skin.SubstanceChallengerDeepLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-creme', 'org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-creme-coffee', 'org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-dust', 'org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-dust-coffee', 'org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-emerald-dusk', 'org.pushingpixels.substance.api.skin.SubstanceEmeraldDuskLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-gemini', 'org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-graphite', 'org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-graphite-aqua', 'org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-graphite-glass', 'org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-magellan', 'org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-mariner', 'org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-mist-aqua', 'org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-mist-silver', 'org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-moderate', 'org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-nebula', 'org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-nebula-brick-wall', 'org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-office-blue-2007', 'org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-office-silver-2007', 'org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-raven', 'org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-sahara', 'org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel')
        LookAndFeelHelper.instance.addLookAndFeelAlias('substance-twilight', 'org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel')

        LookAndFeelHelper.instance.addLookAndFeelAlias('seaglass', 'com.seaglasslookandfeel.SeaGlassLookAndFeel')
    }
    
    OusiaBuilder(boolean init = true) {
        super(init)
    }
	
	def registerDefaultOverrides() {
		try {
			registerFactory 'fileChooser', new JFileChooserFactory()
			registerFactory 'frame', new OusiaFrameFactory(klass: JFrame)
		}
		catch (Throwable e) {
			log.warn 'Failed to register default overrides'
		}
	}

	def registerOusiaExtras() {
		try {
			registerFactory 'htmlEditorKit', new HTMLEditorKitFactory()
			registerBeanFactory 'styleSheet', StyleSheet
			registerFactory 'styleSheetRule', new StyleSheetRuleFactory()
			registerFactory 'paddedIcon', new PaddedIconFactory()
			registerFactory 'fileTreePanel', new FileTreePanelFactory()
		}
		catch (Throwable e) {
			log.warn 'Failed to register Ousia extras'
		}
	}
	
	def registerLayouts() {
		try {
			registerFactory('migLayout', new LayoutFactory(MigLayout))
		}
		catch (Throwable e) {
			log.warn 'Failed to register layouts'
		}
	}
	
    def registerRSyntaxComponents() {
		try {
	        registerFactory('rTextArea', new TextArgWidgetFactory(RTextArea))
	        registerFactory('rSyntaxTextArea', new TextArgWidgetFactory(RSyntaxTextArea))
	        registerFactory('textEditorPane', new TextArgWidgetFactory(TextEditorPane))
	        registerFactory('rSyntaxScrollPane', new ScrollPaneFactory(RTextScrollPane))
		}
		catch (Throwable e) {
			log.warn 'Failed to register rsyntax components'
		}
    }
	
	def registerFlamingoComponents() {
		try {
			registerFactory 'resizableIcon', new ResizableIconFactory()
			registerFactory 'breadcrumbBar', new BreadcrumbBarFactory()
			registerBeanFactory 'breadcrumbFileSelector', BreadcrumbFileSelector
			registerFactory 'commandButtonStrip', new JCommandButtonStripFactory()
			registerFactory 'commandPopupMenu', new JCommandPopupMenuFactory()
			registerFactory 'commandButton', new CommandButtonFactory(JCommandButton)
			registerFactory 'commandToggleButton', new CommandButtonFactory(JCommandToggleButton)
			registerFactory 'commandMenuButton', new CommandButtonFactory(JCommandMenuButton)
			registerFactory 'commandToggleMenuButton', new CommandButtonFactory(JCommandToggleMenuButton)
			registerFactory 'ribbonFrame', new OusiaFrameFactory(klass: JRibbonFrame)
			registerFactory 'ribbonBand', new RibbonBandFactory()
			registerFactory 'ribbonTask', new RibbonTaskFactory()
			registerFactory 'ribbonComponent', new RibbonComponentFactory()
			registerBeanFactory 'ribbonApplicationMenu', RibbonApplicationMenu
			registerFactory 'ribbonApplicationMenuEntryPrimary', new RibbonApplicationMenuEntryFactory(RibbonApplicationMenuEntryPrimary)
			registerFactory 'ribbonApplicationMenuEntrySecondary', new RibbonApplicationMenuEntryFactory(RibbonApplicationMenuEntrySecondary)
			registerFactory 'ribbonApplicationMenuEntryFooter', new RibbonApplicationMenuEntryFactory(RibbonApplicationMenuEntryFooter)
		}
		catch (Throwable e) {
			log.warn 'Failed to register flamingo components'
			if (log.debugEnabled) {
				log.debug 'Trace:', e
			}
		}
	}
	
	def registerSwingXComponents() {
		try {
			registerBeanFactory 'statusBar', JXStatusBar
//			registerBeanFactory 'table', JXTable
			registerBeanFactory 'treeTable', JXTreeTable
			registerFactory 'textField', new TextArgWidgetFactory(JXFormattedTextField)
			registerFactory 'frame', new OusiaFrameFactory(klass: JXFrame)
		}
		catch (Throwable e) {
			log.warn 'Failed to register swingx components'
		}
	}
	
	def registerJXLayerComponents() {
		try {
			registerFactory 'layer', new JXLayerFactory()
		}
		catch (Throwable e) {
			log.warn 'Failed to register jxlayer components'
		}
	}
	
	def registerWindowManagerComponents() {
		try {
			registerBeanFactory 'toolWindowManager', MyDoggyToolWindowManager
//			registerFactory 'toolWindow', new ToolWindowFactory()
		}
		catch (Throwable e) {
			log.warn 'Failed to register window manager components'
		}
	}
	
	def registerGlazedListsComponents() {
		try {
			registerFactory 'sortedList', new SortedListFactory()
			registerFactory 'filterList', new FilterListFactory()
			registerFactory 'treeList', new TreeListFactory()
		}
		catch (Throwable e) {
			log.warn 'Failed to register glazed lists components'
		}
	}
	
	String resourceString(String key, String bundleName = 'messages', Locale locale = Locale.default) {
		ResourceBundle rb = ResourceBundle.getBundle(bundleName, locale)
		try {
			return rb.getString(key)
		}
		catch (MissingResourceException e) {
			log.warn "Resource for key: ${key} not found in bundle: ${bundleName}"
		}
		key
	}
	
	String rs(String key, String bundleName = 'messages', Locale locale = Locale.default) {
		resourceString(key, bundleName, locale)
	}
	
	/**
	 * Inspired by <a href="http://www.catalysoft.com/articles/busycursor.html">catalysoft.com</a>.
	 * @param component the component to set the busy cursor on
	 * @param closure the closure to call
	 * @return
	 */
	def busyCursor(Component component, Closure closure) {
		try {
			edt {
				component.cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)
			}
			closure.call()
		}
		finally {
			edt {
				component.cursor = Cursor.defaultCursor
			}
		}
	}
	
	Preferences prefs(Class clazz = null) {
		if (clazz) {
			return Preferences.userNodeForPackage(clazz)
		}
		Preferences.userRoot()
	}
}