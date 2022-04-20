package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AuthorDTO;
import dtos.BookDTO;
import dtos.UserDTO;
import entities.Author;
import entities.Book;
import entities.User;
import facades.BookFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/book")
public class BookResource
{
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final BookFacade FACADE = BookFacade.getBookFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response getBooks()
    {
        List response = FACADE.getAllBooks();
        System.out.println(response);
        return Response
                .ok()
                .entity(GSON.toJson(response))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("newbook")
    @RolesAllowed({"admin"})
    public Response addNewBook(String data) {
        BookDTO bookDTO = GSON.fromJson(data, BookDTO.class);
        AuthorDTO authorDTO = GSON.fromJson(data, AuthorDTO.class);
        Book book = bookDTO.toBook();
        Author author = authorDTO.toAuthor();
        Book book1 = BookFacade.getBookFacade(EMF).registerNewBook(book,author);
        BookDTO bookDTO1 = new BookDTO(book1);
        return Response
                .ok()
                .entity(GSON.toJson(bookDTO1))
                .build();
    }
}