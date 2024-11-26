public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.userInterface();
        Members enes = new Members(1,"Enes",60,50102030,
                "EnesZeki@dk",true,false,true);


        enes.setMembershipFee(enes.getIsActive(), enes.getAge());
        System.out.println(enes);


    }
}