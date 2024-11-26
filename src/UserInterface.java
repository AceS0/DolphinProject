import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

                }
            } catch (ArrayIndexOutOfBoundsException | IOException aioobe) {
                System.out.println("Unknown request, please try again.");
            }
        }
    }

    public void searchForFilm(){
        ArrayList<Members> found = controller.runSearch();
        Scanner sc = new Scanner(System.in);
    }
}

