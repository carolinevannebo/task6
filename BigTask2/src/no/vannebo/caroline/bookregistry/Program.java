package no.vannebo.caroline.bookregistry;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    BookRegister bookRegister;
    Scanner scanner;

    public Program() {
        this.bookRegister = new BookRegister();
        this.scanner = new Scanner(System.in);
    }

    private void printMainMenu() {
        System.out.println("1: All books");
        System.out.println("2: Add book");
        System.out.println("3: Edit book");
        System.out.println("4: Find book by genre");
        System.out.println("5: Find book by author");
        System.out.println("6: Find book by ISBN");
        System.out.println("7: Remove book");
        System.out.println("8: Quit");
    }

    public void handleUI(){
        int choice = 0;

        while(choice != 8){
            printMainMenu();
            choice = getInteger(1, 8);
        }
    }

    private int getInteger(int min, int max){
        int choice = min -1;
        while(choice < min || choice > max){
            System.out.println("make a choice");
            try{
                choice = scanner.nextInt();
            } catch (InputMismatchException inputMismatchException){
                System.out.println("thats not a number between" +  min + "or" + max);
            }
            if(choice < min || choice > max){
                System.out.println("make a choice");
            }
        }
        scanner.nextLine(); // reading carridge return
        return choice;
    }

    private String captureString(String label){
        System.out.println(label);
        scanner.nextLine();
        return scanner.nextLine().trim();
    }

    public void menu(){
        boolean isRunning = true;

        while(isRunning){
            printMainMenu();
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    bookRegister.printAllBook();
                    break;
                case 2:
                    System.out.println("Create new book");
                    scanner.nextLine();
                    var isbn = captureString("Please enter ISBN: ");
                    var title = captureString("Please enter title: ");
                    var author = captureString("Please enter author: ");
                    var pages = Integer.valueOf(captureString("Please enter number of pages: ")).intValue();
                    var genre = Enum.valueOf(Genre.class, captureString("Please enter genre: " + bookRegister.listGenre()));
                    var book = new Book(isbn, title, author, pages, genre);
                    bookRegister.addBook(book);

                case 4:
                    System.out.println("Please enter genre: " + bookRegister.listGenre().toString());
                    for (Book b: bookRegister.booksInGenre(Enum.valueOf(Genre.class, scanner.next()))) {
                        System.out.println(b);
                    }
                    break;
                case 5:
                    System.out.println("Please enter author:" + bookRegister.listAuthors().toString());
                    for (Book b: bookRegister.booksByAuthor(scanner.next())){
                        System.out.println(b);
                    }
                default:
                    System.out.println("The choice was not recognized: "+choice);
            }
        }
    }

    public static void main(String[] args) {

        Program prog = new Program();
        prog.menu();
    }

}
