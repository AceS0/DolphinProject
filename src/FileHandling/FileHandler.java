package FileHandling;

import Domain.MemberClasses.Member;
import Domain.MemberClasses.Members;
import Domain.TournamentClasses.Competitor;
import Domain.TournamentClasses.Tournament;
import Domain.TournamentClasses.Tournaments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class FileHandler {
    private File memberFile;
    private File tourneyFile;

    public FileHandler() {
        memberFile = new File("src/Files/MembersFile.txt");
        tourneyFile = new File("src/Files/TourneysFile.txt");
            //memberFile = new File("Files/MembersFile.txt");
            //tourneyFile = new File("Files/TourneysFile.txt");

    }

    public String saveToMembersFile(Members members) {
        try {
            FileWriter writer = new FileWriter(memberFile);
            writer.write("contains all members \n");
            writer.write(members.compactMembers());
            writer.close();
            return "You have succesfully saved your members";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String saveToTournamentsFile(Tournaments tournaments) {//wip
        try {
            FileWriter writer = new FileWriter(tourneyFile);
            writer.write("contains all tourneys \n");
            writer.write(tournaments.compactTourneys());
            writer.close();
            return "You have succesfully saved your tourneys";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String loadMembersFile(Members members) {
        Scanner sc = null;
        int x = 0;
        try {
            sc = new Scanner(memberFile);
            sc.nextLine();

            Member checkFile = null;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] attributes = line.split(";");
                if (attributes.length == 1) {
                    members.setOpenID(Integer.parseInt(attributes[0]));
                } else {
                    x++;
                    int ID = Integer.parseInt(attributes[0]);
                    String name = attributes[1];
                    int age = Integer.parseInt(attributes[2]);
                    int number = Integer.parseInt(attributes[3]);
                    String mail = attributes[4];
                    boolean isActive = Boolean.parseBoolean(attributes[5]);
                    boolean isSenior = Boolean.parseBoolean(attributes[6]);
                    boolean isCompetitive = Boolean.parseBoolean(attributes[7]);
                    double debt = Double.parseDouble(attributes[8]);
                    double balance = Double.parseDouble(attributes[9]);
                    String butterfly = (attributes[10]);
                    String crawl = (attributes[11]);
                    String backstroke = (attributes[12]);
                    String breaststroke = (attributes[13]);
                    checkFile = new Member(ID, name, age, number, mail, isActive, isSenior, isCompetitive, debt, balance, butterfly, crawl, backstroke, breaststroke);

                    members.addMemberByObject(checkFile);
                }
            }
            sc.close();
        } catch (NullPointerException | FileNotFoundException | NoSuchElementException ignored) {
            return "\nload failed";
        }
        return ("\n" + x + " members Loaded successfully.");
    }


    public String wipeTournamentFile() {
        try {
            FileWriter writer = new FileWriter(tourneyFile);
            writer.write("contains all tourneys \n");
            writer.close();
            return "tournaments wiped";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String wipeMemberFile()
    {
        try {
            FileWriter writer = new FileWriter(tourneyFile);
            writer.write("contains all tourneys \n");
            writer.close();
            return "members wiped";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //wip
    public String loadTournamentFile(Tournaments tournaments) {

        Scanner sc = null;
        int x = 0;
        try {
            sc = new Scanner(tourneyFile);
            sc.nextLine();

            Tournament checkFile = null;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] attributes = line.split(";");
                if (attributes.length == 1) {
                    tournaments.setOpenID(Integer.parseInt(attributes[0]));
                } else {
                    x++;
                    int attributesChecked;
                    ArrayList<Competitor> competitors = new ArrayList<Competitor>();
                    int id = Integer.parseInt(attributes[0]);
                    String name = attributes[1];
                    String date = attributes[2];
                    String place = attributes[3];
                    String category = attributes[4];
                    attributesChecked = 5;
                    while ((attributes.length - attributesChecked) > 0) {
                        String cName = attributes[attributesChecked];
                        int cId = Integer.parseInt(attributes[attributesChecked + 1]);
                        double cTime = Double.parseDouble(attributes[attributesChecked + 2]);
                        competitors.add(new Competitor(cName, cId, cTime));
                        attributesChecked += 3;
                    }


                    checkFile = new Tournament(id, name, date, place, category, competitors);

                    tournaments.addTournamentByObject(checkFile);
                }
            }
            sc.close();
        } catch (NullPointerException | FileNotFoundException | NoSuchElementException ignored) {
            return "\nload failed";
        }
        return ("\n" + x + " Tournaments Loaded successfully.");
    }
}
