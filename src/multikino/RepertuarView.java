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
import multikino.helper.SeansFilmowyTableUtil;

/**
 *
 * Klasa widoku dla zakładki Repertuar
 */
public class RepertuarView extends VBox {

    TableColumn actionCol = new TableColumn("Bilety");
    public RepertuarView() {
        layoutForm();
    }

    private void layoutForm() {
        
        TitledPane searchPane = getSearchPane();
        TitledPane repertuarPane = getRepertuarPane();
        Accordion root = new Accordion();
        root.getPanes().addAll(searchPane, repertuarPane);
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
                TableView<SeansFilmowy> tabela = new TableView<>(SeansFilmowyTableUtil.getFilmList());
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

}
