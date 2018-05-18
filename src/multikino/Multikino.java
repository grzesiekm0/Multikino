/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.ws.Holder;
import multikino.Widz.Rola;

/**
 *
 * @author 
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

            setWidzHolder(new Holder<>());
            TabPane tabPane = new TabPane();
            //wyłączamy możliwość zamykania zakładek
            tabPane.tabClosingPolicyProperty().set(TabPane.TabClosingPolicy.UNAVAILABLE);
            RejestracjaTab rejestracjaTab = new RejestracjaTab();
            LogowanieTab logowanieTab = new LogowanieTab();
            RepertuarTab repertuarTab = new RepertuarTab();
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
                    if(widzHolder.get().value != null && widzHolder.get().value.getRola() == Rola.WIDZ)
                        tabPane.getTabs().addAll( repertuarTab);
                    else
                        tabPane.getTabs().addAll(dodajSeansTab, dodajFilmTab, repertuarTab);
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

class ShowAndWaitApp  {
       protected static int counter = 0;
       protected Stage lastOpenStage;

       public static void strt() {
        Application.launch();
    }
        
       
       public void start() {
    
       }
        
       public void open(int stageNumber) {
               Stage stage = new Stage();
               stage.setTitle("#" + stageNumber);
 
               Button sayHelloButton = new Button("Say Hello");
               sayHelloButton.setOnAction(
                      e -> { System.out.println("Hello from #" + stageNumber);
                            showAlert();
                      });
 
               Button openButton = new Button("Open");
               openButton.setOnAction(e -> open(++counter));
                
               VBox root = new VBox();
               root.getChildren().addAll(sayHelloButton, openButton);
               Scene scene = new Scene(root, 200, 200);
               stage.setScene(scene);
               stage.setX(50);
               stage.setY(50);
               this.lastOpenStage = stage;
               
               System.out.println("Before stage.showAndWait(): " + stageNumber);
                
               // Show the stage and wait for it to close
               stage.showAndWait();
                
               System.out.println("After stage.showAndWait(): " + stageNumber);
       }
       
       
       protected void showAlert() {
            Stage s = new Stage(StageStyle.UTILITY);
            //s.initModality(Modality.WINDOW_MODAL);

            Label msgLabel = new Label("This is an FX alert!");
            Group root = new Group(msgLabel);
            Scene scene = new Scene(root);
            s.setScene(scene);

            s.setTitle("FX Alert");
            s.show();
        }
}