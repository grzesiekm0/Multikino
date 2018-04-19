/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 
 */
public class Widz implements Dane{
    
        public enum KategoriaWiekowa {NIEMOWLE, DZIECKO, NASTOLATEK, DOROSLY, SENIOR, BRAK };
 
        private final ReadOnlyIntegerWrapper widzId =
                new ReadOnlyIntegerWrapper(this, "widzId", ++widzSeq);
        private final StringProperty sImie =
                 new SimpleStringProperty(this, "firstName", null);
        private final StringProperty sNazwisko =
                 new SimpleStringProperty(this, "lastName", null);
        private final ObjectProperty<LocalDate> dataUr =
                 new SimpleObjectProperty<>(this, "birthDate", null);

        private final StringProperty sLogin =
                 new SimpleStringProperty(this, "login", null);
        private final StringProperty sPass =
                 new SimpleStringProperty(this, "pass", null);
        
        private static int widzSeq = 0; 
        //karta widza
        private final IntegerProperty cardId = new ReadOnlyIntegerWrapper();

        public int getCardId() {
            return cardId.get();
        }

        private void setCardId(int value) {
            cardId.set(value);
        }

        public IntegerProperty cardIdProperty() {
            return cardId;
        }
        
        
        /**
         *  Tworzy nowy obiekt widza.
         */
        public Widz() {
                this(null, null, null);
        }
        
 
    /**
     * Pobiera obiekt widza dla każdego widza w bazie danych.
     * Jeśli jest to rejestracja obiekt zostaje utworzony i dodany do bazy
     * @param firstName imię - String
     * @param lastName nazwsiko - String 
     * @param birthDate data urdzodzenia - obiekt typu LocalDate
     */
    public Widz(String firstName, String lastName, LocalDate birthDate)
        { 
            this.sImie.set(firstName);
            this.sNazwisko.set(lastName);
            this.dataUr.set(birthDate);
        }
 
    /**
     * unikatowy obidentyfikator widza
     * @return
     */
    public final int getWidzId() {
                return widzId.get();
        }
 
    /**
     * Wraca property identyfikatora tylko do odczytu
     * @return
     */
    public final ReadOnlyIntegerProperty widzIdProperty() {
                return widzId.getReadOnlyProperty();
        }
 
    /**
     * Wraca string odpowiadający imieniu widza
     * @return
     */
    public final String getFirstName() {
                return sImie.get();
        }
 
    /**
     * Wraca string odpowiadający nazwisku widza
     * @param firstName
     */
    public final void setFirstName(String firstName) {
        firstNameProperty().set(firstName);
    }

    /**
     * Wraca property odpowiadające imieniu widza
     * @return
     */
    public final StringProperty firstNameProperty() {
                return sImie;
        }

    /**
     * Wraca property odpowiadające loginowi widza
     * @return
     */
     public final StringProperty loginProperty() {
                return sLogin;
     }

     /**
     * Wraca property odpowiadające hasłu widza
     * @return
     */
     public final StringProperty passProperty() {
                return sPass;
     }
 
    /**
     * Wraca property odpowiadające nazwisku widza
     * @return
     */
    public final String getLastName() {
                return sNazwisko.get();
        }
 
    /**
     * Ustawia nazwisko widza w obiekcie
     * @param lastName
     */
    public final void setLastName(String lastName) {
                lastNameProperty().set(lastName);
        }
 
    /**
     * Wraca property nazwiska widza.
     * @return
     */
    public final StringProperty lastNameProperty() {
                return sNazwisko;
        }
 
        /* birthDate Property */

    /**
     * Wraca obiekt LocalDate odpowiadający dacie urodzenia widza
     * @return
     */

        public final LocalDate getBirthDate() {
                return dataUr.get();
        }
 
    /**
     * Ustawia dobiekt daty urodzenia widza.
     * @param birthDate
     */
    public final void setBirthDate(LocalDate birthDate) {
                birthDateProperty().set(birthDate);
        }
 
    /**
     * Wraca obiekt property daty urodzenia widza.
     * @return
     */
    public final ObjectProperty<LocalDate> birthDateProperty() {
                return dataUr;
        }

