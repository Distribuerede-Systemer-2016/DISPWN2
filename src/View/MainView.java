package View;

import Controller.MainController;
import Encryption.Digester;

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
                    System.out.println("Forkert brugernavn eller password. Prøv igen\n");
                showMenu();

                break;
            case 2:
                System.out.println("Du har valgt at oprette en bruger\n");
               createUser();
            default:
                System.out.println("Indtast enten 1 eller 2");
                showMenu();
                break;
        }
    }

    private void createUser() {
        input.nextLine();
        String firstName, lastName, email,username, password;
        System.out.println("Indtast Fornavn");
        firstName = input.nextLine();
        System.out.println("Indtast Efternavn");
        lastName = input.nextLine();
        System.out.println("Indtast Email");
        email = input.nextLine();
        System.out.println("Indtast Brugernavn");
        username = input.nextLine();
        System.out.println("Indtast Password");
        password = input.nextLine();

        boolean userCreated = mainController.createUser(firstName, lastName, email, username, Digester.hashWithSalt(password)); //kaldt på serveren

        if (userCreated)
        System.out.println("Du har oprettet en bruger");
        else
        System.out.println("Der opstod en fejl, der gjorde, at din bruger ikke blev oprettet\n");

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

