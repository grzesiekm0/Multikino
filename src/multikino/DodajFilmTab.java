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
 * Zakładka dodaj film
 */
public class DodajFilmTab extends Tab  {
    
    public final DodajFilmView view;
    
    public DodajFilmTab() {
        super();
        setText("Dodaj Film");
        view = new DodajFilmView();
        
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
