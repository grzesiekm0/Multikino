/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javax.xml.ws.Holder;

/**
 *
 * Zakładka tepertuaru
 */
public class RepertuarTab extends Tab {
    RepertuarView view;
    private Holder<Widz> widzHolder;
    public RepertuarTab(Holder<Widz> widzHolder) {
            super();
            this.widzHolder =  widzHolder;
            setText("Repertuar");
            view = new RepertuarView(widzHolder);
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
