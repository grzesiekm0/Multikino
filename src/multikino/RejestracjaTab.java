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
 *  Zakładka rejestracji widza 
 */
public class RejestracjaTab extends Tab {

    public final Widz model;
    public final RejestracjaView view;
    
    public RejestracjaTab() {
        super();
        setText("Rejestracja");
        model = new Widz();
        view = new RejestracjaView(model);
        setContent(view);
        setClosable(false);
        
        
        
        
        this.setOnSelectionChanged(e -> {
            if (this.isSelected()) {
                    System.out.println("Rejestracja tab selected.");
            } else {
                    System.out.println("Rejestracja tab unselected.");
            }        
        });
    }
    
}
