package View;


import Controller.MainController;
import Models.Book;
import Models.Curriculum;
import Sdk.HTTPRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenuView {
    private MainController mainController;
    private static Scanner input;


    public MainMenuView(MainController mainController) {
        this.mainController = mainController;
        input = new Scanner(System.in);
    }


    public void showMenu() {
        System.out.println("Du har nu følgende valgmuligheder:\n");
        System.out.println("1) Udskriv alle bøger ");
        System.out.println("2) Udskriv aktuelle semestre for Ha(mat) & Ha(it) ");
        switch (input.nextInt()) {
            case 1:
                printBooks();
                break;
            case 2:
                printPensumListe();
                break;
        }
    }

    public void printBooks() {
        ArrayList<Book> books = mainController.getBooks();
        System.out.println("Pensumliste: ");
        for (Book book : books) {
            System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
        }
    }
    public void printBook(){
        System.out.println("Indast id på den ønskede bog");
        Book book = HTTPRequest.getBook(input.nextInt());
        System.out.println("Id: " + book.getBookID() + " title: " + book.getTitle()
                + "\nPris Saxo \t" + book.getPriceSAXO() + "\t Kr. "
                + "\nPris CDON \t" + book.getPriceCDON() + "	 Kr. "
                + "\nPris AB \t" + book.getPriceAB() + "	 Kr. ");
    }


    private void printPensumListe() {

        input = new Scanner(System.in);
        ArrayList<Curriculum> curriculums = HTTPRequest.getCurriculums();


        System.out.println("Vælg ID'et på det semester, som du vil have vist bøgerne for\n");
        for (Curriculum curriculum : curriculums) {
            System.out.println("ID: " + curriculum.getCurriculumID() + " - " + curriculum.getSchool() + " " + curriculum.getSemester() + ". Semester  " + curriculum.getEducation());
        }
        int searchCurriculum;
        do {

            System.out.println("Indtast ID for semester: \n");
            while (!input.hasNextInt()) {
                System.out.println("Indtast venligst et gyldigt ID");
                input.next();
            }
            searchCurriculum = input.nextInt();

        } while (searchCurriculum <= 0 || searchCurriculum > curriculums.size());

        ArrayList<Book> curriculumBooks = HTTPRequest.getCurriculumBooks(searchCurriculum);

        System.out.println("Følgende bøger er aktuelle på det valgte semester: \nFor at få vist priserner på en bestemt bog; indtast ID'et på den ønskede bog \n");

        for (Book book : curriculumBooks) {
            System.out.println("ID: " + book.getBookID() + "\t Title: " + book.getTitle() + "\t\t\t ISBN Nummer: " + book.getIsbn());

        }
        printBook();


    }
}

