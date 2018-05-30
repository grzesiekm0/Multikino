/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.xml.ws.Holder;
import multikino.Widz.Rola;
import multikino.helper.ActorTableUtil;
import multikino.helper.FilmTableUtil;
import multikino.helper.SeansFilmowyTableUtil;
import multikino.helper.WidzUtil;

/**
 * Główna klasa aplikacji. Inicjuje podstawowe klasy widoku i predzentacji oraz silnik bazodanowy
 * 
 */
public class Multikino extends Application {
        private Text helpText = new Text();
        //Holder<Widz> widzHolder = new Holder<>();
    private final ObjectProperty<Holder<Widz>> widzHolder = new SimpleObjectProperty<>();

    public Holder getWidzHolder() {
        return widzHolder.get();
    }

    public void setWidzHolder(Holder value) {
        widzHolder.set(value);
    }

    public ObjectProperty widzHolderProperty() {
        return widzHolder;
    }
        
        @Override
        public void start(Stage stage) {
            
/*
                TextField fName = new TextField();
                TextField lName = new TextField();

                Button closeBtn = new Button("Close");
                closeBtn.setOnAction(e -> Platform.exit());

                fName.getProperties().put("toolTipText", "Podaj imie");
                lName.getProperties().put("toolTipText", "Podaj nazwisko");

                helpText.setManaged(false);
                helpText.setTextOrigin(VPos.TOP);
                helpText.setFill(Color.DARKGREEN);
                helpText.setFont(Font.font(null, 10));
                helpText.setMouseTransparent(true);

                GridPane root = new GridPane();

                root.setId("gridPane");
                Label labName = new Label("imie:");
                root.add(labName, 1, 1);
                root.add(fName, 2, 1);
                root.setRowSpan(labName, 3);
                root.add(new Label("nazwisko:"), 1, 3);
                root.add(lName, 2, 3);


                root.add(helpText, 4, 4);

                root.add(testBtn, 2, 5);
                root.add(closeBtn, 2, 6);
                closeBtn.setId("closeBtn");
                labName.setOnMouseClicked(mouseEventHandler);
                fName.setOnKeyTyped( e -> handleNumbers(e));

*/

            //* * * * * * INIT * * * * * * *
            //1. Wczytaj dane z bazy
            //2. aktualizuj id autoinkrementacji
            
            for(Widz w : WidzUtil.getWidzList()) {
                if(Widz.ID < w.getWidzId())
                    Widz.ID = w.getWidzId()+1;
            }
            for(Film f : FilmTableUtil.getFilmList()) {
                if(Film.ID < f.getId())
                    Film.ID = f.getId()+1;
            }
            for(Aktor a : ActorTableUtil.getActorList() ) {
                if(Aktor.ID < a.getId())
                    Aktor.ID = a.getId()+1;
            }
            for(SeansFilmowy s : SeansFilmowyTableUtil.getSeansList() ) {
                if(SeansFilmowy.ID < s.getSeansId())
                    SeansFilmowy.ID = s.getSeansId()+1;
            }
            //* * * * * * * * * * * * * * * * 
            setWidzHolder(new Holder<>());
            TabPane tabPane = new TabPane();
            //wyłączamy możliwość zamykania zakładek
            tabPane.tabClosingPolicyProperty().set(TabPane.TabClosingPolicy.UNAVAILABLE);
            RejestracjaTab rejestracjaTab = new RejestracjaTab();
            LogowanieTab logowanieTab = new LogowanieTab();
            RepertuarTab repertuarTab = new RepertuarTab(widzHolder.get());
            DodajFilmTab dodajFilmTab = new DodajFilmTab();
            DodajSeansTab dodajSeansTab = new DodajSeansTab();



            tabPane.getTabs().addAll(logowanieTab, rejestracjaTab);
            
            


            BorderPane root1 = new BorderPane();
            root1.setCenter(tabPane);
            root1.setStyle("-fx-padding: 10;" +
                          "-fx-border-style: solid inside;" +
                          "-fx-border-width: 2;" +
                          "-fx-border-insets: 5;" +
                          "-fx-border-radius: 5;" +
                          "-fx-border-color: blue;");

            // dodaje tooltip gdy fokus pada na dane pole
            Scene scene = new Scene(root1, 900, 600);
//                 scene.focusOwnerProperty().addListener(
//                        (ObservableValue<? extends Node> value, Node oldNode, Node newNode)
//                                -> focusChanged(value, oldNode, newNode));
            scene.getStylesheets().add("res/style.css");

            stage.setScene(scene);
            stage.setTitle("Multikino");
            stage.show();
            RejestracjaPresenter wp = new RejestracjaPresenter(rejestracjaTab.model, rejestracjaTab.view, getWidzHolder());
            FilmPresenter fp = new FilmPresenter(dodajFilmTab.view, getWidzHolder());
            RepertuarPresenter rp = new RepertuarPresenter(repertuarTab.view, getWidzHolder());
            SeansPresenter sp = new SeansPresenter(dodajSeansTab.view, getWidzHolder());
            LogowaniePresenter lp = new LogowaniePresenter(logowanieTab.view, getWidzHolder());
            
            lp.modelProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if(widzHolder.get().value != null && widzHolder.get().value.getRola() == Rola.WIDZ) {
                        stage.setTitle("Witaj " + widzHolder.get().value.getFirstName());
                        tabPane.getTabs().clear();
                        tabPane.getTabs().addAll(logowanieTab, rejestracjaTab, repertuarTab);
                    }
                    else {
                        stage.setTitle("Witaj pracowniku " + widzHolder.get().value.getFirstName());
                        tabPane.getTabs().clear();
                        tabPane.getTabs().addAll(logowanieTab, rejestracjaTab, dodajSeansTab, dodajFilmTab, repertuarTab);
                    }
                }
            });
        }                    
        
        public void handle(KeyEvent e) {
            // Consume event jeśli nie litera
            String str = e.getCharacter();
            int len = str.length();
            for(int i = 0; i < len; i++) {
                    Character c = str.charAt(i);
                    if (!Character.isLetter(c)) {
                            e.consume();
                    }
            }
        }
        public void handleNumbers(KeyEvent e) {
            // Consume event jeśli nie cyfra
            String str = e.getCharacter();
            int len = str.length();
            for(int i = 0; i < len; i++) {
                    Character c = str.charAt(i);
                    if (!Character.isDigit(c)) {
                            e.consume();
                    }
            }                
        }

        public void focusChanged(ObservableValue<? extends Node> value,
           Node oldNode, Node newNode) {
            // Focus has changed to a new node
            String toolTipText = (String)newNode.getProperties().get("toolTipText");

            if (toolTipText != null && toolTipText.trim().length() > 0)  {
                    helpText.setText(toolTipText);
                    helpText.setVisible(true);

                    // Position the help text node
                    double x = newNode.getLayoutX() + newNode.getLayoutBounds().getWidth()+10;
 //                                   newNode.getLayoutBounds().getMinX() -
 //                                 helpText.getLayoutBounds().getMinX();
                    double y = newNode.getLayoutY();// +
 //                                   newNode.getLayoutBounds().getMinY() +
 //                                  newNode.getLayoutBounds().getHeight() -
 //                                  helpText.getLayoutBounds().getMinX();

                    helpText.setLayoutX(x);
                    helpText.setLayoutY(y);
                    helpText.setWrappingWidth(newNode.getLayoutBounds().getWidth());
            }
            else {
                    helpText.setVisible(false);
            }
        }              

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}

