package de.htwg.se.ubongo.util.geo;

import java.awt.Graphics;

/** Interface to Paint Objects. */
public interface IPaintable {

    /** Paint the Object.
     * @param g Graphics
     * @param scale scale
     * @param xOffset xOffset
     * @param yOffset yOffset */
    void paint(Graphics g, double scale, double xOffset, double yOffset);

}
