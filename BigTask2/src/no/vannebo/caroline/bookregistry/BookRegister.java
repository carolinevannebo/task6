package no.vannebo.caroline.bookregistry;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class BookRegister {

    private final List<Book> books;

    public BookRegister() {
        this.books = BookRepository.getBooksFromFile( toPath("bok.txt"));
    }

    private String toPath(String filename){
        String path = null;
        try {
            var url = BookRegister.class.getResource(filename);
            path = Paths.get(url.toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }

   public void printAllBook(){
        for (Book b : books) {
            System.out.println(b);

        }
    }

    public void addBook(Book book){
        if(books.contains(book)) {
            return;
        }
        books.add(book);
    }

    public List<Book> booksInGenre(Genre genre){
        return books
                .stream()
                .filter(book -> book.getGenre().equals(genre) )
                .collect(Collectors.toList());
    }


    public List<Book> booksByAuthor(String author){
        return books
                .stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Book> booksByIsbn(String isbn){
        return books
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .sorted()
                .collect(Collectors.toList());
    }

    public void removeBookByISBN(String isbn){
        books.removeIf(book -> book.getIsbn().equalsIgnoreCase(isbn));
    }

    public List<String> listAuthors() {
        return books
                .stream()
                .map(book -> book.getAuthor())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> listGenre() {
        return books.stream()
                .map(book -> book.getGenre().toString())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> listIsbn() {
        return books.stream()
                .map(book -> book.getIsbn())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
