package View;

import Controller.MainController;
import java.util.Scanner;

public class MainView {
    MainController mainController;
    MainMenuView mainMenuView;
    private Scanner input;

    public MainView(MainController mainController){
        this.mainController = mainController;
        mainMenuView = new MainMenuView(mainController);
        input = new Scanner(System.in);

    }

    public void showMenu() {
        System.out.println("Velkommen til BookIT\n");
        System.out.println("Du har nu følgende muligheder:");
        System.out.println("1) Login");
        System.out.println("2) Opret bruger");


        switch (input.nextInt()) {
            case 1:
                boolean authUser = loginMenu();

                if(authUser)
                    mainMenuView.showMenu();
                else
                    System.out.println("Forkert brugernavn eller password");

                break;
            case 2:
                System.out.println("Opret ny bruger: ");
                boolean userCreated = createUser();
                break;
            default:
                System.out.println("Indtast enten 1 eller 2");
                break;
        }
    }

    private boolean createUser() {
        String username;

        System.out.println("Indtast brugernavn");
        username = input.nextLine();

        return mainController.createUser(username);
    }

    private boolean loginMenu(){
        input.nextLine();

        String username, password;
        System.out.println("Du har valgt: Login\n");
        System.out.println("Indtast dit brugernavn");
        username = input.nextLine();

        System.out.println("Indtast dit password");
        password = input.nextLine();

        return mainController.authUser(username, password);

    }
}

