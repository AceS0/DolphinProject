package Domain.TournamentClasses;

import java.util.ArrayList;

public class Tournament {
    private ArrayList<Competitor> competitors;
    private String name;
    private String date;
    private String place;
    private String category;
    private int id;

    public Tournament(int id,String name, String date, String place, String category, ArrayList<Competitor> competitors)
    {
        this.name = name;
        this.date = date;
        this.place = place;
        this.competitors = competitors;
        this.category = category;
        this.id = id;

    }

    public String getCompact() {
        StringBuilder competitorsCompact = new StringBuilder();
        for (Competitor competitor:competitors) {
            competitorsCompact.append(competitor.getCompact());
        }
        return id +";"+ name +";"+ date +";"+ place +";"+ category +";"+ competitorsCompact;
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

    public int getID() {
        return id;
    }

    public String getShortDescription() {
        return "ID: " + id +
                "\nName: " + name +
                "\nTook place:" + place +
                "\nDate: " + date +
                "\nNumber of competitors: " + competitors.size() +
                "\nCategory" + category;
    }


}
