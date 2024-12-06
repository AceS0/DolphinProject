package UI;

import Controller.Controller;
import Domain.MemberClasses.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserInterface {
    private final Controller controller = new Controller();

    public void userInterface() {
        frontPage();

    }
    public void frontPage()
    {
        boolean running = true;
        while (running)
        {
            Scanner sc = new Scanner(System.in);
            String userInput = reqString(
                    """
                            Welcome to your swimming club.
                            choose what category to manage:\s
                            1. members
                            2. tourneys
                            3. Exit
                            Type here:\s""", sc);
            switch (userInput)
            {
                case  "1", "members", "m" -> memberManagement();
                case  "2", "tourney", "t" -> tourneyManagement();
                case  "3", "exit" -> running = false;
            }
        }
    }

    //Metode til at tilføje en member

    public void memberManagement() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(
                """
                        Welcome to your swimming club.
                        Below is your options:\s
                        1. Create a member.
                        2. Remove a member.
                        3. Search for a member.
                        4. List the members.
                        5. Edit a member.
                        6. Calculate total annual membership fee.
                        7. Check member's balance and pay fee.
                        8. Deposit balance for a member.
                        10. Exit""");
        /*"""

                        1. Create a member.
                        2. Remove a member.
                        3. Search for a member.
                        4. List the members.
                        5. Get a help list.
                        6. Edit a member.
                        7. Save members to a file.
                        8. Load members from a file.
                        9. Delete a file.
                        10. Exit"""*/

        while (running) {
            try {
                System.out.print("""
                        
                        Type "help", for a list of commands.\
                        
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
                            System.out.print("insert search term: ");
                            searchForMember(sc.next());
                        }
                    }
                    case "4", "list", "l" -> listMembers();
                    case "5", "edit", "e" -> {
                        if (splitPut.length > 1) {
                            editMember(splitPut[1]);
                        } else {
                            editMember(reqString("Type the member you want to edit: ", sc));
                        }
                    }
                    case "6", "sum" -> System.out.println(controller.sumMembershipFees());
                    case "7", "balance" -> {
                        if (splitPut.length > 1) {
                            runBalancePayment(splitPut[1]);
                        } else {
                            System.out.print("insert the member you want to check: ");
                            runBalancePayment(sc.next());
                        }
                    }
                    case "8", "deposit" -> depositMemberBalance();
                    case "10", "exit" -> running = false;
                }
            } catch (ArrayIndexOutOfBoundsException | IOException aioobe) {
                System.out.println("Unknown request, please try again.");
            }
        }
    }

    public void addMemberByUser() {
        Scanner sc = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("You are creating a member");
        //udkommenteret pga. autoID
                    /*System.out.print("Insert MemberID: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Type here: ");
                        sc.next();
                    }
                    int memberId = sc.nextInt();*/

        String memberName = reqString("Insert full name: ", sc);

        int age = reqInt("Insert age: ", sc);

        int number = reqInt("Insert telephone number: ", sc);

        String mail = reqString("Insert mail: ", sc);

        Boolean activity = reqBool("Is the member active: ", sc);

        System.out.print("Is the member a senior (+18): ");
        String stage = sc.nextLine().toLowerCase();
        boolean stage1 = true;
        while (!stage.equals("yes") && !stage.equals("no")) {
            System.out.println("Invalid input, please try again");
            System.out.print("Type yes/no here: ");
            stage = sc.nextLine();
        }
        if (stage.equals("no")) {
            stage1 = false;
            System.out.println("The member has been assigned the junior status (<18)");

        }

        boolean competitive1 = reqBool("Is the member competitive: ", sc);

        int balance = reqInt("How much did the member deposit?: ", sc);

        Member m = controller.addMemberToList(memberName, age, number, mail, activity, stage1, competitive1);
        controller.setMembershipBalanceFee(m.getID(), memberName, activity, age, balance);
        System.out.println("You have created a new member, and successfully connected a membership ID.");
    }

    public void removeMemberByUser(String inputs) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Member> found = controller.runSearch(inputs);
        if (found.isEmpty()) {
            System.out.println("\nThe member doesn't exist, please create the member if needed.\n");
            userInterface();
        } else {
            for (Member member : found) {
                if (found.size() == 1) {
                    System.out.println("You have successfully removed " + member.getName() + "\n");
                    controller.removeMemberFromList(member);
                    userInterface();
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
        ArrayList<Member> found = controller.runSearch(thisMember);
        Scanner sc = new Scanner(System.in);
        if (found.isEmpty()) {
            System.out.println("The member you searched for does not exist, please try again.");
        } else {

            if (found.size() == 1) {
                for (Member member : found) {
                    System.out.println(member.toString());
                }
                boolean input = reqBool("Do you want to edit " + found.getFirst().getName() + "? \nType here: ", sc);
                if (input) {
                    editMemberSplit(found.getFirst());
                } else if (!input) {
                    System.out.println("-> Returning back to menu.");
                    userInterface();
                }

            } else {
                for (Member member : found) {
                    System.out.println(member.toString());
                }
                System.out.println("Which member do you want to get more details about?");
                System.out.print("Type here: ");
                String input = sc.nextLine();
                found = controller.runSearch(input);

                for (Member ignored : found) {
                    if (!found.isEmpty()) {
                        searchForMember(input);
                    }
                }

                if (found.isEmpty()) {
                    System.out.println("Couldn't find the member, please try again or to leave type exit or quit. ");
                    System.out.print("Type here: ");
                    input = sc.nextLine();
                    if (input.equals("quit") || input.equals("exit")) {
                        userInterface();
                    } else {
                        searchForMember(input);
                    }
                }
            }
        }
    }

    public void editMember(String thisMember) {
        try {
            ArrayList<Member> found = controller.runSearch(thisMember);
            Scanner sc = new Scanner(System.in);


            System.out.println("Do you want to edit '" + found.getFirst().getName() + "'? (yes/no)");
            String input = sc.next().toLowerCase();
            while (true) {
                if (input.equals("yes") || input.equals("y")) {
                    editMemberSplit(found.getFirst());
                    return;
                } else if (input.equals("no") || input.equals("n")) {
                    System.out.println("-> Returning back to menu.");
                    return;
                } else {
                    System.out.print("Couldn't interpret the input, please enter Yes or No: ");
                    input = sc.next().toLowerCase();
                }
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("The member was either not found or the members collection is empty, please try again.");
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
        ArrayList<Member> found = controller.runSearch(findMember);
        Member foundMember = found.getFirst();
        String name = foundMember.getName();
        System.out.println(controller.getBalancePayment(foundMember));
        if (found.size() == 1 && foundMember.getPaidStatus().equals("has not paid")) {
            Scanner sc = new Scanner(System.in);
            boolean input = reqBool("Would you like to pay the fee with your current balance?" + "\n" + "Type yes or no: ", sc);
            double debt = foundMember.getDebt();
            double balance = foundMember.getBalance();
            double remainingBalance = balance - debt;
            double remainingFee = debt - balance;
            if (input && balance >= debt && balance > 0) {
                foundMember.setPaidStatus(true);
                System.out.println(name + "'s balance after the payment: " + remainingBalance +
                        " remaining fee after the payment: 0.0");
                foundMember.setBalance(remainingBalance);
                foundMember.setDebt(0);
            } else if (input && balance < debt && balance > 0) {
                System.out.println(name + "'s balance after the payment: " + "0.0" +
                        " remaining fee after the payment: " + remainingFee);
                foundMember.setBalance(0);
                foundMember.setDebt(remainingFee);
            } else if (input && balance <= 0) {
                System.out.println("To proceed the payment, please deposit some money.\nYour balance: "
                        + balance + "\nWhat you owe: " + debt);
            } else if (!input) {
                System.out.println("-> Returning back to menu.");
            }
        } else if (found.size() == 1 && foundMember.getPaidStatus().equals("has not paid")) {
            System.out.println(name + " has already paid his fee for this year.");
        } else if (found.isEmpty()) {
            System.out.println("Couldn't find the member, try again or use ID instead.");
        } else {
            StringBuilder toPrint = new StringBuilder();
            toPrint.append("We found more than 1 member, please try again.\n");
            for (Member member : found) {
                toPrint.append("\nID: ").append(member.getID()).append("\nName: ").append(name);
            }
            toPrint.append("\nHINT Try to use ID instead of name.");
            System.out.println(toPrint);
        }
    }

    public void depositMemberBalance() {
        Scanner sc = new Scanner(System.in);
        Member found;
        while (true) {
            ArrayList<Member> list = controller.runSearch(reqString("what member are you looking for: ", sc));
            if (list.isEmpty()) {
                System.out.println("member was not found, please try again \n");
            } else {
                found = list.getFirst();
                break;
            }
        }

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
                } else {
                    System.out.println("Every member has paid their fees.");
                }
            }
            default -> System.out.println("Unknown request, please try again.");
        }
    }

    public void tourneyManagement()
    {
        System.out.println("you're in tourneyManagement, bye");
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
            switch (sc.nextLine().toLowerCase()) {
                case "true", "yes":
                    return true;
                case "false", "no":
                    return false;
                default:
                    System.out.println("that's not a true or false, try again");
            }
        }
    }

    public double reqDouble(String quote, Scanner sc) {
        System.out.print(quote);
        while (true) {
            String input = sc.next();
            try {
                return Double.parseDouble(input);

            } catch (NumberFormatException nsee) {
                System.out.println("invalid input, try again");

            }
        }
    }
}