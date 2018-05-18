/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino.helper;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
          Widz w1 = new Widz("Mirek", "Kowalski", LocalDate.now().minusYears(39), Rola.WIDZ );
          Widz w2 = new Widz("Ewa", "Kowalska", LocalDate.now().minusYears(67), Rola.PRACOWNIK );
          Widz w3 = new Widz("Jan", "Nowak", LocalDate.now().minusYears(9), Rola.PRACOWNIK );

          lista.addAll(w1, w3,  w2);
        }
        return lista;
    }  
}
