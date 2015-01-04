package de.htwg.se.ubongo.util.frame;

import java.awt.Container;

/** Frame with switchable content-pane. */
public interface ISwitchFrame {

    /** Set the size of the content.
     * @param width content-width
     * @param height content-height */
    void setContentSize(int width, int height);

    /** Show a content.
     * @param content content to show */
    void showContent(Container content);

    /** Hide the content and show a load screen. */
    void hideContent();

    /** Shutdown the Frame. */
    void shutdown();

}
