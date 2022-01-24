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

    public static void main(String[] args) {
        Program program = new Program();
        try {
            program.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printMainMenu() {
        System.out.println("======== BOOK REGISTRY ========");
        System.out.println("1: All books");
        System.out.println("2: Add book");
        System.out.println("3: Edit book");
        System.out.println("4: Find books by genre");
        System.out.println("5: Find books by author");
        System.out.println("6: Find books by ISBN");
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
            try {
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
                        System.out.println("======== EDIT BOOK ========");
                        modifyBook();
                    }
                    case 4 -> {
                        System.out.println("======== FIND BOOKS BY GENRE ========");
                        scanner.nextLine();
                        var genre = captureString("Please enter genre " + bookRegister.listGenre().toString());
                        for(Book b : bookRegister.booksInGenre(genre) ) {
                            System.out.println(b);
                        }
                    }
                    case 5 -> {
                        System.out.println("======== FIND BOOKS BY AUTHOR ========");
                        scanner.nextLine();
                        var author = captureString("Please enter author: " + bookRegister.listAuthors().toString());
                        for (Book b : bookRegister.booksByAuthor(author) ) {
                            System.out.println(b);
                        }
                    }
                    case 6 -> {
                        System.out.println("======== FIND BOOKS BY ISBN ========");
                        scanner.nextLine();
                        var isbn = captureString("Please enter ISBN: " + bookRegister.listIsbn().toString());

                        try {
                            Integer.parseInt(isbn);
                        } catch (NumberFormatException e) {
                            throw new ISBNException("ISBN should be a number.");
                        }

                        for (Book b : bookRegister.booksByIsbn(isbn) ) {
                            System.out.println(b);
                        }
                    }
                    case 7 -> {
                        System.out.println("======== REMOVE BOOK ========");
                        scanner.nextLine();
                        removeBook();
                        bookRegister.printAllBook();
                    }
                    case 8 -> {
                        System.out.println("======== QUIT PROGRAM ========");
                        BookRepository.writeBooksToFile(bookRegister.getList());
                        isRunning = false;
                    }
                    default -> System.out.println("The choice was not recognized: " + choice);
                }
            } catch (ISBNException e) {
                System.out.println("An error occured, "+ e.getMessage());
                BookRepository.writeBooksToFile(bookRegister.getList());
                isRunning = false;
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
        var isbn = scanner.nextLine();

        var oldVersionOfBook = bookRegister.getBook(isbn);
        if (oldVersionOfBook == null) {
            System.out.println("Unable to modify book.");
            return;
        }
        System.out.println("Enter new title for: " + oldVersionOfBook.getTitle());
        oldVersionOfBook.setTitle(
                getNextLineOrDefault(
                        oldVersionOfBook.getTitle()
                )
        );

        System.out.println("Enter new author for: " + oldVersionOfBook.getAuthor());
        oldVersionOfBook.setAuthor(
                getNextLineOrDefault(oldVersionOfBook.getAuthor())
        );

        System.out.println("Enter number of pages for: " + oldVersionOfBook.getNumberOfPages());
        oldVersionOfBook.setNumberOfPages(
                Integer.parseInt(
                        getNextLineOrDefault(
                                String.valueOf(
                                        oldVersionOfBook.getNumberOfPages()))));

        System.out.println("Enter new genre for: " + oldVersionOfBook.getGenre() + bookRegister.listGenre().toString());
        oldVersionOfBook.setGenre(Genre.valueOf(getNextLineOrDefault(oldVersionOfBook.getGenre().toString().toUpperCase())));

    }

    private String getNextLineOrDefault(String defaultString) {
        return isEmptyOrDefault(defaultString, scanner.nextLine());
    }

    private String isEmptyOrDefault(String defaultString, String newString) {
        return newString.isEmpty() ? defaultString : newString;
    }

    private void removeBook() {
        var isbn = captureString("Please enter ISBN: ");
        bookRegister.removeBookByISBN(isbn);
    }

}
