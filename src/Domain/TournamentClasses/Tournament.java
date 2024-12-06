package Domain.TournamentClasses;

import java.util.ArrayList;

public class Tournament {
    private ArrayList<Competitor> competitors;
    private String name;
    private String date;
    private String place;
    private String category;

    public Tournament(String name, String date, String place, ArrayList<Competitor> competitors, String category)
    {
        this.name = name;
        this.date = date;
        this.place = place;
        this.competitors = competitors;
        this.category = category;

    }


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }
}
