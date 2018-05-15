/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino.helper;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import multikino.Aktor;
import multikino.Film;
import multikino.Film.PEGI;
import multikino.Sala;
import multikino.Sala.Typ;
import multikino.SeansFilmowy;
/**
 * Klasa pomocnicza ułatwia budowę tabelki z aktualnym repertuarem
 * 
 */
public class SeansFilmowyTableUtil {
      public static ObservableList<SeansFilmowy> getFilmList() {
                Film p1 = new Film(PEGI.R, 1);
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
                
                Film p2 = new Film(PEGI.PG13, 2);
                p2.setsTytul("Post");
                p2.setsRezyser("Spilberg");
                p2.setRokProdukcji(2017);
                
                p2.setCzasTrwania(LocalTime.of(1 ,43));
                Film p3 = new Film(PEGI.PG, 3);
                p3.setCzasTrwania(LocalTime.of(2 ,15));
//                Film p4 = new Film(PEGI.PG, 4);
//                Film p5 = new Film(PEGI.PG, 5);

                a3.addFilm(p1);

                a1.addFilm(p2);
                a2.addFilm(p2);
                
                p1.addActor(a3);
                p2.addActor(a1).addActor(a2);
                Sala s1 = new Sala(1,300, Typ.DWA_DE);
                Sala s2 = new Sala(2,200, Typ.TRZY_DE);
                
                LocalDateTime ldt = LocalDateTime.now().plusHours(4);
                SeansFilmowy sf1 = new SeansFilmowy(p1,s1, ldt.minusHours(2),2000);
                SeansFilmowy sf2 = new SeansFilmowy(p2,s2, ldt.minusMinutes(15),3000);
                SeansFilmowy sf3 = new SeansFilmowy(p3,s1, ldt.minusHours(1),3500);
                SeansFilmowy sf4 = new SeansFilmowy(p3,s2, ldt.minusHours(1),3500);
                return FXCollections.<SeansFilmowy>observableArrayList(sf1, sf3,  sf2, sf4);
        }
         
        
        public static TableColumn<SeansFilmowy, Integer> getIdColumn() {
                TableColumn<SeansFilmowy, Integer> IdCol = new TableColumn<>("seans");
                IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                IdCol.setMinWidth(150);
                return IdCol;
        }
         
        
        public static TableColumn<SeansFilmowy, String> getTitleColumn() {
                TableColumn<SeansFilmowy, String> tytulCol = new TableColumn<>("Tytul");
                tytulCol.setCellValueFactory(new PropertyValueFactory<>("sTytul"));
                tytulCol.setMinWidth(100);
                return tytulCol;
        }
         
        
        public static TableColumn<SeansFilmowy, String> getDirectorColumn() {
                TableColumn<SeansFilmowy, String> directorCol = new TableColumn<>("Reżyser");
                directorCol.setCellValueFactory(new PropertyValueFactory<>("sRezyser"));
                directorCol.setMinWidth(100);
                return directorCol;
        }
        public static TableColumn<SeansFilmowy, LocalDateTime> getGodzinaColumn() {
                TableColumn<SeansFilmowy, LocalDateTime> godzCol = new TableColumn<>("Godzina");
                godzCol.setCellValueFactory(new PropertyValueFactory<>("startFilmu"));
                godzCol.setMinWidth(70);
                godzCol.setCellFactory(
                    new Callback<TableColumn<SeansFilmowy, LocalDateTime>, TableCell<SeansFilmowy, LocalDateTime>>() {

                    @Override
                    public TableCell<SeansFilmowy, LocalDateTime> call(TableColumn<SeansFilmowy, LocalDateTime> param
                    ) {
                        return new TableCell<SeansFilmowy, LocalDateTime>() {

                            @Override
                            protected void updateItem(LocalDateTime item, boolean empty) {
                                super.updateItem(item, empty); 
                                if (item == null || empty) {
                                    setText(null);
                                    setStyle("");
                                } else {
                                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM  HH:mm");
                                    setText(item.format(df));



                                }
                            }

                        };
                    }
                }
        );                
                return godzCol;
        }
        
        public static TableColumn<SeansFilmowy, LocalTime> getCzasTrwaniaColumn() {
                TableColumn<SeansFilmowy, LocalTime> godzCol = new TableColumn<>("Czas filmu");
                godzCol.setCellValueFactory(new PropertyValueFactory<>("czasTrwania"));
                godzCol.setMinWidth(65);
                godzCol.setCellFactory(
                    new Callback<TableColumn<SeansFilmowy, LocalTime>, TableCell<SeansFilmowy, LocalTime>>() {

                    @Override
                    public TableCell<SeansFilmowy, LocalTime> call(TableColumn<SeansFilmowy, LocalTime> param
                    ) {
                        return new TableCell<SeansFilmowy, LocalTime>() {

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
       
        public static TableColumn<SeansFilmowy, String> getCenaColumn() {
                TableColumn<SeansFilmowy, String> cenaCol = new TableColumn<>("Cena");
                cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
                cenaCol.setMinWidth(65);
                 Callback<CellDataFeatures<SeansFilmowy, String>, ObservableValue<String>> cenaCellFactory =
                    new Callback<CellDataFeatures<SeansFilmowy, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<SeansFilmowy, String> cellData) {
                        SeansFilmowy p = cellData.getValue();
                        p.setCena(new DecimalFormat("#.##").format(p.getPrice() * p.getSala().typ.val())+" zł");
                        return p.cenaProperty();
                    }};
    
                cenaCol.setStyle("-fx-text-fill: darkgreen; -fx-font-weight: bold;");
                
                cenaCol.setCellValueFactory(cenaCellFactory);
                return cenaCol;
        }        
       
         public static TableColumn<SeansFilmowy, Sala> getSalaColumn() {
                TableColumn<SeansFilmowy, Sala> salaCol = new TableColumn<>("Sala");
                salaCol.setCellValueFactory(new PropertyValueFactory<>("sala"));
                salaCol.setMinWidth(15);
                return salaCol;
        }
        public static TableColumn<SeansFilmowy, PEGI> getPEGIColumn() {
                TableColumn<SeansFilmowy, PEGI> pegCol = new TableColumn<>("PEGI");
                pegCol.setCellValueFactory(new PropertyValueFactory<>("pegi"));
                pegCol.setMinWidth(20);
                return pegCol;
        }         
        
        public static TableColumn<SeansFilmowy, String> getActorsColumn() {
                TableColumn<SeansFilmowy, String> aktorCol =
                                         new TableColumn<>("Obsada");
                aktorCol.setCellValueFactory(new PropertyValueFactory<>("aktorzyList"));
                aktorCol.setMinWidth(120);
                return aktorCol;
        }    

        public static TableColumn<SeansFilmowy, Integer> getYearColumn() {
                TableColumn<SeansFilmowy, Integer> bDateCol =
                                         new TableColumn<>("Rok Prod.");
                bDateCol.setCellValueFactory(new PropertyValueFactory<>("rokProdukcji"));
                bDateCol.setMinWidth(45);
                return bDateCol;
        }    
}
