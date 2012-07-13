package org.mnode.ousia

import java.awt.CardLayout
import java.awt.Color;
import java.awt.Component
import java.awt.Container
import java.awt.Graphics2D
import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.Point
import java.awt.image.BufferedImage

import javax.swing.BorderFactory;
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JViewport

import org.pushingpixels.trident.Timeline
import org.pushingpixels.trident.Timeline.TimelineState
import org.pushingpixels.trident.callback.TimelineCallbackAdapter
import org.pushingpixels.trident.ease.Sine


class SlidingCardLayout extends CardLayout {

    private final WeakHashMap componentNames = []
    
    private JViewport viewport = []
    
    private Timeline slidingTimeline
    
    SlidingCardLayout() {
        slidingTimeline = [viewport]
        slidingTimeline.duration = 400
        slidingTimeline.ease = new Sine()
    }
    
    @Override
    public void addLayoutComponent(Component component, Object obj) {
        componentNames.put(component, obj)
        super.addLayoutComponent(component, obj);
    }
    
    private Component currentComponent(Container container) {
        for (i in 0..(container.componentCount - 1)) {
            if (container.getComponent(i).showing) {
                return container.getComponent(i)
            }
        }
        return null
    }
    
    @Override
    public void show(Container container, String id) {
        def c = componentNames.entrySet().find { it.value == id}.key
        Image transitionImage = createTransitionImage(currentComponent(container), c)
	
    	ImageIcon imageIcon = []
        imageIcon.image = transitionImage
			
		JLabel imageLabel = [imageIcon]
        viewport.view = imageLabel
        slidingTimeline.resetDoneFlag()
        slidingTimeline.addPropertyToInterpolate('viewPosition', viewport.viewPosition, new Point(viewport.viewPosition.x + transitionImage.getWidth() / 2 as int, viewport.viewPosition.y as int))
        slidingTimeline.addCallback(new ShowCallback(container: container, id: id))
        
        container.add(viewport, '__transition__')
        super.show(container, '__transition__')
        slidingTimeline.play()
    }
    
    private void internalShow(Container container, String id) {
        super.show(container, id)
    }
    
    private Image createTransitionImage(Component from, Component to) {
        BufferedImage img = GraphicsEnvironment.localGraphicsEnvironment
            .defaultScreenDevice.defaultConfiguration
            .createCompatibleImage(from.width * 2 as int, from.height as int)
        
        Graphics2D g2d = img.createGraphics()
        from.print(g2d)
        g2d.translate(from.width, 0)
        to.print(g2d)
        g2d.dispose()
        return img
    }
    
    private class ShowCallback extends TimelineCallbackAdapter {
        Container container
        String id
        
        void onTimelineStateChanged(TimelineState arg0, TimelineState arg1, float arg2, float arg3) {
            if (arg1 == TimelineState.DONE) {
                SlidingCardLayout.this.internalShow(container, id);
                container.remove(SlidingCardLayout.this.viewport)
                slidingTimeline.removeCallback this
            }
        }
    }
}
