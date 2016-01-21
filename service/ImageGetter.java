package foggitops.service;

import foggitops.system.*;

import java.io.*;

import javafx.scene.image.Image;

/**
 * Gets images.
 *
 * Sample usage:
 *
 * Image image = ImageGetter.getImage("status.png");
 *
 * @author Brother Neff
 */
public class ImageGetter
{
   /**
    * The path prefix for images resources.
    */
   public static final String cImagesPathPrefix = "/resources/images/";

   /**
    * Encapsulates getting an image from a resource file.
    */
   public static Image getImage(String pName)
   {
      Image im = null;

      String imageFilename = cImagesPathPrefix + pName;

      try
      {
         im = new Image(ResourceGetter.getResourceAsStream(imageFilename));
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

      return im;
   }
}