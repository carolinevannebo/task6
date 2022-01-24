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
        return isbn + '\n' + title + '\n' + author + '\n' + numberOfPages + '\n' + genre + '\n' + "---" + '\n';
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

    public String getTitle() { return title; }

    public int getNumberOfPages() { return numberOfPages; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
