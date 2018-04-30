/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 
 */
public class SeansFilmowy {

    private static int sID = 1;
    private final ReadOnlyIntegerWrapper seansId = new ReadOnlyIntegerWrapper();
    private final ObjectProperty<Film> film = new ReadOnlyObjectWrapper<>();
    private final ObjectProperty<Sala> sala = new SimpleObjectProperty<>();
    //godzina rozpoczecia seansu
    private final ObjectProperty<LocalDateTime> startFilmu = new SimpleObjectProperty<>();
    private final StringProperty cena = new SimpleStringProperty();
    private int iPrice = 3000;       //30zł

    public void setiPrice(int iPrice) {
        this.iPrice = iPrice;
    }

    SeansFilmowy() {
        seansId.set(sID++);
    }
    public String getCena() {
        return cena.get();
    }

    public void setCena(String value) {
        cena.set(value);
    }

    public StringProperty cenaProperty() {
        return cena;
    }

    public SeansFilmowy(Film f, Sala s, LocalDateTime tmStart, int iPrice) {
        film.set(f);
        sala.set(s);
        seansId.set(sID++);
        startFilmu.set(tmStart);
        this.iPrice = iPrice;
        
    }
    public LocalDateTime getStartFilmu() {
        return startFilmu.get();
    }

    public void setStartFilmu(LocalDateTime value) {
        startFilmu.set(value);
    }

    public ObjectProperty startFilmuProperty() {
        return startFilmu;
    }

    public Sala getSala() {
        return sala.get();
    }

    public void setSala(Sala value) {
        sala.set(value);
    }

    public ObjectProperty salaProperty() {
        return sala;
    }
    public IntegerProperty nrProperty() {
        return sala.get().nrProperty();
    }
    

    public Film getFilm() {
        return film.get();
    }

    public void setFilm(Film value) {
        film.set(value);
    }

    public ObjectProperty filmProperty() {
        return film;
    }

    public SeansFilmowy(int id) {
        seansId.set(id);
    }
    public int getSeansId() {
        return seansId.get();
    }

    public ReadOnlyIntegerProperty seansIdProperty() {
        return seansId.getReadOnlyProperty();
    }
    
    public StringProperty sRezyserProperty() {
        return film.get().sRezyserProperty();
    }
    public ReadOnlyObjectProperty pegiProperty() {
        return film.get().pegiProperty();
    }
    public IntegerProperty rokProdukcjiProperty() {
        return film.get().rokProdukcjiProperty();
    }
     public StringProperty sTytulProperty() {
        return film.get().sTytulProperty();
    }   
   
     public SetProperty aktorzyListProperty() {
        return film.get().aktorzyListProperty();
    }  
    public ObjectProperty czasTrwaniaProperty() {
        return film.get().czasTrwaniaProperty();
    }

     /**
      * sprawdza możliwość zakupu biletu i ewwentualnie wraca błąd
      * @param errorList
      * @return 
      */
    boolean buyTicketTest(List<String> errorList) {
        boolean bRet = true;
        LocalDateTime tm1 = getStartFilmu();
        LocalTime tm = film.get().getCzasTrwania();
        if(getStartFilmu().plusSeconds(film.get().getCzasTrwania().toSecondOfDay()).isBefore( LocalDateTime.now()) )  {
            errorList.add("Seans już się zakończył.");
            bRet = false;
        }
        else if(getStartFilmu().isBefore( LocalDateTime.now()) ) {
            errorList.add("Seans już się rozpoczął.");
            bRet = false;
        }
        else if(getStartFilmu().minusMinutes(5).isBefore( LocalDateTime.now()) )  {
            errorList.add("Pozostało już mniej niż 5 minut do ropoczęcia seansu.");
            bRet = false;
        }
        if( sala.get().getFreeSeats() <= 0 ) {
            errorList.add("Brak wolnych miejsc.");
            bRet = false;
        }
        
        return bRet;
    }

    public float getPrice() {
        return iPrice/100.0f;
    }
}
