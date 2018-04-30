/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.IntHolder;
import multikino.helper.BuyTicketButtonTableCell;
import org.omg.CORBA.FloatHolder;

/**
 *
 * Klasa presentera pomiędzy widokiem a modelem wzorca MPV
 */
public class RepertuarPresenter {
    
    final RepertuarView view;
    
    public RepertuarPresenter(RepertuarView view) {
    
        this.view = view;
    
        attachEvents();
        bindFieldsToModel();
    }
    private final IntegerProperty ticketNo = new SimpleIntegerProperty();
    private final FloatProperty multi = new SimpleFloatProperty();
    private final FloatProperty totalPrice = new SimpleFloatProperty();
    private final FloatProperty cena = new SimpleFloatProperty();

    public float getCena() {
        return cena.get();
    }

    public void setCena(float value) {
        cena.set(value);
    }

    public FloatProperty cenaProperty() {
        return cena;
    }

    public float getTotalPrice() {
        return totalPrice.get();
    }

    public void setTotalPrice(float value) {
        totalPrice.set(value);
    }

    public FloatProperty totalPriceProperty() {
        return totalPrice;
    }

    public float getMulti() {
        return multi.get();
    }

    public void setMulti(float value) {
        multi.set(value);
    }

    public FloatProperty multiProperty() {
        return multi;
    }

    
    public int getTicketNo() {
        return ticketNo.get();
    }

    public void setTicketNo(int value) {
        ticketNo.set(value);
    }

