/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
 

/**
 * Klasa Widoku wzorca MPV dla rejetsracji nowego widza
 * 
 * @author 
 */
public class RejestracjaView extends GridPane {
        private final Widz model;
 
        // Labels
        Label widzIdLabel = new Label("Id widza:");
        Label imieLabel = new Label("Imię:");
        Label nazwiskoLabel = new Label("Nazwisko:");
        Label dataLabel = new Label("Data urodzenia:");
        Label katWiekowaLabel = new Label("Kategoria wiekowa:");
 
        // pola tekstowe
        TextField txtWidzId = new TextField();
        TextField txtImie = new TextField();
        TextField txtNazwisko = new TextField();
        //TextField txtData = new TextField();
        TextField txtKatWiek = new TextField();
        DatePicker txtData = new DatePicker();
 


         
        // Buttons
        Button saveBtn = new Button("Zapisz");
        Button cleanBtn = new Button("Wyczyść");
 
        // Date format
        String dateFormat;
 
        public RejestracjaView(Widz model) {
                setId("reg_view");

                this.model = model;
                this.dateFormat = "dd.MM.yyyy";
                layoutForm();
                initFieldData();
                bindFieldsToModel();
        }
 
        /**
         *  wypełnia dane widoku domyślnymi danymi modelowymi obiektu Widz
         */
        private void initFieldData() {
                syncBirthDate();
        }
 
        /**
         *  Buduje widok rejestracji , umieszcza w nim kontrolki
         */
        private void layoutForm() {
                this.setHgap(10);
                this.setVgap(25);
 
                this.add(widzIdLabel, 1, 1);
                this.add(imieLabel, 1, 2);
                this.add(nazwiskoLabel, 1, 3);
                this.add(dataLabel, 1, 4);
                this.add(katWiekowaLabel, 1, 5);
 
                this.add(txtWidzId, 2, 1);
                this.add(txtImie, 2, 2);
                this.add(txtNazwisko, 2, 3);
                this.add(txtData, 2, 4);
                this.add(txtKatWiek, 2, 5);
                
                txtData.setEditable(false);
                txtData.setOnAction(e ->
                        System.out.println("New Date:" + txtData.getValue()));
                
                //VBox buttonBox = new VBox(saveBtn, cleanBtn);
                HBox buttonBox = new HBox(15, cleanBtn, saveBtn);
                saveBtn.setMaxWidth(Double.MAX_VALUE);
                cleanBtn.setMaxWidth(Double.MAX_VALUE);
                cleanBtn.setOnAction( e -> {
                    txtImie.setText("");
                    txtNazwisko.setText("");
                    //txtData.setText("");
                    txtData.setValue(model.getBirthDate());
                });
                //this.add(buttonBox, 3, 1, 1, 5);
                this.add(buttonBox, 2,6);
                
                        
                txtWidzId.setDisable(true);
                txtKatWiek.setDisable(true);
 
                txtImie.setPromptText("wprowadz swoje imię");
                txtNazwisko.setPromptText("wprowadz swoje nazwisko");
                txtData.setPromptText(dateFormat.toLowerCase());
        }
 
        /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        public void bindFieldsToModel() {
                txtWidzId.textProperty().bind(model.widzIdProperty().asString());
                txtImie.textProperty().bindBidirectional(model.firstNameProperty());
                txtNazwisko.textProperty().bindBidirectional(model.lastNameProperty());
        }
 
        /**
         * Wypełnia pole daty domyślną wartośią daty.
         */
        public void syncBirthDate() {
                LocalDate bdate = model.getBirthDate();
                if (bdate != null) {
                          txtData.setValue(bdate);
                }
 
                syncAgeCategory();
        }
 
        public void syncAgeCategory() {
                txtKatWiek.setText(model.getAgeCategory().toString());
        }
}