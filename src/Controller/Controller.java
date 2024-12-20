package Controller;

import Domain.MemberClasses.Member;
import Domain.MemberClasses.Members;
import Domain.TournamentClasses.Competitor;
import Domain.TournamentClasses.Tournament;
import Domain.TournamentClasses.Tournaments;
import FileHandling.FileHandler;
import FileHandling.InvoiceWriter;

import java.util.ArrayList;

public class Controller {
    private final Members members = new Members();
    private final Tournaments tournaments = new Tournaments();
    private final FileHandler fileHandler = new FileHandler();
    private final InvoiceWriter invoiceWriter = new InvoiceWriter();

    public Member addMemberToList(String name, int age, int number,
                                  String mail, boolean isActive, boolean isSenior, boolean isCompetitive){
        Member result = members.addMember(name, age, number, mail, isActive, isSenior, isCompetitive);
        fileHandler.saveToMembersFile(members);
        return result;
    }

    public Controller() {
    }

    public void writeInvoices()
    {
        ArrayList<Member> mList = members.getUnpaid();
        for (Member member : mList) {
            invoiceWriter.debtInvoice(member);
        }
    }

    public String getDisciplinesList(Member member){
        return members.disciplinesList(member);
    }

    public String loadMembers()
    {
        return fileHandler.loadMembersFile(members);
    }

    public String loadTourneys() {
        return  fileHandler.loadTournamentFile(tournaments);
    }

    public void removeMemberFromList(Member member){

        members.removeMember(member);
        saveMembers();
    }

    public String sumMembershipFees() {
        return members.sumMembershipFees();
    }

    public String getBalancePayment(Member member) {
        return members.balancePaid(member);
    }

    public Members getMembers(){
        return members;
    }

    public String depositMemberBalance(String name, double balance){
        String result = members.depositMemberBalance(name,balance);
        saveMembers();
       return result;
    }

    public void setMembershipBalanceFee(int memberID,String name,boolean isActive, int age, double balance){
       members.setMembershipBalanceFee(memberID,name,isActive,age,balance);
       saveMembers();
    }

    public String editMember(Member member, String command, String edit) {
        String result = members.editMember(member,command,edit);
        saveMembers();
       return result;
    }

    public ArrayList<Member> runSearch(String search){
        ArrayList<Member> results = members.memberLookUp(search);
        return results;
    }

    public ArrayList<Member> paidSearch(boolean search){
        ArrayList<Member> results = members.memberPaidList(search);
        return results;
    }

    public ArrayList<Tournament> runTournamentSearch(String input) {
        return tournaments.tourneyLookUp(input);

    }

    public void saveTournament()
    {
        fileHandler.saveToTournamentsFile(tournaments);
    }

    public void saveMembers()
    {
        String text = fileHandler.saveToMembersFile(members);
    }


    public void createTournament(String name, String date, String place, String category, ArrayList<Competitor> competitors) {
        tournaments.createTournament(name, date, place, category, competitors);
        saveMembers();
    }

    public Competitor createCompetitor(Member member, double time,String date , String category)
    {
        Competitor c = tournaments.createCompetitor(member, time, date, category);
        saveMembers();
        return c;
    }

    public Tournaments getTournaments() {
        return tournaments;
    }

    public String wipeMembers() {
        return fileHandler.wipeMemberFile();
    }

    public String  wipeTourneys() {
        return fileHandler.wipeTournamentFile();
    }

    public String addMemberToDiscipline(Member member, String command) {
        String result = members.addMemberToDiscipline(member, command);
        saveMembers();
         return result;

    }

    public String getTopDisciplinesList(String command, String command2){
        return members.topDisciplinesList(command,command2);
    }
}