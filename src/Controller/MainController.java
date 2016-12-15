package Controller;

import Sdk.HTTPRequest;
import Models.Book;
import View.MainView;
import Models.User;

import java.util.ArrayList;

public class MainController {
    private User currentUser;

// starter "startMenu" op som den første menu
    public MainController(){
        new MainView(this).startMenu();
    }
// denn metode bekræfter, om brugeren har rettigheder til at logge ind
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
//Denne metode opretter en bruger
    public boolean addUser(String firstName, String lastName, String email, String username, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        return HTTPRequest.addUser(user);

    }

//Denne metode sætter user til current user, som bruges til at logge ud og ind, opdatere og slette
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    //denne metode sletter en bruger
    public boolean deleteUser() {
        boolean userDeleted = HTTPRequest.deleteUser(currentUser);
        if (userDeleted)
            currentUser = null;
        return userDeleted;
    }
//Denne metode opdaterer en brugers oplysninger
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
// Denne metode henter bøger
    public ArrayList<Book> getBooks(){
        return HTTPRequest.getBooks();
    }

}
