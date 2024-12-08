package UI;

import Controller.Controller;
import Domain.Members.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserInterface {
    private final Controller controller = new Controller();

    public void userInterface() {

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
                    case "3","search","s" -> {
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
                            System.out.print("Type the member you want to edit: ");
                            editMember(sc.next());
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
                }
            }catch(ArrayIndexOutOfBoundsException | IOException aioobe){
                            System.out.println("Unknown request, please try again.");
                        }
                    }
                }

                //Metode til at tilføje en member

                public void addMemberByUser () {
                    Scanner sc = new Scanner(System.in);
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


                    System.out.println("You are creating a member");
                    System.out.print("Insert MemberID: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Type here: ");
                        sc.next();
                    }
                    int memberId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Insert full name: ");
                    while (sc.hasNextInt()) {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Type here: ");
                        sc.next();
                    }
                    String memberName = sc.nextLine();


                    System.out.print("Insert age: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Type here: ");
                        sc.next();

                    }
                    int age = sc.nextInt();

                    sc.nextLine();


                    System.out.print("Insert telephone number: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Type here: ");
                        sc.next();
                    }
                    int number = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Insert mail: ");
                    String mail = sc.next();


                    System.out.print("Is the member active: ");
                    String activity = sc.next().toLowerCase();
                    boolean activity1 = true;
                    while (!activity.equals("yes") && !activity.equals("no")) {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Type yes/no here: ");
                        activity = sc.next();
                    }
                    if (activity.equals("no")) {
                        activity1 = false;
                        System.out.println("The member's activity status has been set to passive ");
                    }
                    sc.nextLine();


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


                    System.out.print("Is the member competitive: ");
                    String competitive = sc.nextLine().toLowerCase();
                    boolean competitive1 = true;

                    while (!competitive.equals("yes") && !competitive.equals("no")) {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Type yes/no here: ");
                        competitive = sc.nextLine();
                    }
                    if (competitive.equals("no")) {
                        competitive1 = false;
                        System.out.println("The members status has been changed to recreational");
                    }

                    System.out.print("How much did the member deposit?: ");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Type here: ");
                        sc.next();
                    }
                    double balance = sc.nextDouble();

                    controller.addMemberToList(memberId, memberName, age, number, mail, activity1, stage1, competitive1);
                    controller.setMembershipBalanceFee(memberId, memberName, activity1, age,balance);
                    System.out.println("You have created a new member, and successfully connected a membership ID.");
                }

                public void removeMemberByUser (String inputs){
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
                                System.out.println("Which member do you want to remove?");
                                System.out.print("Type here: ");
                                inputs = sc.nextLine();
                                found = controller.runSearch(inputs);
                                for (Member member : found) {
                                    if (found.size() == 1) {
                                        System.out.println("You have successfully removed " + member.getName());
                                        controller.removeMemberFromList(member);
                                        return;
                                    }
                                }
                                if (found.isEmpty()) {
                                    System.out.println("The member doesn't exist, please try again or to leave type \"exit\" or \"quit\".");
                                    System.out.print("Type here: ");
                                    String input = sc.nextLine();
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

                public void memberListShortInfo () {
                    if (Objects.equals(controller.getMembers().memberListShort(), "")) {
                        System.out.println("\nThe list is empty, please create a member.\n");
                    } else {
                        System.out.println(controller.getMembers().memberListShort());
                    }
                }

                public void searchForMember (String thisMember){
                    ArrayList<Member> found = controller.runSearch(thisMember);
                    Scanner sc = new Scanner(System.in);
                    if (found.isEmpty()) {
                        System.out.println("The member you searched for does not exist, please try again.");
                    } else {

                        if (found.size() == 1) {
                            for (Member member : found) {
                                System.out.println(member.toString());
                            }
                            System.out.println("Do you want to edit " + found.getFirst().getName() + "? HINT \"Yes\" or \"No\"");
                            System.out.print("Type here: ");
                            String input = sc.next().toLowerCase();

                            while (true) {
                                if (input.equals("yes") || input.equals("y")) {
                                    editMemberSplit(found.getFirst());
                                    return;
                                } else if (input.equals("no") || input.equals("n")) {
                                    System.out.println("-> Returning back to menu.");
                                    userInterface();
                                } else {
                                    System.out.print("Couldn't interpret the input, please enter \"Yes\" or \"No\": ");
                                    input = sc.next().toLowerCase();
                                }
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
                                System.out.println("Couldn't find the member, please try again or to leave type \"exit\" or \"quit\". ");
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

                public void editMember (String thisMember){
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
                                System.out.print("Couldn't interpret the input, please enter \"Yes\" or \"No\": ");
                                input = sc.next().toLowerCase();
                            }
                        }
                    } catch (NoSuchElementException nsee) {
                        System.out.println("The member was either not found or the members collection is empty, please try again.");
                    }
                }

                public void editMemberSplit (Member thisMember){
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

                public void runBalancePayment (String findMember){
                    Scanner sc = new Scanner(System.in);
                    ArrayList<Member> found = controller.runSearch(findMember);
                    System.out.println(controller.getBalancePayment(found.getFirst()));
                        if (found.size() == 1 && found.getFirst().getPaidStatus().equals("has not paid")) {
                                System.out.println("Would you like to pay the fee with your current balance?");
                                System.out.print("Type yes or no: ");
                                while(true) {
                                    String input = sc.next().toLowerCase();
                                    double remainingBalance = found.getFirst().getBalance() - found.getFirst().getAnnualFee();
                                    double remainingFee = found.getFirst().getAnnualFee() - found.getFirst().getBalance();
                                    if (input.equals("yes") && found.getFirst().getBalance() >= found.getFirst().getAnnualFee() && found.getFirst().getBalance() > 0.0) {
                                        found.getFirst().setPaidStatus(true);
                                        System.out.println(found.getFirst().getName() + "'s balance after the payment: " + remainingBalance +
                                                " remaining fee after the payment: 0.0");
                                        found.getFirst().setBalance(remainingBalance);
                                        found.getFirst().setAnnualFee(0.0);
                                        break;
                                    } else if (input.equals("yes") && found.getFirst().getBalance() < found.getFirst().getAnnualFee() && found.getFirst().getBalance() > 0.0) {
                                        System.out.println(found.getFirst().getName() + "'s balance after the payment: " + "0.0" +
                                                " remaining fee after the payment: " + remainingFee);
                                        found.getFirst().setBalance(0.0);
                                        found.getFirst().setAnnualFee(remainingFee);
                                        break;
                                    } else if (input.equals("yes") && found.getFirst().getBalance() <= 0.0){
                                        System.out.println("To proceed the payment, please deposit some money.\nYour balance: "
                                                + found.getFirst().getBalance() +"\nYour fee: " + found.getFirst().getAnnualFee());
                                        break;
                                    } else if (input.equals("no")) {
                                        System.out.println("-> Returning back to menu.");
                                        break;
                                    } else {
                                        System.out.println("Unknown request, please try again.");
                                    }
                                }
                        } else if (found.size() == 1 && found.getFirst().getPaidStatus().equals("has not paid")) {
                            System.out.println(found.getFirst().getName() + " has already paid his fee for this year.");
                        } else if (found.isEmpty()) {
                            System.out.println("Couldn't find the member, try again or use ID instead.");
                        } else {
                            StringBuilder toPrint = new StringBuilder();
                            toPrint.append("We found more than 1 member, please try again.\n");
                            for (Member member : found){
                                toPrint.append("\nID: ").append(member.getID()).append("\nName: ").append(member.getName());
                            }
                            toPrint.append("\n\"HINT\" Try to use ID instead of name.");
                            System.out.println(toPrint);
                        }
                }

                public void depositMemberBalance(){
                Scanner sc = new Scanner(System.in);
                System.out.print("Which member deposited money?: ");
                String name = sc.nextLine();
                System.out.print("Register the amount that got deposited: ");
                double balance = sc.nextDouble();
                System.out.println(controller.depositMemberBalance(name, balance));
                }

                public void listMembers(){
                    Scanner sc = new Scanner(System.in);
                    System.out.println("You have 3 types of lists:\n1. Print a list of all members." +
                            "\n2. Print a list of all members who paid their fees." +
                            "\n3. Print a list of all members who didn't pay their fees.");
                    String command = sc.next();
                    switch (command){
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
}