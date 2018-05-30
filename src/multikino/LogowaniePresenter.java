/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.xml.ws.Holder;
import multikino.helper.BiletTableUtil;
import multikino.helper.WidzUtil;

/**
 * Klasa kontrolera w modelu MVC dla widoku logowania i modelu Widza
 * @author 
 */
public class LogowaniePresenter {
        //private Widz model;
        private final LogowanieView view;
        private Holder<Widz> widzHolder;
    private final ObjectProperty<Widz> model = new SimpleObjectProperty<>();

    public Widz getModel() {
        return model.get();
    }

    public void setModel(Widz value) {
        model.set(value);
    }

    public ObjectProperty modelProperty() {
        return model;
    }
        
        
        
        public LogowaniePresenter(LogowanieView view, Holder<Widz> widzHolder) {
            this.widzHolder = widzHolder;
            this.model.set(new Widz());
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
                //view.txtLogin.textProperty().bindBidirectional(model.get().loginProperty());
                //view.txtPass.textProperty().bindBidirectional(model.get().passProperty());
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

            String sLogin = view.txtLogin.getText().trim();
            String sPass = view.txtPass.getText().trim();
            if (sLogin != null && !sLogin.trim().equals("")) {
                if (sPass != null && !sPass.trim().equals("")) {
                    model.get().login(errorList);
                }            
            }
            for(Widz widz : WidzUtil.getWidzList()) {
                if(sLogin.compareTo(widz.getWidzId()+"") == 0 &&   sPass.compareTo(widz.getLastName()) == 0) {
                    //ZALOGOWANY
                    widzHolder.value = widz;
                    model.set( widz );
                    //usuń bilety kogoś wcześniej
                    BiletTableUtil.bilety.clear();
                    return true;
                    
                }
            }
            WidzUtil.showErrorDialog("Błąd podczas logowania", "Błąd podczas logowania", "Login albo hasło są niepoprawne");
            return bRet;
        }
        
}
