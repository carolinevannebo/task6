package no.vannebo.caroline.bookregistry;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    private String captureString(String label){
        System.out.println(label);
        var line = scanner.nextLine().trim();
        System.out.println("You entered: ["+line+"]");
        return line;
    }

    public void menu(){
        boolean isRunning = true;

        while(isRunning){
            printMainMenu();
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("======== LIST ALL BOOKS ========");
                    scanner.nextLine();
                    bookRegister.printAllBook();
                    break;
                case 2:
                    System.out.println("======== ADD NEW BOOK ========");
                    scanner.nextLine();
                    Book book = createNewBook();
                    bookRegister.addBook(book);
                    bookRegister.printAllBook();
                    break;
                case 3:
                    System.out.println("======== EDIT BOOK ========");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Please enter genre: " + bookRegister.listGenre().toString());
                    scanner.nextLine();
                    for (Book b: bookRegister.booksInGenre(Enum.valueOf(Genre.class, scanner.nextLine().trim()))) {
                        System.out.println(b);
                    }
                    break;
                case 5:
                    System.out.println("Please enter author:" + bookRegister.listAuthors().toString());
                    scanner.nextLine();
                    for (Book b: bookRegister.booksByAuthor(scanner.nextLine().trim())){
                        System.out.println(b);
                    }
                    break;
                case 8:
                    isRunning = false;
                    break;
                default:
                    System.out.println("The choice was not recognized: "+choice);
            }
        }
    }

    private Book createNewBook() {
        var isbn = captureString("Please enter ISBN: ");
        var title = captureString("Please enter title: ");
        var author = captureString("Please enter author: ");
        var pages = Integer.valueOf(captureString("Please enter number of pages: ")).intValue();
        var genre = Enum.valueOf(Genre.class, captureString("Please enter genre: " + bookRegister.listGenre().toString()));
        return new Book(isbn, title, author, pages, genre);
    }

    public static void main(String[] args) {
        Program program = new Program();
        program.menu();
    }

}
