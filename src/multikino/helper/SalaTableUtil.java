/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino.helper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import multikino.Aktor;
import multikino.Film;
import multikino.Sala;
import multikino.Sala;
import multikino.SeansFilmowy;

/**
 * Klasa pomocnicza ułatwia budowę tabelki z salami do parowania z filmem
 */
public class SalaTableUtil {
         public static ObservableList<Sala> getSalaList() {
                Sala s1 = new Sala(1,300, Sala.Typ.DWA_DE);
                Sala s2 = new Sala(2,200, Sala.Typ.TRZY_DE);
                return FXCollections.<Sala>observableArrayList(s1, s2);
        }
        public static TableColumn<Sala, Integer> getIdColumn() {
                TableColumn<Sala, Integer> IdCol = new TableColumn<>("Nr");
                IdCol.setCellValueFactory(new PropertyValueFactory<>("nr"));
                IdCol.setMinWidth(15);
                return IdCol;
        }        
         public static TableColumn<Sala, Sala> getSalaColumn() {
                TableColumn<Sala, Sala> salaCol = new TableColumn<>("Typ");
                salaCol.setCellValueFactory(new PropertyValueFactory<>("sala"));
                salaCol.setMinWidth(25);
                return salaCol;
        }
         
        public static TableColumn<Sala, Integer> getMscColumn() {
                TableColumn<Sala, Integer> seatCountCol = new TableColumn<>("Miejsc");
                seatCountCol.setCellValueFactory(new PropertyValueFactory<>("seatCount"));
                seatCountCol.setMinWidth(25);
                return seatCountCol;
        } 
}
