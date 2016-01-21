package foggitops.desktop;


import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Puchito
 */
public class ReceiptDisplay
{
    private Image mImage;
    private ImageView mImageView;
    private ScrollPane mScrollPane;
  
    public ReceiptDisplay()
    {
        mScrollPane = new ScrollPane();
        mScrollPane.setStyle("-fx-border-color:gray;-fx-border-width:25;" +
                             "-fx-background-color:gray;");
        mScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        mScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        mImage = new Image("foggitops/resources/DefaultPic.jpg");
        mImageView = new ImageView();
        fitToScreen();
        mScrollPane.setContent(mImageView);    
    }
    
    public ScrollPane get()
    {
        return mScrollPane;
    }
  
    
    public void setDisplay(String fileName)throws Exception
    {
        mImage = new Image("file:" + fileName);
        fitToScreen();    
    }
    private void fitToScreen()
    {
        if (mImage.getWidth() < 1100 || mImage.getHeight() < 1100)
        {
            mImageView.setFitWidth(1100);
            mImageView.setFitHeight(1100);
        }
        else 
        {
            mImageView.setFitWidth(mImage.getWidth());
            mImageView.setFitHeight(mImage.getHeight());
        }
        mImageView.setPreserveRatio(true);
        mImageView.setSmooth(true);
        mImageView.setCache(true);
        mImageView.setImage(mImage);
    }
    
    public void zoomIn()
    {
        if (mImageView.getFitWidth() < 3000 || mImageView.getFitHeight() < 3000)
        {
            mImageView.setFitWidth(mImageView.getFitWidth() * 1.1);
            mImageView.setFitHeight(mImageView.getFitHeight() * 1.1);
        }
    }
    
    public void zoomOut()
    {
        if (mImageView.getFitWidth() > 450|| mImageView.getFitHeight() > 600)
        {
            mImageView.setFitWidth(mImageView.getFitWidth() * .9);
            mImageView.setFitHeight(mImageView.getFitHeight() * .9);
        }
    }
    
    public void rotateClockwise()
    {
        double r = mImageView.getRotate();
        mImageView.setRotate(r + 90);
    }
  
}
