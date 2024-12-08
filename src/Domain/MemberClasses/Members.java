package Domain.MemberClasses;

import java.util.ArrayList;
import java.util.Comparator;

public class Members {
    private final ArrayList<Member> members = new ArrayList();
    
    public Members(){
        Member enes = new Member(5,"enes",50,50102030,
                "EnesZeki@dk",true,true,true);

        Member enes1 = new Member(3,"anes",10,50102030,
        "EnesZeki@dk",false,false,true);

        Member enes2 = new Member(1,"cnes",5,50102030,
        "EnesZeki@dk",false,false,true);

        Member enes3 = new Member(4,"dnes",7,50102030,
        "EnesZeki@dk",false,false,true);

        members.add(enes);
        members.add(enes1);
        members.add(enes2);
        members.add(enes3);
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
            return results;
        } else {
            for (Member member : members) {

                if (member.getName().toLowerCase().contains(search.toLowerCase())) {
                    results.add(member);
                }
            }
             return results;
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

    public String sortedMemberList(String term1, String term2){
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nHere is the list of members:");
        sortBy(term1,term2);
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

    public void sortBy(String term1, String term2) {
        Comparator<Member> ID = Member.ID_COMPARATOR;
        Comparator<Member> name = Member.NAME_COMPARATOR;
        Comparator<Member> age = Member.AGE_COMPARATOR;
        Comparator<Member> number = Member.NUMBER_COMPARATOR;
        Comparator<Member> mail = Member.MAIL_COMPARATOR;
        Comparator<Member> isActive = Member.ISACTIVE_COMPARATOR;
        Comparator<Member> isSenior = Member.ISSENIOR_COMPARATOR;
        Comparator<Member> isCompetitve = Member.ISCOMPETITIVE_COMPARATOR;

        switch (term1) {
            case "id" -> {
                switch (term2) {
                    case "name" -> Member.ID_COMPARATOR.thenComparing(name);
                    case "age" -> Member.ID_COMPARATOR.thenComparing(age);
                    case "number" -> Member.ID_COMPARATOR.thenComparing(number);
                    case "mail" -> Member.ID_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Member.ID_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Member.ID_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Member.ID_COMPARATOR.thenComparing(isCompetitve);
                    default -> members.sort(ID);
                }

            }

            case "name" -> {
                switch (term2) {
                    case "id" -> Member.NAME_COMPARATOR.thenComparing(ID);
                    case "age" -> Member.NAME_COMPARATOR.thenComparing(age);
                    case "number" -> Member.NAME_COMPARATOR.thenComparing(number);
                    case "mail" -> Member.NAME_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Member.NAME_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Member.NAME_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Member.NAME_COMPARATOR.thenComparing(isCompetitve);
                    default -> members.sort(name);
                }
            }

            case "age" -> {
                switch (term2) {
                    case "id" -> Member.AGE_COMPARATOR.thenComparing(ID);
                    case "name" -> Member.AGE_COMPARATOR.thenComparing(name);
                    case "number" -> Member.AGE_COMPARATOR.thenComparing(number);
                    case "mail" -> Member.AGE_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Member.AGE_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Member.AGE_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Member.AGE_COMPARATOR.thenComparing(isCompetitve);
                    default -> members.sort(age);
                }
            }

            case "number" -> {
                switch (term2) {
                    case "id" -> Member.NUMBER_COMPARATOR.thenComparing(ID);
                    case "name" -> Member.NUMBER_COMPARATOR.thenComparing(name);
                    case "age" -> Member.NUMBER_COMPARATOR.thenComparing(age);
                    case "mail" -> Member.NUMBER_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Member.NUMBER_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Member.NUMBER_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Member.NUMBER_COMPARATOR.thenComparing(isCompetitve);
                    default -> members.sort(number);
                }
            }
            case "mail" -> {
                switch (term2) {
                    case "id" -> Member.MAIL_COMPARATOR.thenComparing(ID);
                    case "name" -> Member.MAIL_COMPARATOR.thenComparing(name);
                    case "age" -> Member.MAIL_COMPARATOR.thenComparing(age);
                    case "number" -> Member.MAIL_COMPARATOR.thenComparing(number);
                    case "isActive" -> Member.MAIL_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Member.MAIL_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Member.MAIL_COMPARATOR.thenComparing(isCompetitve);
                    default -> members.sort(mail);
                }

            }

            case "isactive" -> {
                switch (term2) {
                    case "id" -> Member.ISACTIVE_COMPARATOR.thenComparing(ID);
                    case "name" -> Member.ISACTIVE_COMPARATOR.thenComparing(name);
                    case "age" -> Member.ISACTIVE_COMPARATOR.thenComparing(age);
                    case "number" -> Member.ISACTIVE_COMPARATOR.thenComparing(number);
                    case "mail" -> Member.ISACTIVE_COMPARATOR.thenComparing(mail);
                    case "isSenior" -> Member.ISACTIVE_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Member.ISACTIVE_COMPARATOR.thenComparing(isCompetitve);
                    default -> members.sort(isActive);
                }

            }

            case "issenior" -> {
                switch (term2) {
                    case "id" -> Member.ISSENIOR_COMPARATOR.thenComparing(ID);
                    case "name" -> Member.ISSENIOR_COMPARATOR.thenComparing(name);
                    case "age" -> Member.ISSENIOR_COMPARATOR.thenComparing(age);
                    case "number" -> Member.ISSENIOR_COMPARATOR.thenComparing(number);
                    case "mail" -> Member.ISSENIOR_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Member.ISSENIOR_COMPARATOR.thenComparing(isActive);
                    case "isCompetitve" -> Member.ISSENIOR_COMPARATOR.thenComparing(isCompetitve);
                    default -> members.sort(isSenior);
                }
            }

            case "iscompetitive" -> {
                switch (term2) {
                    case "id" -> Member.ISCOMPETITIVE_COMPARATOR.thenComparing(ID);
                    case "name" -> Member.ISCOMPETITIVE_COMPARATOR.thenComparing(name);
                    case "age" -> Member.ISCOMPETITIVE_COMPARATOR.thenComparing(age);
                    case "number" -> Member.ISCOMPETITIVE_COMPARATOR.thenComparing(number);
                    case "mail" -> Member.ISCOMPETITIVE_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Member.ISCOMPETITIVE_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Member.ISCOMPETITIVE_COMPARATOR.thenComparing(isSenior);
                    default -> members.sort(isCompetitve);
                }
            }
            default -> {
                switch (term2) {
                    case "id" -> Member.NAME_COMPARATOR.thenComparing(ID);
                    case "age" -> Member.NAME_COMPARATOR.thenComparing(age);
                    case "number" -> Member.NAME_COMPARATOR.thenComparing(number);
                    case "mail" -> Member.NAME_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Member.NAME_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Member.NAME_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Member.NAME_COMPARATOR.thenComparing(isCompetitve);
                    default -> members.sort(name);

                }
            }






        }
    }


    public void addMember(Member member){
        members.add(member);
    }

    public void removeMember(Member member){
        members.remove(member);
    }
}