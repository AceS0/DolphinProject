package Controller;

import Domain.MemberClasses.Member;
import Domain.MemberClasses.MemberHandling;
import java.util.ArrayList;

public class Controller {
    private final MemberHandling members = new MemberHandling();

    public void addMemberToList(int ID, String name, int age, int number,
                                String mail, boolean isActive, boolean isSenior, boolean isCompetitive){

        members.addMember(new Member(ID, name, age, number, mail, isActive, isSenior, isCompetitive));
    }

    public void removeMemberFromList(Member member){

        members.removeMember(member);
    }

    public String sumMembershipFees() {
        return members.sumMembershipFees();
    }

    public String getBalancePayment(Member member) {
        return members.balancePaid(member);
    }

    public MemberHandling getMembers(){
        return members;
    }

    public String depositMemberBalance(String name, double balance){
       return members.depositMemberBalance(name,balance);
    }

    public void setMembershipBalanceFee(int memberID,String name,boolean isActive, int age, double balance){
       members.setMembershipBalanceFee(memberID,name,isActive,age,balance);
    }

    public String editMember(Member member, String command, String edit) {
       return members.editMember(member,command,edit);
    }

    public ArrayList<Member> runSearch(String search){
        ArrayList<Member> results = members.memberLookUp(search);
        return results;
    }

    public ArrayList<Member> paidSearch(boolean search){
        ArrayList<Member> results = members.memberPaidList(search);
        return results;
    }
}