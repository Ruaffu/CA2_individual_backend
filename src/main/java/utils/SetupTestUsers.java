package utils;


import entities.Author;
import entities.Book;
import entities.Role;
import entities.User;
import facades.BookFacade;
import facades.UserFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "test123");
    User admin = new User("admin", "test123");
    User both = new User("user_admin", "test123");
    Book book1 = new Book("Akata Witch","9780142420911","great");
    Book book2 = new Book("The Three Musketeers","9781539444688","The Three Musketeers by Alexandre Dumas, unabridged republishing of a classic.");
    Book book3 = new Book("The Count of Monte Cristo","9780141392462","A beautiful new clothbound edition of Alexandre Dumas' classic novel of wrongful imprisonment, adventure and revenge.");
    Book book4 = new Book("Silmarillion","9780618391110","The story of the creation of the world and of the the First Age");
    Book book5 = new Book("The Hobbit","9780547951973","A great modern classic and the prelude to The Lord of the Rings");
    Book book6 = new Book("The Lord of the Rings","9780547951942"," One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them");
    Book book7 = new Book("Treasure Island","9780141192451","Part of Penguin's beautiful hardback Clothbound Classics series");

    Author author1 = new Author("Nnedi Okorafor");
    Author author2 = new Author("Mike Meyers");
    Author author3 = new Author("J.R.R. Tolkien");
    Author author4 = new Author("Robert Louis Stevenson");
    Author author5 = new Author("Alexandre Dumas");


    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
    {
      throw new UnsupportedOperationException("You have not changed the passwords");
    }

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
//    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
//    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    UserFacade.getUserFacade(emf).registerNewUser(user);
    BookFacade.getBookFacade(emf).registerNewBook(book1, author1);
    BookFacade.getBookFacade(emf).registerNewBook(book2, author5);
    BookFacade.getBookFacade(emf).registerNewBook(book3, author5);
    BookFacade.getBookFacade(emf).registerNewBook(book4, author3);
    BookFacade.getBookFacade(emf).registerNewBook(book5, author3);
    BookFacade.getBookFacade(emf).registerNewBook(book6, author3);
    BookFacade.getBookFacade(emf).registerNewBook(book7, author4);
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test123"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
