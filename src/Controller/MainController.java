package Controller;

import Models.Book;
import Sdk.Connection;

import java.util.ArrayList;
import java.util.Scanner;

public class MainController {

    Scanner input;

    public MainController() {
        input = new Scanner(System.in);
    }

    public void menu() {

/*
 System.out.println("     Velkommen til BookIt. Du har nu følgende to muligheder:     ");
        System.out.println("1) Login");
        System.out.println("2) Opret bruger");

        Scanner inputReader = new Scanner(System.in);
        int choice = inputReader.nextInt();

        switch (choice) {

            case 1:
                printBook();
                break;

            case 2:
                System.out.println("Opret en ny bruger");
                break;

            default:
                System.out.println("Du skal trykke enten 1 eller 2 på dit tastetur");
                break;

*/
        String username, password;
        System.out.println("Login");
        System.out.println("Indtast brugernavn");
        username = input.nextLine();
        System.out.println("Indtast password");
        password = input.nextLine();

        String token = Connection.authorizeLogin(username, password);
        if(token != null){
            do {
                System.out.println("     Velkommen til BookIt. Du har nu følgende to muligheder:     ");
                System.out.println("1) Login");
                System.out.println("2) Opret bruger");

                Scanner inputReader = new Scanner(System.in);
                int choice = inputReader.nextInt();

                switch (choice) {

                    case 1:
                        printBook();
                        break;

                    case 2:
                        System.out.println("Opret en ny bruger");
                        break;

                    default:
                        System.out.println("Du skal trykke enten 1 eller 2 på dit tastetur");
                        break;
                }
            }while(true);
        }
        else {
            System.out.println("Så er der fejl!");
        }

    }

    public void printBooks(){
        ArrayList<Book> books = Connection.getBooks();
        System.out.println("Bøger i BookIT");
        for (Book book : books) {
            System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
        }
    }

    public void printBook(){
        System.out.println("Indast id på den ønskede bog");
        Book book = Connection.getBook(input.nextInt());
        System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
    }
}
