package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RandomDTO;
import utils.HttpUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("random")
public class RandomResource
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("bored")
    public Response getRandomActivity() throws IOException
    {
        String response = HttpUtils.fetchData("https://www.boredapi.com/api/activity");
        RandomDTO randomDTO = GSON.fromJson(response, RandomDTO.class);
        return Response
                .ok()
                .entity(GSON.toJson(randomDTO))
                .build();
    }
}