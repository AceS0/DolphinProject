import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Members enes = new Members(1,"Enes",19,
                "EnesZeki@dk",50102030,true,false,true);

        enes.setMembershipFee(enes.getIsActive(), enes.getAge());
        System.out.println(enes);


    }
}
