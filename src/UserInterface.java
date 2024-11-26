import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Controller controller = new Controller();

    public void userInterface() {

        boolean running = true;
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(
                """
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
                        10. Exit""");

        while (running) {
            try {
                System.out.print("""
                        
                        Type "help", for a list of commands.\
                        
                        Choose an option:\s""");
                //Dette splitter brugerens input, som vi gør brug af i bla search funktionen:
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
        System.out.print("Insert MemberID: ");
        int memberId = sc.nextInt();

        System.out.print("Insert first name: ");
        String memberName = sc.next();

        System.out.print("Insert age: ");
        int age = sc.nextInt();

        System.out.print("Insert telephone number: ");
        int number = sc.nextInt();

        System.out.print("Insert mail: ");
        String mail = sc.next();

        System.out.print("Is the member active: ");
        String activity = sc.next().toLowerCase();
        boolean activity1 = true;
        while (!activity.equals("yes") && !activity.equals("no")){
            System.out.println("Invalid input, please try again");
            System.out.print("Type yes/no here: ");
            activity = sc.next().toLowerCase();

            if (activity.equals("yes") || activity.equals("no")){
                if (activity.equals("no")) activity1 = false;
                    System.out.println("The member's activity status has been set to passive ");
                    break;
            }
        }

        System.out.print("Is the member a senior (+18): ");
        String stage = sc.nextLine().toLowerCase();
        boolean stage1 = true;
        while (!stage.equals("yes") && !stage.equals("no")){
            System.out.println("Invalid input, please try again");
            System.out.print("Type yes/no here: ");
            stage = sc.nextLine().toLowerCase();

            if (stage.equals("yes") || stage.equals("no")){
                if (stage.equals("no")){
                    stage1 = false;
                    System.out.println("The member has been assigned the junior status (<18)");
                    break;
                }
            }
        }

        System.out.print("Is the member competitive: ");
        String competitive = sc.nextLine().toLowerCase();
        boolean competitive1 = true;

        while (!competitive.equals("yes") && !competitive.equals("no")){
            System.out.println("Invalid input, please try again");
            System.out.print("Type yes/no here: ");
            competitive = sc.nextLine().toLowerCase();

            if (competitive.equals("yes") || competitive.equals("no")){
                if (competitive.equals("no")) {
                    competitive1 = false;
                    System.out.println("The members status has been changed to recreational");
                    break;
                }
            }
        }

        controller.addMemberToList(memberId,memberName,age,number,mail,activity1,stage1,competitive1);
        controller.setMembershipFee(memberName,activity1,age);
    }

    public void searchForMember(String members){
        ArrayList<Members> found = controller.runSearch(members);
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
                        //editFilm(found.getFirst(), "placeholder");
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
}


