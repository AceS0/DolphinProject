package Domain.TournamentClasses;

import Domain.MemberClasses.Member;

import java.security.PublicKey;
import java.util.ArrayList;
public class Tournaments {

    private ArrayList<Tournament> tournamentList = new ArrayList<Tournament>();
    private int openID = 0;

    public void createTournament(String name, String date, String place, String category, ArrayList<Competitor> competitors)
    {
        tournamentList.add(new Tournament(nextID(),name, date, place, category, competitors));
    }

    public int nextID()
    {
        openID++;
        return openID;
    }
    public Competitor createCompetitor(Member member, double time)
    {
        return new Competitor(member.getName(), member.getID(), time);
    }

    public ArrayList<Tournament> tourneyLookUp(String search){
        ArrayList<Tournament> results = new ArrayList<>();
        if (tournamentList != null) {
            if (search.matches(".*\\d.*")) {
                for (Tournament tournament : tournamentList) {

                    if (String.valueOf(tournament.getID()).matches(search)) {
                        results.add(tournament);
                    }
                }
            } else {
                for (Tournament tournament : tournamentList) {

                    if (tournament.getName().toLowerCase().contains(search.toLowerCase())) {
                        results.add(tournament);
                    }
                }
            }
            return results;
        }
        else return null;
    }


    public String compactTourneys() {
        StringBuilder result = new StringBuilder();
        result.append(openID+"\n");
        for (Tournament tournament:tournamentList)
        {
            result.append(tournament.getCompact()+ "\n");
        }
        return result.toString();
    }

   public String listOfShortDescriptions()
   {
       StringBuilder result = new StringBuilder();
       for (Tournament t: tournamentList)
       {
           result.append(t.getName() + " " + t.getDate() + "\namount of contestants: " + t.getCompetitors().size() );
       }
       return result.toString();
   }

    public String listToShortDescriptions(ArrayList<Tournament> list) {
        StringBuilder result = new StringBuilder();
        for (Tournament t: list)
        {
            result.append(t.getName() + " " + t.getDate()+ "\namount of contestants: " + t.getCompetitors().size());
            result.append("\n");
        }
        return result.toString();
    }

    public void setOpenID(int id)
    {
        openID=id;
    }

    public void addTournamentByObject(Tournament checkFile) {
        tournamentList.add(checkFile);
    }
}
