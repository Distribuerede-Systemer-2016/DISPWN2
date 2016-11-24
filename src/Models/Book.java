package Models;

public class Book extends Curriculum{

    private String title;
    private int version;
    private double isbn;
    private int bookID;
    private double priceSAXO;
    private double priceCDON;
    private double priceAB;
    private String author;
    private String Publisher;


    public Book(String title, int version, double isbn, int bookID, double priceSAXO, double priceCDON, double priceAB, String author, String publisher) {
        this.title = title;
        this.version = version;
        this.isbn = isbn;
        this.bookID = bookID;
        this.priceSAXO = priceSAXO;
        this.priceCDON = priceCDON;
        this.priceAB = priceAB;
        this.author = author;
        Publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public double getIsbn() {
        return isbn;
    }

    public void setIsbn(double isbn) {
        this.isbn = isbn;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public double getPriceSAXO() {
        return priceSAXO;
    }

    public void setPriceSAXO(double priceSAXO) {
        this.priceSAXO = priceSAXO;
    }

    public double getPriceCDON() {
        return priceCDON;
    }

    public void setPriceCDON(double priceCDON) {
        this.priceCDON = priceCDON;
    }

    public double getPriceAB() {
        return priceAB;
    }

    public void setPriceAB(double priceAB) {
        this.priceAB = priceAB;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }
}

