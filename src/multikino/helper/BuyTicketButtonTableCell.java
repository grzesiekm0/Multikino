/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino.helper;


import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author 
 */
public class BuyTicketButtonTableCell<S> extends TableCell<S, Button>  {
    private final Hyperlink actionButton;

    public BuyTicketButtonTableCell(String label, Function< S, S> function) {
        this.getStyleClass().add("action-button-table-cell");
        
        this.actionButton = new Hyperlink(label);
        this.actionButton.setCursor(Cursor.HAND);
        this.actionButton.setOnAction((ActionEvent e) -> {
            function.apply(getCurrentItem());
            actionButton.setDisable(true);
            actionButton.setCursor(Cursor.CLOSED_HAND);
        });
        this.actionButton.setMaxWidth(Double.MAX_VALUE);
    }

    public S getCurrentItem() {
        return (S) getTableView().getItems().get(getIndex());
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(String label, Function< S, S> function) {
        return param -> new BuyTicketButtonTableCell<>(label, function);
    }

    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {                
            setGraphic(actionButton);
        }
    }
}
