/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

/**
 *
 * Zakładka tepertuaru
 */
public class RepertuarTab extends Tab {
    RepertuarView view;
    public RepertuarTab() {
            super();
            setText("Repertuar");
            view = new RepertuarView();
            Node root = view;
            setContent(root);
            setClosable(false);


            this.setOnSelectionChanged(e -> {
            if (this.isSelected()) {
                    System.out.println("Repertuar tab selected.");
            } else {
                    System.out.println("Repertuar tab unselected.");
            }
        }); 
    }
    
}
