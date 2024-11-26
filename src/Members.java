import java.util.ArrayList;

public class Members {
    private int ID;
    private String firstName;
    private String lastname;
    private int age;
    private int number;
    private String mail;
    private boolean activity;
    private boolean stage;
    private boolean competitive;


    public Members(int ID, String name, String lastname, int age, int number, String mail, boolean activity, boolean stage, boolean discipline) {
        this.ID = ID;
        this.firstName = name;
        this.lastname = lastname;
        this.age = age;
        this.number = number;
        this.mail = mail;
        this.activity = activity;
        this.stage = stage;
        this.competitive = discipline;
    }
}
