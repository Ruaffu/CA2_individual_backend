package facades;

import entities.Author;
import entities.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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

        try
        {

            if (em.find(Book.class, book.getId()) == null){
                book.addAuthor(checkAuthor(author));
                em.getTransaction().begin();
                em.merge(book);
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

    public Author checkAuthor(Author author){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a " +
                    "WHERE a.name =:name", Author.class);
            query.setParameter("name", author.getName());
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(author.getName());
            return new Author(author.getName());
        } finally {
            em.close();
        }

    }
}
