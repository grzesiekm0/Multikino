/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.util.function.UnaryOperator;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import multikino.helper.ActorTableUtil;

/**
 * Klasa reprezentująca widok zakładki dodaj Film tylko dla personelu
 */
public class DodajFilmView extends GridPane {
        
        // Labels
        Label filmIdLabel = new Label("Id filmu:");
        Label tytulLabel = new Label("Tytuł filmu:");
        Label czasLabel = new Label("Czas trwania:");
        Label imieRLabel = new Label("Imię reżysera:");
        Label nazwiskoRLabel = new Label("Nazwisko reżysera:");
        Label imieALabel = new Label("Imię aktora:");
        Label nazwiskoALabel = new Label("Nazwisko aktora:");
        Label rokLabel = new Label("Rok produkcji:");
        Label katWiekowaLabel = new Label("Kategoria wiekowa:");

 
        // pola tekstowe
        TextField txtFilmId = new TextField();
        TextField txtImie = new TextField();
        TextField txtNazwisko = new TextField();
        TextField txtAImie = new TextField();
        TextField txtANazwisko = new TextField();
        TextField txtData = new TextField();
        TextField txtTytul = new TextField();
        TextField txtCzasTrwania = new TextField("01:30");
        TextField txtKatWiek = new TextField();
        
        ComboBox<String> cbPegi = new ComboBox<>();
        ComboBox<String> cbAktors = new ComboBox<>();
                        
        // Buttons
        Button saveBtn = new Button("Dodaj Film");
        Button cleanBtn = new Button("Wyczyść");
        Button dodajAktoraBtn = new Button("+");
        Button dodajAktoraBtn1 = new Button("+");
        
