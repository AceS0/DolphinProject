package FileHandling;

import Domain.MemberClasses.Member;
import Domain.MemberClasses.Members;
import Domain.TournamentClasses.Tournaments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileHandler {

    public String saveToMembersFile(Members members) {
        File file = new File("Files/MembersFile.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("contains all members \n");
            writer.write(members.compactMembers());
            writer.close();
            return "You have succesfully saved your members";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String saveToTournamentsFile(Tournaments tournaments) {//wip
        File file = new File("Files/TournamentsFile.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("\n");
            writer.write(tournaments.compactTourneys());
            System.out.println(tournaments.compactTourneys());
            writer.close();
            return "You have succesfully saved your tourneys";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String loadMembersFile(Members members) {
        File file = new File("Files/MembersFile.txt");
        Scanner sc = null;
        int x = 0;
        try {
            sc = new Scanner(file);
            sc.nextLine();

            Member checkFile = null;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] attributes = line.split(";");
                if (attributes.length == 1) {
                    members.setOpenID(Integer.parseInt(attributes[0]));
                } else {
                    x++;
                    checkFile = new Member(
                            Integer.parseInt(attributes[0]),
                            attributes[1],
                            (Integer.parseInt(attributes[2])),
                            (Integer.parseInt(attributes[3])),
                            ((attributes[4])),
                            Boolean.parseBoolean(attributes[5]),
                            Boolean.parseBoolean(attributes[6]),
                            Boolean.parseBoolean(attributes[7]),
                            Double.parseDouble(attributes[8]),
                            Double.parseDouble(attributes[9])
                    );

                    members.addMemberByObject(checkFile);
                }
            }
            sc.close();
        } catch (NullPointerException | FileNotFoundException ignored) {
            return "\nload failed";
        }
        return ("\n" + x + " members Loaded successfully.");
    }


    public String deleteFile() {
        File file = new File("save.txt");
        if (file.delete()) {
            return "You have deleted a file ";
        } else {
            return "You need a file, before you can delete.";
        }
    }

    //wip
    public String loadTournamentFile(Tournaments tournaments) {

        return "tournament load is wip";
    }
}
