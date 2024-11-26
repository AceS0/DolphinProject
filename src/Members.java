import java.util.ArrayList;

public class Members {
    private int ID;
    private String name;
    private int age;
    private int number;
    private String mail;
    private boolean activity;
    private boolean stage;
    private boolean competitive;


    public Members(int ID, String name, int age, int number, String mail, boolean activity, boolean stage, boolean discipline) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.number = number;
        this.mail = mail;
        this.activity = activity;
        this.stage = stage;
        this.competitive = discipline;
    }
}
