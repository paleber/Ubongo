package de.htwg.se.ubongo.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import de.htwg.se.ubongo.ctrl.obs.IHelpController;
import de.htwg.se.ubongo.ctrl.sub.IHelpControllerSubject;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;

/** Help-Controller. */
public final class GuiHelpController implements IHelpControllerSubject {

    private final class Content extends JPanel {

        private static final long serialVersionUID = 1L;

        private Content() {

            setBorder(BorderFactory.createEmptyBorder(5,50,50,50));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel text = new JLabel("Hier kommt eine kurze Anleitung hin");
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(text);

            add(Box.createRigidArea(new Dimension(1,50)));

            JButton b = new JButton("Zur\u00fcck zum Men\u00fc");
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.switchToMenu();
                }
            });
            add(b);
        }

    }

    private final Content content = new Content();
    private final IHelpController observer;
    private ISwitchFrame frame;

    /** Constructor.
     * @param observer Observer
     * @param frame Frame */
    public GuiHelpController(final IHelpController observer,
            final ISwitchFrame frame) {
        observer.register(this);
        this.observer = observer;
        this.frame = frame;
    }

    @Override
    public void onStartSubController() {
        frame.showContent(content, "Hilfe");
    }

    @Override
    public void onStopSubController() {
        frame.hideContent();
    }

}
