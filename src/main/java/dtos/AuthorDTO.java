package dtos;

import entities.Author;
import entities.Book;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO
{
    String name;
    List<String> books = new ArrayList<>();

    public AuthorDTO(Author author)
    {
        this.name = author.getName();
        this.books = getBooks(author.getBooks());
    }

    public List<String> getBooks(List<Book> books){
        List<String> stringBooks = new ArrayList<>();
        for (Book book : books)
        {
            stringBooks.add(book.getTitle());
            stringBooks.add(book.getDescription());
            stringBooks.add(book.getIsbn());
        }
        return stringBooks;
    }

    public Author toAuthor () {
        return new Author(this.name);
    }
}
