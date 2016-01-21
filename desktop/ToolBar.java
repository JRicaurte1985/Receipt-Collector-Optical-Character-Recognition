/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package foggitops.desktop;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 *
 * @author Puchito
 */
public class ToolBar
{
    private MenuBar mMenuBar;
    private Menu mMenuFile;
    private Menu mMenuEdit;
    private Menu mMenuView;
   
    public ToolBar()
    {
        mMenuBar = new MenuBar();
        mMenuFile = new Menu("File");
        mMenuEdit = new Menu("Edit");
        mMenuView = new Menu("View");
        
        mMenuBar.getMenus().addAll(mMenuFile, mMenuEdit, mMenuView); 
    }
    
    public MenuBar get()
    {
        return mMenuBar;
    }
}
