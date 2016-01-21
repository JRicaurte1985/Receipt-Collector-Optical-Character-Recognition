package foggitops.desktop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Puchito
 */
public class ReceiptForm
{
    private final int SIZE = 4;
    private HBox mForm;
    private GridPane mGridPane;
    private Text[] mTextArray;
    private TextField[] mTextFieldArray;
    private Button mSave;
   
    public ReceiptForm()
    {
        mForm = new HBox();
        mForm.setPadding(new Insets(25, 25,25,25));
        mForm.setStyle("-fx-background-color: dimgrey;");
        mForm.setAlignment(Pos.CENTER);
        mForm.setMaxHeight(263);
        mForm.setMinHeight(263);
        
        mGridPane = new GridPane();
        mGridPane.setVgap(10);
        mGridPane.setHgap(10);
           
        mTextArray = new Text[SIZE];
        mTextArray[0] = new Text("Date");
        mTextArray[1] = new Text("Vendor");
        mTextArray[2] = new Text("Payment Type");
        mTextArray[3] = new Text("Amount");
          
        for (int i = 0; i < SIZE; i++)
        {
            mTextArray[i].setFont(Font.font("Arial", 23));
            mTextArray[i].setFill(Color.WHITE);
            mGridPane.add(mTextArray[i], 0, i + 1);
        }
     
        mTextFieldArray = new TextField[SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            mTextFieldArray[i] = new TextField();
            mTextFieldArray[i].setPrefHeight(30);
            mTextFieldArray[i].setPrefColumnCount(20);
            mGridPane.add(mTextFieldArray[i], 1, i + 1);
        }
        
        mSave = new Button("Save");
        mSave.setPrefSize(240, 40);
        mGridPane.add(mSave, 1, 5);
        
        mForm.getChildren().add(mGridPane);
    }
    
    public HBox get()
    {
        return mForm;
    }
    
    public Button getSaveBtn()
    {
        return mSave;
    }
    
    public String getDate()
    {
        return mTextFieldArray[0].getText();
    }
    
    public void setDate(String pDate)
    {
        mTextFieldArray[0].setText(pDate);
    }
    
     public String getVendor()
    {
        return mTextFieldArray[1].getText();
    }
    
    public void setVendor(String pVendor)
    {
        mTextFieldArray[1].setText(pVendor);
    }
    
     public String getPaymentMethod()
    {
        return mTextFieldArray[2].getText();
    }
    
    public void setPaymentMethod(String pPaymentMethod)
    {
        mTextFieldArray[2].setText(pPaymentMethod);
    }
    
     public String getAmount()
    {
        return mTextFieldArray[3].getText();
    }
    
    public void setAmount(String pAmount)
    {
        mTextFieldArray[3].setText(pAmount);
    }
    
    public void clearAllFields()
    {
        for (int i = 0; i < SIZE; i++)
        {
            mTextFieldArray[i].clear();
        }
    }
    
    public boolean allFieldsClear()
    {
        return (mTextFieldArray[0].getText().trim().length() == 0 &&
                mTextFieldArray[1].getText().trim().length() == 0 &&
                mTextFieldArray[2].getText().trim().length() == 0 &&
                mTextFieldArray[3].getText().trim().length() == 0 );
    }
}
