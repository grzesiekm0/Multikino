/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import javafx.beans.binding.When;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TitledPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.cell.PropertyValueFactory;
import multikino.helper.BuyTicketButtonTableCell;
import multikino.helper.FilmTableUtil;

/**
 *
 * @author 
 */
public class RepertuarView extends VBox {

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
                TableView<Film> tabela = new TableView<>(FilmTableUtil.getFilmList());
                TableColumn<Film, String> filmCol = new TableColumn<>("Film");
                filmCol.getColumns().addAll(FilmTableUtil.getTitleColumn(), 
                        FilmTableUtil.getDirectorColumn(),
                        FilmTableUtil.getActorsColumn(),
                        FilmTableUtil.getYearColumn(),
                        FilmTableUtil.getPEGIColumn());
                
                
                TableColumn actionCol = new TableColumn("Zakup");
                actionCol.setCellValueFactory(new PropertyValueFactory<>(""));
                actionCol.setCellFactory(BuyTicketButtonTableCell.<Film>forTableColumn("Kup bilet", (Film f) -> {
                    //tabela.getItems().remove(f);
                    
                    return f;
                }));     

                tabela.getColumns().addAll(FilmTableUtil.getIdColumn(),
                        filmCol, actionCol );

                tabela.setPlaceholder(new Label("Brak filmów."));
                tabela.placeholderProperty().bind(
                    new When(new SimpleIntegerProperty(0)
                                 .isEqualTo(tabela.getVisibleLeafColumns().size()))
                            .then(new When(new SimpleIntegerProperty(0)
                                              .isEqualTo(tabela.getItems().size()))
                                      .then(new Label("No columns and data exist."))
                                      .otherwise(new Label("No columns exist.")))
                            .otherwise(new When(new SimpleIntegerProperty(0)
                                           .isEqualTo(tabela.getItems().size()))
                                           .then(new Label("Brak filmów."))
                                           .otherwise((Label)null))
                );
                
                pane.getChildren().add(tabela);
                tabela.positionInArea(pane, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, Insets.EMPTY, HPos.CENTER, VPos.CENTER, true);
                TitledPane phonePane = new TitledPane("Aktualnie grane", pane);
                return phonePane;
        }

}
