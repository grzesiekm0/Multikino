/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TitledPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.xml.ws.Holder;
import multikino.helper.BiletTableUtil;
import multikino.helper.SeansFilmowyTableUtil;

/**
 *
 * Klasa widoku dla zakładki Repertuar
 */
public class RepertuarView extends VBox {

    TableColumn actionCol = new TableColumn("Bilety");
    TableView<SeansFilmowy> tabela = new TableView<>(SeansFilmowyTableUtil.getSeansList());
    private final Holder<Widz> widzHolder;
    
    public RepertuarView( Holder<Widz> widzHolder) {
        this.widzHolder = widzHolder;
        layoutForm();
    }

    private void layoutForm() {
        
        TitledPane searchPane = getSearchPane();
        TitledPane repertuarPane = getRepertuarPane();
        TitledPane biletyPane = getBiletyPane();
        Accordion root = new Accordion();
        root.getPanes().addAll(searchPane, repertuarPane, biletyPane);
        this.getChildren().add(root);
        root.setExpandedPane(repertuarPane);
    }

    public TitledPane getSearchPane() {
                GridPane grid = new GridPane();
                TextField txtTytul = new TextField();
                TextField txtAktor = new TextField();
                TextField txtDirector = new TextField();
                TextField txtRok = new TextField();
                grid.addRow(0, new Label("Tytuł:"), txtTytul);
                grid.addRow(1, new Label("Aktor:"), txtAktor);
                grid.addRow(2, new Label("Reżyser:"), txtDirector);
                grid.addRow(3, new Label("Rok Produkcji:"), txtRok);
                Button btSearch = new Button("Szukaj");
                grid.addRow(4, new Group(), btSearch);
                //wyrównaj przycisk do prawej
                GridPane.setHalignment(btSearch, HPos.RIGHT);
                btSearch.setOnAction(e -> {
                    //if(txtTytul.getText() != null)
                });
 
                TitledPane addressPane = new TitledPane("Szukaj", grid);
                return addressPane;
        }
         
        public TitledPane getRepertuarPane() {
                StackPane pane = new StackPane();
                
                TableColumn<SeansFilmowy, String> filmCol = new TableColumn<>("Film");
                filmCol.getColumns().addAll(SeansFilmowyTableUtil.getTitleColumn(), 
                        SeansFilmowyTableUtil.getCzasTrwaniaColumn(),
                        SeansFilmowyTableUtil.getDirectorColumn(),
                        SeansFilmowyTableUtil.getActorsColumn(),
                        SeansFilmowyTableUtil.getYearColumn(),
                        SeansFilmowyTableUtil.getPEGIColumn());
                
                
                
                actionCol.setCellValueFactory(new PropertyValueFactory<>(""));
                actionCol.setMinWidth(80);
                
                tabela.getColumns().addAll(SeansFilmowyTableUtil.getGodzinaColumn(),
                        SeansFilmowyTableUtil.getSalaColumn(),
                        SeansFilmowyTableUtil.getCenaColumn(),
                        filmCol, actionCol );

                tabela.setPlaceholder(new Label("Brak filmów."));
                
                
                pane.getChildren().add(tabela);
                tabela.positionInArea(pane, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, Insets.EMPTY, HPos.CENTER, VPos.CENTER, true);
                TitledPane repertuarPane = new TitledPane("Aktualnie grane", pane);
                return repertuarPane;
        }
        
       public TitledPane getBiletyPane() {
                StackPane pane = new StackPane();
                TableView<Bilet> tabelaBilet = null;
                
                tabelaBilet = new TableView<>(BiletTableUtil.getBiletyList());
               
               TableColumn<Bilet, String> filmCol = new TableColumn<>("Film");
                filmCol.getColumns().addAll(
                        BiletTableUtil.getTitleColumn(), 
                        BiletTableUtil.getCzasTrwaniaColumn(),
                        BiletTableUtil.getPEGIColumn());
 
                TableColumn<Bilet, String> salaCol = new TableColumn<>("Sala");
                salaCol.getColumns().addAll(
                        BiletTableUtil.getSalaColumn(),
                        BiletTableUtil.getSeatNrColumn());
                
                
                
                
                tabelaBilet.getColumns().addAll(
                    BiletTableUtil.getGodzinaColumn(),
                    salaCol,
                    filmCol,
                    BiletTableUtil.getCenaColumn(),
                    BiletTableUtil.getDiscColumn()
                );
//                        SeansFilmowyTableUtil.getGodzinaColumn(),
//                        SeansFilmowyTableUtil.getSalaColumn(),
//                        SeansFilmowyTableUtil.getCenaColumn(),
//                        filmCol);

                tabelaBilet.setPlaceholder(new Label("Brak biletów."));
                
                
                pane.getChildren().add(tabelaBilet);
                tabelaBilet.positionInArea(pane, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, Insets.EMPTY, HPos.CENTER, VPos.CENTER, true);

                TitledPane repertuarPane = new TitledPane("Twoje bilety", pane);
                return repertuarPane;
        }
}
