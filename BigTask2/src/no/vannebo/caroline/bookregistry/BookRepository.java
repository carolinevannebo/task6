package no.vannebo.caroline.bookregistry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {


    public static List<Book> getBooksFromFile(String path) {
        var list = new ArrayList<Book>();
        try (BufferedReader br = new BufferedReader( new FileReader(path)) ) {
            String line;
            int i = 0;
            String isbn = null, title = null, author = null, genre=null;
            int numberOfPages = 0;

            while ((line = br.readLine()) != null) {
                i++;
                switch (i) {
                    case 1 -> isbn = line;
                    case 2 -> title = line;
                    case 3 -> author = line;
                    case 4 -> numberOfPages = Integer.parseInt(line);
                    case 5 -> genre = line;
                    default -> { // We assume that this line is ---
                        list.add(new Book(isbn, title, author, numberOfPages, Enum.valueOf(Genre.class, genre)));
                        i = 0;
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
