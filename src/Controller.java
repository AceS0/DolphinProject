public class Controller {
    private MemberHandling members = new MemberHandling();


    public void addMemberToList(int ID, String name, int age, int number,
                                String mail, boolean actitvity, boolean stage, boolean competitive){

        members.addMember(new Members(ID, name, age, number, mail, actitvity, stage, competitive));
    }

}
