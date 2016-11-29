package Controller;


import Sdk.HTTPRequest;
import Models.Book;
import View.MainView;
import Models.User;
import View.BookView;
import View.UserView;

import java.util.ArrayList;

public class MainController {

    private BookView bookView;
    private UserView userView;
    private User currentUser;

    public MainController(){

        new MainView(this).showMenu();
    }
    public BookView getBookView() {
        return bookView;
    }
    public UserView getUserView() {
        return userView;
    }

    public boolean authUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User userResponse = HTTPRequest.authorizeLogin(user);

        if(userResponse != null){
            currentUser = userResponse;
            return true;
        }
        else
            return false;
    }

    public boolean createUser(String username) {
        User user = new User();
        user.setUsername(username);

        return HTTPRequest.createUser(user);
    }

    public ArrayList<Book> getBooks(){
        return HTTPRequest.getBooks();
    }
}