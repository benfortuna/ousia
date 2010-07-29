package org.mnode.ousia

import java.awt.Insets;

import javax.swing.UIManager;

import groovy.swing.LookAndFeelHelper;
import groovy.swing.SwingBuilder

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
    }    
}