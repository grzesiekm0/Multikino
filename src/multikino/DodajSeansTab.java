/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 * Zakładka dodaj seans filmowy
 */
class DodajSeansTab extends Tab  {

    public final DodajSeansView view;
    
    public DodajSeansTab() {
        super();
        setText("Dodaj Seans");
        view = new DodajSeansView();
        
        Node root = view;
        
        setContent(root);
        setClosable(false);
        
        
        this.setOnSelectionChanged(e -> {
            if (this.isSelected()) {
                    System.out.println("dodaj film tab selected.");
            } else {
                    System.out.println("dodaj film tab unselected.");
            }
        });
    }    
}
