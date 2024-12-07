package Domain.MemberClasses;

public class Member {
    private int ID;
    private String name;
    private int age;
    private int number;
    private String mail;
    private boolean isActive;
    private boolean isCompetitive;
    private double debt = 0;
    private double balance;

    //separation for what can be done automatically or as a final
    private double annualFee;
    private boolean isSenior;
    private boolean hasPaid;
    private final int juniorRate = 1000;
    private final int seniorRate = 1600;
    private final double seniorDiscount = 25;
    private final double seniorDiscountedRate = (seniorRate*(1-(seniorDiscount/100)));
    private final int passiveRate = 500;


    public Member(int ID, String name, int age, int number, String mail, boolean isActive, boolean isSenior,
                  boolean isCompetitive) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.number = number;
        this.mail = mail;
        this.isActive = isActive;
        this.isSenior = isSenior;
        this.isCompetitive = isCompetitive;
        setMembershipFee(isActive, age);
    }
    //overload for the save files
    public Member(int ID, String name, int age, int number, String mail, boolean isActive, boolean isSenior,
                  boolean isCompetitive, double debt, double balance) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.number = number;
        this.mail = mail;
        this.isActive = isActive;
        this.isSenior = isSenior;
        this.isCompetitive = isCompetitive;
        this.debt = debt;
        this.balance = balance;
        setMembershipFee(isActive, age);
        if (debt == 0)
        {
            this.hasPaid = true;
        }else this.hasPaid = false;
    }

    public String getCompact() {
        return ID+";"+ name +";"+ age +";"+ number +";"+ mail +";"+ isActive +";"+ isSenior +";"+ isCompetitive +";"+ debt +";"+ balance;
    }
    public void setMembershipFee (boolean isActive, int age) {
        try {
            if (isActive) {
                if (age < 18) {
                    setAnnualFee(juniorRate);
                } else if (age > 60) {
                    setAnnualFee(seniorRate);
                } else {
                    setAnnualFee(seniorDiscountedRate);
                }
            } else {
                setAnnualFee(passiveRate);
            }
        } catch (Exception e) {
        }
    }

    public void setPaidStatus(boolean hasPaid){
        this.hasPaid = hasPaid;
    }

    public String getPaidStatus(){
        if (hasPaid){
            return "has paid";
        } else {
            return "has not paid";
        }
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
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


    public String toString() {
        return  "ID: "+ID+
                "\nName: "+name+
                "\nAge: "+age+
                "\nPhone number: "+number+
                "\nMail: "+mail+
                "\nIs active: "+isActive+
                "\nIs senior: "+isSenior+
                "\nIs competitive: "+isCompetitive+
                "\nAnnual fee: "+getAnnualFee()+" DKK"+
                "\ndebt: " + getDebt()+
                "\nBalance: "+getBalance()+" DKK" +
                "\nPayment status: "+getPaidStatus()+"\n";
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public void yearpassed()
    {
        debt =+ annualFee;
    }
    public String getShortDescription()
    {
        return name+ "\n"+ mail;
    }


}