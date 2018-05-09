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
import multikino.SeansFilmowy;

/**
 * Klasa pomocnicza w budowie tabelki dodającej aktorów do filmu
 */
public class ActorTableUtil {
    private static ObservableList<Aktor> lista = null;
    public static ObservableList<Aktor> getActorList() {
        if(lista == null)
        {
          lista = FXCollections.<Aktor>observableArrayList();
          Aktor a1 = new Aktor(1);
          a1.setsImie("Tom");
          a1.setsNazwisko("Hanks");

          Aktor a2 = new Aktor(2);
          a2.setsImie("Meryl");
          a2.setsNazwisko("Streep");

          Aktor a3 = new Aktor(2);
          a3.setsImie("Sigourney");
          a3.setsNazwisko("Weaver");
          lista.addAll(a1, a3,  a2);
        }
        return lista;
    }
    public static TableColumn<Aktor, Integer> getIdColumn() {
          TableColumn<Aktor, Integer> IdCol = new TableColumn<>("id");
          IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
          IdCol.setMinWidth(20);
          return IdCol;
    }       
    public static TableColumn<Aktor, String> getImieColumn() {
            TableColumn<Aktor, String> imieCol = new TableColumn<>("Imię");
            imieCol.setCellValueFactory(new PropertyValueFactory<>("sImie"));
            imieCol.setMinWidth(100);
            return imieCol;
    }

    public static TableColumn<Aktor, String> getNazwiskoColumn() {
            TableColumn<Aktor, String> imieCol = new TableColumn<>("Nazwisko");
            imieCol.setCellValueFactory(new PropertyValueFactory<>("sNazwisko"));
            imieCol.setMinWidth(100);
            return imieCol;
    }
}
