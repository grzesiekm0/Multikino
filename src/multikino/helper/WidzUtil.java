/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino.helper;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import multikino.Widz;
import multikino.Widz.Rola;

/**
 * Zawiera listę wszystkich widzów z tą listą parowane sa dane z logowania
 */
public class WidzUtil {
    
    private static ObservableList<Widz> lista = null;
    public static ObservableList<Widz> getWidzList() {
        if(lista == null)
        {
          lista = FXCollections.<Widz>observableArrayList();
          
          Widz w1 = new Widz("Aleksandra", "Flis", LocalDate.now().minusYears(67), Rola.PRACOWNIK );
          

          lista.addAll(w1);
        }
        return lista;
    }  
      
    public static void showErrorDialog(String sError, String sTitle, String msg) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle(sTitle);
         alert.setHeaderText(sError);
         
         alert.setContentText(msg);
         alert.showAndWait().ifPresent(rs -> {
             if (rs == ButtonType.OK) {
                 System.out.println("OK.");
             }
         });
     }    
}
