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
            assert url != null;
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

    public void modifyBook(Book oldVersion, Book newVersion){
        if ( !oldVersion.getIsbn().equalsIgnoreCase(newVersion.getIsbn()) ){
            System.out.println("Unable to modify book, ISBN is not a match.");
            return;
        }
        books.remove(oldVersion);
        books.add(newVersion);
        System.out.println("Book successfully modified!");
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
                .filter(f -> f.getIsbn().startsWith(isbn))
                .collect(Collectors.toList());
    }

    public void removeBookByISBN(String isbn){
        books.removeIf(book -> book.getIsbn().equalsIgnoreCase(isbn));
    }

    public List<String> listAuthors() {
        return books
                .stream()
                .map(Book::getAuthor)
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

    public Book getBook(String isbn){
        for (Book b: books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)){
                return b;
            }
        }
        return null;
    }

    public List<Book> getList(){
        return this.books;
    }
}
