package facades;

import entities.Author;
import entities.Book;
import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BookFacade
{
    private static EntityManagerFactory emf;
    private static BookFacade instance;

    public BookFacade()
    {
    }

    public static BookFacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }

    public Book registerNewBook(Book book, Author author){
        EntityManager em = emf.createEntityManager();
        author.addBook(book);

        try
        {
            if (em.find(Book.class, book.getId()) == null){
                em.getTransaction().begin();
                em.persist(book);
                em.getTransaction().commit();
            } else throw new Exception("Book already exists :(");
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            em.close();
        }
        return book;
    }
}
