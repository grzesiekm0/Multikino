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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import multikino.Bilet;
import multikino.Discount;
import multikino.Film;
import multikino.Sala;
import multikino.SeansFilmowy;

/**
 *  Wspomaga tworzenie tabelki z biletami.
 */
public class BiletTableUtil {
    
          public static ObservableList<Bilet> bilety = FXCollections.<Bilet>observableArrayList();
          public static ObservableList<Bilet> getBiletyList() {
              return bilety;
          }
          public static TableColumn<Bilet, Integer> getSeatNrColumn() {
                TableColumn<Bilet, Integer> seatCol = new TableColumn<>("miejsce nr");
                seatCol.setCellValueFactory(new PropertyValueFactory<>("seatNr"));
                seatCol.setMinWidth(15);
                return seatCol;
        }
         
          public static TableColumn<Bilet, Sala> getSalaColumn() {
                TableColumn<Bilet, Sala> salaCol = new TableColumn<>("Sala");
                salaCol.setCellValueFactory(new PropertyValueFactory<>("sala"));
                salaCol.setMinWidth(15);
                return salaCol;
        }
                  
       public static TableColumn<Bilet, Float> getCenaColumn() {
                TableColumn<Bilet, Float> cenaCol = new TableColumn<>("Zapłacono");
                cenaCol.setCellValueFactory(new PropertyValueFactory<>("cenaZakupu"));
                cenaCol.setMinWidth(35);
                cenaCol.setCellFactory(
                    new Callback<TableColumn<Bilet, Float>, TableCell<Bilet, Float>>() {

                    @Override
                    public TableCell<Bilet, Float> call(TableColumn<Bilet, Float> param) {
                        return new TableCell<Bilet, Float>() {

                            @Override
                            protected void updateItem(Float item, boolean empty) {
                                super.updateItem(item, empty); 
                                if (item == null || empty) {
                                    setText(null);
                                    setStyle("");
                                } else {
                                    //DateTimeFormatter df = DateTimeFormatter.ofPattern("#.00 zł.");
                                    
                                        setText(String.format("%.2f zł", item.floatValue()));
                                    }
                                }

                            };
                        }
                    }
                );                

                cenaCol.setStyle("-fx-text-fill: darkgreen; -fx-font-weight: bold;");
                return cenaCol;
        }   
        public static TableColumn<Bilet, Film.PEGI> getPEGIColumn() {
                TableColumn<Bilet, Film.PEGI> pegCol = new TableColumn<>("PEG");
                pegCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bilet, Film.PEGI>, ObservableValue<Film.PEGI>>() {
                    @Override
                    public ObservableValue<Film.PEGI> call(TableColumn.CellDataFeatures<Bilet, Film.PEGI> param) {
                        return param.getValue().getSf().getFilm().pegiProperty();
                    }
                });
                pegCol.setMinWidth(50);
                return pegCol;
        }  

        public static TableColumn<Bilet, Discount> getDiscColumn() {
                TableColumn<Bilet, Discount> pegCol = new TableColumn<>("Ulga na bilet");
                pegCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bilet, Discount>, ObservableValue<Discount>>() {
                    @Override
                    public ObservableValue<Discount> call(TableColumn.CellDataFeatures<Bilet, Discount> param) {
                        return param.getValue().discProperty();
                    }
                });
                pegCol.setMinWidth(110);
                return pegCol;
        }  
        public static TableColumn<Bilet, String> getTitleColumn() {
                TableColumn<Bilet, String> tytulCol = new TableColumn<>("Tytul");
                tytulCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bilet, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Bilet, String> param) {
                        Bilet b = param.getValue();
                        return b.sTytulProperty();
                    }
                });
                tytulCol.setMinWidth(170);
                return tytulCol;
        }

        public static TableColumn<Bilet, LocalTime> getCzasTrwaniaColumn() {
                TableColumn<Bilet, LocalTime> godzCol = new TableColumn<>("Czas filmu");
                godzCol.setCellValueFactory(new   Callback<TableColumn.CellDataFeatures<Bilet, LocalTime>, ObservableValue<LocalTime>>() {
                    @Override
                    public ObservableValue<LocalTime> call(TableColumn.CellDataFeatures<Bilet, LocalTime> param) {
                        return param.getValue().getSf().getFilm().czasTrwaniaProperty();
                    }
                });
                godzCol.setMinWidth(45);
                godzCol.setCellFactory(
                new Callback<TableColumn<Bilet, LocalTime>, TableCell<Bilet, LocalTime>>() {

                @Override
                public TableCell<Bilet, LocalTime> call(TableColumn<Bilet, LocalTime> param
                ) {
                    return new TableCell<Bilet, LocalTime>() {

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

         public static TableColumn<Bilet, LocalDateTime> getGodzinaColumn() {
                TableColumn<Bilet, LocalDateTime> godzCol = new TableColumn<>("Rozpoczęcie");
                godzCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bilet, LocalDateTime>, ObservableValue<LocalDateTime>>() {
                    @Override
                    public ObservableValue<LocalDateTime> call(TableColumn.CellDataFeatures<Bilet, LocalDateTime> param) {
                        return param.getValue().getSf().startFilmuProperty();
                    }
                });
                godzCol.setMinWidth(70);
                godzCol.setCellFactory(
                    new Callback<TableColumn<Bilet, LocalDateTime>, TableCell<Bilet, LocalDateTime>>() {

                    @Override
                    public TableCell<Bilet, LocalDateTime> call(TableColumn<Bilet, LocalDateTime> param
                    ) {
                        return new TableCell<Bilet, LocalDateTime>() {

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
       
}
