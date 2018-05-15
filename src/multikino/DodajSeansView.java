/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.time.LocalDate;
import java.util.function.UnaryOperator;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import multikino.helper.FilmTableUtil;
import multikino.helper.SalaTableUtil;

/**
 *
 * Zakładka dodaje seanse Filmowe - paruje film z salą i określoną godziną nadawania
 */
public class DodajSeansView  extends GridPane {
    TableView<Film> tabelaFilm = new TableView<>(FilmTableUtil.getFilmList());
    TableView<Sala> tabelaSala = new TableView<>(SalaTableUtil.getSalaList());
        // Labels
        Label seansIdLabel = new Label("Id seansu:");
        Label czasLabel = new Label("Godzina seansu:");
        Label dataLabel = new Label("Data seansu:");
        Label cenaLabel = new Label("Cena za bilet normalny:");

         
        // pola tekstowe
        TextField txtSeansId = new TextField();
        DatePicker txtData = new DatePicker(LocalDate.now());
        TextField txtCzasTrwania = new TextField("01:30");
        TextField txtCena = new TextField();

         // Buttons
        Button saveBtn = new Button("Dodaj Seans");       
        
    public DodajSeansView() {
        setId("add_seans_view");
        this.setMinWidth(500);
        layoutForm();
    }

    private void layoutForm() {
        tabelaFilm.getColumns().addAll(
                FilmTableUtil.getIdColumn(),
                FilmTableUtil.getTitleColumn(),
                FilmTableUtil.getCzasTrwaniaColumn(),
                FilmTableUtil.getDirectorColumn(),
                FilmTableUtil.getActorsColumn()
        );
        this.setHgap(10);
        this.setVgap(10);
        this.add(tabelaFilm, 0, 1,5,1);
        tabelaSala.getColumns().addAll(
                SalaTableUtil.getIdColumn(),
                SalaTableUtil.getSalaColumn(),
                SalaTableUtil.getMscColumn()
        );
        this.add(tabelaSala, 7, 1, 1,1);

        add(czasLabel, 0,2);
        add(txtCzasTrwania, 1,2);

        //filtr czasu 00:00 - 99:99
        UnaryOperator<TextFormatter.Change> filterTime = change -> {
            boolean bFail = false;
            String text = change.getText();

            String fullText = txtCzasTrwania.getText();
            if(change.isDeleted() || text.length() == 0)
                return change;
            if(fullText.length()>4 && text.length() > 0)
                return null;

            if (fullText.length() == 2) {
                if(!text.matches(":")) 
                    return null;
            }
            else
                if(!text.matches("[0-9]")) 
                    return null;
            return change;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filterTime);

        txtCzasTrwania.setTextFormatter(new TextFormatter<>(filterTime));     
        txtCzasTrwania.setPromptText("00:00");
        //filtr ceny zz.gg 
         UnaryOperator<TextFormatter.Change> filterPrice = change -> {
             boolean bFail = false;
             String text = change.getText();

             String fullText = txtCena.getText();
             if(change.isDeleted() || text.length() == 0)
                 return change;
             if(fullText.length()>4 && text.length() > 0)
                 return null;

             if (fullText.length() == 2) {
                 if(!text.matches(",")) 
                     return null;
             }
             else
                 if(!text.matches("[0-9]")) 
                     return null;
             return change;
         };

        txtCena.setTextFormatter(new TextFormatter<>(filterPrice));      
        txtCena.setPromptText("00,00 - 99,99");
        
        add(dataLabel, 3,2);
        add(txtData, 4,2);
        
        this.add(cenaLabel, 0, 3);
        this.add(txtCena, 1, 3);
        
        add(saveBtn, 4,3);
        txtData.setEditable(false);
        GridPane.setHalignment(txtData, HPos.RIGHT);
        GridPane.setHalignment(saveBtn, HPos.RIGHT);
    }
    
}
