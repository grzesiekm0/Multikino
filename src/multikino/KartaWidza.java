/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

/**
 * Karta pozwala na zniżki i jednorazowy w dniu wstep za darmo
 */
public class KartaWidza {
    public static int ID = 1;
    
    Widz widz;
    
    
    enum Okres {
        DNI30(30),
        DNI90(90),
        DNI180(180);
        int dni;
        Okres(int dni) {
            this.dni = dni;
        }
        public int getVal() {return dni;}
    }
    
    public Okres okres;
    private final LocalDate expireDate;
    private final IntegerProperty id = new ReadOnlyIntegerWrapper();

    LocalDate ldStart;

    public LocalDate getLdStart() {
        return ldStart;
        
    }

    public void setLdStart(LocalDate ldStart) {
        this.ldStart = ldStart;
    }
    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    
    public KartaWidza(Okres okres, LocalDate ld) {
        id.set(ID++);
        this.okres = okres;
        this.ldStart = ld;
        this.expireDate = ld.plusDays(okres.dni);
    }
    
}
