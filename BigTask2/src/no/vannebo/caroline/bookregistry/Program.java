package no.vannebo.caroline.bookregistry;

import java.io.IOException;
import java.util.Scanner;

public class Program {

    BookRegister bookRegister;
    Scanner scanner;

    public Program() {
        this.bookRegister = new BookRegister();
        this.scanner = new Scanner(System.in);
    }

    private void printMainMenu() {
        System.out.println("======== BOOK REGISTRY ========");
        System.out.println("1: All books");
        System.out.println("2: Add book");
        System.out.println("3: Edit book");
        System.out.println("4: Find book by genre");
        System.out.println("5: Find book by author");
        System.out.println("6: Find book by ISBN");
        System.out.println("7: Remove book");
        System.out.println("8: Quit");
    }

    private String captureString(String label) {
        System.out.println(label);
        var line = scanner.nextLine().trim();
        System.out.println("You entered: [" + line + "]");
        return line;
    }

    public void menu() throws IOException {
        boolean isRunning = true;

        while (isRunning) {
            printMainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("======== LIST ALL BOOKS ========");
                    scanner.nextLine();
                    bookRegister.printAllBook();
                }
                case 2 -> {
                    System.out.println("======== ADD NEW BOOK ========");
                    scanner.nextLine();
                    Book book = createNewBook();
                    bookRegister.addBook(book);
                    bookRegister.printAllBook();
                }
                case 3 -> {
                    System.out.println("======== EDIT BOOK ========"); // This case needs to be completed
                    modifyBook();
                }
                case 4 -> {
                    System.out.println("Please enter genre: " + bookRegister.listGenre().toString());
                    scanner.nextLine();
                    for (Book b : bookRegister.booksInGenre(Enum.valueOf(Genre.class, scanner.nextLine().toUpperCase().trim()))) {
                        System.out.println(b);
                    }
                }
                case 5 -> {
                    System.out.println("Please enter author: " + bookRegister.listAuthors().toString());
                    scanner.nextLine();
                    for (Book b : bookRegister.booksByAuthor(scanner.nextLine().trim())) {
                        System.out.println(b);
                    }
                }
                case 6 -> {
                    System.out.println("Please enter ISBN: " + bookRegister.listIsbn().toString());
                    scanner.nextLine();
                    for (Book b : bookRegister.booksByIsbn(scanner.nextLine().trim())) {
                        System.out.println(b);
                    }
                }
                case 7 -> {
                    System.out.println("======== REMOVE BOOK ========");
                    scanner.nextLine();
                    removeBook(System.in.toString());
                    bookRegister.printAllBook();
                }
                case 8 -> {
                    BookRepository.writeBooksToFile(bookRegister.getList());
                    isRunning = false;
                }
                default -> System.out.println("The choice was not recognized: " + choice);
            }
        }
    }

    private Book createNewBook() {
        var isbn = captureString("Please enter ISBN: ");
        var title = captureString("Please enter title: ");
        var author = captureString("Please enter author: ");
        var pages = Integer.valueOf(captureString("Please enter number of pages: "));
        var genre = Enum.valueOf(Genre.class, captureString("Please enter genre: " + bookRegister.listGenre().toString()).toUpperCase());
        return new Book(isbn, title, author, pages, genre);
    }


    private void modifyBook() {
        System.out.println("Please enter ISBN: " + bookRegister.listIsbn().toString());
        scanner.nextLine(); // leftover new line, unable to send input without it
        String isbn = scanner.nextLine();

        Book oldVersionOfBook = bookRegister.getBook(isbn);
        if (oldVersionOfBook == null) {
            System.out.println("Unable to modify book.");
            return;
        }
        System.out.println("Enter new title for: " + oldVersionOfBook.getTitle());
        String title = scanner.nextLine();

        System.out.println("Enter new author for: " + oldVersionOfBook.getAuthor());
        String author = scanner.nextLine();

        System.out.println("Enter number of pages for: " + oldVersionOfBook.getNumberOfPages());
        Integer pages = scanner.nextInt();

        System.out.println("Enter new genre for: " + oldVersionOfBook.getGenre() + bookRegister.listGenre().toString());
        scanner.nextLine(); // leftover new line, unable to send input without it
        Genre genre = Genre.valueOf(scanner.nextLine());

        Book newVersionOfBook = new Book(isbn, title, author, pages, genre);
        bookRegister.modifyBook(oldVersionOfBook, newVersionOfBook);
    }

    private void removeBook(String s) {
        var isbn = captureString("Please enter ISBN: ");
        bookRegister.removeBookByISBN(isbn);
    }

    public static void main(String[] args) {
        Program program = new Program();
        try {
            program.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
