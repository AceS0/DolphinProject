import java.util.ArrayList;

public class MemberHandling {
    private final ArrayList<Member> members = new ArrayList();
    
    public MemberHandling(){
        Member enes = new Member(1,"enes",60,50102030,
                "EnesZeki@dk",true,false,true);
        members.add(enes);
    }

    //Metode til at finde members
    public ArrayList<Member> memberLookUp(String search){
        ArrayList<Member> results = new ArrayList<>();
        if (search.matches(".*\\d.*")){
            for (Member member : members) {

                if (String.valueOf(member.getID()).matches(search)) {
                    results.add(member);
                }
            }
            return results;
        } else {
            for (Member member : members) {

                if (member.getName().toLowerCase().contains(search.toLowerCase())) {
                    results.add(member);
                }
            }
             return results;
        }
    }

    public String balancePaid(Member member){
        double balance = member.getBalance();
        double annualFee = member.getAnnualFee();
        double remainingBalance = balance - annualFee;
        if (balance >= annualFee){
            return member.getName() + "'s balance: " + balance + " the fee: " +
                    annualFee + " and the remaining balance: " + remainingBalance + " \nPayment recieved succesfully.";
        } else {
            return member.getName() + "'s balance: " + balance + " the fee: " +
                    annualFee + " and the remaining balance: " + remainingBalance + " \nPayment recieved. \nPlease, deposit the remaining as soon as possible.";
        }
    }

    public String sumMembershipFees() {
        double sum = 0;
        for(Member member : members) {
            sum += member.getAnnualFee();
        }
        return "The total annual membership fee is " + sum + " DKK";
    }

    public String memberList() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nHere is the list of members:");
        for (Member member : members) {
            toPrint.append("\n\n").append(member.toString());
        }
        return toPrint.toString();
    }

    public String memberListShort() {
       StringBuilder toPrint = new StringBuilder();
        for (Member member : members) {
            toPrint.append("\nID: ").append(member.getID()).append("\nName: ").append(member.getName());
        }
        return toPrint.toString();
    }

    public void addMember(Member member){
        members.add(member);
    }

    public void removeMember(Member member){
        members.remove(member);
    }
}