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

    public void addBook(Book book) throws ISBNException {
        if(books
                .stream()
                .filter(f -> f.getIsbn().equals(book.getIsbn()))
                .count() > 0
                ) {
            throw new ISBNException("Could not add book because ISBN: " +book.getIsbn() + " already exit");
        }
        books.add(book);
    }

    public List<Book> booksInGenre(String genre){
        return books
                .stream()
                .filter(f -> f.getGenre()
                        .name()
                        .toUpperCase()
                        .startsWith(genre.toUpperCase())
                )
                .collect(Collectors.toList());
    }

    public List<Book> booksByAuthor(String author){
        return books
                .stream()
                .filter(f -> f.getAuthor().toUpperCase().startsWith(author.toUpperCase()))
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
                .map(Book::getIsbn)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public Book getBook(String isbn){
        return books
                .stream()
                .filter(f -> f.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElseThrow();
    }

    public List<Book> getList(){
        return this.books;
    }
}
