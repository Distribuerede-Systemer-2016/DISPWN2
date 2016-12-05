package Controller;

import Sdk.HTTPRequest;
import Models.Book;
import View.MainView;
import Models.User;

import java.util.ArrayList;

public class MainController {

    private User currentUser;

    public MainController(){

        new MainView(this).showMenu();
    }

    public boolean authUser(String username, String hashedPassword) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        User userResponse = HTTPRequest.authorizeLogin(user);

        if(userResponse != null){
            currentUser = userResponse;
            return true;
        }
        else
            return false;
    }

    public boolean createUser(String firstName, String lastName, String email, String username, String password) {
        User user = new User();
        user.getFirstName(firstName);
        user.getLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);


        return HTTPRequest.createUser(user);
    }

    public ArrayList<Book> getBooks(){
        return HTTPRequest.getBooks();
    }
}