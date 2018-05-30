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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import multikino.SeansFilmowy;

/**
 *
 * Formatuje komórkę zakupu biletu tabeli repertuar 
 */
public class BuyTicketButtonTableCell<S> extends TableCell<S, Button>  {
    public final Hyperlink btKupBilet;
    ProgressBar determinateBar = new ProgressBar(0.5);

    public BuyTicketButtonTableCell(String label, Function< S, S> function) {
        this.getStyleClass().add("action-button-table-cell");
        
        this.btKupBilet = new Hyperlink(label);
        this.btKupBilet.setCursor(Cursor.HAND);
        
        this.btKupBilet.setOnAction((ActionEvent e) -> {
            function.apply(getCurrentItem());
            //btKupBilet.setDisable(true);
            btKupBilet.setCursor(Cursor.CLOSED_HAND);
        });
        this.btKupBilet.setMaxWidth(Double.MAX_VALUE);
    }

    public S getCurrentItem() {
        return (S) getTableView().getItems().get(getIndex());
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(String label, Function< S, S> function) {
        
        

        final String sLabel = label;
        return param -> new BuyTicketButtonTableCell<>(sLabel, function);
    }

    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {                
            setGraphic(btKupBilet);
        }
    }
}
