package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.BookFacade;
import utils.EMF_Creator;

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
    public Response getBooks()
    {
        List response = FACADE.getAllBooks();
        System.out.println(response);
        return Response
                .ok()
                .entity(GSON.toJson(response))
                .build();
    }
}