import java.util.ArrayList;

public class Members {
    private int ID;
    private String name;
    private int age;
    private int number;
    private String mail;
    private boolean isActive;
    private boolean stage;
    private boolean isCompetitive;
    private final ArrayList<Members> members = new ArrayList();


    public Members(int ID, String name, int age, String mail, int number, boolean isActive, boolean stage, boolean isCompetitive) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.number = number;
        this.mail = mail;
        this.isActive = isActive;
        this.stage = stage;
        this.isCompetitive = isCompetitive;
    }

    public void addMember(Members ID){
        members.add(ID);
    }

    public void removeMember(Members ID){
        members.remove(ID);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getNumber() {
        return number;
    }

    public String getMail() {
        return mail;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isStage() {
        return stage;
    }

    public boolean isCompetitive() {
        return isCompetitive;
    }

    public ArrayList<Members> getMembers() {
        return members;
    }

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
}