    /**
     * Sprawdza poprawność wprowadzonej daty
     * @param bdate
     * @return true jeśli data jest poprawna w przeciwnym razie false. 
     */
    public boolean isValidBirthDate(LocalDate bdate) {
                return isValidBirthDate(bdate, new ArrayList<>());
        }
         
    /**
     * Waliduje datę. W liście błędów umieszczone są wiadomości o błędzie daty.
     * @param bdate
     * @param errorList
     * @return true jeśli data jest poprawna w przeciwnym razie false. 
     */
    public boolean isValidBirthDate(LocalDate bdate, List<String> errorList) {
                if (bdate == null) {
                        return true;
                }
 
                // Birth date cannot be in the future
                if (bdate.isAfter(LocalDate.now())) {
                        errorList.add("Birth date must not be in future.");
                        return false;
                }
 
                return true;
        }

    /**
     * Sprawdza poprawność pół obiektu widz
     * @param errorList
     * @return
     */
    public boolean isValidWidz(List<String> errorList) {
                return isValidWidz(this, errorList);
        }
 
    /**
     *
     * @param p
     * @param errorList
     * @return
     */
    public boolean isValidWidz(Widz p, List<String> errorList) {
                boolean isValid = true;
 
                String fn = p.sImie.get();
                if (fn == null || fn.trim().length() == 0) {
                        errorList.add("Imię musi zawierać przynajmniej jeden znak.");
                        isValid = false;
                }
 
                String ln = p.sNazwisko.get();
                if (ln == null || ln.trim().length() == 0) {
                        errorList.add("Nazwisko musi zawierać przynajmniej jeden znak.");
                        isValid = false;
                }
 
                if (!isValidBirthDate(this.dataUr.get(), errorList)) {
                        isValid = false;
                }
 
                return isValid;
        }
 
    /**
     * Ustanawia kategorię wiekową widza w oparciu o jego datę urodzenia. Pole użyteczne przy parowaniu z filmem w kasie biletowej.
     * @return
     */
    public KategoriaWiekowa getAgeCategory() {
                if (dataUr.get() == null) {
                        return KategoriaWiekowa.BRAK;
                }
                 
                long years = ChronoUnit.YEARS.between(dataUr.get(), LocalDate.now());
                if (years >= 0 && years < 2) {
                        return KategoriaWiekowa.NIEMOWLE;
                } else if (years >= 2 && years < 13) {
                        return KategoriaWiekowa.DZIECKO;
                } else if (years >= 13 && years <= 19) {
                        return KategoriaWiekowa.NASTOLATEK;
                } else if (years > 19 && years <= 50) {
                        return KategoriaWiekowa.DOROSLY;
                } else if (years > 50) {
                        return KategoriaWiekowa.SENIOR;
                } else {
                        return KategoriaWiekowa.BRAK;
                }
        }

    /**
     * Zapisuje obiekt widza (podczas tworzenia/modyfikacji w wybranym źródle danych.
     * @param errorList
     * @return
     */
    public boolean save(List<String> errorList) {
                boolean isSaved = false;
                if (isValidWidz(errorList)) {
                        System.out.println("Saved " + this.toString());
                        isSaved = true;
                }
                 
                return isSaved;
        }
 
   /**
     * Loguje obiekt widza do bazy.
     * @param errorList  lista błędów jeśli logowanie nie powiedzie się
     * @return true dla prawidłowego logowania
     */
    public boolean login(List<String> errorList) {
        boolean isLoged = false;
        if (login()) {
            System.out.println("Saved " + this.toString());
            isLoged = true;
        }

        return isLoged;
    }
        
    /**
     * Funkcja logowania do modelu danych
     * @return true jeśli logowanie powiodło się
     */
    private boolean login() {
        //TODO: wypełnij procedurą logowania
        return true;
    }
    @Override
    public String toString() {
        return "[ Id = " + widzId.get() +
            ", imie = " + sImie.get() +
            ", nazwisko = " + sNazwisko.get() +
            ", data urodzenia = " + dataUr.get() + " ]";
    }
}
