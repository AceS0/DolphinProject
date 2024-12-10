package UI;

import Controller.Controller;
import Domain.MemberClasses.Member;
import Domain.TournamentClasses.Competitor;
import Domain.TournamentClasses.Tournament;
import Domain.TournamentClasses.Tournaments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserInterface {
    private final Controller controller = new Controller();

    public void userInterface() {
        System.out.println(controller.loadMembers() + "\n");
        System.out.println(controller.loadTourneys());
        frontPage();

    }

    public void frontPage() {
        boolean running = true;
        while (running) {
            Scanner sc = new Scanner(System.in);
            String userInput = reqString(
                    """
                            
                            Welcome to your swimming club.
                            choose what category to manage:\s
                            1. members
                            2. tourneys
                            3. Exit
                            Type here:\s""", sc);
            switch (userInput) {
                case "1", "members", "m" -> memberManagement();
                case "2", "tourney", "t" -> tourneyManagement();
                case "3", "exit" -> running = false;
            }
        }
    }

    public void memberManagement() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(
                """
                        
                        You're managing members.
                        Below is your options:\s
                        1. Create a member.
                        2. Remove a member.
                        3. Search for a member.
                        4. List the members.
                        5. Sort member list
                        6. Edit a member.
                        7. Calculate total annual membership fee.
                        8. Record, Update, Display member performance.
                        9. Check member's balance and pay fee.
                        10. Deposit balance for a member.
                        11. Wipe members
                        12. Exit""");
        while (running) {
            try {
                System.out.print("""
                        
                        Type "help", for a list of commands.
                        
                        Choose an option:\s""");
                //Dette splitter brugerens input, som vi gør brug af i bl.a. search-funktionen:
                String userInput = br.readLine().toLowerCase();
                String[] splitPut = userInput.split(" ");
                String command = splitPut[0];

                //Switch på forskellige commands brugeren kan vælge
                switch (command) {
                    case "1", "create", "c" -> addMemberByUser();
                    case "2", "remove", "r" -> {
                        if (splitPut.length > 1) {
                            removeMemberByUser(splitPut[1]);
                        } else {
                            System.out.println("Here is a list of members: ");
                            memberListShortInfo();
                            System.out.println("Insert the member you want to remove.");
                            System.out.print("Type here: ");
                            removeMemberByUser(sc.next());
                        }
                    }
                    case "3", "search", "s" -> {
                        if (splitPut.length > 1) {
                            searchForMember(splitPut[1]);
                        } else {
                            searchForMember(reqString("insert search term: ", sc));
                        }
                    }
                    case "4", "list", "l" -> listMembers();
                    case "5", "sort" -> sortMembers();
                    case "6", "edit", "e" -> {
                        if (splitPut.length > 1) {
                            editMember(splitPut[1]);
                        } else {
                            editMember(reqString("Type the member you want to edit: ", sc));
                        }
                    }
                    case "7", "sum" -> System.out.println(controller.sumMembershipFees());
                    case "8", "record" -> recordTime();
                    case "9", "balance" -> {
                        if (splitPut.length > 1) {
                            runBalancePayment(splitPut[1]);
                        } else {
                            System.out.print("insert the member you want to check: ");
                            runBalancePayment(sc.next());
                        }
                    }
                    case "10", "deposit" -> depositMemberBalance();
                    case "11", "wipe" ->wipeMembers();
                    case "12", "discipline" -> listMemberDisciplines();
                    case "help" -> System.out.println(
                            """
                                    
                                    You're managing members.
                                    Below is your options:\s
                                    1. Create a member.
                                    2. Remove a member.
                                    3. Search for a member.
                                    4. List the members.
                                    5. sort member list
                                    6. Edit a member.
                                    7. Calculate total annual membership fee.
                                    8. Record, Update, Display member performance.
                                    9. Check member's balance and pay fee.
                                    10. Deposit balance for a member.
                                    11. Wipe members
                                    12. List disciplines of the member.
                                    13. Exit""");
                    case "13", "exit" -> running = false;
                }
            } catch (ArrayIndexOutOfBoundsException | IOException aioobe) {
                System.out.println("Unknown request, please try again.");
            }
        }
    }

    private void wipeMembers() {
        Scanner sc = new Scanner(System.in);
        if (reqBool("are you sure you want to wipe all tournaments from the saved files?",sc)) System.out.println(controller.wipeMembers());
    }

    public void addMemberByUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You are creating a member");
        String memberName = reqString("Insert full name: ", sc);

        int age = reqInt("Insert age: ", sc);

        boolean stage;
        if (age < 18){
            stage = false;
            System.out.println("The member status has been set to junior");
        } else {
            stage = true;
            System.out.println("The member status has been set to senior");
        }

        int number = reqInt("Insert telephone number: ", sc);


        String mail = reqString("Insert mail: ", sc);

        Boolean activity = reqBool("Is the member active: ", sc);

        sc.nextLine();


        boolean competitive1 = reqBool("Is the member competitive: ", sc);
        sc.nextLine();

        int balance = reqInt("How much did the member deposit?: ", sc);

        Member m = controller.addMemberToList(memberName, age, number, mail, activity, stage, competitive1);
        //tilføjer ikke balance, check helst på et tidspunkt
        controller.setMembershipBalanceFee(m.getID(), memberName, activity, age, balance);
        addMemberToDiscipline(m);
        System.out.println("You have created a new member, and successfully connected a membership ID.");
    }

    public void removeMemberByUser(String inputs) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Member> found = controller.runSearch(inputs);
        if (found.isEmpty()) {
            System.out.println("\nThe member doesn't exist, please create the member if needed.\n");
        } else {
            for (Member member : found) {
                if (found.size() == 1) {
                    System.out.println("You have successfully removed " + member.getName() + "\n");
                    controller.removeMemberFromList(member);
                    return;
                }
            }
            while (true) {
                if (found.size() >= 2) {
                    System.out.println("\nHere is a list of the members you searched for: ");
                    StringBuilder toPrint = new StringBuilder();
                    for (Member member : found) {
                        toPrint.append("\nID: ").append(member.getID()).append(": \nName: ").append(member.getName());
                    }
                    System.out.println(toPrint);
                    inputs = reqString("Which member do you want to remove? Type here: ", sc);
                    found = controller.runSearch(inputs);
                    for (Member member : found) {
                        if (found.size() == 1) {
                            System.out.println("You have successfully removed " + member.getName());
                            controller.removeMemberFromList(member);
                            return;
                        }
                    }
                    if (found.isEmpty()) {
                        System.out.println("The member doesn't exist, please try again or to leave type exit or quit.");
                        System.out.print("Type here: ");
                        String input = reqString("The member doesn't exist, please try again or to leave type exit or quit \n Type here: ", sc);
                        if (input.equals("quit") || input.equals("exit")) {
                            return;
                        } else {
                            removeMemberByUser(input);
                        }
                    }
                }
            }
        }
    }

    public void memberListShortInfo() {
        if (Objects.equals(controller.getMembers().memberListShort(), "")) {
            System.out.println("\nThe list is empty, please create a member.\n");
        } else {
            System.out.println(controller.getMembers().memberListShort());
        }
    }

    public void searchForMember(String thisMember) {
        Scanner sc = new Scanner(System.in);
        Member found = searchSpecificMember(thisMember, sc);
        if (found == null) {
            System.out.println("Returning back to menu.");
            return;
        }
        editMember(String.valueOf(found.getID()));
    }


    public void recordTime() {
        Scanner sc = new Scanner(System.in);
        System.out.println
                ("""
                Below is your options: 
                
                1. Record/Update record time.             
                2. Display record time for one member.
                3. Display record time for all members.         
                """);
        System.out.print("Type here: ");
        int command = sc.nextInt();
        switch (command){
            case 1 ->{
                System.out.print("which member do you want to record time for: ");
                String thisMember = sc.next();
                Member found = searchSpecificMember(thisMember,sc);
                System.out.println(
                """
                Which discipline do you want to record time for
                
                1. Butterfly.
                2. Crawl.
                3. backstroke.
                4. breaststroke.
                """);
                System.out.print("Type here:");



                String command2 = sc.next();
                switch (command2){
                    case "1","butterfly" -> command2 = "Butterfly.";
                    case "2","crawl" -> command2 = "Crawl.";
                    case "3","backstroke"-> command2 = "Backstroke.";
                    case "4","breaststroke"-> command2 = "Breaststroke.";
                    default -> System.out.println("Please, try again.");
                }

                System.out.println("Discipline: " + command2);

                System.out.print("What is the date of the record: ");
                String recordDate = sc.next();
                System.out.print("What was the members best time performance (in minutes): ");
                double recordTime = sc.nextDouble();


                switch (command2){
                    case "Butterfly." -> found.setButterfly("Butterfly -> Time: " + recordTime + " min. || Date: " + recordDate);
                    case "Crawl." -> found.setCrawl("Crawl -> Time: " + recordTime + " min. || Date: " + recordDate);
                    case "Backstroke."-> found.setBackstroke("Backstroke -> Time: " + recordTime + " min. || Date: " + recordDate);
                    case "Breaststroke."-> found.setBreaststroke("Breaststroke -> Time: " + recordTime + " min. || Date: " + recordDate);
                }

                System.out.println("You have Added/Updated record time for " + found.getName());
            }

            case 2 -> {
                System.out.println("Which member do you want to get info about");
                String thisMember = sc.next();
                Member found = searchSpecificMember(thisMember,sc);
                System.out.println("ID: " +found.getID() +
                                    "\nName: " + found.getName() +
                                "\nDiscipline info: "
                                + found.getButterfly()
                                + found.getCrawl()
                                + found.getBackstroke()
                                + found.getBreaststroke());

            }

            case 3 ->  {
                System.out.println(controller.getMembers().memberListShortRecord());
            }

        }
    }





    public void editMember(String thisMember) {
        Scanner sc = new Scanner(System.in);
        Member found = searchSpecificMember(thisMember, sc);
        if (found == null) {
            System.out.println("Returning back to menu.");
            return;
        }

        Boolean input = reqBool("found member: \n" + found+  "do you wish to edit this member?:", sc);
        if (input) {
            editMemberSplit(found);
        } else {
            System.out.println("-> Returning back to menu.");
        }
    }

    public void editMemberSplit(Member thisMember) {
        boolean running = true;
        while (running) {
            System.out.println(thisMember.toString());
            Scanner sc = new Scanner(System.in);
            System.out.println("0. exit, 1. id, 2. name, 3. age, 4. number, 5. mail, 6. active, 7. senior, 8. competitive");
            switch (sc.next()) {
                case "0", "exit" -> {
                    System.out.println("-> Returning back to menu.");
                    running = false;
                }
                case "1", "id" -> {
                    System.out.print("what should the new ID be: ");
                    if (sc.hasNextInt()) {
                        System.out.println("The value has now been changed to: " + controller.editMember(thisMember, "id", String.valueOf(sc.nextInt())));
                    }
                }
                case "2", "name" -> {
                    System.out.print("what should the new name be: ");
                    System.out.println(controller.editMember(thisMember, "name", sc.next()));
                }
                case "3", "age" -> {
                    System.out.print("what should the new age be: ");
                    if (sc.hasNextInt()) {
                        System.out.println("The value has now been changed to: " + controller.editMember(thisMember, "age", String.valueOf(sc.nextInt())));
                    }
                }
                case "4", "number" -> {
                    System.out.print("what should the new number be: ");
                    if (sc.hasNextInt()) {
                        System.out.println("The value has now been changed to: " + controller.editMember(thisMember, "number", String.valueOf(sc.nextInt())));
                    }
                }
                case "5", "mail" -> {
                    System.out.print("what should the new mail be: ");
                    System.out.println(controller.editMember(thisMember, "mail", sc.next()));
                }

                case "6", "active" -> {
                    while (true) {
                        System.out.println("is " + thisMember.getName() + " active?");
                        System.out.print("Type here: ");
                        String input = sc.next();
                        if (input.equals("yes") || input.equals("no")) {
                            System.out.println(controller.editMember(thisMember, "active", input));
                            break;
                        }
                    }
                }
                case "7", "senior" -> {
                    while (true) {
                        System.out.println("is " + thisMember.getName() + " a senior?");
                        System.out.print("Type here: ");
                        String input = sc.next();
                        if (input.equals("yes") || input.equals("no")) {
                            System.out.println(controller.editMember(thisMember, "senior", input));
                            break;
                        }
                    }
                }
                case "8", "competitive" -> {
                    while (true) {
                        System.out.println("is " + thisMember.getName() + " competitive?");
                        System.out.print("Type here: ");
                        String input = sc.next();
                        if (input.equals("yes") || input.equals("no")) {
                            System.out.println(controller.editMember(thisMember, "competitive", input));
                            break;
                        }
                    }
                }
                default -> System.out.print("Invalid input, please try again.");
            }
        }
    }

    public void runBalancePayment(String findMember) {
        Scanner sc = new Scanner(System.in);
        Member found = searchSpecificMember(findMember, sc);
        if (found == null) {
            System.out.println("Returning back to menu.");
            return;
        }
        String name = found.getName();
        System.out.println(controller.getBalancePayment(found));
        if (found.getPaidStatus().equals("has not paid")) {
            boolean input = reqBool("Would you like to pay the fee with your current balance?" + "\n" + "Type yes or no: ", sc);
            double debt = found.getDebt();
            double balance = found.getBalance();
            double remainingBalance = balance - debt;
            double remainingFee = debt - balance;
            if (input && balance >= debt && balance > 0) {
                found.setPaidStatus(true);
                System.out.println(name + "'s balance after the payment: " + remainingBalance +
                        " remaining fee after the payment: 0.0");
                found.setBalance(remainingBalance);
                found.setDebt(0);
            } else if (input && balance < debt && balance > 0) {
                System.out.println(name + "'s balance after the payment: " + "0.0" +
                        " remaining fee after the payment: " + remainingFee);
                found.setBalance(0);
                found.setDebt(remainingFee);
            } else if (input && balance <= 0) {
                System.out.println("To proceed the payment, please deposit some money.\nYour balance: "
                        + balance + "\nWhat you owe: " + debt);
            } else if (!input) {
                System.out.println("-> Returning back to menu.");
            }
        } else if (found.getPaidStatus().equals("has not paid")) {
            System.out.println(name + " has already paid his fee for this year.");
        }
    }

    public void depositMemberBalance() {
        Scanner sc = new Scanner(System.in);
        Member found;
        found = searchSpecificMember(reqString("what member are you looking for: ", sc), sc);
        if (found == null) {
            System.out.println("Returning back to menu.");
            return;
        }
        System.out.println("found member: "+ found.getShortDescription());
        double balance = reqDouble("Register the amount that got deposited:", sc);

        System.out.println(controller.depositMemberBalance(found.getName(), balance));
    }

    public void listMembers() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You have 3 types of lists:\n1. Print a list of all members." +
                "\n2. Print a list of all members who paid their fees." +
                "\n3. Print a list of all members who didn't pay their fees.");
        String command = sc.next();
        switch (command) {
            case "1" -> System.out.println(controller.getMembers().memberList());
            case "2" -> {
                ArrayList<Member> found = controller.paidSearch(true);
                if (!found.isEmpty()) {
                    for (Member member : found) {
                        System.out.println(member.toString());
                    }
                } else {
                    System.out.println("No member has paid their fees yet.");
                }
            }
            case "3" -> {
                ArrayList<Member> found = controller.paidSearch(false);
                if (!found.isEmpty()) {
                    for (Member member : found) {
                        System.out.println(member.toString());
                    }
                    boolean b = reqBool("do you wish to send invoices to these members: ", sc);
                    if (b) controller.writeInvoices();
                } else {
                    System.out.println("Every member has paid their fees.");
                }
            }
            default -> System.out.println("Unknown request, please try again.");
        }
    }

    private Member searchSpecificMember(String input, Scanner sc) {
        ArrayList<Member> toNarrow = controller.runSearch(input.toLowerCase());

        if (input.equals("quit")) {
            return null;
        }

        if (toNarrow.isEmpty()) {
            System.out.println();
            return searchSpecificMember(reqString("no result found, try to write something different, \n or type quit to return to member management:", sc), sc);
        }
        if (toNarrow.size() == 1) {
            return toNarrow.getFirst();
        } else {
            System.out.println("the following members were found" + "\n" + controller.getMembers().memberListShort());
            return searchSpecificMember(reqString("please try to specify the member using the id's of the listed members, or type quit to stop " +
                    "\ntype here: ", sc), sc);
        }
    }

    public void tourneyManagement() {
        boolean running = true;
        while (running) {
            Scanner sc = new Scanner(System.in);
            String userInput = reqString(
                    """
                            
                            you're currently managing tournaments
                            
                            choose what to do:\s
                            1. create tourney
                            2. list tourneys
                            3. search for tourneys
                            10. exit
                            Type here:\s""", sc);
            String[] splitPut = userInput.split(" ");
            String command = splitPut[0];
            switch (command) {
                case "1", "create":
                    createTournament();
                    break;
                case "2", "list":
                {
                    listTourneys();
                    break;
                }
                case "3", "search":
                    if (splitPut.length > 1) {
                        searchForTourney(splitPut[1]);
                    } else searchForTourney(reqString("enter search term: ", sc));
                    break;
                    case "9", "wipe":
                    {
                        wipeTourneys();
                    }
                case "10", "exit": {
                    running = false;
                }
            }
        }
    }

    private void wipeTourneys() {
        Scanner sc = new Scanner(System.in);
        if (reqBool("are you sure you want to wipe all tournaments from the saved files?",sc)) System.out.println(controller.wipeTourneys());
    }


    private void createTournament() {
        Scanner sc = new Scanner(System.in);
        String name = reqString("enter tournament name: ", sc);
        String date = reqString("enter tournament date: ", sc);
        String place = reqString("enter tournament placement: ", sc);
        String category = reqString("enter tournament category: ", sc);
        ArrayList<Competitor> competitors = new ArrayList<Competitor>();
        while (true) {
            boolean sameMember = false;
            Member competitorMember = searchSpecificMember(reqString("please enter the name or id of a competitor", sc), sc);
            if (competitorMember == null) break;
            for (Competitor m: competitors)
            {
                if (m.getName().equals(competitorMember.getName())) sameMember = true;
            }
            if (!sameMember) {
                System.out.println("added " + "\n" + competitorMember.getShortDescription() + "\n" + "to the tournament");
                competitors.add(controller.createCompetitor(competitorMember, reqDouble("competitors time:", sc)));
            }else System.out.println("this member has already been added\n");
            if (!reqBool("do you wish to add more competitors: ", sc)) break;

        }
        controller.createTournament(name, date, place, category, competitors);
    }

    private void listTourneys() {
        Tournaments tournaments = controller.getTournaments();
        System.out.println("tourneys:\n" +tournaments.listOfShortDescriptions());
    }


    private void workInProgress() {
        System.out.println("work in progress \n");
    }

    public void searchForTourney(String thisTourney) {
        Scanner sc = new Scanner(System.in);
        Tournament found = searchSpecificTourney(thisTourney, sc);
        if (found == null) {
            System.out.println("Returning back to menu.");
            return;
        }
        System.out.println(found.getLongDescription());
    }

    private Tournament searchSpecificTourney(String input, Scanner sc) {
        ArrayList<Tournament> toNarrow = controller.runTournamentSearch(input);
        if (input.equals("quit")) {
            return null;
        }
        if (toNarrow.isEmpty()) {
            System.out.println();
            return searchSpecificTourney(reqString("no result found, try to write something different, \n or type quit to return to tournament management:", sc), sc);
        }
        if (toNarrow.size() == 1) {
            return toNarrow.getFirst();
        } else {
            System.out.println("the following tourneys were found" + "\n" + controller.getTournaments().listToShortDescriptions(toNarrow));
            return searchSpecificTourney(reqString("please try to specify the tourney using the id's of the listed tourneys, or type quit to stop searching" +
                    "\ntype here: ", sc), sc);
        }
    }


    public int reqInt(String quote, Scanner sc) {
        System.out.print(quote);
        while (true) {
            String temp = sc.nextLine();
            String tempExclLetters = temp.replaceAll("[^0-9]", "");
            if (!tempExclLetters.isEmpty()) {
                return Integer.parseInt(tempExclLetters);
            } else System.out.println("no number was found");
        }
    }

    public String reqString(String quote, Scanner sc) {
        System.out.print(quote);
        return sc.nextLine();
    }

    public Boolean reqBool(String quote, Scanner sc) {
        System.out.print(quote);

        while (true) {
            String next = sc.next().toLowerCase();
            switch (next) {
                case "true", "yes", "1":
                    return true;
                case "false", "no", "2":
                    return false;
                case"":
                    break;
                default:
                    System.out.println("that's not yes or no, try again");
            }
        }
    }

    public double reqDouble(String quote, Scanner sc) {
        System.out.print(quote);
        while (true) {
            String input = sc.next();
            try {
                return Double.parseDouble(input);

            } catch (NumberFormatException e) {
                System.out.println("invalid input, try again");

            }
        }
    }
    public void sortMembers() {
        if (controller.getMembers().memberList().isEmpty()) {
            System.out.println("\nThere is no member list to be sorted.\n");
            userInterface();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println(
                """ 
                        How do you want to list the members?
                        
                        Sorted by: 
                        1. ID?
                        2. Name?
                        3. Age?
                        4. Number?
                        5. Mail?
                        6. IsActive?
                        7. IsSenior?
                        8. IsCompetitive?
                        9. Return to menu
                        """);
        System.out.print("Type here: ");
        String input = sc.next().toLowerCase();


        switch (input) {
            case "1", "id", "i" -> {
                input = "id";
                controller.getMembers().sortedMemberList(input,"");
            }
            case "2", "name", "n" -> {
                input = "name";
                controller.getMembers().sortedMemberList(input,"");
            }
            case "3", "age", "a" -> {
                input = "age";
                controller.getMembers().sortedMemberList(input,"");
            }
            case "4", "number" -> {
                input = "number";
                controller.getMembers().sortedMemberList(input,"");
            }
            case "5", "mail", "m" -> {
                input = "mail";
                controller.getMembers().sortedMemberList(input,"");
            }
            case "6", "isactive" -> {
                input = "isactive";
                controller.getMembers().sortedMemberList(input,"");
            }
            case "7", "issenior" -> {
                input = "issenior";
                controller.getMembers().sortedMemberList(input,"");
            }
            case "8", "iscompetitive" -> {
                input = "iscompetitive";
                controller.getMembers().sortedMemberList(input,"");
            }
            case "9", "return", "exit" -> {
                System.out.println("Returning back to menu");
                userInterface();
            }
            default -> {
                input = "name";
                controller.getMembers().sortedMemberList(input,"");
            }
        }



        System.out.println("Do you want to have secondary sort?");
        System.out.print("Type (yes/no): ");

        while (true) {
            String input2 = sc.next().toLowerCase();
            switch (input2) {
                case "yes" -> {
                    System.out.println(
                            """ 
                                    How do you want to list the members?
                                    
                                    Sorted by: 
                                    1. ID?
                                    2. Name?
                                    3. Age?
                                    4. Number?
                                    5. Mail?
                                    6. IsActive?
                                    7. IsSenior?
                                    8. IsCompetitive?
                                    9. Return to menu
                                    """);
                    System.out.print("Type here: ");
                    String input3 = sc.next().toLowerCase();
                    switch (input3) {
                        case "1", "id", "i" -> controller.getMembers().sortedMemberList(input,"id");
                        case "2", "name", "n" -> controller.getMembers().sortedMemberList(input,"name");
                        case "3", "age", "a" -> controller.getMembers().sortedMemberList(input,"age");
                        case "4", "number" -> controller.getMembers().sortedMemberList(input,"number");
                        case "5", "mail", "m" -> controller.getMembers().sortedMemberList(input,"mail");
                        case "6", "isactive" -> controller.getMembers().sortedMemberList(input,"isactive");
                        case "7", "issenior" -> controller.getMembers().sortedMemberList(input,"issenior");
                        case "8", "iscompetitive" -> controller.getMembers().sortedMemberList(input,"iscompetitive");
                        case "9", "return", "exit" -> {
                            System.out.println("Returning back to menu");
                            userInterface();
                        }
                    }
                }
                case "no" -> System.out.println(controller.getMembers().memberList());
                default -> System.out.print("Invalid input, please try again: ");

            }
        }
    }


    public void addMemberToDiscipline (Member member) {
        System.out.println("You are adding the member to one or more disciplines.");
        Scanner sc = new Scanner(System.in);
        boolean boolTrue = true;
            while(boolTrue) {
                System.out.print(
                        """
                                These are the disciplines you can choose from:
                                1. if butterfly write: \"bu\" or \"butterfly\"
                                2. if crawl write: \"c\" or \"crawl\"
                                3. if backstroke write: \"ba\" or \"backstroke\"
                                4. if breaststroke write: \"br\" or \"breaststroke\"
                                
                                Which discipline do you want to add the member to: """);
                String command = sc.next().toLowerCase();
                switch (command) {
                    case "bu", "butterfly","1" -> {
                        controller.addMemberToDiscipline(member, "butterfly");
                        boolTrue = wantToAddToMoreDisciplines();
                    }
                    case "c", "crawl","2" -> {
                        controller.addMemberToDiscipline(member, "crawl");
                        boolTrue = wantToAddToMoreDisciplines();
                    }
                    case "ba", "backstroke","3" -> {
                        controller.addMemberToDiscipline(member, "backstroke");
                        boolTrue = wantToAddToMoreDisciplines();
                    }
                    case "br", "breaststroke","4" -> {
                        controller.addMemberToDiscipline(member, "breaststroke");
                        boolTrue = wantToAddToMoreDisciplines();
                    }
                    default -> System.out.println("Invalid command. Try again.");
                }
        }
    }

    public boolean wantToAddToMoreDisciplines() {
        System.out.println("Do you want to add member to more disciplines (\"yes\" or \"no\")? ");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if(input.equalsIgnoreCase("yes")) {
            return true;
        }else if(input.equalsIgnoreCase("no")) {
            return false;
        }else {
            System.out.println("Invalid input. You have to answer either \"yes\" or \"no\".\nTry again");
            return wantToAddToMoreDisciplines();
        }
    }

    public void listMemberDisciplines() {
        System.out.println("Which members disciplines do you want to look up?");
        Scanner sc = new Scanner(System.in);
        String thisMember = sc.next();
        Member found = searchSpecificMember(thisMember, sc);

        System.out.println(found);
        controller.getDisciplinesList(found);
        addMemberToDiscipline(found);
    }
}