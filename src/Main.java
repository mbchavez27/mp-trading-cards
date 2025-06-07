import com.elements.mainMenu.*;


public class Main {
    public static void main(String[] args){

        mainMenuModel menuModel = new mainMenuModel();
        mainMenuView menuView = new mainMenuView();
        mainMenuController menuController = new mainMenuController(menuModel, menuView);



        int choice = menuController.start();

    }
}
