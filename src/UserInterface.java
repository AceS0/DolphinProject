import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller = new Controller();

    public void userInterface() {

        boolean running = true;
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(
                """
                        Welcome to your movie collection.
                        Below is your options:\s
                        1. Create a member.
                        2. Search for a member.
                        3. Edit a member.
                        4. Calculate total annual membership fee.
                        10. Exit""");
        /*"""
                        Welcome to your movie collection.
                        Below is your options:\s
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
                    case "2","search","s" -> {
                        if (splitPut.length > 1) {
                            searchForMember(splitPut[1]);
                        } else {
                            System.out.print("insert search term: ");
                            searchForMember(sc.next());
                        }
                    }
                    case "3","edit","e" -> {
                            if (splitPut.length > 1) {
                                editMember(splitPut[1]);
                            } else {
                                System.out.print("Type the member you want to edit: ");
                                editMember(sc.next());
                            }
                    }
                    case "4", "sum" -> System.out.println(controller.sumMembershipFees());
                }
            } catch (ArrayIndexOutOfBoundsException | IOException aioobe) {
                System.out.println("Unknown request, please try again.");
            }
        }
    }

    //Metode til at tilføje en member

    public void addMemberByUser(){
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("You are creating a member");
        System.out.print("Insert MemberID: ");
        while (!sc.hasNextInt()){
            System.out.println("Invalid input, please try again");
            System.out.print("Type here: ");
            sc.next();
        }
        int memberId = sc.nextInt();

        sc.nextLine();

        System.out.print("Insert full name: ");
        while (sc.hasNextInt()){
            System.out.println("Invalid input, please try again");
            System.out.print("Type here: ");
            sc.next();
        }
        String memberName = sc.nextLine();


        System.out.print("Insert age: ");
        while (!sc.hasNextInt()){
            System.out.println("Invalid input, please try again");
            System.out.print("Type here: ");
            sc.next();

        }
        int age = sc.nextInt();

        sc.nextLine();


        System.out.print("Insert telephone number: ");
        while (!sc.hasNextInt()){
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
        while (!activity.equals("yes") && !activity.equals("no")){
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
            System.out.println("Invalid input, please try again");
            System.out.print("Type yes/no here: ");
            competitive = sc.nextLine();
        }
        if (competitive.equals("no")) {
            competitive1 = false;
            System.out.println("The members status has been changed to recreational");
        }


        controller.addMemberToList(memberId,memberName,age,number,mail,activity1,stage1,competitive1);
        controller.setMembershipFee(memberName,activity1,age);
        System.out.println("You have created a new membership");
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
                        editMemberSplit(found.getFirst(), "placeholder");
                        System.out.println("Not finished");
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
                StringBuilder toPrint = new StringBuilder();
                for (Members member : found) {
                    toPrint.append("ID: ").append(member.getID()).append
                            ("\nName: ").append(member.getName()).append
                            ("\nAge: ").append(member.getAge()).append
                            ("\nPhone number: ").append(member.getNumber()).append
                            ("\nMail: ").append(member.getMail()).append
                            ("\nIs active: ").append(member.getIsActive()).append
                            ("\nIs senior: ").append(member.getIsSenior()).append
                            ("\nIs competitive: ").append(member.getIsCompetitive()).append
                            ("\nAnnual fee: ").append(member.getAnnualFee()).append(" DKK");
                }

                System.out.println(toPrint);
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
                    editMemberSplit(found.getFirst(), "placeholder");
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

    public void editMemberSplit(Members thisMember, String edit) {
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
}