public class Main {
    public static void main(String[] args) {
        Members enes = new Members(1,"Enes Filikci",60,
                50102030,"EnesZeki@dk",true,false,true);

        enes.setMembershipFee(enes.getIsActive(), enes.getAge());
        System.out.println(enes);
    }
}