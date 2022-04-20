package dtos;

import entities.Author;
import entities.Book;
import entities.Role;

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
}
