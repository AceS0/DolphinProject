import java.util.ArrayList;

public class Members {
    private int ID;
    private String name;
    private String lastname;
    private int age;
    private int number;
    private String mail;
    private boolean isActive;
    private boolean isSenior;
    private boolean isCompetitive;
    private double annualFee;
    private final int juniorRate = 1000;
    private final int seniorRate = 1600;
    private final double seniorDiscount = 25;
    private final double seniorDiscountedRate = (seniorRate*(1-(seniorDiscount/100)));
    private final int passiveRate = 500;
    private ArrayList<Members> members = new ArrayList();

    public Members(int ID, String name, String lastname, int age, String mail, int number, boolean isActive, boolean isSenior,
                   boolean isCompetitive) {
        this.ID = ID;
        this.name = name;
        this.lastname = lastname;
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

    public void setMembershipFee (boolean isActive, int age) {
        try {
            if (isActive == true) {
                if (age < 18) {
                    setAnnualFee(juniorRate);
                } else if (age <= 60){
                    setAnnualFee(seniorRate);
                } else {
                    setAnnualFee(seniorDiscountedRate);
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

    public int getAge() {
        return age;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String toString() {
        return  "ID: "+ID+
                "\nName: "+name+
                "\nLastname: "+lastname+
                "\nAge: "+age+
                "\nPhone number: "+number+
                "\nMail: "+mail+
                "\nIs active: "+isActive+
                "\nStage: "+isSenior+
                "\nIs competitive: "+isCompetitive+
                "\nAnnual fee: "+getAnnualFee()+" DKK";
    }
}