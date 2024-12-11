package Domain.MemberClasses;

import java.util.ArrayList;
import java.util.Comparator;

public class Members {
    private final ArrayList<Member> members = new ArrayList<>();
    private int openId = 0;
    private final ArrayList<Member> butterflyList = new ArrayList<>();
    private final ArrayList<Member> crawlList = new ArrayList<>();
    private final ArrayList<Member> backstrokeList = new ArrayList<>();
    private final ArrayList<Member> breaststrokeList = new ArrayList<>();

    public Members(){
    }

    public int generateID() {
        openId++;
        return openId;
    }

    public void setOpenID(int id) {
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

    public String sortedMemberList(String term1, String term2){
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nHere is the list of members:");
        sortBy(term1,term2);
        for (Member member : members) {
            toPrint.append("\n\n").append(member.toString());
        }
        return String.valueOf(toPrint);
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

    public ArrayList<Member> getUnpaid()
    {
        ArrayList<Member> result = new ArrayList<>();
        for (Member m: members)
        {
            if (!m.gethasPaid())
            {
                result.add(m);
            }
        }
        return result;
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

    public String memberListShortRecord() {
        StringBuilder toPrint = new StringBuilder();
        for (Member member : members) {
            toPrint.append("\nID: ").append(member.getID()).append("\nName: ").append(member.getName())
                    .append("\nDiscipline: ").append(member.getButterfly())
                    .append("\nDiscipline: ").append(member.getCrawl())
                    .append("\nDiscipline: ").append(member.getBackstroke())
                    .append("\nDiscipline: ").append(member.getBreaststroke());


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

    public String compactMembers() {
        StringBuilder result = new StringBuilder();
        result.append(openId+"\n");
        for(Member member : members)
        {
            result.append(member.getCompact() + "\n");
        }
        return (result.toString());
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

    public void addMemberByObject(Member member)
    {
        members.add(member);
    }

    public String disciplinesList(Member member){
        StringBuilder toPrint = new StringBuilder();

        toPrint.append(member.getName()).append(" has these disciplines: \n");
        int counter = 0;

        if (butterflyList.contains(member)){
            counter++;
            toPrint.append(counter).append(". Butterfly\n");
        }

        if (crawlList.contains(member)){
            counter++;
            toPrint.append(counter).append(". Crawl\n");
        }

        if (backstrokeList.contains(member)){
            counter++;
            toPrint.append(counter).append(". Backstroke\n");
        }

        if (breaststrokeList.contains(member)){
            counter++;
            toPrint.append(counter).append(". Breaststroke\n");
        }
        return String.valueOf(toPrint);
    }

    public String topDisciplinesList(String command, String command2){
        //Sorting not finished yet.
        StringBuilder toPrint = new StringBuilder();
        int counter = 0;
        switch(command) {
            case "1","butterfly", "bu" -> {

                butterflyList.sort(Comparator.comparingDouble(member -> {
                    String butterflyInfo = member.getButterfly();
                    return Member.getTimeFromSTring(butterflyInfo);
                }));


                for (Member thisMember : butterflyList) {
                    if (command2.equals("1")) {
                        if (thisMember.getIsSenior()) {
                            counter++;
                            toPrint.append("\nID: ").append(thisMember.getID()).append(" Name: ").append(thisMember.getName()).append(" senior: ").append(thisMember.getIsSenior()).append(" Discipline: ").append(thisMember.getButterfly());
                        }
                    } else if (command2.equals("2")){
                        if (!thisMember.getIsSenior()){
                            counter++;
                            toPrint.append("\nID: ").append(thisMember.getID()).append(" Name: ").append(thisMember.getName()).append(" junior: ").append(thisMember.getIsSenior()).append(" Discipline: ").append(thisMember.getButterfly());
                        }
                    }
                    if (counter == 5){
                        return String.valueOf(toPrint);
                    }
                }

                if (counter == 0) {
                    return "There is no " + (command2.equals("1") ? "senior" : "junior") + " recorded.";
                }

                return String.valueOf(toPrint);
            }

            case "2", "crawl", "c" -> {

                crawlList.sort(Comparator.comparingDouble(member -> {
                    String crawlInfo = member.getCrawl();
                    return Member.getTimeFromSTring(crawlInfo);
                }));

                for (Member thisMember : crawlList) {
                    if (command2.equals("1")) {
                        if (thisMember.getIsSenior()) {
                            counter++;
                            toPrint.append("\nID: ").append(thisMember.getID()).append(" Name: ").append(thisMember.getName()).append(" senior: ").append(thisMember.getIsSenior()).append(" Discipline: ").append(thisMember.getCrawl());
                        }
                    } else if (command2.equals("2")){
                        if (!thisMember.getIsSenior()){
                            counter++;
                            toPrint.append("\nID: ").append(thisMember.getID()).append(" Name: ").append(thisMember.getName()).append(" junior: ").append(thisMember.getIsSenior()).append(" Discipline: ").append(thisMember.getCrawl());
                        }
                    }
                    if (counter == 5){
                        return String.valueOf(toPrint);
                    }
                }

                if (counter == 0) {
                    return "There is no " + (command2.equals("1") ? "senior" : "junior") + " recorded.";
                }

                return String.valueOf(toPrint);
            }

            case "3","backstroke", "ba" -> {

                backstrokeList.sort(Comparator.comparingDouble(member -> {
                    String backstrokeInfo = member.getBackstroke();
                    return Member.getTimeFromSTring(backstrokeInfo);
                }));

                for (Member thisMember : backstrokeList) {
                    if (command2.equals("1")) {
                        if (thisMember.getIsSenior()) {
                            counter++;
                            toPrint.append("\nID: ").append(thisMember.getID()).append(" Name: ").append(thisMember.getName()).append(" senior: ").append(thisMember.getIsSenior()).append(" Discipline: ").append(thisMember.getBackstroke());
                        }
                    } else if (command2.equals("2")){
                        if (!thisMember.getIsSenior()){
                            counter++;
                            toPrint.append("\nID: ").append(thisMember.getID()).append(" Name: ").append(thisMember.getName()).append(" junior: ").append(thisMember.getIsSenior()).append(" Discipline: ").append(thisMember.getBackstroke());
                        }
                    }
                    if (counter == 5){
                        return String.valueOf(toPrint);
                    }
                }

                if (counter == 0) {
                    return "There is no " + (command2.equals("1") ? "senior" : "junior") + " recorded.";
                }

                return String.valueOf(toPrint);
            }

            case "4","breaststroke", "br" -> {

                breaststrokeList.sort(Comparator.comparingDouble(member -> {
                    String breaststrokeInfo = member.getBreaststroke();
                    return Member.getTimeFromSTring(breaststrokeInfo);
                }));

                for (Member thisMember : breaststrokeList) {
                    if (command2.equals("1")) {
                        if (thisMember.getIsSenior()) {
                            counter++;
                            toPrint.append("\nID: ").append(thisMember.getID()).append(" Name: ").append(thisMember.getName()).append(" senior: ").append(thisMember.getIsSenior()).append(" Discipline: ").append(thisMember.getBreaststroke());
                        }
                    } else if (command2.equals("2")){
                        if (!thisMember.getIsSenior()){
                            counter++;
                            toPrint.append("\nID: ").append(thisMember.getID()).append(" Name: ").append(thisMember.getName()).append(" junior: ").append(thisMember.getIsSenior()).append(" Discipline: ").append(thisMember.getBreaststroke());
                        }
                    }
                    if (counter == 5){
                        return String.valueOf(toPrint);
                    }
                }
                if (counter == 0) {
                    return "There is no " + (command2.equals("1") ? "senior" : "junior") + " recorded.";
                }
                return String.valueOf(toPrint);
            }
        }
        return "An error occured.";
    }

    public String addMemberToDiscipline(Member member, String command) {
        switch (command) {
            case "butterfly"-> {
                //A checker-method to see if the member is already added
                if(butterflyList.contains(member)) {
                   return "This member is already added to the butterfly list.";
                } else {
                    butterflyList.add(member);
                   return "Member successfully added to butterfly discipline.";
                }
            }
            case "crawl" -> {
                //A checker-method to see if the member is already added
                if(crawlList.contains(member)) {
                    return "This member is already added to the crawl list.";
                } else {
                    crawlList.add(member);
                    return "Member successfully added to crawl discipline.";
                }
            }
            case "backstroke" -> {
                //A checker-method to see if the member is already added
                if(backstrokeList.contains(member)) {
                    return "This member is already added to the backstroke list.";
                } else {
                    backstrokeList.add(member);
                    return "Member successfully added to backstroke discipline.";
                }
            }
            case "breaststroke" -> {
                //A checker-method to see if the member is already added
                if(breaststrokeList.contains(member)) {
                    return "This member is already added to the breaststroke list.";
                } else {
                    breaststrokeList.add(member);
                    return "Member successfully added to breaststroke discipline.";
                }
            }
        }
        return "An error occurred";
    }

}