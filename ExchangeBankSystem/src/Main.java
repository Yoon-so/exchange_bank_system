import controller.MainController;
import view.MainMenuView;

public class Main {
    public static void main(String[] args) {
        
        MainController controller = new MainController();
        new MainMenuView(controller);
    }
}
