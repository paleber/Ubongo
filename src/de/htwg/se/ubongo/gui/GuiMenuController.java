package de.htwg.se.ubongo.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import de.htwg.se.ubongo.ctrl.obs.IMenuController;
import de.htwg.se.ubongo.ctrl.sub.IMenuControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;

/** Gui for Menu. */
public final class GuiMenuController implements IMenuControllerSubject {

    private final class Content extends JPanel {

        private static final long serialVersionUID = 1L;

        private Content() {

            setBorder(BorderFactory.createEmptyBorder(5,50,50,50));
            setLayout(new GridLayout(5,1));

            JLabel l = new JLabel("Ubongo", SwingConstants.CENTER);
            l.setFont(new Font("Sans", Font.BOLD, 50));
            add(l);

            // add button - select level
            JButton b = new JButton("Levelauswahl");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.switchToLevel();
                }
            });
            add(b);


            // add button - random level
            b = new JButton("Zuf\u00e4lliges Level");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    level.random();
                    observer.switchToGame();
                }
            });
            add(b);

            // add button - help
            b = new JButton("Hilfe");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.switchToHelp();
                }
            });
            add(b);

            // add button - shutdown
            b = new JButton("Beenden");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.shutdown();
                }
            });
            add(b);

        }
    }

    private final Content content = new Content();
    private final ISwitchFrame frame;
    private final IMenuController observer;
    private final ILevelData level;

    /** Constructor.
     * @param observer Observer
     * @param frame Frame
     * @param level Level */
    public GuiMenuController(final IMenuController observer,
            final ISwitchFrame frame, final ILevelData level) {
        observer.register(this);
        this.frame = frame;
        this.observer = observer;
        this.level = level;
    }

    @Override
    public void onStartSubController() {
        frame.showContent(content, "Men\u00fc");
    }

    @Override
    public void onStopSubController() {
        frame.hideContent();
    }

}
