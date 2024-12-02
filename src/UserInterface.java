import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller = new Controller();

    public void userInterface() {

        boolean running = true;
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(
                """
                        Welcome to Dolphin - Swimming - Club.
                        Below is your options:\s
                        1. Create a member.
                        2. Remove a member.
                        3. Search for a member.
                        4. List the members.
                        5. Sort memberlist.
                        6. Edit a member.
                        7. Calculate total annual membership fee.
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
                    case "4", "list", "l" -> System.out.println(controller.getMembers().memberList());
                    case "5","edit","e" -> {
                            if (splitPut.length > 1) {
                                editMember(splitPut[1]);
                            } else {
                                System.out.print("Type the member you want to edit: ");
                                editMember(sc.next());
                            }
                    }
                    case "5", "Sort" -> sortMembers();
                    case "6", "sum" -> System.out.println(controller.sumMembershipFees());
                    case "10", "exit" -> running = false;
                    default -> System.out.println("Unknown request, please try again.");

                }
            } catch (ArrayIndexOutOfBoundsException | IOException aioobe) {
                System.out.println("Unknown request, please try again.");
            }
        }
    }

    //Metode til at tilføje en member

    public void addMemberByUser(){
        Scanner sc = new Scanner(System.in);

        System.out.println("You are creating a member");
        System.out.println("Type exit for return to menu");
        System.out.print("Insert MemberID: ");
        while (!sc.hasNextInt()){
            if (sc.next().equals("exit")){
                System.out.println("Returning back to menu");
                userInterface();
            }else {
                System.out.println("Invalid input, please try again");
                System.out.print("Type here: ");
                sc.nextLine();
            }

        }
        int memberId = sc.nextInt();

        sc.nextLine();

        System.out.print("Insert full name: ");
        while (sc.hasNextInt()){
            System.out.println("Invalid input, please try again");
            System.out.print("Type here: ");
            sc.next();
        }

       if (sc.next().equals("exit")) {
            System.out.println("Returning back to menu");
            userInterface();
        }
        String memberName = sc.nextLine();


        System.out.print("Insert age: ");
        while (!sc.hasNextInt()){
            if (sc.next().equals("exit")) {
                System.out.println("Returning back to menu");
                userInterface();
            }else {
                System.out.println("Invalid input, please try again");
                System.out.print("Type here: ");
                sc.next();
            }
        }
        int age = sc.nextInt();
        sc.nextLine();

        while (age <= 0){
            try {
                System.out.print("A person cannot be in this age, try again: ");
                age = sc.nextInt();
            }catch (InputMismatchException iME){
                System.out.print("A person cannot be in this age, try again:");
                sc.nextLine();
                sc.next();
            }
        }

       boolean stage;
        if (age >= 18){
          System.out.println("The member has been assigned the senior status (+18)");
          stage = true;
        }else {
          System.out.println("The member has been assigned the junior status (<18)");
          stage = false;
        }



        System.out.print("Insert telephone number: ");
        while (!sc.hasNextInt()){
            if (sc.next().equals("exit")) {
                System.out.println("Returning back to menu");
                userInterface();
            }else {
                System.out.println("Invalid input, please try again");
                System.out.print("Type here: ");
                sc.next();
            }
        }
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Insert mail: ");
        if (sc.next().equals("exit")) {
            System.out.println("Returning back to menu");
            userInterface();
        }
        String mail = sc.nextLine();

        System.out.print("Is the member active: ");
        String activity = sc.next().toLowerCase();
        boolean activity1 = true;
        while (!activity.equals("yes") && !activity.equals("no")){
            if (activity.equals("exit")) {
                System.out.println("Returning back to menu");
                userInterface();
            } else {
                System.out.println("Invalid input, please try again");
                System.out.print("Type yes/no here: ");
                activity = sc.next();
            }
        }
        if (activity.equals("no")) {
            activity1 = false;
            System.out.println("The member's activity status has been set to passive ");
        }
        sc.nextLine();


        System.out.print("Is the member a senior (+18): ");
        String stage = sc.nextLine().toLowerCase();
        boolean stage1 = true;
        while (!stage.equals("yes") && !stage.equals("no")){
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

        while (!competitive.equals("yes") && !competitive.equals("no")){
            if (competitive.equals("exit")){
                System.out.println("Returning back to menu");
                userInterface();
            } else {
                System.out.println("Invalid input, please try again");
                System.out.print("Type yes/no here: ");
                competitive = sc.nextLine();
            }
        }
        if (competitive.equals("no")) {
            competitive1 = false;
            System.out.println("The members status has been changed to recreational");
        }else if (!activity1) {
            System.out.println("A member cannot be passive and competitive/recreational at the same time");
            System.out.println("Edit the member status to active to continue");
            editMember("");
        }



        controller.addMemberToList(memberId,memberName,age,number,mail,activity1,stage,competitive1);
        controller.setMembershipFee(memberName,activity1,age);
        System.out.println("You have created a new membership for " + memberName);
    }

    public void removeMemberByUser(String inputs) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Members> found = controller.runSearch(inputs);
        if (found.isEmpty()) {
            System.out.println("\nThe member doesn't exist, please create the member if needed.\n");
            userInterface();
        } else {
            for (Members member : found) {
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
                    for (Members member : found) {
                        toPrint.append("\nID: ").append(member.getID()).append(": \nName: ").append(member.getName());
                    }
                    System.out.println(toPrint);
                    System.out.println("Which member do you want to remove?");
                    System.out.print("Type here: ");
                    inputs = sc.nextLine();
                    found = controller.runSearch(inputs);
                    for (Members member : found) {
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

    public void memberListShortInfo(){
        if (Objects.equals(controller.getMembers().memberListShort(), "")) {
            System.out.println("\nThe list is empty, please create a member.\n");
        } else {
            System.out.println(controller.getMembers().memberListShort());
        }
    }

    public void searchForMember(String thisMember){
        ArrayList<Members> found = controller.runSearch(thisMember);
        Scanner sc = new Scanner(System.in);
        if (found.isEmpty()) {
            System.out.println("The member you searched for does not exist, please try again.");
        } else {

            if (found.size() == 1) {
                for (Members member : found) {
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
                for (Members member : found) {
                    System.out.println(member.toString());
                }
                System.out.println("Which member do you want to get more details about?");
                System.out.print("Type here: ");
                String input = sc.nextLine();
                found = controller.runSearch(input);

                for (Members ignored : found) {
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

    public void editMember(String thisMember) {
        try {
            ArrayList<Members> found = controller.runSearch(thisMember);
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

    public void editMemberSplit(Members thisMember) {
        boolean running = true;
        while (running) {
            System.out.println(thisMember.toString());
            Scanner sc = new Scanner(System.in);
            System.out.println("0. exit, 1. id, 2. name, 3. age, 4. number, 5. mail, 6. active, 7. senior, 8. competitive");
            switch (sc.next()) {
                case "0", "exit"-> {
                    System.out.println("-> Returning back to menu.");
                    running = false;
                }
                case "1", "id"-> {
                    System.out.print("what should the new ID be: ");
                    if (sc.hasNextInt()) {
                        System.out.println("The value has now been changed to: " + controller.editMember(thisMember, "id", String.valueOf(sc.nextInt())));
                    }
                }
                case "2", "name"-> {
                    System.out.print("what should the new name be: ");
                    System.out.println(controller.editMember(thisMember, "name", sc.next()));
                }
                case "3", "age"-> {
                    System.out.print("what should the new age be: ");
                    if (sc.hasNextInt()) {
                        System.out.println("The value has now been changed to: " + controller.editMember(thisMember, "age", String.valueOf(sc.nextInt())));
                    }
                }
                case "4", "number"-> {
                    System.out.print("what should the new number be: ");
                    if (sc.hasNextInt()) {
                        System.out.println("The value has now been changed to: " + controller.editMember(thisMember, "number", String.valueOf(sc.nextInt())));
                    }
                }
                case "5", "mail"-> {
                    System.out.print("what should the new mail be: ");
                    System.out.println(controller.editMember(thisMember, "mail", sc.next()));
                }

                case "6","active"-> {
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
                case "8", "competitive"-> {
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
    public void sortMembers() {
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
            case "1", "id", "i" -> System.out.println("test");
            case "2", "name", "n" -> System.out.println("test");
            case "3", "age", "a" -> System.out.println("test");
            case "4", "number" -> System.out.println("test");
            case "5", "mail", "m" -> System.out.println("test");
            case "6", "isactive" -> System.out.println("test");
            case "7", "issenior" -> System.out.println("test");
            case "8", "iscompetitive" -> System.out.println("test");
            case "9", "return", "exit" -> userInterface();
            default -> sortMembers();




        }
        System.out.println("Do you want to have secondary sort?");
        System.out.print("Type (yes/no): ");
        String input2 = sc.next().toLowerCase();
        while (true) {

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
                        case "1", "id", "i" -> System.out.println("test");
                        case "2", "name", "n" -> System.out.println("test");
                        case "3", "age", "a" -> System.out.println("test");
                        case "4", "number" -> System.out.println("test");
                        case "5", "mail", "m" -> System.out.println("test");
                        case "6", "isactive" -> System.out.println("test");
                        case "7", "issenior" -> System.out.println("test");
                        case "8", "iscompetitive" -> System.out.println("test");
                        case "9", "Return", "exit" -> userInterface();
                    }
                }
                case "no" -> System.out.println("test");
                case "9", "return", "exit" -> {
                    System.out.println("Returning back to menu");
                    userInterface();
                }
                default -> System.out.print("Invalid input, please try again: ");


            }

        }

    }
}


