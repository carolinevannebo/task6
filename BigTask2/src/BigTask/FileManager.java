package BigTask;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

/*
    private static final String filepath = "/bok.txt";

 */


    //Leser av filen, og går gjennom for hver linje og plasserer verdier i objektet. Ikke helt ferdig obv
    //Må få til å opprette nytt objekt hver gang "--" treffes, men det er ikke vanskelig :D

    /*
    public void readObjectFromFile() {

        ArrayList<Book> books = new ArrayList<>();
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
    }

     */

    public void writeObjectToFile(){
    //trenger en SkrivObjektTilFil her



    }
}