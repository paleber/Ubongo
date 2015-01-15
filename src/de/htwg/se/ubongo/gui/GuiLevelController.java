package de.htwg.se.ubongo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;

/** Gui-Level Controller. */
public final class GuiLevelController implements ILevelControllerSubject {

    private final class Content extends JPanel {

        private static final long serialVersionUID = 1L;

        private final JSlider levelSlider = new JSlider();
        private final JSlider variantSlider = new JSlider();

        private Content() {

            JLabel text = new JLabel("Level");
            add(text);

            levelSlider.setMinimum(1);
            levelSlider.setMaximum(level.getNumberBoards());
            levelSlider.setMajorTickSpacing(1);
            levelSlider.setPaintTicks(true);
            levelSlider.setPaintLabels(true);
            levelSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(final ChangeEvent e) {
                    level.setBoard(levelSlider.getValue() - 1);
                    updateSlider();
                }
            });
            add(levelSlider);

            text = new JLabel("Variante");
            add(text);

            variantSlider.setMinimum(1);
            variantSlider.setMajorTickSpacing(1);
            variantSlider.setPaintTicks(true);
            variantSlider.setPaintLabels(true);
            variantSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(final ChangeEvent e) {
                    level.setVariant(variantSlider.getValue() - 1);
                }
            });
            add(variantSlider);

            updateSlider();

            JButton b = new JButton("Zurück zum Menü");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.switchToMenu();
                }
            });
            add(b);

            b = new JButton("Start");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.switchToGame();
                }
            });
            add(b);
        }

        private void updateSlider() {
            variantSlider.setMaximum(level.getNumberVariantsOfBoard(level
                    .getBoardIndex()));
            levelSlider.setValue(level.getBoardIndex() + 1);
            variantSlider.setValue(level.getVariant() + 1);
        }

    }

    private final Content content;
    private final ILevelData level;
    private final ISwitchFrame frame;
    private final ILevelController observer;

    /** Constructor.
     * @param observer Observer
     * @param frame Frame
     * @param level LevelData */
    public GuiLevelController(final ILevelController observer,
            final ISwitchFrame frame, final ILevelData level) {
        observer.register(this);
        this.frame = frame;
        this.level = level;
        this.observer = observer;
        content = new Content();
    }

    @Override
    public void onStartSubController() {
        frame.showContent(content, "Levelauswahl");

    }

    @Override
    public void onStopSubController() {
        frame.hideContent();

    }

    @Override
    public void onSelect(final int index, final int variant) {
        content.updateSlider();

    }

}
