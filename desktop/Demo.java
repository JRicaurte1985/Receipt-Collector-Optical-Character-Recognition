/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foggitops.desktop;

import foggitops.service.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Puchito
 */
public class Demo extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        //... Create model, view, and controller.  They are
        //    created once here and passed to the parts that
        //    need them so there is only one copy of each.
        Model mModel = new Model();
        View mView = new View(primaryStage);
        Controller mController = new Controller(mModel, mView);
        mController.show(mView);
        
    }
}
