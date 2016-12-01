package Models;
//Kun oprettet getters i forhold til serveren
public class Book extends Curriculum{

    private int bookID;
    private double ISBN;
    private String publisher;
    private String title;
    private String author;
    private double priceAB;
    private double priceSAXO;
    private double priceCDON;
    private int version;

    public Book(int bookID, String publisher, String title, String author, int version, double ISBN, double priceAB, double priceSAXO, double priceCDON) {
        this.bookID = bookID;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.version = version;
        this.ISBN = ISBN;
        this.priceAB = priceAB;
        this.priceSAXO = priceSAXO;
        this.priceCDON = priceCDON;

    }

    public int getBookID() {
        return bookID;
    }

    public double getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPriceAB() {
        return priceAB;
    }

    public double getPriceSAXO() {
        return priceSAXO;
    }

    public double getPriceCDON() {
        return priceCDON;
    }

    public int getVersion() {
        return version;
    }
}


