/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * Klasa kontrolera modelu MVC pomiędzy modelem Film a widokiem DodajFilmView
 */
public class FilmPresenter {

    Film model;
    final DodajFilmView view;
    public FilmPresenter(DodajFilmView view) {
        this.view = view;
        model = new Film();
        attachEvents();
        bindFieldsToModel();
    }
         /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        private void bindFieldsToModel() {
                view.txtFilmId.textProperty().bind(model.idProperty().asString());
        }
        /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        public void reBindFieldsToModel(Film _model) {
                
                view.txtFilmId.textProperty().unbind();
                this.model = _model;
                bindFieldsToModel();
        }      
    
    
        /**
         *  Wiąże zdarzenia widoku z modelem Widz
         */
        private void attachEvents() {
            // Zapisz dane do modelu
            view.saveBtn.setOnAction(e -> saveData());

        }

        /**
         * Zapisuje dane Filmu w modelu i do bazy do tabeli Film
         */
        private void saveData() {
                    
                try {
                    int rokProdukcji = Integer.parseInt(view.txtData.getText());
                    model.setRokProdukcji(rokProdukcji);
                } catch(NumberFormatException ne) {}

                model.setsRezyser(view.txtImie.getText() + " " + view.txtNazwisko.getText());
                model.setsTytul(view.txtTytul.getText());
                String input = view.txtCzasTrwania.getText();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime czas = LocalTime.parse(input, fmt);


                for(Aktor a : view.tabela.getItems() ) {
                    model.addActor(a);
                }
                model.setCzasTrwania(czas);
                view.cleanAllFields();
                //TODO:
                //zapisz model w bazie
                //i powołaj nowy model
                reBindFieldsToModel(new Film());
        }
    
}
