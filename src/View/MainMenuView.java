package View;


import Controller.MainController;
import Models.Book;
import Models.Curriculum;
import Models.User;
import Sdk.HTTPRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenuView {
    private MainController mainController;
    private static Scanner input;
    User currentUser;


    public MainMenuView(MainController mainController) {
        this.mainController = mainController;
        input = new Scanner(System.in);
    }


    public void showMenu() {
        System.out.println("Du har nu følgende valgmuligheder:\n");
        System.out.println("1) Udskriv alle bøger ");
        System.out.println("2) Udskriv aktuelle semestre ");
        System.out.println("3) Ændre brugeroplysninger ");
        System.out.println("4) Logud ");
        switch (input.nextInt()) {
            case 1:
                printBooks();
                break;
            case 2:
                printPensumListe();
                break;
            case 3:
                updateUser();
            case 4:
                currentUser = null;
                break;

            default:
                System.out.println("Du skal vælge et tal\n\n");
                showMenu();
                break;
        }
    while (currentUser != null);
        System.out.println("Du er nu logget ud\n");


    }

    public void printBooks() {
        ArrayList<Book> books = mainController.getBooks();
        System.out.println("Her er alle aktuelle bøger i systemet: ");
        for (Book book : books) {
            System.out.println("\nId: " + book.getBookID() + "\nTitle: " + book.getTitle());
        }
    }
    public void printBook(){
        System.out.println("\nIndast id på den ønskede bog");
        Book book = HTTPRequest.getBook(input.nextInt());
        System.out.println("\nHer er de forskellige priser for den valgte bog:\n");
        System.out.println("Id: " + book.getBookID()
                + "\nTitle: " + book.getTitle()
                + "\nPris Saxo \t" + book.getPriceSAXO() + "\t Kr. "
                + "\nPris CDON \t" + book.getPriceCDON() + "	 Kr. "
                + "\nPris AB \t" + book.getPriceAB() + "	 Kr. \n");
    }
    private void updateUser(){
        System.out.println("\nDu har valgt at opdatere dine brugeroplysninger");
        System.out.println("\nVælg nyt fornavn");
        System.out.println("\nVælg nyt efternavn");
        System.out.println("\nVælg ny email");
        System.out.println("\nVælg nyt brugernavn");
        System.out.println("\nVælg nyt password");

    }

    private void printPensumListe() {

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
        System.out.println("\nFor at få vist priserner på en bestemt bog; indtast ID'et på den ønskede bog");
        System.out.println("Følgende bøger er aktuelle på det valgte semester:\n");

        for (Book book : curriculumBooks) {
            System.out.println("ID:  " + book.getBookID()
                    + "\n Titel:     " + book.getTitle()
                    + "\n Forfatter: " + book.getAuthor()
                    + "\n Udgiver:   " + book.getPublisher()
                    + "\n Version:   " + book.getVersion()
                    + "\n ISBN:      " + book.getISBN());
        }
        printBook();
        showMenu();
    }
}