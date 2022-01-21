package BigTask;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Veldig bra så langt! Du finner masse her som kan brukes på baeldung, SoF og w3s.
public class BookRegister {

    private ArrayList<Book> books;
    private int numberOfBooks;
    private String bookFileName;
    //private static final String filepath = "/bok.txt";

    public BookRegister() throws FileNotFoundException {
        this.books = new ArrayList<>();
        this.numberOfBooks = 0;
        this.bookFileName = "bok.txt";
        readBooksFromFile();
    }

    public void readBooksFromFile() throws FileNotFoundException {

        File file = new File(bookFileName);
        Scanner scanner = new Scanner(file);

        while(scanner.hasNext()){
            String isbn = scanner.nextLine();
            String title = scanner.nextLine();
            String author = scanner.nextLine();
            int numberOfPages = Integer.parseInt(scanner.nextLine());
            String genre = scanner.nextLine();
            scanner.nextLine();
            books.add(new Book(isbn, title, author, numberOfPages, Enum.valueOf(Genre.class, genre)));
            System.out.println("reading books");
        }


        /*
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                String isbn = reader.readLine();
                String title = reader.readLine();
                String author = reader.readLine();
                int numberOfPages = Integer.parseInt(reader.readLine());
                String genre = reader.readLine();
                books.add(new Book(isbn, title, author, numberOfPages, Enum.valueOf(Genre.class, genre)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
    }

    public void printAllBook(){
        for (Book b : books) {
            System.out.println(b);

        }
    }

    public int getNumberOfBooks(){
        return numberOfBooks;
    }

    public boolean addBook(Book book){
        if(numberOfBooks <= 19){
            books.add(book);
            numberOfBooks++;
            return true;
        }
        return false;
    }

    /*
    public ArrayList<Book> allRegisteredBooks(){
        ArrayList<Book> result = new ArrayList<>(this.books);
        return result;
    }

     */

    public ArrayList<Book> booksInGenre(Genre genre){
        ArrayList<Book> result = new ArrayList<>();
        for(Book b : this.books){
            if(b.getGenre() == genre){
                result.add(b);
            }
        }
        return result;
    }

    public ArrayList<Book> booksByAuthor(String author){
        ArrayList<Book> result = new ArrayList<>();
        for(Book b : this.books){
            if(b.getAuthor().equalsIgnoreCase(author)){
                result.add(b);
            }
        }
        return result;
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public void removeBookByISBN(String isbn){
        books.removeIf(book -> book.getIsbn().equalsIgnoreCase(isbn));
    }
}
