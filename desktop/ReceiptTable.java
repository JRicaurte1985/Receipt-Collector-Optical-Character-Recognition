package foggitops.desktop;

import foggitops.service.Receipt;
import foggitops.service.SQLite;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author Puchito
 */
public class ReceiptTable
{
    private final int SIZE = 4;
    private TableView<Receipt> mTableView;
    
    public ReceiptTable()
    {
        mTableView = new TableView<>();
        mTableView.setMinHeight(150);
        mTableView.setMinWidth(250);
        mTableView.setEditable(true);
            
        TableColumn DateCol = new TableColumn("Date");
        DateCol.setCellValueFactory(new PropertyValueFactory<Receipt, String>("Date"));
        DateCol.setCellFactory(TextFieldTableCell.<Receipt>forTableColumn());
        DateCol.setOnEditCommit(new EventHandler<CellEditEvent<Receipt, String>>() 
              {
                  @Override
                  public void handle(CellEditEvent<Receipt, String> t) {
                      ((Receipt) t.getTableView().getItems().get(
                          t.getTablePosition().getRow())
                          ).setDate(t.getNewValue());
                      SQLite.updateRow((Receipt) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                  }
              }
        );
        
        TableColumn VendorCol = new TableColumn("Vendor");
        VendorCol.setCellValueFactory(new PropertyValueFactory<Receipt, String>("Vendor"));
        VendorCol.setCellFactory(TextFieldTableCell.<Receipt>forTableColumn());
        VendorCol.setOnEditCommit(
              new EventHandler<CellEditEvent<Receipt, String>>() {
                  @Override
                  public void handle(CellEditEvent<Receipt, String> t) {
                      ((Receipt) t.getTableView().getItems().get(
                          t.getTablePosition().getRow())
                          ).setVendor(t.getNewValue());
                      SQLite.updateRow((Receipt) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                  }
              }
          );
        
        TableColumn PaymentMethodCol = new TableColumn("Payment Method");
        PaymentMethodCol.setCellValueFactory(new PropertyValueFactory<Receipt, String>("PaymentMethod"));
        PaymentMethodCol.setCellFactory(TextFieldTableCell.<Receipt>forTableColumn());
        PaymentMethodCol.setOnEditCommit(
              new EventHandler<CellEditEvent<Receipt, String>>() {
                  @Override
                  public void handle(CellEditEvent<Receipt, String> t) {
                      ((Receipt) t.getTableView().getItems().get(
                          t.getTablePosition().getRow())
                          ).setPaymentMethod(t.getNewValue());
                      SQLite.updateRow((Receipt) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                  }
              }
          );
        
        TableColumn AmountCol = new TableColumn("Amount");
        AmountCol.setCellValueFactory(new PropertyValueFactory<Receipt, String>("Amount"));
        AmountCol.setCellFactory(TextFieldTableCell.<Receipt>forTableColumn());
        AmountCol.setOnEditCommit(
              new EventHandler<CellEditEvent<Receipt, String>>() {
                  @Override
                  public void handle(CellEditEvent<Receipt, String> t) {
                      ((Receipt) t.getTableView().getItems().get(
                          t.getTablePosition().getRow())
                          ).setAmount(t.getNewValue());
                      SQLite.updateRow((Receipt) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                  }
              }
          );
        
        mTableView.getColumns().setAll(DateCol, VendorCol, PaymentMethodCol, AmountCol);
        mTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    public TableView get()
    {
        return mTableView;
    }
   
    public void setItems(ObservableList data)
    {
        mTableView.setItems(data);
    }
    
}
