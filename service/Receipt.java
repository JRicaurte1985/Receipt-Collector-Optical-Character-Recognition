/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package foggitops.service;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Puchito
 */
public class Receipt
{
   private SimpleStringProperty mDate;
   private SimpleStringProperty mVendor;
   private SimpleStringProperty mPaymentMethod;
   private SimpleStringProperty mAmount;
   private SimpleIntegerProperty mID;
   
  
   public Receipt ()
   {
      this.mDate = new SimpleStringProperty("");
      this.mVendor = new SimpleStringProperty("");
      this.mPaymentMethod = new SimpleStringProperty("");
      this.mAmount = new SimpleStringProperty("");
      this.mID = new SimpleIntegerProperty(-1);
   }
   
  public Receipt(String pDate, String pVendor, String pPaymentMethod, String pAmount)
   {
       this.mDate = new SimpleStringProperty(pDate);
       this.mVendor = new SimpleStringProperty(pVendor);
       this.mPaymentMethod = new SimpleStringProperty(pPaymentMethod);
       this.mAmount = new SimpleStringProperty(pAmount);
       this.mID = new SimpleIntegerProperty(-1);
   }
           
   public String getDate()
   {
       return mDate.get();
   }
   
   public void setDate(String pDate)
   {
       mDate.set(pDate);
   }
  
   
   public String getVendor()
   {
      return mVendor.get();
   }
   
   public void setVendor(String pVendor)
   {
      mVendor.set(pVendor);
   }
 
   
   public String getAmount()
   {
      return mAmount.get();
   }
   
   public void setAmount(String pAmount)
   {
      mAmount.set(pAmount);
   }
   
   public String getPaymentMethod()
   {
      return mPaymentMethod.get();
   }
   
   public void setPaymentMethod(String pPaymentMethod)
   {
      mPaymentMethod.set(pPaymentMethod);
   }

   public int getID()
   {
      return mID.get();
   }
   
   public void setID(int pID)
   {
      mID.set(pID);
   }
 
}