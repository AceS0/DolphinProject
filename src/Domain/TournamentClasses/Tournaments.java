package Domain.TournamentClasses;

import Domain.MemberClasses.Member;

import java.util.ArrayList;
public class Tournaments {

    private ArrayList<Tournament> tournamentList;

    private void createTournament(String name, String date, String place, ArrayList<Competitor> competitors, String category)
    {
        tournamentList.add(new Tournament(name, date, place, competitors, category));
    }

    private Competitor createCompetitor(Member member, double time)
    {
        return new Competitor(member.getName(), member.getID(), time);
    }

}
