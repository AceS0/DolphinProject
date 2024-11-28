import java.util.ArrayList;

public class MemberHandling {
    private ArrayList<Members> members = new ArrayList();

    //abdyk
    public void addMember(Members member){
        members.add(member);
    }

    public void removeMember(Members ID){
        members.remove(ID);
    }
}