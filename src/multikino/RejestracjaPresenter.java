/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.ws.Holder;
import multikino.helper.WidzUtil;


/**
 *
 * @author 
 */
public class RejestracjaPresenter {
        private Widz model;
        private final RejestracjaView view;
        Holder<Widz> widzHolder = null;
        
        public RejestracjaPresenter(Widz model, RejestracjaView view, Holder<Widz> widzHolder) {
                this.model = model;
                this.view = view;
                attachEvents();
                bindFieldsToModel();
                this.widzHolder = widzHolder;
        }
        
        /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        public void bindFieldsToModel() {
                view.txtWidzId.textProperty().bind(model.widzIdProperty().asString());
                view.txtImie.textProperty().bindBidirectional(model.firstNameProperty());
                view.txtNazwisko.textProperty().bindBidirectional(model.lastNameProperty());
        }
        /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        public void reBindFieldsToModel(Widz _model) {
                
                view.txtWidzId.textProperty().unbind();
                view.txtImie.textProperty().unbindBidirectional(model.firstNameProperty());
                view.txtNazwisko.textProperty().unbindBidirectional(model.lastNameProperty());
                this.model = _model;
        }
                
        
        /**
         *  Wiąże zdarzenia widoku z modelem Widz
         */
        private void attachEvents() {
            view.txtData.setOnAction(e -> handleBirthDateChange());
            view.txtData.getScene().focusOwnerProperty().addListener(this::focusChanged);

            // Zapisz dane modelu
            view.saveBtn.setOnAction(e -> saveData());

            // Czyści pola
            view.cleanBtn.setOnAction(e -> view.getScene().getWindow().hide());
        }
 
        public void focusChanged(ObservableValue<? extends Node> value,
                                 Node oldNode,
                                 Node newNode) {
            if (oldNode == view.txtData) {
                handleBirthDateChange();
            }
        }
         
        private void handleBirthDateChange() {
                LocalDate bdate = view.txtData.getValue();
                if (bdate == null) {
                    model.setBirthDate(null);
                    view.syncBirthDate();
                } else {
                    try {
                        List<String> errorList = new ArrayList<>();
                        if (model.isValidBirthDate(bdate, errorList)) {
                                model.setBirthDate(bdate);
                                view.syncAgeCategory();
                        } else {
                                this.showError(errorList);
                                view.syncBirthDate();
                        }
                    }
                    catch (DateTimeParseException e) {
                        // Birth date is not in the specified date format
                        List<String> errorList = new ArrayList<>();
                        errorList.add("Data musi być w formacie " +
                                      view.dateFormat.toLowerCase() + ".");
                        this.showError(errorList);

                        // Refresh the view
                        view.syncBirthDate();
                    }
                }
        }
 
        private void saveData() {
                List<String> errorList = new ArrayList<>();
                
                LocalDate bdate = view.txtData.getValue();
                if (bdate == null) {
                    model.setBirthDate(null);
                    view.syncBirthDate();
                } else {
                    if (model.isValidBirthDate(bdate, errorList)) {
                                model.setBirthDate(bdate);
                                view.syncAgeCategory();
                        } else {
                                this.showError(errorList);
                                view.syncBirthDate();
                        }
                    errorList.clear();
                }
                

                boolean isSaved = model.save(errorList);
                if (!isSaved) {
                        this.showError(errorList);
                }
                else {
                    model.rejestracja(LocalDate.now());
                    WidzUtil.getWidzList().add(model);
                    //TODO: Zapisz w modelu danych
                }
        }
         
        public void showError(List<String> errorList) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd rejestracji");
                alert.setHeaderText("Rejestracja zakończyła się niepowodzeniem.");
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
 