/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * Klasa identyfikująca salę
 */

public class Sala {


    List<Bilet> reserveSeats(Widz value, SeansFilmowy sf, int ticketNo, float sum, int discIndex) {
        sf.getSala().reserveSeats(ticketNo, sum, discIndex);

    }

    public enum Typ {
        DWA_DE(1.0f),
        TRZY_DE(1.3f),
        CZTERY_DE(1.5f);

        float typ;
        Typ(float typ) {this.typ = typ;}
        public float val() {
            return typ;
        }
        @Override
        public String toString() {
            String sRet = "2D";
            if(typ >= 1.5f) 
                    sRet = "4D";
            else if(typ >= 1.3f) sRet = "3D";
            //else if(typ >= 1.0f) sRet = "2D";

            return sRet;
        }
        
    };

    private final IntegerProperty nr = new ReadOnlyIntegerWrapper();
    //lista miejsc w sali
    public int[] seats;
    final public Typ typ;
    private final ReadOnlyIntegerWrapper seatCount = new ReadOnlyIntegerWrapper();
    private final StringProperty sala = new SimpleStringProperty();
    private final FloatProperty okulary3D = new SimpleFloatProperty();

    public float getOkulary3D() {
        return okulary3D.get();
    }

    public void setOkulary3D(float value) {
        okulary3D.set(value);
    }

    public FloatProperty okulary3DProperty() {
        return okulary3D;
    }

    
    public String getSala() {
        return sala.get();
    }

    public void setSala(String value) {
        sala.set(value);
    }

    public StringProperty salaProperty() {
        return sala;
    }
    
    public int getSeatCount() {
        return seatCount.get();
    }

    public ReadOnlyIntegerProperty seatCountProperty() {
        return seatCount.getReadOnlyProperty();
    }
    
    public int getNr() {
        return nr.get();
    }

    public void setNr(int value) {
        nr.set(value);
    }

    public IntegerProperty nrProperty() {
        return nr;
    }

    public Sala(int nr, int seatCount, Typ typ) {
        this.typ = typ;
        this.nr.set(nr);
        this.seatCount.set(seatCount);
        seats = new int[seatCount];
        //jak 0 to wolne jak > 0 to id widza
        for(int i = 0 ; i < seatCount; i++)            
            seats[i] = 0;
        
        sala.set(typ.toString());
        if(typ != Typ.DWA_DE) {
            okulary3D.set(3.0f);
        }
        else
            okulary3D.set(0.0f);
    }
    public List<Integer> getSeats(int nr, int widzId) {
        List<Integer> seatResv = new LinkedList<>();
        for(int i = 0 ; i < seats.length && nr > 0; i++)  {          
            if(seats[i] == 0) {
                seats[i] = widzId;
                seatResv.add(i);
                nr--;
            }
        }
        return seatResv;
    }

    public synchronized int getFreeSeats() {
        
        int freeSeats = 0;
        for(int i = 0 ; i < seats.length; i++)  { 
            if(seats[i] == 0)
                freeSeats++;
        }
        return freeSeats;
    }

    public int getTotalSeats() {
        return seats.length;
    }

    @Override
    public String toString() {
        return "#" + nr.get() + " "+typ.toString()+"";
    }
    
    
}
