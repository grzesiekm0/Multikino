/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

/**
 *
 * @author 
 */
public class SeansFilmowy {

    private final ReadOnlyIntegerWrapper seansId = new ReadOnlyIntegerWrapper();

    public SeansFilmowy(int id) {
        seansId.set(id);
    }
    public int getSeansId() {
        return seansId.get();
    }

    public ReadOnlyIntegerProperty seansIdProperty() {
        return seansId.getReadOnlyProperty();
    }
    
}
