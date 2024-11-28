import java.util.ArrayList;

public class MemberHandling {
    private final ArrayList<Members> members = new ArrayList();
    
    public MemberHandling(){
        Members enes = new Members(1,"enes",60,50102030,
                "EnesZeki@dk",true,false,true);
        members.add(enes);
    }

    //Metode til at finde members
    public ArrayList<Members> memberLookUp(String search){
        ArrayList<Members> results = new ArrayList<>();
        if (search.matches(".*\\d.*")){
            for (Members member : members) {

                if (String.valueOf(member.getID()).matches(search)) {
                    results.add(member);
                }
            }
            return results;
        } else {
            for (Members member : members) {

                if (member.getName().toLowerCase().contains(search.toLowerCase())) {
                    results.add(member);
                }
            }
             return results;
        }
    }
    public void addMember(Members member){
        members.add(member);
    }

    public void removeMember(Members member){
        members.remove(member);
    }
}
