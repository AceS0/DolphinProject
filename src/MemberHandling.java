import java.util.ArrayList;

public class MemberHandling {
    private ArrayList<Members> members = new ArrayList();

    //Metode til at finde members
    public ArrayList<Members> memberLookUp(String search){
        ArrayList<Members> results = new ArrayList<>();
        for (Members member : members) {

            if (member.getName().toLowerCase().contains(search.toLowerCase())) {
                results.add(member);
            }
        }
        return results;
    }

//abdyk
    public void addMember(Members member){
        members.add(member);
    }

    public void removeMember(Members ID){
        members.remove(ID);
    }
}
