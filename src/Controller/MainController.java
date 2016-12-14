package Controller;

import Sdk.HTTPRequest;
import Models.Book;
import View.MainView;
import Models.User;

import java.util.ArrayList;

public class MainController {
    private User currentUser;


    public MainController(){
        new MainView(this).startMenu();
    }

    public boolean authUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = HTTPRequest.authorizeLogin(user);

        if(user!= null){
            currentUser = user;
            return true;
        }
        else
            return false;
    }

    public boolean addUser(String firstName, String lastName, String email, String username, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        return HTTPRequest.addUser(user);

    }


    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public boolean deleteUser() {
        boolean userDeleted = HTTPRequest.deleteUser(currentUser);
        if (userDeleted)
            currentUser = null;
        return userDeleted;
    }

    public boolean updateUser(String firstName, String lastName, String email, String username) {
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setUsername(username);

        boolean updated = HTTPRequest.updateUser(currentUser);

        if (updated)
            currentUser = null;

        return updated;

    }

    public ArrayList<Book> getBooks(){
        return HTTPRequest.getBooks();
    }

}
