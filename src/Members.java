import java.util.ArrayList;

public class Members {
    private int ID;
    private String name;
    private int age;
    private int number;
    private String mail;
    private boolean isActive;
    private boolean isSenior;
    private boolean isCompetitive;
    private final ArrayList<Members> members = new ArrayList();
    private double annualFee;
    private final int juniorRate = 1000;
    private final int seniorRate = 1600;
    private final double seniorDiscount = 25;
    private final double seniorDiscountedRate = (seniorRate*(1-(seniorDiscount/100)));
    private final int passiveRate = 500;

    public Members(int ID, String name, String lastname, int age, String mail, int number, boolean isActive, boolean isSenior,
                   boolean isCompetitive) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.number = number;
        this.mail = mail;
        this.isActive = isActive;
        this.isSenior = isSenior;
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

    public boolean isSenior() {
        return isSenior;
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

    public void setMembershipFee (boolean isActive, int age) {
        try {
            if (isActive == true) {
                if (age < 18) {
                    setAnnualFee(juniorRate);
                } else if (age < 60) {
                    setAnnualFee(seniorDiscountedRate);
                } else if (age >= 60){
                    setAnnualFee(seniorRate);
                }
            } else if (isActive == false) {
                setAnnualFee(passiveRate);
            }
        } catch (Exception e) {
            System.out.println("Input not valid!");
        }
    }

    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }

    public double getAnnualFee() {
        return annualFee;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String toString() {
        return  "ID: "+ID+
                "\nName: "+name+
                "\nAge: "+age+
                "\nPhone number: "+number+
                "\nMail: "+mail+
                "\nIs active: "+isActive+
                "\nStage: "+isSenior+
                "\nIs competitive: "+isCompetitive+
                "\nAnnual fee: "+getAnnualFee()+" DKK";
    }
}