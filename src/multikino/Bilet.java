/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * Klasa Biletu wiąże seans filmowy (film + sala + godzina) z obiektem widza
 */
public class Bilet {
    //final private SeansFilmowy sf;
    //final private Widz w;
    //final Discount disc;
    //final float cenaZakupu;
    //final int seatNr;
    private final IntegerProperty seatNr = new ReadOnlyIntegerWrapper();
    private final ObjectProperty<Discount> disc = new SimpleObjectProperty<>();
    private final ObjectProperty<SeansFilmowy> sf = new SimpleObjectProperty<>();
    private final ObjectProperty<Widz> w = new SimpleObjectProperty<>();
    private final FloatProperty cenaZakupu = new SimpleFloatProperty();
    private final ObjectProperty<Sala> sala = new SimpleObjectProperty<>();

    public Sala getSala() {
        return sala.get();
    }

    public void setSala(Sala value) {
        sala.set(value);
    }

    public ObjectProperty salaProperty() {
        return sala;
    }
    

    public float getCenaZakupu() {
        return cenaZakupu.get();
    }

    public void setCenaZakupu(float value) {
        cenaZakupu.set(value);
    }

    public FloatProperty cenaZakupuProperty() {
        return cenaZakupu;
    }

    public Widz getW() {
        return w.get();
    }

    public void setW(Widz value) {
        w.set(value);
    }

    public ObjectProperty wProperty() {
        return w;
    }

    public SeansFilmowy getSf() {
        return sf.get();
    }

    public void setSf(SeansFilmowy value) {
        sf.set(value);
    }

    public ObjectProperty sfProperty() {
        return sf;
    }
    
    public Discount getDisc() {
        return disc.get();
    }

    public void setDisc(Discount value) {
        disc.set(value);
    }

    public ObjectProperty discProperty() {
        return disc;
    }

    public int getSeatNr() {
        return seatNr.get();
    }

    public void setSeatNr(int value) {
        seatNr.set(value);
    }

    public IntegerProperty seatNrProperty() {
        return seatNr;
    }
    
    public Bilet(SeansFilmowy sf, Widz w, Discount disc, float cena, int seatNr, Sala s) {
        this.w.set(w);
        this.sf.set(sf);
        this.disc.set(disc);
        cenaZakupu.set(cena);
        this.seatNr.set(seatNr);
        sala.set(s);
    }
   public ObjectProperty czasTrwaniaProperty() {
        return sf.get().czasTrwaniaProperty();
    }
     public StringProperty sTytulProperty() {
        return sf.get().sTytulProperty();
    }   

}
