import Controller.MainController;
import Sdk.Config;

public class Main {
// Dette er BookITs start. Her initierers MainController, som starter "startMenu"
    public static void main(String[] args){
        Config.initConfig();
        new MainController();
    }
}
