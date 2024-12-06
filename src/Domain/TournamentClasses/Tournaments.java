package Domain.TournamentClasses;

import Domain.MemberClasses.Member;

import java.util.ArrayList;
public class Tournaments {

    private ArrayList<Tournament> tournamentList;
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
        result.append("Contains all tournaments on record\n");
        result.append(openID+"\n");
        for (Tournament tournament:tournamentList)
        {
            result.append(tournament.getCompact()+ "\n");
        }
        return null;
    }
}
