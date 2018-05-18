/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.util.LinkedList;
import java.util.List;
import javax.xml.ws.Holder;

/**
 *
 * Model Danych jest klasą szablonową(abstrackyjną), która implementuje metody dla konkretnych realizacji tej klasy (bazy danych, pliku)
 */
public class ModelDanych {
    private Holder<Widz> widzHolder;
    public ModelDanych(Holder<Widz> widzHolder) {
        this.widzHolder = widzHolder;
    }

    public List<Bilet> kupBilety(SeansFilmowy sf, int ticketNo, float sum, int discIndex) {

        return sf.getSala().reserveSeats(widzHolder.value, sf, ticketNo, sum, discIndex);
    }
}
