package FileHandling;

import Domain.MemberClasses.Member;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InvoiceWriter {



    public String debtInvoice(Member m)
    {
        try {
            //FileWriter writer = new FileWriter("src/Files/Invoices/"+ m.getName(.txt));
            FileWriter writer = new FileWriter("Files/Invoices/"+ m.getName()+".txt");

            writer.write("hi " + m.getName() + " We noticed that you have yet to pay your annual membership fee, and as such have a outstanding of "+ m.getDebt() + "dkk. " +
                    "\n please pay your outstanding before the end of the month");
            writer.close();
            return "You have succesfully written an invoice";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
