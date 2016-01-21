package foggitops.desktop;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.SplitPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Puchito
 */
public class View 
{
    private Group mRoot;
    private BorderPane mRootPane;
    private Stage mStage;
    private Scene mScene;
    
    private ToolBar mToolBar;
    private ButtonPanel mButtonPanel;
    private ReceiptDisplay mReceiptDisplay;
    private ReceiptForm mReceiptForm;
    private ReceiptTable mReceiptTable;
    private SplitPane mCenterPane;
    private SplitPane mRightPane;
  
    public View(Stage pStage)
    {
      
        mReceiptDisplay = new ReceiptDisplay();
        mToolBar = new ToolBar();
        mReceiptForm = new ReceiptForm();
        mReceiptTable = new ReceiptTable();
        mButtonPanel = new ButtonPanel(); 
        
        mRightPane = new SplitPane();
        mRightPane.setOrientation(Orientation.VERTICAL);
        mRightPane.setDividerPositions(.34, 1,.1);
        mRightPane.getItems().addAll(mReceiptForm.get(), mReceiptTable.get(), mButtonPanel.get());
        
        mCenterPane = new SplitPane();
        mCenterPane.setOrientation(Orientation.HORIZONTAL);
        mCenterPane.setDividerPositions(.6);
        mCenterPane.getItems().addAll(mReceiptDisplay.get(),mRightPane);
        
        mRootPane = new BorderPane();
        mRootPane.setTop(mToolBar.get());
        mRootPane.setCenter(mCenterPane);
        
        mRoot = new Group();
        mRoot.getChildren().add(mRootPane);
        mScene = new Scene(mRoot, 1200, 900);
        mRootPane.prefWidthProperty().bind(mScene.widthProperty());
        mRootPane.prefHeightProperty().bind(mScene.heightProperty());
   
        mStage = pStage;
        pStage.setTitle("ReceiptCollector");
        pStage.setScene(mScene);
    }
    
    //Getters for private variable access
    public ReceiptDisplay getReceiptDisplay()
    {
        return mReceiptDisplay;
    }
    
     public ToolBar getToolBar()
    {
        return mToolBar;
    }
     
      public ReceiptForm getReceiptForm()
    {
        return mReceiptForm;
    }
      
       public ReceiptTable getReceiptTable()
    {
        return mReceiptTable;
    }
       
        public ButtonPanel getButtonPanel()
    {
        return mButtonPanel;
    }
    
    //Getters for Controller to access mButtonPanel's buttons via View
    public Button getImportBtn()
    {
        return mButtonPanel.getImport();
    }
    
    public Button getExportBtn()
    {
        return mButtonPanel.getExport();
    }
    
    public Button getZoomInBtn()
    {
        return mButtonPanel.getZoomIn();
    }
    
    public Button getZoomOutBtn()
    {
        return mButtonPanel.getZoomOut();
    }
    
    public Button getSaveBtn()
    {
        return mReceiptForm.getSaveBtn();
    }

    public Stage getStage()
    {
        return mStage;
    }
    
}   
