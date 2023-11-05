/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digimetz.fch.btlights;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

/**
 *
 * @author saliya
 */
public class DigimetzFchBtlights {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel");
                UIManager.put(SubstanceLookAndFeel.COLORIZATION_FACTOR, 1.0);
                UIManager.put(SubstanceLookAndFeel.USE_THEMED_DEFAULT_ICONS, false);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                System.exit(0);
            }
            
            new ui().setVisible(true);
        });
    }

}
