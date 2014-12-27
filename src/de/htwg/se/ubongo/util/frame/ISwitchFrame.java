package de.htwg.se.ubongo.util.frame;

import java.awt.Container;

/** Frame with switchable content-pane. */
public interface ISwitchFrame {

    int getContentWidth();

    int getContentHeight();

    void setContentSize(int width, int height);

    void showContent(Container conent);

    void hideContent();

    void shutdown();

}
