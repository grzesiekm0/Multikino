/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino.helper;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import multikino.Aktor;
import multikino.Film;
import multikino.Film.PEGI;
/**
 * Klasa pomocnicza ułatwia budowę tabelki z aktualnym repertuarem
 * @author 
 */
public class FilmTableUtil {
      public static ObservableList<Film> getFilmList() {
                Film p1 = new Film(PEGI.R, 1);
                p1.setsTytul("Alien");
                p1.setsRezyser("Stanley Cubric");
                p1.setRokProdukcji(1979);
                Aktor a1 = new Aktor(1);
                a1.setsImie("Tom");
                a1.setsNazwisko("Hanks");

                Aktor a2 = new Aktor(2);
                a2.setsImie("Meryl");
                a2.setsNazwisko("Streep");

                Aktor a3 = new Aktor(2);
                a3.setsImie("Sigourney");
                a3.setsNazwisko("Weaver");
                
                Film p2 = new Film(PEGI.PG13, 2);
                p2.setsTytul("Post");
                p2.setsRezyser("Spilberg");
                p2.setRokProdukcji(2017);
                Film p3 = new Film(PEGI.PG, 3);
//                Film p4 = new Film(PEGI.PG, 4);
//                Film p5 = new Film(PEGI.PG, 5);

                a3.addFilm(p1);

                a1.addFilm(p2);
                a2.addFilm(p2);
                
                p1.addActor(a3);
                p2.addActor(a1).addActor(a2);
                return FXCollections.<Film>observableArrayList(p1, p3,  p2);
        }
         
        
        public static TableColumn<Film, Integer> getIdColumn() {
                TableColumn<Film, Integer> IdCol = new TableColumn<>("seans");
                IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                IdCol.setMinWidth(20);
                return IdCol;
        }
         
        
        public static TableColumn<Film, String> getTitleColumn() {
                TableColumn<Film, String> tytulCol = new TableColumn<>("Tytul");
                tytulCol.setCellValueFactory(new PropertyValueFactory<>("sTytul"));
                tytulCol.setMinWidth(180);
                return tytulCol;
        }
         
        
        public static TableColumn<Film, String> getDirectorColumn() {
                TableColumn<Film, String> directorCol = new TableColumn<>("Reżyser");
                directorCol.setCellValueFactory(new PropertyValueFactory<>("sRezyser"));
                directorCol.setMinWidth(120);
                return directorCol;
        }
        public static TableColumn<Film, PEGI> getPEGIColumn() {
                TableColumn<Film, PEGI> pegCol = new TableColumn<>("PEGI");
                pegCol.setCellValueFactory(new PropertyValueFactory<>("pegi"));
                pegCol.setMinWidth(50);
                return pegCol;
        }         
        
        public static TableColumn<Film, String> getActorsColumn() {
                TableColumn<Film, String> aktorCol =
                                         new TableColumn<>("Obsada");
                aktorCol.setCellValueFactory(new PropertyValueFactory<>("aktorzyList"));
                aktorCol.setMinWidth(150);
                return aktorCol;
        }    

        public static TableColumn<Film, Integer> getYearColumn() {
                TableColumn<Film, Integer> bDateCol =
                                         new TableColumn<>("Rok Produkcji");
                bDateCol.setCellValueFactory(new PropertyValueFactory<>("rokProdukcji"));
                bDateCol.setMinWidth(75);
                return bDateCol;
        }    
}
