/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import multikino.helper.SeansFilmowyTableUtil;

/**
* Klasa kontrolera modelu MVC pomiędzy modelem Seans a widokiem DodajSeansView
 */
public class SeansPresenter {
    SeansFilmowy model;
    final DodajSeansView view;
    public SeansPresenter(DodajSeansView view) {
        this.view = view;
        model = new SeansFilmowy();
        attachEvents();
        bindFieldsToModel();
    }
         /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        private void bindFieldsToModel() {
                //view.txtFilmId.textProperty().bind(model.idProperty().asString());
                view.saveBtn.setOnAction(value -> { 
                    saveData();
                });
        }
        /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        public void reBindFieldsToModel(SeansFilmowy _model) {
                
                //view.txtFilmId.textProperty().unbind();
                this.model = _model;
                bindFieldsToModel();
        }      
    
    
        /**
         *  Wiąże zdarzenia widoku z modelem Widz
         */
        private void attachEvents() {
            // Zapisz dane do modelu
            view.saveBtn.setOnAction(e -> saveData());

        }

        /**
         * Zapisuje dane Seansu w modelu i do bazy do tabeli Seans
         */
        private void saveData() {
            List<String> errorList = new LinkedList<>();
            
            if( view.tabelaSala.getSelectionModel().getSelectedItem() != null)
                model.setSala(view.tabelaSala.getSelectionModel().getSelectedItem());
            else {
                errorList.add("Trzeba zaznaczyć salę dla seansu filmowego.");
            }
            if( view.tabelaFilm.getSelectionModel().getSelectedItem() != null)
                model.setFilm(view.tabelaFilm.getSelectionModel().getSelectedItem());
            else {
                errorList.add("Trzeba zaznaczyć film dla seansu filmowego.");
            }
            try {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                DecimalFormat format = new DecimalFormat("0.#");
                
                format.setDecimalFormatSymbols(symbols);
                
                float fCena = format.parse( view.txtCena.getText() ).floatValue();
                int iCena = (int)(fCena *100);
                model.setCena(iCena+"");
                model.setiPrice(iCena);
            }
            catch (ParseException ex) {
                errorList.add("Źle sformatowana pozycja w polu cena.");
            }

            try {
                String input = view.txtCzasTrwania.getText();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime czas = LocalTime.parse(input, fmt);
                model.setStartFilmu(view.txtData.getValue().atTime(czas));
            }
            catch (Exception e) {
                errorList.add("Źle sformatowana godzina rozpoczecia.");
            }
            
            if(errorList.size() > 0)
                showError(errorList);
            else {
                //TODO: dodaj model seansu do bazy danych
                SeansFilmowyTableUtil.getFilmList().add(model);
            }
            
        }
        public void showError(List<String> errorList) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd dodawania seansu");
            alert.setHeaderText("Dodanie seansu zakończyło się niepowodzeniem.");
            String msg = "Brak dodatkowych informacji o błędzie.";
            if(errorList.size() > 0)
                msg = "";
            for(String s : errorList)
                msg += s+"\n";
            alert.setContentText(msg);
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("OK.");
                }
            });
        }
}
