package Sdk;
// Denne klasse gør det muligt for klienten at lave HTTP kald til serveren
import Encryption.Crypter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;
import Models.Book;
import Models.Curriculum;
import Models.User;

import java.util.ArrayList;

public class HTTPRequest {


    public static User authorizeLogin(User user) {

        String encryptedJson = Crypter.encryptDecryptXOR(new Gson().toJson(user));
        ClientResponse clientResponse = Connection.post(null, "/user/login", encryptedJson);

        User u = null;

        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            String response = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                u = new Gson().fromJson(response, User.class);
            }
        }
        return u;


    }
    public static boolean addUser(User user) {
        String encryptedJson = Crypter.encryptDecryptXOR(new Gson().toJson(user));
        ClientResponse clientResponse = Connection.post(null, "/user/", encryptedJson);
        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            if (clientResponse.getStatus() == 200) {
                return true;
            }
        }
        return false;
    }

    public static boolean deleteUser(User currentUser) {
        ClientResponse clientResponse = Connection.delete(currentUser.getToken(), "/user/" + currentUser.getUserID());
        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            if (clientResponse.getStatus() == 200) {
                return true;
            }

        }
        System.out.println("false");
        return false;
    }
    public static boolean updateUser(User currentUser) {
        String encryptedJson = Crypter.encryptDecryptXOR(new Gson().toJson(currentUser));
        ClientResponse clientResponse = Connection.put(currentUser.getToken(), "/user/" + currentUser.getUserID(), encryptedJson);

        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            if (clientResponse.getStatus() == 200) {
                return true;
            }
        }
        return false;

    }

    public static ArrayList<Book> getBooks() {
        ClientResponse clientResponse = Connection.get("book/");
        ArrayList<Book> books = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                books = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Book>>() {
                }.getType());
            } else {
                System.out.println("Server error");
            }
        }
        return books;
    }

    public static Book getBook(int id) {
        ClientResponse clientResponse = Connection.get("book/" + id);
        Book book = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                book = new Gson().fromJson(decryptedJson, Book.class);
            } else {
                System.out.println("Server error");
            }
        }
        return book;
    }


    public static ArrayList<Curriculum> getCurriculums() {
        ClientResponse clientResponse = Connection.get("/curriculum");
        ArrayList<Curriculum> curriculums = null;

        if (clientResponse == null) {
            System.out.println("No sdk ");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                curriculums = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Curriculum>>() {
                }.getType());
            } else {
                System.out.println("Error");
            }
        }
        return curriculums;
    }

    public static ArrayList<Book> getCurriculumBooks(int curriculumId) {
        ClientResponse clientResponse = Connection.get("/curriculum/" + curriculumId + "/books");
        ArrayList<Book> books = null;

        if (clientResponse == null) {
            System.out.println("No sdk  ");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                books = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Book>>() {
                }.getType());
            } else {
                System.out.println("Error");
            }
        }
        return books;
    }

}