package Domain.MemberClasses;

import java.util.Comparator;
import java.util.Objects;


public class Member implements Comparable {
    private int ID;
    private String name;
    private int age;
    private int number;
    private String mail;
    private boolean isActive;
    private boolean isCompetitive;
    private double debt = 0;
    private double balance;
    private String butterfly = "Nothing";
    private String crawl = "Nothing";
    private String backstroke = "Nothing";
    private String breaststroke = "Nothing";

    //separation for what can be done automatically or as a final
    private double annualFee;
    private boolean isSenior;
    private boolean hasPaid;


    //sort function:
    public static Comparator<Member> ID_COMPARATOR = Comparator.comparing(Member::getID);
    public static Comparator<Member> NAME_COMPARATOR = Comparator.comparing(Member::getName);
    public static Comparator<Member> AGE_COMPARATOR = Comparator.comparing(Member::getAge);
    public static Comparator<Member> NUMBER_COMPARATOR = Comparator.comparing(Member::getNumber);
    public static Comparator<Member> MAIL_COMPARATOR = Comparator.comparing(Member::getMail);
    public static Comparator<Member> ISACTIVE_COMPARATOR = Comparator.comparing(Member:: getIsActive);
    public static Comparator<Member> ISSENIOR_COMPARATOR = Comparator.comparing(Member::getIsSenior);
    public static Comparator<Member> ISCOMPETITIVE_COMPARATOR = Comparator.comparing(Member::getIsCompetitive);


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
                  boolean isCompetitive, double debt, double balance, String butterfly, String crawl, String backstroke, String breaststroke) {
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
        this.butterfly = butterfly;
        this.crawl = crawl;
        this.backstroke = backstroke;
        this.breaststroke = breaststroke;
        setMembershipFee(isActive, age);
        if (debt == 0)
        {
            this.hasPaid = true;
        }else this.hasPaid = false;
    }
    public Member() {
    }

    public String getCompact() {
        return ID+";"+ name +";"+ age +";"+ number +";"+ mail +";"+ isActive +";"+ isSenior +";"+ isCompetitive +";"+ debt +";"+ balance + ";" + butterfly + ";" + crawl + ";" + backstroke + ";" + breaststroke + ";";
    }
    public void setMembershipFee (boolean isActive, int age) {
        final int juniorRate = 1000;
        final int seniorRate = 1600;
        final double seniorDiscount = 25;
        final double seniorDiscountedRate = (seniorRate*(1-(seniorDiscount/100)));
        final int passiveRate = 500;
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
        } catch (Exception ignored) {
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

    public Boolean gethasPaid()
    {
        checkpaid();
        return hasPaid;
    }

    private void checkpaid() {
        if (debt<=0) hasPaid=true;
        else hasPaid=false;
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

    public void yearpassed() {
        debt += annualFee;
    }

    public String getShortDescription() {
        return name+ "\n"+ mail;
    }

    public void setButterfly(String newString){
        if (Objects.equals(butterfly, "Nothing")) {
            butterfly = newString;
            return;
        }
        double newTime = getTimeFromSTring(newString);
        double oldTime = getTimeFromSTring(butterfly);
        if (newTime < oldTime) butterfly= newString;

    }

    public static double getTimeFromSTring(String timeString)
    {
        String[] splitTime1 = timeString.split(" ");
        return Double.parseDouble(splitTime1[3]);
    }

    public void setCrawl(String timeString){
        if (Objects.equals(crawl, "Nothing")) {
            crawl = timeString;
            return;
        }
        double newTime = getTimeFromSTring(timeString);
        double oldTime = getTimeFromSTring(crawl);
        if (newTime < oldTime) this.crawl=timeString;

    }

    public void setBackstroke(String timeString){
        if (Objects.equals(backstroke, "Nothing")) {
            backstroke = timeString;
            return;
        }
        double newTime = getTimeFromSTring(timeString);
        double oldTime = getTimeFromSTring(backstroke);
        if (newTime < oldTime) backstroke=timeString;

    }

    public void setBreaststroke(String timeString){
        if (Objects.equals(breaststroke, "Nothing")) {
            breaststroke = timeString;
            return;
        }
        double newTime =getTimeFromSTring(timeString);
        double oldTime = getTimeFromSTring(breaststroke);
        if (newTime < oldTime) this.breaststroke=timeString;
    }

    public String getButterfly(){
        if (butterfly == null) {
            return "Butterfly: There is no record.";
        } else {
            return butterfly;
        }
    }

    public String getCrawl(){
        if (crawl == null) {
            return "Crawl: There is no record.";
        } else {
            return crawl;
        }
    }

    public String getBackstroke(){
        if (backstroke == null) {
            return "Backstroke: There is no record.";
        } else {
            return backstroke;
        }
    }

    public String getBreaststroke(){
        if (breaststroke == null) {
            return "Breaststroke: There is no record.";
        } else {
            return breaststroke;
        }
    }




    @Override
    public int compareTo(Object o) {
        return 0;
    }
}