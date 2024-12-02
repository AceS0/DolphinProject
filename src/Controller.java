import java.util.ArrayList;

public class Controller {
    private final MemberHandling members = new MemberHandling();

    public void addMemberToList(int ID, String name, int age, int number,
                                String mail, boolean isActive, boolean isSenior, boolean isCompetitive){

        members.addMember(new Member(ID, name, age, number, mail, isActive, isSenior, isCompetitive));
    }

    public void removeMemberFromList(Member member){

        members.removeMember(member);
    }

    public String sumMembershipFees() {
        return members.sumMembershipFees();
    }

    public String getBalancePayment(Member member) {
        return members.balancePaid(member);
    }

    public MemberHandling getMembers(){
        return members;
    }

    public void setMembershipBalanceFee(int memberID,String name,boolean isActive, int age, double balance){
        ArrayList<Member> found = runSearch(name);
        if (found.size() == 1){
            found.getFirst().setMembershipFee(isActive,age);
            found.getFirst().setBalance(balance);
        } else {
            ArrayList<Member> foundID = runSearch(String.valueOf(memberID));
            foundID.getFirst().setMembershipFee(isActive,age);
            foundID.getFirst().setBalance(balance);
        }
    }

    public String editMember(Member member, String command, String edit) {
        switch (command) {
            case "id":
                String editnum1 = edit.replaceAll("[^0-9]", "");
                member.setID(Integer.parseInt(editnum1));
                return edit;
            case "name":
                member.setName(edit);
                return edit;
            case "age":
                String editnum2 = edit.replaceAll("[^0-9]", "");
                member.setAge(Integer.parseInt(editnum2));
                return edit;
            case "number":
                String editnum3 = edit.replaceAll("[^0-9]", "");
                member.setNumber(Integer.parseInt(editnum3));
                return edit;
            case "mail":
                member.setMail(edit);
                return edit;
            case "active":
                if (edit.equalsIgnoreCase("yes")) {
                    member.setActive(true);
                } else member.setActive(false);
                return edit;
            case "senior":
                if (edit.equalsIgnoreCase("yes")) {
                    member.setSenior(true);
                } else member.setSenior(false);
                return edit;
            case "competitive":
                if (edit.equalsIgnoreCase("yes")) {
                    member.setCompetitive(true);
                } else member.setCompetitive(false);
                return edit;
            default:
                return null;
        }
    }

    public ArrayList<Member> runSearch(String search){
        ArrayList<Member> results = members.memberLookUp(search);
        return results;
    }
}