        TableView<Aktor> tabela = new TableView<>();
    
        
        public DodajFilmView() {
                setId("add_film_view");

                
                this.setMinWidth(500);
                layoutForm();
        }
 
 
        /**
         *  Buduje widok rejestracji , umieszcza w nim kontrolki
         */
        private void layoutForm() {
                this.setHgap(10);
                this.setVgap(10);
                for (Film.PEGI peg : Film.PEGI.values()) {
                    cbPegi.getItems().add(peg.toString());
                }
                cbPegi.getSelectionModel().select(3);
 
                this.add(filmIdLabel, 1, 1);
                this.add(tytulLabel, 1, 2);
                this.add(imieRLabel, 1, 3);
                this.add(nazwiskoRLabel, 1, 4);
                this.add(czasLabel, 1, 5);
                this.add(rokLabel, 1, 6);
                
                this.add(katWiekowaLabel, 1, 7);
 
                this.add(txtFilmId, 2, 1);
                this.add(txtTytul, 2, 2);
                this.add(txtImie, 2, 3);
                this.add(txtNazwisko, 2, 4);
                this.add(txtCzasTrwania, 2, 5);
                this.add(txtData, 2, 6);
                this.add(cbPegi, 2, 7);

                //filtr daty 1000 - 2999
                UnaryOperator<TextFormatter.Change> filter = change -> {
                    boolean bFail = false;
                    String text = change.getText();
                    
                    String fullText = txtData.getText();
                    if(change.isDeleted())
                        return change;
                    if(fullText.length()>3)
                        return null;
                     
                    if (fullText.length() == 0) {
                        if(!text.matches("^[1-2]")) 
                            return null;
                    }
                    else
                        if(!text.matches("[0-9]")) 
                            return null;
                    return change;
                };

                //filtr czasu 00:00 - 99:99
                UnaryOperator<TextFormatter.Change> filterTime = change -> {
                    boolean bFail = false;
                    String text = change.getText();
                    
                    String fullText = txtCzasTrwania.getText();
                    if(change.isDeleted() || text.length() == 0)
                        return change;
                    if(fullText.length()>4 && text.length() > 0)
                        return null;
                     
                    if (fullText.length() == 2) {
                        if(!text.matches(":")) 
                            return null;
                    }
                    else
                        if(!text.matches("[0-9]")) 
                            return null;
                    return change;
                };
 

                TextFormatter<String> textFormatter = new TextFormatter<>(filter);

                
                txtData.setTextFormatter(textFormatter);
                
                
                txtCzasTrwania.setTextFormatter(new TextFormatter<>(filterTime));
                
                //VBox buttonBox = new VBox(saveBtn, cleanBtn);
                HBox buttonBox = new HBox(15, cleanBtn, saveBtn);
                saveBtn.setMaxWidth(Double.MAX_VALUE);
                

                cleanBtn.setMaxWidth(Double.MAX_VALUE);
                cleanBtn.setOnAction( e -> {
                    cleanAllFields();
                });
                
                this.add(buttonBox, 2,9,3,1);
                
                        
                txtFilmId.setDisable(true);
                txtKatWiek.setDisable(true);
 
                txtTytul.setPromptText("wprowadz tytuł filmu");
                txtImie.setPromptText("wprowadz imię reżysera");
                txtNazwisko.setPromptText("wprowadz nazwisko reżysera");

                txtAImie.setPromptText("wprowadz imię aktora");
                txtANazwisko.setPromptText("wprowadz nazwisko aktora");
                
                txtData.setPromptText("rok produkcji filmu");
                
                txtCzasTrwania.setPromptText("00:00");
                
                GridPane paneActor = new GridPane();
                
                for(Aktor a : ActorTableUtil.getActorList().sorted()) {
                    cbAktors.getItems().add(a.toString());
                }
                paneActor.setId("actor_panel");
                paneActor.add(new Label("Wybierz aktora:"), 0, 0);
                paneActor.add(cbAktors, 1, 0);
                paneActor.add(dodajAktoraBtn1, 2, 0);
                dodajAktoraBtn1.setOnAction(e -> {
                
                    int indx = cbAktors.getSelectionModel().getSelectedIndex();
                    if(indx < 0) {
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Błąd dodawania aktora.");
                        alert.setHeaderText("Wybierz z listy rozwijanej jakąś pozycję.");
                        alert.setContentText("Jeśli nie ma w liście żadnego aktora musisz dodać jedengo ręcznie.");
                        alert.showAndWait();        
                        return;
                    }
                    Aktor a = ActorTableUtil.getActorList().sorted().get(indx);
                    tabela.getItems().add(a);
                    
                });
                paneActor.add(new Label("Imię aktora:"), 0, 1);
                paneActor.add(txtAImie, 1, 1);
                paneActor.setHgap(10);
                paneActor.setVgap(10);
                paneActor.add(new Label("Nazwisko aktora:"), 0, 2);
                paneActor.add(txtANazwisko, 1, 2);
                paneActor.add(dodajAktoraBtn, 2, 2);
                dodajAktoraBtn.setOnAction(e -> {
                    if(txtAImie.getText().trim().length() == 0 || txtANazwisko.getText().trim().length() == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Błąd danych aktora.");
                        alert.setHeaderText("Nie wystarczające dane dla dodania Aktora do Filmu.");
                        alert.setContentText("Imię i nazwisko muszą zawierać przynajmniej jedną literę.");
                        alert.showAndWait();        
                        return;
                    }
                    Aktor aktor = new Aktor();
                    aktor.setsImie(txtAImie.getText());
                    aktor.setsNazwisko(txtANazwisko.getText());
                    ActorTableUtil.getActorList().add(aktor);

                    cbAktors.getItems().add(aktor.toString());
                    cbAktors.getItems().sorted();
                    
                    
                    tabela.getItems().add(aktor);
                    
                });
                
                tabela.getColumns().addAll(
                        ActorTableUtil.getIdColumn(),
                        ActorTableUtil.getImieColumn(),
                        ActorTableUtil.getNazwiskoColumn());

                tabela.setPlaceholder(new Label("Brak aktorów."));
                tabela.setMaxHeight(200);
                
                paneActor.add(tabela,0,3,3,1);
                tabela.positionInArea(paneActor, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, Insets.EMPTY, HPos.CENTER, VPos.CENTER, true);                
                this.add(paneActor, 4, 1,2,9);
        }
        void cleanAllFields() {
            txtTytul.setText("");
//            txtCena.setText("");
            txtImie.setText("");
            txtNazwisko.setText("");
            txtAImie.setText("");
            txtANazwisko.setText("");
//            txtCzasTrwania.textProperty().unbind();
//            txtCzasTrwania.setText("01:30");
//            txtFilmId.setText("");
            txtData.setText("");
            for ( int i = 0; i<tabela.getItems().size(); i++) {
                tabela.getItems().clear();
            }
        }
  
}
