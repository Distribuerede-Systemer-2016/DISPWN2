package View;
// Denne klasse indeholder de muligheder, som er tilgængelige, når brugeren ikke er logget ind.

import Controller.MainController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    MainController mainController;
    MainMenuView mainMenuView;
    private Scanner input;

    public MainView(MainController mainController){
        input = new Scanner(System.in);
        this.mainController = mainController;
        mainMenuView = new MainMenuView(mainController);

    }
// Dette er startmenuen, som man bliver præsenteret for, når man åbner BookIT
    public void startMenu() {
        System.out.println("Velkommen til BookIT\n");
        System.out.println("Du har nu følgende muligheder:");
        System.out.println("1) Login");
        System.out.println("2) Opret bruger");

        Scanner input = new Scanner(System.in);

        int choice = 0;

        do {
            try {
                choice = input.nextInt();

        switch (choice) {
            case 1:
                loginMenu();
                break;
            case 2:
                System.out.println("Du har valgt at oprette en bruger\n");
               createUser();
            default:
                System.out.println("Du skal vælge enten 1 eller 2");
                break;
        }
            }
        catch (InputMismatchException e) {
            System.out.println ("Du skal vælge et tal\n");
            input.nextLine();
            }
        }
        while(true);

    }
// her opretter man en bruger med de informationer der skal til
    private void createUser() {
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

        boolean userCreated = mainController.addUser(firstName, lastName, email, username, password);

        if (userCreated)
        System.out.println("Du har oprettet en bruger");
        else
        System.out.println("Der opstod en fejl, der gjorde, at din bruger ikke blev oprettet\n");
        startMenu();

    }
// her skal man indtaste sit brugernavn og password, hvis det bliver accepteret, bliver man logget ind.
    private void loginMenu(){
        input.nextLine();
        String username, password;
        System.out.println("Du har valgt: Login\n");
        System.out.println("Indtast dit brugernavn");
        username = input.nextLine();
        System.out.println("Indtast dit password");
        password = input.nextLine();


        boolean authUser = mainController.authUser(username, password);
        if(authUser)
            mainMenuView.showMenu();
        else
            System.out.println("Forkert brugernavn eller password. Prøv igen\n");
        startMenu();
    }

}

