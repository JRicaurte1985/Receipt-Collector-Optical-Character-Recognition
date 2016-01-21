/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foggitops.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.image.BufferedImage;
import net.sourceforge.tess4j.Tesseract;



/**
 *
 * @author Puchito
 */
public class OCR
{
    private Tesseract mTesseract;
    private String mOCRText;
    private String mPaymentMethod;
    private String mDate;
    private String mVendor;
    private String mAmount;
  
    public OCR()
    {
        mTesseract = Tesseract.getInstance();
    }
   
    public String getOCRText() throws Exception
    {
        return mOCRText;
    }
    
    public void runOCR(BufferedImage img) throws Exception
    {
        if (img != null)
            mOCRText = mTesseract.doOCR(img);
        else
        {
            System.out.println("ERROR");
        }
        
    }
  
    public void parseDate()
    {
       
        Pattern pattern;
        Matcher matcher;
        String datePattern1 = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)";
        String datePattern2 = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/[0-9][0-9]";
        pattern = Pattern.compile(datePattern1);
        matcher = pattern.matcher(mOCRText);
       
        if (matcher.find())
        {
            mDate = "";
            mDate += matcher.group(0);
        }
        
        else
        {
            pattern = Pattern.compile(datePattern2);
            matcher = pattern.matcher(mOCRText);
            if (matcher.find())
            {
                mDate = "";
                mDate += matcher.group(0);
                
            }
        }
    }

    public void parseAmount()
    {
        String[] words = {"Total", "TOTAL", "otal", "OTAL", "TUTAL", "tutal", 
                                    "Ttal", "TTAL"};
        char c;
        String amt = "";
        
        for (String word : words)
        {
            if (mOCRText.contains(word))
            {
                int pos = mOCRText.lastIndexOf(word);
                
                for (; pos < mOCRText.length(); pos++)
                {
                    c = mOCRText.charAt(pos);
                    if (c == '.')
                    {    
                        amt += c;
                        break;
                    }
                    else if (Character.isDigit(c))
                    {
                        amt += c;
                        
                    } 
                }
                amt += mOCRText.charAt(pos + 1);
                amt += mOCRText.charAt(pos + 2);
                
                mAmount = amt; 
                break;    
                
            }
        }
      
       
    }
    public void parseVendor()
    {
        String[] vendors = {"Walmart", "WALMART", "CVS", "KMart", "Publix", "Broulims"};
        for (String vendor : vendors)
        {
            if (mOCRText.contains(vendor))
            {
                mVendor = vendor;
            }
        }
    }
    
    public void parsePaymentMethod()
    {
        String[] paymentMethods = {"Cash", "CASH","Credit", "CREDIT","Check", "CHECK",
                          "Gift Card", "GIFT CARD"};
        for (String paymentMethod : paymentMethods)
        {
            if (mOCRText.contains(paymentMethod))
            {
                mPaymentMethod = paymentMethod;
            }
        }
    }
    
    public void parseImage()
    {
        parseDate();
        parseAmount();
        parseVendor();
        parsePaymentMethod();       
    }
    
    public void reset()
    {
        mDate = null;
        mAmount = null;
        mVendor = null;
        mPaymentMethod = null;
    }
    
    public String getDate()
    {
        return mDate;
    }
    
    public String getVendor()
    {
        return mVendor;
    }
    
    public String getPaymentType()
    {
        return mPaymentMethod;
    }
    
    public String getAmount()
    {
        return mAmount;
    }
}
