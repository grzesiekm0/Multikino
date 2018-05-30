/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import javafx.util.StringConverter;
import javax.xml.ws.Holder;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.IntHolder;
import multikino.helper.BuyTicketButtonTableCell;
import multikino.helper.SeansFilmowyTableUtil;
import multikino.helper.WidzUtil;
import org.omg.CORBA.FloatHolder;

/**
 *
 * Klasa presentera pomiędzy widokiem a modelem wzorca MPV
 */
public class RepertuarPresenter {
    
    final RepertuarView view;
    Holder<Widz> widzHolder = null;
    public RepertuarPresenter(RepertuarView view, Holder<Widz> widzHolder) {
    
        this.view = view;
        this.widzHolder = widzHolder;
        attachEvents();
        bindFieldsToModel();
    }
    private final IntegerProperty ticketNo = new SimpleIntegerProperty();
    private final FloatProperty multi = new SimpleFloatProperty();
    private final FloatProperty totalPrice = new SimpleFloatProperty();
    private final FloatProperty cena = new SimpleFloatProperty();
    private CheckBox chkKartaWidza = new CheckBox("użyj Karty Widza");
    private NumberBinding op;
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
                //wiąże zawartość repertuaru ze zmianami w liście
               SeansFilmowyTableUtil.getSeansList().addListener(new ListChangeListener<SeansFilmowy>(){
                    @Override
                    public void onChanged(ListChangeListener.Change<? extends SeansFilmowy> c) {
                       while (c.next()) 
                            if (c.wasAdded()) 
                                for(SeansFilmowy sf : c.getAddedSubList()) {
                                    //TODO: dodaj do bazy danych
                                }
                        view.tabela.setItems(SeansFilmowyTableUtil.getSeansList());
                    }
                });
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
            
            
            
            
            
            //totalPrice.multiply(multi);
        }
        
            
        void buyTicket() {
            view.actionCol.setCellFactory(BuyTicketButtonTableCell.<SeansFilmowy>forTableColumn("Kup bilet", (SeansFilmowy sf) -> {
                view.actionCol.setId("btnKup");
                    
                StringConverter<Float> sc = new StringConverter<Float>() {
                        @Override
                        public String toString(Float object) {
                            if (object != null)
                            {
                                String str = new DecimalFormat("#.00").format(object.floatValue());
                                return str;
                            }
                                
                            else
                                return null;
                        }

                        @Override
                        public Float fromString(String string) {
                            Float d = Float.parseFloat(string);
                            totalPrice.setValue( ((float)Math.round(d)));
                            return d;
                        }
                    };
            
                List<String> errorList = new ArrayList<String>(10);
                       
                    totalPrice.unbind();
                    
                    cena.set(sf.getPrice());
                   
                    op = cena.multiply(ticketNo.multiply(multi));
                    NumberBinding nb = op.multiply(sf.getSala().typ.val());
                   
                    totalPrice.bind( nb.add(sf.getSala().okulary3DProperty().multiply(ticketNo)) );
                   
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
                        
                        txtTotalPrice.textProperty().bindBidirectional(totalPrice.asObject(),sc);
                        
                        Button btBuy = new Button("Kup");
                        btBuy.setOnAction( e->{
                            try {
                                int ticketCount = Integer.parseInt(txtTicketNo.getText());
                                if(widzHolder.value.checkAge((Film.PEGI) sf.pegiProperty().get()))
                                {
                                    WidzUtil.showErrorDialog("Twój wiek nie pozwala na oglądanie tego filmu (:", 
                                            "Zakupy wycofane", 
                                            "Niestety film ma kategorię wiekową: "+  sf.pegiProperty().get().toString());
                                    Window    wnd = dlg.getDialogPane().getScene().getWindow();
                                    wnd.hide();
                                    return;
                                }
                                    
                                synchronized(sf.getSala()) {
                                    
                                    if( sf.getSala().getFreeSeats() >= ticketCount) {
                                        kupBilety(sf, ticketNo.get(), totalPrice.get(), cbDiscounts.getSelectionModel().getSelectedIndex());
                                    }
                                    else {
                                        WidzUtil.showErrorDialog("Nie ma już tylu wolnych miejsc.", "Zakupy wycofane", "liczba dostepnych miejsc: " + sf.getSala().getFreeSeats());
                                    }
                                    Window    wnd = dlg.getDialogPane().getScene().getWindow();
                                    wnd.hide();
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
                        //jeszcze 1 warunek KW nie używane w tym dniu
                        if(widzHolder.value != null && widzHolder.value.getKw() != null)
                            content.add(chkKartaWidza, 0, 3);
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

    private void kupBilety(SeansFilmowy sf, int ticketNo, float sum, int selectedIndex) {
        List<Bilet> listaFree = null, lista = null;
        if(ticketNo <= 0 ) {
            WidzUtil.showErrorDialog("Trzeba zamówić choć 1 bilet.", "Zakupy wycofane", "Pole liczba biletów musi zawierac przynajmniej 1 bilet.");
            return;
        }
            
        if(chkKartaWidza.isSelected()) {
                sum -= sum/ticketNo;
                ticketNo--;
                listaFree = sf.getSala().reserveSeats(widzHolder.value, sf, 1, 0.0f, selectedIndex);
        }        
        if(ticketNo > 0) {
            lista = sf.getSala().reserveSeats(widzHolder.value, sf, ticketNo, sum, selectedIndex);
        }
        if(listaFree != null) {
            if(lista == null) 
                lista = listaFree;
            else {      //dodaj darmówkę
                lista.addAll(listaFree);
            }
        }
        widzHolder.value.addTickets(lista);
       
    }

}
