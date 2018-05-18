/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 *
 * Zakładka logowania
 */
public class LogowanieTab extends Tab {

    LogowanieView view;
    public LogowanieTab() {
        super();
        setText("Logowanie");
        view = new LogowanieView();

        setContent(view);
        setClosable(false);
        
        
        this.setOnSelectionChanged(e -> {
        if (this.isSelected()) {
                System.out.println("Login tab selected.");
        } else {
                System.out.println("Login tab unselected.");
        }
});
    }
    
}
