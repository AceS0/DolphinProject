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


    public ArrayList<Members> runSearch(String search){
        ArrayList<Members> results = members.memberLookUp(search);
        return results;
    }

}
