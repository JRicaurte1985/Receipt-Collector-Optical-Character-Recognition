/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foggitops.desktop;

import foggitops.service.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import net.sourceforge.vietocr.ImageHelper;

/**
 *
 * @author Puchito
 */
public class Controller
{

    private View mView;
    private Model mModel;

    public Controller(Model pModel, View pView)
    {
        mModel = pModel;
        mView = pView;

        mView.getImportBtn().setOnAction(new importBtnEventHandler());
        mView.getExportBtn().setOnAction(new exportBtnEventHandler());
        mView.getZoomInBtn().setOnAction(new zoomInBtnEventHandler());
        mView.getZoomOutBtn().setOnAction(new zoomOutBtnEventHandler());
        mView.getSaveBtn().setOnAction(new saveBtnEventHandler());
        
        mView.getReceiptTable().setItems(mModel.getData());
        
    }

    public void show(View pView)
    {
        pView.getStage().show();
    }

    class importBtnEventHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            try
            {
                File file = chooseFile();
                String fileName = file.getAbsolutePath();
                String extension = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
                
                //Do image scaling to help accuracy
                BufferedImage img = ImageIO.read(file);

                if (img.getHeight() < 384 || img.getWidth() < 288)
                {
                    System.out.println("Rescaled x 4");
                    img = ImageHelper.getScaledInstance(img, img.getWidth() * 4, img.getHeight() * 4);
                } else if (img.getHeight() < 640 || img.getWidth() < 480)
                {
                    System.out.println("Rescaled x 3");
                    img = ImageHelper.getScaledInstance(img, img.getWidth() * 3, img.getHeight() * 3);
                } else if (img.getHeight() < 800 || img.getWidth() < 600)
                {
                    System.out.println("Rescaled x 2");
                    img = ImageHelper.getScaledInstance(img, img.getWidth() * 2, img.getHeight() * 2);
                }
                //display picture
                mView.getReceiptDisplay().setDisplay(fileName);
                
                //do OCR
                mView.getReceiptForm().clearAllFields();
                mModel.getOCR().reset();    
                mModel.getOCR().runOCR(img);
                mModel.getOCR().parseImage();

                System.out.println(mModel.getOCR().getOCRText());
                
                //set form and display
                mView.getReceiptForm().setDate(mModel.getOCR().getDate());
                mView.getReceiptForm().setVendor(mModel.getOCR().getVendor());
                mView.getReceiptForm().setPaymentMethod(mModel.getOCR().getPaymentType());
                mView.getReceiptForm().setAmount(mModel.getOCR().getAmount());
             
            } catch (Exception ex)
            {

            }
        }
    };

    class exportBtnEventHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export to CSV");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("CSV file (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(filter);

            File file = fileChooser.showSaveDialog(null);

            if (file != null)
            {
                SQLite.exportCSV(file.getAbsolutePath());
            }
        }
    };

    class zoomInBtnEventHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            try
            {
                mView.getReceiptDisplay().zoomIn();
            } catch (Exception ex)
            {

            }
        }
    };

    class zoomOutBtnEventHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            try
            {
                mView.getReceiptDisplay().zoomOut();
            } catch (Exception ex)
            {

            }
        }
    };

    class saveBtnEventHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            try
            {   //TODO  validate data before saving. Use valid()
                if (!mView.getReceiptForm().allFieldsClear())
                {   
                    if (isNum(mView.getReceiptForm().getAmount()))
                    {
                    
                        Receipt temp = new Receipt(mView.getReceiptForm().getDate(),
                                                   mView.getReceiptForm().getVendor(),
                                                   mView.getReceiptForm().getPaymentMethod(),
                                                   mView.getReceiptForm().getAmount());
                        mModel.addReceipt(temp);
                        SQLite.insertRow(temp);
                        mView.getReceiptForm().clearAllFields();
                    }
                }

            } catch (Exception ex)
            {

            }
        }
    }

    //Helper function for opening files
    public static File chooseFile() throws Exception
    {
        FileChooser fileChooser = new FileChooser();
        //Extention filter
        FileChooser.ExtensionFilter JPG_Filter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter PNG_Filter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter PDF_Filter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF");
        fileChooser.getExtensionFilters().addAll(JPG_Filter, PNG_Filter, PDF_Filter);

        //Set to user directory or go to default if cannot access
        String userDirectoryString = System.getProperty("user.home");
        File file = new File(userDirectoryString);
        fileChooser.setInitialDirectory(file);

        //show open file dialog
        file = fileChooser.showOpenDialog(null);
        if (file != null)
        {
            return file;
        } else
        {
            file = null;
        }
        return file;
    }


    public static boolean isNum(String strNum) 
    {
        boolean ret = true;

        try 
        {
            Double.parseDouble(strNum);

        }catch (NumberFormatException e) 
        {
            ret = false;
        }
        return ret;
    }
    
    
    public void validForm()
    {
        
    }
}

/*if (extension == "pdf")
 {


 PDFDocument document = new PDFDocument();
 document.load(file);
 SimpleRenderer renderer = new SimpleRenderer();

 // set resolution (in DPI)
 renderer.setResolution(300);
 List<java.awt.Image> images = renderer.render(document);
 for (int i = 0; i < images.size(); i++) 
 {
 ImageIO.write((RenderedImage) images.get(i), "png", file);
 }
 }*/
