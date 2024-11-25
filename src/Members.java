import java.util.ArrayList;

public class Members {
    private int ID;
    private String name;
    private String lastname;
    private int age;
    private int number;
    private String mail;
    private boolean activity;
    private boolean stage;
    private boolean competitive;
    private ArrayList<Members> members = new ArrayList();

    public Members(int ID, String name, String lastname, int age, String mail, int number, boolean activity, boolean stage, boolean discipline) {
        this.ID = ID;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.number = number;
        this.mail = mail;
        this.activity = activity;
        this.stage = stage;
        this.competitive = discipline;
    }

    public void addMember(Members ID){
        members.add(ID);
    }

    public void removeMember(Members ID){
        members.remove(ID);
    }
}
