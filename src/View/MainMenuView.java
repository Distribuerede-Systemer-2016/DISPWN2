package View;
//Denne klasse indeholder de forskellige muligheder brugeren har, når brugeren er logget ind

import Controller.MainController;
import Models.Book;
import Models.Curriculum;
import Sdk.HTTPRequest;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenuView {
    private MainController mainController;
    private static Scanner input;

    public MainMenuView(MainController mainController) {
        this.mainController = mainController;
        input = new Scanner(System.in);
    }

// denne menu bliver præsenteret for en, når man er logget ind
    public void showMenu() {
        System.out.println("\nDu har nu følgende valgmuligheder:\n");
        System.out.println("1) Udskriv alle bøger ");
        System.out.println("2) Udskriv aktuelle semestre ");
        System.out.println("3) Ændre brugeroplysninger ");
        System.out.println("4) Logud ");
        System.out.println("5) Slet bruger");

        Scanner input = new Scanner(System.in);

        int choice = 0;

        do {
            try {
                choice = input.nextInt();

                switch (choice) {
            case 1:
                Books();
                break;
            case 2:
                curriculumLists();
                break;
            case 3:
                updateUser();
                break;
            case 4:
                System.out.println("Du er nu logget ud");
                mainController.setCurrentUser(null);
                break;
            case 5:
                deleteUser();
                break;
            default:
                System.out.println("Du skal vælge et tal mellem 1 og 5\n");
                showMenu();
                break;
        }
            }
        catch (InputMismatchException e) {
            System.out.println ("Du skal vælge et tal\n");
            input.nextLine();
        }
    }
        while (true);
    }
// Denne metode sletter brugeren, som er logget ind
    private void deleteUser() {

        boolean userDeleted = mainController.deleteUser();
        if (userDeleted){
            System.out.println("Din bruger er nu slettet\n");

        }
        else{
            System.out.println("Noget gok galt. Din bruger blev ikke slettet");
            showMenu();

    }
    }
// denne metode henter alle bøger fra databasen og præsentere dem for brugeren
    public void Books() {
        ArrayList<Book> books = mainController.getBooks();
        System.out.println("Her er alle aktuelle bøger i systemet: ");
        for (Book book : books) {
            System.out.println("\nId: " + book.getBookID() + "\nTitle: " + book.getTitle());
        }
        showMenu();
    }
    // Denne metode henter en bestemt bog samt priserne på bogen
    public void Book(){
        System.out.println("\nIndast id på den ønskede bog");
        Book book = HTTPRequest.getBook(input.nextInt());
        System.out.println("\nHer er de forskellige priser for den valgte bog:\n");
        System.out.println("Id: " + book.getBookID()
                + "\nTitle: " + book.getTitle()
                + "\nPris Saxo \t" + book.getPriceSAXO() + "\t Kr. "
                + "\nPris CDON \t" + book.getPriceCDON() + "	 Kr. "
                + "\nPris AB \t" + book.getPriceAB() + "	 Kr. \n");
    }

// Denne Menu er med til at opdatere en bruger
    private void updateUser(){
        input.nextLine();
        String firstName, lastName, email,username;
        System.out.println("\nDu har valgt at opdatere dine brugeroplysninger");
        System.out.println("\nVælg nyt fornavn");
        firstName = input.nextLine();
        System.out.println("\nVælg nyt efternavn");
        lastName = input.nextLine();
        System.out.println("\nVælg ny email");
        email = input.nextLine();
        System.out.println("\nVælg nyt brugernavn");
        username = input.nextLine();


        boolean userUpdate = mainController.updateUser(firstName, lastName, email, username);

        if (userUpdate){
            System.out.println("Du har Opdateret din bruger");
            showMenu();
        }
            else
            System.out.println("Der opstod en fejl, der gjorde, at din bruger ikke blev opdateret\n");

    }
// denne metode henter hvilke semestre der er tilgængelig i BookIT, så vælger man semester, hvor man får vist pensumlister fra databasen og prøsenterer dem for brugeren.
// Herefter vælger brugeren hvilken bog de vil have vist bogen for
    private void curriculumLists() {

        input = new Scanner(System.in);
        ArrayList<Curriculum> curriculums = HTTPRequest.getCurriculums();


        System.out.println("Vælg ID'et på det semester, som du vil have vist bøgerne for.\n");
        for (Curriculum curriculum : curriculums) {
            System.out.println("ID: " + curriculum.getCurriculumID() + " - "
                    + curriculum.getSchool() + " "
                    + curriculum.getSemester() + ". Semester  "
                    + curriculum.getEducation());
        }
        int searchCurriculum;
        do {

            System.out.println("Indtast ID for semester: \n");
            while (!input.hasNextInt()) {
                System.out.println("Indtast venligst et gyldigt ID.");
                input.next();
            }
            searchCurriculum = input.nextInt();

        } while (searchCurriculum <= 0 || searchCurriculum > curriculums.size());

        ArrayList<Book> curriculumBooks = HTTPRequest.getCurriculumBooks(searchCurriculum);
        System.out.println("\nFor at få vist priserner på en bestemt bog; indtast ID'et på den ønskede bog\n");
        System.out.println("Følgende bøger er aktuelle på det valgte semester:\n");

        for (Book book : curriculumBooks) {
            System.out.println("ID:  " + book.getBookID()
                    + "\n Titel:     " + book.getTitle()
                    + "\n Forfatter: " + book.getAuthor()
                    + "\n Udgiver:   " + book.getPublisher()
                    + "\n Version:   " + book.getVersion()
                    + "\n ISBN:      " + book.getISBN());
        }
        Book();
        showMenu();
    }
}