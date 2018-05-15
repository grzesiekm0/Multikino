/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.time.LocalTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * Klasa film mapuje wszystkie pozycje z tabelki bazy danych (lub innego źródła) film
 *  
 */
public class Film implements Dane{

    public static int ID = 1;
    Film() {
        this.id.set(ID++);
    }

    public enum PEGI {
        G(0),
        PG(2),
        PG13(13),
        R(15),
        N17(18);
        
        int peg;
        PEGI(int pg) {
            peg = pg;
        }
        
        @Override
        public String toString() {
            String str = "";
            switch(peg) {
                case 0:
                    str="G";
                    break;
                case 2:
                    str="PG";
                    break;
                case 13:
                    str="PG13";
                    break;
                case 15:
                    str="R";
                    break;
                case 18:
                    str="N17";
                    break;
                default:
                    break;
            }
            return str;
        }
            
        
        
    }
    
    
    
    public Film(PEGI peg, int id) {
        this.pegi.set(peg);
        this.id.set(id);
    }


    
    //lista aktorów powiązanych z filmem
    //private List<Aktor> aktorzyList = new LinkedList<Aktor>();
    
    private final StringProperty sTytul = new SimpleStringProperty();
    private final StringProperty sRezyser = new SimpleStringProperty();
    private final IntegerProperty rokProdukcji = new SimpleIntegerProperty();
    private final ObjectProperty<PEGI> pegi = new SimpleObjectProperty<>();
    private final SetProperty<Aktor> aktorzyList = new SimpleSetProperty<>();
    private final ObjectProperty<LocalTime> czasTrwania = new SimpleObjectProperty<>();

    public LocalTime getCzasTrwania() {
        return czasTrwania.get();
    }

    public void setCzasTrwania(LocalTime value) {
        czasTrwania.set(value);
    }

    public ObjectProperty czasTrwaniaProperty() {
        return czasTrwania;
    }
    
    
    public ObservableSet getAktorzyList() {
        return aktorzyList.get();
    }

    public void setAktorzyList(ObservableSet value) {
        aktorzyList.set(value);
    }

    public SetProperty aktorzyListProperty() {
        return aktorzyList;
    }    
    public PEGI getPegi() {
        return pegi.get();
    }

        public ReadOnlyObjectProperty pegiProperty() {
            return pegi;
        }
    

    public int getRokProdukcji() {
        return rokProdukcji.get();
    }

    public void setRokProdukcji(int value) {
        rokProdukcji.set(value);
    }

    public IntegerProperty rokProdukcjiProperty() {
        return rokProdukcji;
    }
    
    //id filmu
    private final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper();
    
    public String getsRezyser() {
        return sRezyser.get();
    }

    public Film setsRezyser(String value) {
        sRezyser.set(value);
        return this;
    }

    public StringProperty sRezyserProperty() {
        return sRezyser;
    }
    
    public int getId() {
        return id.get();
    }

    public ReadOnlyIntegerProperty idProperty() {
        return id.getReadOnlyProperty();
    }

    public String getsTytul() {
        return sTytul.get();
    }

    public Film setsTytul(String value) {
        sTytul.set(value);
        return this;
    }

    public StringProperty sTytulProperty() {
        return sTytul;
    }
    /**
     *  dodaje aktora do filmu
     * @param a - obiekt aktora wystepującego w filmie
     */
    public Film addActor(Aktor a) {
        if(aktorzyList.get() == null)
            aktorzyList.set(FXCollections.observableSet());
        aktorzyList.get().add(a);
        return this;
    }
    /**
     *  wraca listę aktorów występujących w filmie
     * @return lista połączona aktorów
     */
    public Set<Aktor> getActors() {
        return aktorzyList.get();
    }
}
