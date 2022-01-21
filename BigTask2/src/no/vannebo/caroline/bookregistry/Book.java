package no.vannebo.caroline.bookregistry;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private int numberOfPages = 1;
    private Genre genre;

    public Book(String isbn, String title, String author, int pages, Genre genre){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        //Dette kan vel gjøres et annet sted?
        if(pages > 0){
            this.numberOfPages = pages;
        }
        this.genre = genre;
    }

    public Book(){
        this.title = "";
        this.author = "";
        this.genre = Genre.OTHER;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", genre=" + genre +
                '}';
    }

    public String printbook(){
        return title + " - " + author;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public int getNumberOfPages(){
        return numberOfPages;
    }

    public void setNumberOfPages(int pages){
        //Igjen? Grundig frøken!
        if(numberOfPages > 0){
            this.numberOfPages = pages;
        }
        else {
            System.out.println("Null sider i boka, tulling");
        }
    }

    public Genre getGenre(){
        return genre;
    }

    public void setGenre(Genre genre){
        this.genre = genre;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(String isb){
        this.isbn = isbn;
    }
}
