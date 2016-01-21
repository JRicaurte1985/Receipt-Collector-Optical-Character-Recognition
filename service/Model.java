/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package foggitops.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Puchito
 */
public class Model
{
    private OCR mOCR;
    private ObservableList<Receipt> mData;
 
    public Model()
    {
        mOCR = new OCR();
        mData = FXCollections.observableArrayList();
       
        for (Receipt r : SQLite.getReceiptList())
        {
            mData.add(r);
        }      
    }
    
    public OCR getOCR()
    {
        return mOCR;
    } 
    
    public ObservableList<Receipt> getData()
    {
        return mData;
    }
    
    public void addReceipt(Receipt pReceipt)
    {
        mData.add(pReceipt);
    }
}
