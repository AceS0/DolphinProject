package Domain.TournamentClasses;

import java.util.ArrayList;
import java.util.Comparator;

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
        this.category = category;
        this.id = id;
        this.competitors = competitors;
        competitors.sort(Competitor.TIME_COMPARATOR);

    }

    public String getCompact() {
        StringBuilder competitorsCompact = new StringBuilder();
        for (Competitor competitor:competitors) {
            competitorsCompact.append(competitor.getCompact() + ";");
        }
        return id +";"+ name +";"+ date +";"+ place +";"+ category +";"+ competitorsCompact;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
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

    public String getLongDescription()
    {

        return "ID: " + id +
                "\nName: " + name +
                "\nTook place:" + place +
                "\nDate: " + date +
                "\nNumber of competitors: " + competitors.size() +
                "\nCategory" + category +
                "\n" + competitorsToString();

    }

    public String competitorsToString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Competitors: \n");
        int placement = 1;
        for (Competitor c : competitors)
        {
            sb.append(placement+". ");
            placement++;
            sb.append(c.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Competitor> getStandings()
    {
        competitors.sort(Competitor.TIME_COMPARATOR);
        return competitors;
    }

}
