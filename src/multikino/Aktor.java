/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.util.*;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasa aktorów mapuje ado obiektów tej klasy krotki z tabeli Aktor
 * @author 
 */
public class Aktor implements Dane {

    public static int ID = 1;
    private final StringProperty sImie = new SimpleStringProperty();
    private final StringProperty sNazwisko = new SimpleStringProperty();
    private final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper();
    private List<Film> filmyList = new LinkedList<Film>();

    Aktor() {
        id.set(ID++);
    }

    public int getId() {
        return id.get();
    }
 

    /**
     * Wraca listę filmów
     *
     * @return the value of filmyList
     */
    public List<Film> getFilmyList() {
        return filmyList;
    }

    /**
     * Dodaje film z udzialem aktora
     *
     * @param filmyList new value of filmyList
     */
    public void addFilm(Film film) {
        if(film != null)
            this.filmyList.add(film);
    }

    public String getsNazwisko() {
        return sNazwisko.get();
    }

    public Aktor setsNazwisko(String value) {
        sNazwisko.set(value);
        return this;
    }

    public StringProperty sNazwiskoProperty() {
        return sNazwisko;
    }

    public String getsImie() {
        return sImie.get();
    }

    public Aktor setsImie(String value) {
        sImie.set(value);
        return this;
    }

    public StringProperty sImieProperty() {
        return sImie;
    }
    /**
     * konstruktor klasy Aktor 
     * @param id aktora ze źródła danych
     */
    public Aktor(int id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return sImie.get() + " "+sNazwisko.get();
    }
    
}
