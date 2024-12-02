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

    public String sumMembershipFees() {
        double sum = 0;
        for(Members member : members) {
            sum += member.getAnnualFee();
        }
        return "The total annual membership fee is " + sum + " DKK";
    }

    public String memberList() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nHere is the list of members:");
        for (Members member : members) {
            toPrint.append("\n\n").append(member.toString());
        }
        return toPrint.toString();
    }

    public String memberListShort() {
       StringBuilder toPrint = new StringBuilder();
        for (Members member : members) {
            toPrint.append("\nID: ").append(member.getID()).append("\nName: ").append(member.getName());
        }
        return toPrint.toString();
    }

    public void addMember(Members member){
        members.add(member);
    }

    public void removeMember(Members member){
        members.remove(member);
    }
}