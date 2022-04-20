package dtos;

import entities.Author;
import entities.Book;
import entities.Role;
import entities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookDTO
{
    String title;
    String isbn;
    String description;
    List<String> authors = new ArrayList<>();

    public BookDTO(Book book)
    {
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.description = book.getDescription();
        this.authors = getAuthorsList(book.getAuthors());
    }

    public List<String> getAuthorsList(List<Author> authors){
        List<String> stringAuthors = new ArrayList<>();
        for (Author auth : authors)
        {
            stringAuthors.add(auth.getName());
        }
        return stringAuthors;
    }

    public static List<BookDTO> getDtos(List<Book> rms){
        List<BookDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new BookDTO(rm)));
        return rmdtos;
    }

    public Book toBook () {
        return new Book(this.title, this.isbn,this.description);
    }
}
