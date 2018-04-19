/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa kontrolera w modelu MVC dla widoku logowania i modelu Widza
 * @author 
 */
public class LoginPresenter {
        private final Widz model;
        private final LogowanieView view;
         
        public LoginPresenter(Widz model, LogowanieView view) {
                this.model = model;
                this.view = view;
                attachEvents();
                bindFieldsToModel();
        }
         /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        public void bindFieldsToModel() {
                //txtWidzId.textProperty().bind(model.widzIdProperty().asString());
                view.txtLogin.textProperty().bindBidirectional(model.loginProperty());
                view.txtPass.textProperty().bindBidirectional(model.passProperty());
        }
        
         /**
         *  Wiąże zdarzenia widoku z modelem Widz
         */
        private void attachEvents() {

            // Zapisz dane modelu
            view.loginBtn.setOnAction(e -> login());

            // Czyści pola
            view.cleanBtn.setOnAction(e -> view.getScene().getWindow().hide());
        }
        
        /**
         * Wunkcja dokonuje próby zalogowanie z podanymi danymi autoryzacji
         * @return true jeśli próba się powiodła false w przeciwnym wypadku
         */
        private boolean login() {
            boolean bRet = false;
            List<String> errorList = new ArrayList<>();

            String sLogin = view.txtLogin.getText();
            String sPass = view.txtPass.getText();
            if (sLogin != null && !sLogin.trim().equals("")) {
                if (sPass != null && !sPass.trim().equals("")) {
                    model.login(errorList);
                }            

            }            
            return bRet;
        }
        
}
