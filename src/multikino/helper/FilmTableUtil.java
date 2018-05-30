/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino.helper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import multikino.Aktor;
import multikino.Film;
import multikino.Sala;
import multikino.Film;
import multikino.SeansFilmowy;

/**
 * Klasa pomocnicza ułatwia budowę tabelki z filmami
 */
public class FilmTableUtil {

    private static ObservableList<Film> lista = null;
    public static ObservableList<Film> getFilmList() {
        if(lista == null)
        {
            lista = FXCollections.<Film>observableArrayList();

            Film p1 = new Film(Film.PEGI.R, 1);
            LocalTime lt = LocalTime.of(1 ,33);
            p1.setCzasTrwania(lt);

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

            Film p2 = new Film(Film.PEGI.PG13, 2);
            p2.setsTytul("Post");
            p2.setsRezyser("Spilberg");
            p2.setRokProdukcji(2017);

            p2.setCzasTrwania(LocalTime.of(1 ,43));
            Film p3 = new Film(Film.PEGI.PG, 3);
            p3.setCzasTrwania(LocalTime.of(2 ,15));
//                Film p4 = new Film(PEGI.PG, 4);
//                Film p5 = new Film(PEGI.PG, 5);

            a3.addFilm(p1);

            a1.addFilm(p2);
            a2.addFilm(p2);

            p1.addActor(a3);
            p2.addActor(a1).addActor(a2);
            Sala s1 = new Sala(1,300, Sala.Typ.DWA_DE);
            Sala s2 = new Sala(2,200, Sala.Typ.TRZY_DE);
            lista.addAll(p1, p3,  p2);
        }
                
        return lista;
        }  
       public static TableColumn<Film, Integer> getIdColumn() {
                TableColumn<Film, Integer> IdCol = new TableColumn<>("Film Id");
                IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                IdCol.setMinWidth(20);
                return IdCol;
        }
         
        
        public static TableColumn<Film, String> getTitleColumn() {
                TableColumn<Film, String> tytulCol = new TableColumn<>("Tytul");
                tytulCol.setCellValueFactory(new PropertyValueFactory<>("sTytul"));
                tytulCol.setMinWidth(100);
                return tytulCol;
        }
         
        
        public static TableColumn<Film, String> getDirectorColumn() {
                TableColumn<Film, String> directorCol = new TableColumn<>("Reżyser");
                directorCol.setCellValueFactory(new PropertyValueFactory<>("sRezyser"));
                directorCol.setMinWidth(100);
                return directorCol;
        } 
       public static TableColumn<Film, LocalTime> getCzasTrwaniaColumn() {
                TableColumn<Film, LocalTime> godzCol = new TableColumn<>("Czas filmu");
                godzCol.setCellValueFactory(new PropertyValueFactory<>("czasTrwania"));
                godzCol.setMinWidth(65);
                godzCol.setCellFactory(
                    new Callback<TableColumn<Film, LocalTime>, TableCell<Film, LocalTime>>() {

                    @Override
                    public TableCell<Film, LocalTime> call(TableColumn<Film, LocalTime> param
                    ) {
                        return new TableCell<Film, LocalTime>() {

                            @Override
                            protected void updateItem(LocalTime item, boolean empty) {
                                super.updateItem(item, empty); 
                                if (item == null || empty) {
                                    setText(null);
                                    setStyle("");
                                } else {
                                    DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
                                    setText(item.format(df));
                                }
                            }

                        };
                    }
                }
        );                
                return godzCol;
        }  
               public static TableColumn<Film, String> getActorsColumn() {
                TableColumn<Film, String> aktorCol =
                                         new TableColumn<>("Obsada");
                aktorCol.setCellValueFactory(new PropertyValueFactory<>("aktorzyList"));
                aktorCol.setMinWidth(150);
                return aktorCol;
        }    
               public static TableColumn<Film, String> getPriceColumn() {
                TableColumn<Film, String> aktorCol =
                                         new TableColumn<>("Cena");
                aktorCol.setCellValueFactory(new PropertyValueFactory<>("aktorzyList"));
                aktorCol.setMinWidth(150);
                return aktorCol;
        }    

}