    public IntegerProperty ticketNoProperty() {
        return ticketNo;
    }
         /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        private void bindFieldsToModel() {
            //view.txtFilmId.textProperty().bind(model.idProperty().asString());
        }
        /**
         * Wypełnia pola kontrolek domyślnymi danymi modelowymi
         * Wiąże pola w obu kierunkach
         */
        public void reBindFieldsToModel(SeansFilmowy _model) {
                
                //view.txtFilmId.textProperty().unbind();
                
                bindFieldsToModel();
        }      
    
    
        /**
         *  Wiąże zdarzenia widoku z modelem Widz
         */
        private void attachEvents() {
            // Zapisz dane do modelu
            buyTicket();
            totalPrice.set(10.0f);
            multi.set(1.0f);
            ticketNo.set(1);
            cena.set(10.0f);
            NumberBinding op = cena.multiply(ticketNo.multiply(multi));
            totalPrice.bind( op);
            
            //totalPrice.multiply(multi);
        }
        void buyTicket() {
            view.actionCol.setCellFactory(BuyTicketButtonTableCell.<SeansFilmowy>forTableColumn("Kup bilet", (SeansFilmowy sf) -> {
                view.actionCol.setId("btnKup");
                
                List<String> errorList = new ArrayList<String>(10);
                   cena.set(sf.getPrice());
                    if(!sf.buyTicketTest(errorList))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Błąd zakupu biletu");
                        alert.setHeaderText("Nie ma już możliwości zakupu biletu.");
                        String msg = "";
                        for(String s : errorList)
                            msg += s+"\n";
                        alert.setContentText(msg);
                        alert.showAndWait().ifPresent(rs -> {
                            if (rs == ButtonType.OK) {
                                System.out.println("OK.");
                            }
                        });
                    }
                    else {
                        ticketNo.set(1);
                        Dialog dlg = new Dialog();
                        dlg.setTitle("Login Dialog");
                        Window    window = dlg.getDialogPane().getScene().getWindow();
                        window.setOnCloseRequest(event -> window.hide());

                        final GridPane content = new GridPane();
                        content.setMinSize(400, 200);
                        content.setHgap(10);
                        content.setVgap(10);
                        final TextField txtTicketNo = new TextField();
                        final TextField txtTotalPrice = new TextField();
                        txtTicketNo.setText(ticketNo.get()+"");
                    //filtr liczby 1 - 99
                    UnaryOperator<TextFormatter.Change> filter = change -> {
                        boolean bFail = false;
                        String text = change.getText();

                        String fullText = txtTicketNo.getText();
                        if(change.isDeleted() || text.length() == 0)
                            return change;
                        if(fullText.length()>1)
                            return null;

                        if (fullText.length() == 0) {
                            if(!text.matches("^[1-9]")) 
                                return null;
                        }
                        else
                            if(!text.matches("[0-9]")) 
                                return null;
                        return change;
                    };


                        TextFormatter<String> textFormatter = new TextFormatter<>(filter);

                
                        txtTicketNo.setTextFormatter(textFormatter);
   

                        GridPane.setHgrow(new TextField(), Priority.ALWAYS);
                        final ComboBox<String> cbDiscounts = new ComboBox<>();
                        
                        for (Discount disc : Discount.values()) {
                            cbDiscounts.getItems().add(disc.toString());
                        }
                        cbDiscounts.getSelectionModel().select(0);
                        multi.set(1.0f);
                        cbDiscounts.setOnAction(e -> {
                            multi.set(getDiscountTicket(cbDiscounts));
                        });
                        txtTicketNo.textProperty().addListener((observable, oldValue, newValue) -> {
                            ticketNo.set( Integer.parseInt(newValue) );
                        });//textProperty().bindBidirectional(ticketNo.asString());
                        
                        txtTotalPrice.textProperty().bind(totalPrice.asString());
                        
                        Button btBuy = new Button("Kup");
                        btBuy.setOnAction( e->{
                            try {
                                int ticketCount = Integer.parseInt(txtTicketNo.getText());
                                synchronized(sf.getSala()) {
                                    if(ticketCount < 0 || sf.getSala().getFreeSeats() < ticketCount) {
                                        kupBilety(sf, ticketNo.get(), totalPrice.get(), cbDiscounts.getSelectionModel().getSelectedIndex());
                                        Window    wnd = dlg.getDialogPane().getScene().getWindow();
                                        wnd.hide();
                                    }
                                }//synchronized
                            }
                            catch(NumberFormatException ne) {
                                
                            }
                            
                        });
                        Button btCancel = new Button("Anuluj");
                        btCancel.setOnAction( e->{
                            Window    wnd = dlg.getDialogPane().getScene().getWindow();
                            wnd.hide();
                        });
                        content.add(new Label("Liczba biletów"), 0, 0);
                        content.add(txtTicketNo, 1, 0);
                        content.add(new Label("Rodzaj biletu"), 0, 1);
                        content.add(cbDiscounts, 1, 1);
                        content.add(new Label("Suma (zł)"), 0, 2);
                        content.add(txtTotalPrice, 1, 2);
                        
                        content.add(btBuy, 1, 3);
                        content.add(btCancel, 1, 4);
                        GridPane.setHalignment(btBuy, HPos.RIGHT);
                        GridPane.setHalignment(btCancel, HPos.RIGHT);
                        DialogPane dlgPane = new DialogPane();
                        dlgPane.setContent(content);
                        dlg.setDialogPane(dlgPane);
                        GridPane.setHgrow(new TextField(), Priority.ALWAYS);

                        dlg.setResizable(false);
                        //dlg.setIconifiable(false);

                        dlg.showAndWait();
                        
                    }
                       
                    return sf;
                }));
        }
 
    private float getDiscountTicket(ComboBox<String> cbDiscounts) {
        int disc = cbDiscounts.getSelectionModel().selectedIndexProperty().get();
        float multi = 1.0f;
        switch(disc) {
//                                case(0):
//                                    sRet = "bilet normalny";
//                                    break;
            case(1):
                //sRet = "Karta Dużej Rodziny";
                multi = 0.7f;
                break;
            case(2):
                //sRet = "zniżka dla seniora";
                multi = 0.7f;
                break;
            case(3):
                //sRet = "ulga szkolna";
                multi = 0.6f;
                break;
            case(4):
                //sRet = "ulga studencka";
                multi = 0.7f;
                break;
            case(5):
                //sRet = "ulga z tytułu niepełnosprawności";
                multi = 0.7f;
                break;
            case(6):
                //sRet = "ulga kombatancka";
                multi = 0.8f;
                break;
            case(7):
                //sRet = "ulga za honorowe krwiodawstwo";
                multi = 0.8f;
                break;
            case(8):
                //sRet = "ulga dla kobiet w ciąży";
                multi = 0.8f;
                break;

            default:
                //sRet = "bilet normalny";
                break;
        }
        return multi;
    }
    public int getTi9cketNo() {
        return ticketNo.get();
    }

    public void setTi9cketNo(int value) {
        ticketNo.set(value);
    }

    public IntegerProperty ti9cketNoProperty() {
        return ticketNo;
    }

    private void kupBilety(SeansFilmowy sf, int get, float get0, int selectedIndex) {
        //TODO: połącz z bazą i wykonaj zamówienie dla konkretnego widza
    }
}
