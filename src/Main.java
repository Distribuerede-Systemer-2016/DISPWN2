import Controller.MainController;
import Sdk.Config;

public class Main {

    public static void main(String[] args) {
        Config.initConfig();

        new MainController().menu();
    }
}

