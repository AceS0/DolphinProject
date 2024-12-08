package Domain.MemberClasses;

import java.util.ArrayList;

public class Members {
    private final ArrayList<Member> members = new ArrayList();
    private int openId = 0;
    private final ArrayList<Member> butterflyList = new ArrayList<>();
    private final ArrayList<Member> crawlList = new ArrayList<>();
    private final ArrayList<Member> backstrokeList = new ArrayList<>();
    private final ArrayList<Member> breaststrokeList = new ArrayList<>();
    
    public Members(){
        /*Member enes = new Member(0,"enes",60,50102030,
                "EnesZeki@dk",true,false,true);
        members.add(enes);*/
    }

    public int generateID()
    {
        openId++;
        return openId;
    }

    public void setOpenID(int id)
    {
        openId = id;
    }

    //Metode til at finde members
    public ArrayList<Member> memberLookUp(String search){
        ArrayList<Member> results = new ArrayList<>();
        if (search.matches(".*\\d.*")){
            for (Member member : members) {

                if (String.valueOf(member.getID()).matches(search)) {
                    results.add(member);
                }
            }
        } else {
            for (Member member : members) {

                if (member.getName().toLowerCase().contains(search.toLowerCase())) {
                    results.add(member);
                }
            }
        }
        return results;
    }

    public ArrayList<Member> memberPaidList(boolean search){
        ArrayList<Member> results = new ArrayList<>();
        if (search) {
            for (Member member : members) {
                if (member.getPaidStatus().equals("has paid")) {
                    results.add(member);
                }
            }
        } else {
            for (Member member : members) {
                if (member.getPaidStatus().equals("has not paid")) {
                    results.add(member);
                }
            }
        }
        return results;
    }

    public String balancePaid(Member member){
        double balance = member.getBalance();
        double debt = member.getDebt();
        if (balance >= debt){
            return member.getName() + "'s balance: " + balance + " the fee: " +
                    debt;
        } else {
            return member.getName() + "'s balance: " + balance + " the fee: " +
                    debt +"\nPlease, deposit the needed balance to pay the full fee.\n";
        }
    }

    public String depositMemberBalance(String name, double balance){
        ArrayList<Member> found = memberLookUp(name);
        if (found.isEmpty()) {
            return "The member you asked for does not exist, please try again.";
        }
        double memberBalance = found.getFirst().getBalance();
        double totalBalance = memberBalance + balance;
        if (found.size() == 1) {
            found.getFirst().setBalance(totalBalance);
            return found.getFirst().getName() + " has deposited " + balance + " DKK.";
        } else {
            StringBuilder toPrint = new StringBuilder();
            toPrint.append("We found more than 1 member, please try again.\n");
            for (Member member : found){
                toPrint.append("\nID: ").append(member.getID()).append("\nName: ").append(member.getName());
            }
            toPrint.append("\n\"HINT\" Try to use ID instead of name.");
            return toPrint.toString();
        }
    }

    public void setMembershipBalanceFee(int memberID,String name,boolean isActive, int age, double balance){
        ArrayList<Member> found = memberLookUp(name);
        if (found.size() == 1){
            found.getFirst().setMembershipFee(isActive,age);
            found.getFirst().setBalance(balance);
        } else {
            ArrayList<Member> foundID = memberLookUp(String.valueOf(memberID));
            foundID.getFirst().setMembershipFee(isActive,age);
            foundID.getFirst().setBalance(balance);
        }
    }

    public String sumMembershipFees() {
        double sum = 0;
        for(Member member : members) {
            sum += member.getAnnualFee();
        }
        return "The total annual membership fee is " + sum + " DKK";
    }

    public String memberList() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nHere is the list of members:");
        for (Member member : members) {
            toPrint.append("\n\n").append(member.toString());
        }
        return toPrint.toString();
    }

    public String memberListShort() {
       StringBuilder toPrint = new StringBuilder();
        for (Member member : members) {
            toPrint.append("\nID: ").append(member.getID()).append("\nName: ").append(member.getName());
        }
        return toPrint.toString();
    }

    public Member addMember(String name, int age, int number,
                          String mail, boolean isActive, boolean isSenior, boolean isCompetitive){
        Member added = new Member(generateID(), name, age, number, mail, isActive, isSenior, isCompetitive);
        members.add(added);
        return added;
    }

    public void removeMember(Member member){
        members.remove(member);
    }

    public String editMember(Member member, String command, String edit) {
        switch (command) {
            case "id":
                String editnum1 = edit.replaceAll("[^0-9]", "");
                member.setID(Integer.parseInt(editnum1));
                return edit;
            case "name":
                member.setName(edit);
                return edit;
            case "age":
                String editnum2 = edit.replaceAll("[^0-9]", "");
                member.setAge(Integer.parseInt(editnum2));
                return edit;
            case "number":
                String editnum3 = edit.replaceAll("[^0-9]", "");
                member.setNumber(Integer.parseInt(editnum3));
                return edit;
            case "mail":
                member.setMail(edit);
                return edit;
            case "active":
                if (edit.equalsIgnoreCase("yes")) {
                    member.setActive(true);
                } else member.setActive(false);
                return edit;
            case "senior":
                if (edit.equalsIgnoreCase("yes")) {
                    member.setSenior(true);
                } else member.setSenior(false);
                return edit;
            case "competitive":
                if (edit.equalsIgnoreCase("yes")) {
                    member.setCompetitive(true);
                } else member.setCompetitive(false);
                return edit;
            default:
                return null;
        }
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public String compactMembers() {
        StringBuilder result = new StringBuilder();
        result.append(openId+"\n");
        for(Member member : members)
        {
            result.append(member.getCompact() + "\n");
        }
        return (result.toString());
    }

    public void addMemberByObject(Member member)
    {
        members.add(member);
    }

    public void addMemberToDiscipline(Member member, String command) {
        switch (command) {
            case "butterfly"-> {
                //Possibility: Coding a checker-method to see if the member is already added
                if(butterflyList.contains(member)) {
                    System.out.println("This member is already added to the butterfly list.");
                } else {
                    butterflyList.add(member);
                    System.out.println("Member successfully added to butterfly discipline.");
                }
            }
            case "crawl" -> {
                //Possibility: Coding a checker-method to see if the member is already added
                if(crawlList.contains(member)) {
                    System.out.println("This member is already added to the crawl list.");
                } else {
                    crawlList.add(member);
                    System.out.println("Member successfully added to crawl discipline.");
                }
            }
            case "backstroke" -> {
                //Possibility: Coding a checker-method to see if the member is already added
                if(backstrokeList.contains(member)) {
                    System.out.println("This member is already added to the backstroke list.");
                } else {
                    backstrokeList.add(member);
                    System.out.println("Member successfully added to backstroke discipline.");
                }
            }
            case "breaststroke" -> {
                //Possibility: Coding a checker-method to see if the member is already added
                if(breaststrokeList.contains(member)) {
                    System.out.println("This member is already added to the breaststroke list.");
                } else {
                    breaststrokeList.add(member);
                    System.out.println("Member successfully added to breaststroke discipline.");
                }
            }
        }
    }
}