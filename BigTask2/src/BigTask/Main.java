package BigTask;

/*
Sjekk ut:
- Buffered readers/writers.
- Generelt CRUD (Create, Read, Update, Delete) java formater, de fins over alt på GitHub
- Enum override toString for .valueOf(), eller try catch for en elendig
metode for å selecte de ulike enumsene(er det et ord?). (https://stackoverflow.com/questions/6667243/using-enum-values-as-string-literals)
- Oracle er også helt gull for bruk av metoder, du kan se hva slags metode som er innebygd i f.eks buffered reader!
- PS: du har vært flink!

 */

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Program prog = new Program();
        prog.handleUI();


        /*
        Book book = new Book();
        book.printbook();

         */
        /*
        BookRegister br = new BookRegister();
        br.readBooksFromFile();
        br.printAllBook();
         */
    }
}
