import java.util.ArrayList;

public class Controller {
    private MemberHandling members = new MemberHandling();
    private Members member;

    public void addMemberToList(int ID, String name, int age, int number,
                                String mail, boolean actitvity, boolean stage, boolean competitive){

        members.addMember(new Members(ID, name, age, number, mail, actitvity, stage, competitive));
    }

    public void setMembershipFee(String name,boolean isActive, int age){
        ArrayList<Members> found = runSearch(name);
        if (found.size() == 1){
            found.getFirst().setMembershipFee(isActive,age);
        } else {
            System.out.println("in progress");
        }

    }

    public String editMember(Members member, String command, String edit) {
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


    public ArrayList<Members> runSearch(String search){
        ArrayList<Members> results = members.memberLookUp(search);
        return results;
    }

}
