/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *
 * Klasa widoku dla panelu Logowania 
 */
public class LogowanieView extends GridPane {

    Label widzIdLabel = new Label("Id widza:");
    Label loginLabel = new Label("Login:");
    Label passLabel = new Label("Hasło:");

    TextField txtWidzId = new TextField();
    TextField txtLogin = new TextField();
    PasswordField txtPass = new PasswordField();

    // Buttons
     Button loginBtn = new Button("Zaloguj");
     Button cleanBtn = new Button("Wyczyść");

    public LogowanieView() {
        super();
        setId("login_view");
        layoutForm();
    }
   
    
        /**
         *  Buduje widok Panelu Logowania , umieszcza w nim kontrolki
         */
        private void layoutForm() {
                this.setHgap(10);
                this.setVgap(25);
 
//                this.add(widzIdLabel, 1, 1);
                this.add(loginLabel, 1, 2);
                this.add(passLabel, 1, 3);
 
                //this.add(txtWidzId, 2, 1);
                this.add(txtLogin, 2, 2);
                this.add(txtPass, 2, 3);
 
                
                HBox buttonBox = new HBox(15, cleanBtn, loginBtn);
                loginBtn.setMaxWidth(Double.MAX_VALUE);
                cleanBtn.setMaxWidth(Double.MAX_VALUE);
                cleanBtn.setOnAction(e -> {
                    txtLogin.setText("");
                    txtPass.setText("");
                });
                this.add(buttonBox, 2, 4);
 
                // Disable the personId field
                txtWidzId.setDisable(true);
 
                txtLogin.setPromptText("twój login");
                txtPass.setPromptText("hasło do serwisu kina");
        }
    
}
