/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package foggitops.desktop;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;


/**
 * 
 * @author Puchito
 */
public class ButtonPanel
{
    private FlowPane mPanel;
    private Button mImport;
    private Button mExport;
    private Button mZoomIn;
    private Button mZoomOut;
    
    public ButtonPanel()
    {  
        mPanel = new FlowPane();
        mPanel.setAlignment(Pos.CENTER);
        mPanel.setStyle("-fx-background-color:dimgrey;");
        mPanel.setMinHeight(173);
        mPanel.setMaxHeight(173);
 
        mImport = new Button("", new ImageView(new Image
        ("foggitops/resources/Import.png")));  
        mExport = new Button("", new ImageView(new Image
        ("foggitops/resources/Export.png")));
        mZoomIn = new Button("", new ImageView(new Image
        ("foggitops/resources/ZoomIn.png")));
        mZoomOut = new Button("", new ImageView(new Image
        ("foggitops/resources/ZoomOut.png")));
   
        mPanel.getChildren().addAll(mImport, mExport, mZoomIn, mZoomOut);
    }
    
    public FlowPane get()
    {
        return mPanel;
    }
    
    public Button getImport()
    {
        return mImport;
    }
    
    public Button getExport()
    {
        return mExport;
    }
    
    public Button getZoomIn()
    {
        return mZoomIn;
    }
    
    public Button getZoomOut()
    {
        return mZoomOut;
    }

}
       
        
      