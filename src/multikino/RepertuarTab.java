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
 * @author 
 */
public class RepertuarTab extends Tab {
    public RepertuarTab() {
            super();
            setText("Repertuar");
            Node root = new RepertuarView();

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
