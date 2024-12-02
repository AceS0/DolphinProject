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
    private double annualFee;
    private final ArrayList<Members> members = new ArrayList<>();
    private final int juniorRate = 1000;
    private final int seniorRate = 1600;
    private final double seniorDiscount = 25;
    private final double seniorDiscountedRate = (seniorRate*(1-(seniorDiscount/100)));
    private final int passiveRate = 500;

    public Members(int ID, String name, int age, int number, String mail, boolean isActive, boolean isSenior,
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
  
    public void setMembershipFee (boolean isActive, int age) {
        try {
            if (isActive) {
                if (age < 18) {
                    setAnnualFee(juniorRate);
                } else if (age > 60) {
                    setAnnualFee(seniorDiscountedRate);
                } else {
                    setAnnualFee(seniorRate);
                }
            } else {
                setAnnualFee(passiveRate);
            }
        } catch (Exception e) {
            System.out.println("Input not valid!");
        }
    }

    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setSenior(boolean senior) {
        isSenior = senior;
    }

    public void setCompetitive(boolean competitive) {
        isCompetitive = competitive;
    }

    public double getAnnualFee() {
        return annualFee;
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

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsSenior() {
        return isSenior;
    }

    public boolean getIsCompetitive() {
        return isCompetitive;
    }

    public String toString() {
        return  "ID: "+ID+
                "\nName: "+name+
                "\nAge: "+age+
                "\nPhone number: "+number+
                "\nMail: "+mail+
                "\nIs active: "+isActive+
                "\nIs senior: "+isSenior+
                "\nIs competitive: "+isCompetitive+
                "\nAnnual fee: "+getAnnualFee()+" DKK";
    }
}