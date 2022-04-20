package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String isbn;
    private String description;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors = new ArrayList<>();

    public Book()
    {
    }

    public Book(String title, String isbn, String description)
    {
        this.title = title;
        this.isbn = isbn;
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<Author> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<Author> authors)
    {
        this.authors = authors;
    }

    public void addAuthor(Author author){
        authors.add(author);
    }
}
