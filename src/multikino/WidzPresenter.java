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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author 
 */
public class WidzPresenter {
        private final Widz model;
        private final RejestracjaView view;
         
        public WidzPresenter(Widz model, RejestracjaView view) {
                this.model = model;
                this.view = view;
                attachEvents();
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
        }
         
        public void showError(List<String> errorList) {
                String msg = "";
                if (errorList.isEmpty()) {
                        msg = "Brak komunikatu o błędzie.";
                } else {
                        for (String s : errorList) {
                                msg = msg + s + "\n";
                        }
                }
 
                Label msgLbl = new Label(msg);
                Button okBtn = new Button("OK");
                VBox root = new VBox(new StackPane(msgLbl), new StackPane(okBtn));
                root.setSpacing(10);
 
                Scene scene = new Scene(root);
                Stage stage = new Stage(StageStyle.UTILITY);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(scene);
                stage.initOwner(view.getScene().getWindow());
 
                okBtn.setOnAction(e -> stage.close());
  
                stage.setTitle("Error");
                stage.sizeToScene();
                stage.showAndWait();
        }
}
 