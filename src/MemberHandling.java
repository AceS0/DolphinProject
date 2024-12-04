import java.util.ArrayList;
import java.util.Comparator;

public class MemberHandling {
    private final ArrayList<Members> members = new ArrayList();
    
    public MemberHandling(){
        Members enes = new Members(1,"enes",60,50102030,
                "EnesZeki@dk",true,false,true);
        members.add(enes);
    }

    //Metode til at finde members
    public ArrayList<Members> memberLookUp(String search){
        ArrayList<Members> results = new ArrayList<>();
        if (search.matches(".*\\d.*")){
            for (Members member : members) {

                if (String.valueOf(member.getID()).matches(search)) {
                    results.add(member);
                }
            }
            return results;
        } else {
            for (Members member : members) {

                if (member.getName().toLowerCase().contains(search.toLowerCase())) {
                    results.add(member);
                }
            }
             return results;
        }
    }

    public String sumMembershipFees() {
        double sum = 0;
        for(Members member : members) {
            sum += member.getAnnualFee();
        }
        return "The total annual membership fee is " + sum + " DKK";
    }

    public String memberList() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nHere is the list of members:");
        for (Members member : members) {
            toPrint.append("\n\n").append(member.toString());
        }
        return toPrint.toString();
    }

    public String sortedMemberList(String term1, String term2){
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nHere is the list of members:");
        sortBy(term1,term2);
        for (Members member : members) {
            toPrint.append("\n\n").append(member.toString());
        }
        return toPrint.toString();
    }

    public String memberListShort() {
       StringBuilder toPrint = new StringBuilder();
        for (Members member : members) {
            toPrint.append("\nID: ").append(member.getID()).append("\nName: ").append(member.getName());
        }
        return toPrint.toString();
    }

    public void sortBy(String term1, String term2) {
        Comparator<Members> ID = Members.ID_COMPARATOR;
        Comparator<Members> name = Members.NAME_COMPARATOR;
        Comparator<Members> age = Members.AGE_COMPARATOR;
        Comparator<Members> number = Members.NUMBER_COMPARATOR;
        Comparator<Members> mail = Members.MAIL_COMPARATOR;
        Comparator<Members> isActive = Members.ISACTIVE_COMPARATOR;
        Comparator<Members> isSenior = Members.ISSENIOR_COMPARATOR;
        Comparator<Members> isCompetitve = Members.ISCOMPETITIVE_COMPARATOR;

        switch (term1) {
            case "id" -> {
                switch (term2) {
                    case "name" -> Members.ID_COMPARATOR.thenComparing(name);
                    case "age" -> Members.ID_COMPARATOR.thenComparing(age);
                    case "number" -> Members.ID_COMPARATOR.thenComparing(number);
                    case "mail" -> Members.ID_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Members.ID_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Members.ID_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Members.ID_COMPARATOR.thenComparing(isCompetitve);
                    default -> Members.ID_COMPARATOR.thenComparing(ID);
                }

            }

            case "name" -> {
                switch (term2) {
                    case "id" -> Members.NAME_COMPARATOR.thenComparing(ID);
                    case "age" -> Members.NAME_COMPARATOR.thenComparing(age);
                    case "number" -> Members.NAME_COMPARATOR.thenComparing(number);
                    case "mail" -> Members.NAME_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Members.NAME_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Members.NAME_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Members.NAME_COMPARATOR.thenComparing(isCompetitve);
                    default -> Members.NAME_COMPARATOR.thenComparing(name);
                }
            }

            case "age" -> {
                switch (term2) {
                    case "id" -> Members.AGE_COMPARATOR.thenComparing(ID);
                    case "name" -> Members.AGE_COMPARATOR.thenComparing(name);
                    case "number" -> Members.AGE_COMPARATOR.thenComparing(number);
                    case "mail" -> Members.AGE_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Members.AGE_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Members.AGE_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Members.AGE_COMPARATOR.thenComparing(isCompetitve);
                    default -> Members.AGE_COMPARATOR.thenComparing(age);
                }
            }

            case "number" -> {
                switch (term2) {
                    case "id" -> Members.NUMBER_COMPARATOR.thenComparing(ID);
                    case "name" -> Members.NUMBER_COMPARATOR.thenComparing(name);
                    case "age" -> Members.NUMBER_COMPARATOR.thenComparing(age);
                    case "mail" -> Members.NUMBER_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Members.NUMBER_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Members.NUMBER_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Members.NUMBER_COMPARATOR.thenComparing(isCompetitve);
                    default -> Members.NUMBER_COMPARATOR.thenComparing(number);
                }
            }
            case "mail" -> {
                switch (term2) {
                    case "id" -> Members.MAIL_COMPARATOR.thenComparing(ID);
                    case "name" -> Members.MAIL_COMPARATOR.thenComparing(name);
                    case "age" -> Members.MAIL_COMPARATOR.thenComparing(age);
                    case "number" -> Members.MAIL_COMPARATOR.thenComparing(number);
                    case "isActive" -> Members.MAIL_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Members.MAIL_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Members.MAIL_COMPARATOR.thenComparing(isCompetitve);
                    default -> Members.MAIL_COMPARATOR.thenComparing(mail);
                }

            }

            case "isactive" -> {
                switch (term2) {
                    case "id" -> Members.ISACTIVE_COMPARATOR.thenComparing(ID);
                    case "name" -> Members.ISACTIVE_COMPARATOR.thenComparing(name);
                    case "age" -> Members.ISACTIVE_COMPARATOR.thenComparing(age);
                    case "number" -> Members.ISACTIVE_COMPARATOR.thenComparing(number);
                    case "mail" -> Members.ISACTIVE_COMPARATOR.thenComparing(mail);
                    case "isSenior" -> Members.ISACTIVE_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Members.ISACTIVE_COMPARATOR.thenComparing(isCompetitve);
                    default -> Members.ISACTIVE_COMPARATOR.thenComparing(isActive);
                }

            }

            case "issenior" -> {
                switch (term2) {
                    case "id" -> Members.ISSENIOR_COMPARATOR.thenComparing(ID);
                    case "name" -> Members.ISSENIOR_COMPARATOR.thenComparing(name);
                    case "age" -> Members.ISSENIOR_COMPARATOR.thenComparing(age);
                    case "number" -> Members.ISSENIOR_COMPARATOR.thenComparing(number);
                    case "mail" -> Members.ISSENIOR_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Members.ISSENIOR_COMPARATOR.thenComparing(isActive);
                    case "isCompetitve" -> Members.ISSENIOR_COMPARATOR.thenComparing(isCompetitve);
                    default -> Members.ISSENIOR_COMPARATOR.thenComparing(isSenior);
                }
            }

            case "iscompetitive" -> {
                switch (term2) {
                    case "id" -> Members.ISCOMPETITIVE_COMPARATOR.thenComparing(ID);
                    case "name" -> Members.ISCOMPETITIVE_COMPARATOR.thenComparing(name);
                    case "age" -> Members.ISCOMPETITIVE_COMPARATOR.thenComparing(age);
                    case "number" -> Members.ISCOMPETITIVE_COMPARATOR.thenComparing(number);
                    case "mail" -> Members.ISCOMPETITIVE_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Members.ISCOMPETITIVE_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Members.ISCOMPETITIVE_COMPARATOR.thenComparing(isSenior);
                    default -> Members.ISCOMPETITIVE_COMPARATOR.thenComparing(isCompetitve);
                }
            }
            default -> {
                switch (term2) {
                    case "id" -> Members.NAME_COMPARATOR.thenComparing(ID);
                    case "age" -> Members.NAME_COMPARATOR.thenComparing(age);
                    case "number" -> Members.NAME_COMPARATOR.thenComparing(number);
                    case "mail" -> Members.NAME_COMPARATOR.thenComparing(mail);
                    case "isActive" -> Members.NAME_COMPARATOR.thenComparing(isActive);
                    case "isSenior" -> Members.NAME_COMPARATOR.thenComparing(isSenior);
                    case "isCompetitve" -> Members.NAME_COMPARATOR.thenComparing(isCompetitve);
                    default -> Members.NAME_COMPARATOR.thenComparing(name);

                }
            }






        }
    }



    public void addMember(Members member){
        members.add(member);
    }

    public void removeMember(Members member){
        members.remove(member);
    }
}