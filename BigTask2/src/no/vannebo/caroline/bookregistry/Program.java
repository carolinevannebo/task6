package no.vannebo.caroline.bookregistry;

import java.io.FileNotFoundException;
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
            switch (choice){
                case 1:
                    bookRegister.printAllBook();
                    break;
                default:
                    System.out.println("The choice was not recognized: "+choice);
            }
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

    public static void main(String[] args) throws FileNotFoundException {

        Program prog = new Program();
        prog.handleUI();
    }

}
