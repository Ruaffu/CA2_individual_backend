package dtos;

public class RandomDTO
{
    String activity;
    String type;
    String participants;
    String price;
    String link;
    String accessibility;

    public RandomDTO(String activity, String type, String participants, String price, String link, String accessibility)
    {
        this.activity = activity;
        this.type = type;
        this.participants = participants;
        this.price = price;
        this.link = link;
        this.accessibility = accessibility;
    }
}
