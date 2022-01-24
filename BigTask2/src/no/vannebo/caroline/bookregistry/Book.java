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
        if(pages > 0){
            this.numberOfPages = pages;
        }
        this.genre = genre;
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

    public String getAuthor(){
        return author;
    }

    public Genre getGenre(){
        return genre;
    }

    public String getIsbn(){
        return isbn;
    }

}
