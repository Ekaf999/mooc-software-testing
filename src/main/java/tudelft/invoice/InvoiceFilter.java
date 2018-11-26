package tudelft.invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceFilter {

    public List<Invoice> filter() {

        InvoiceDao invoiceDao = new InvoiceDao();
        List<Invoice> allInvoices = invoiceDao.all();

        List<Invoice> filtered = new ArrayList<>();

        for(Invoice inv : allInvoices) {
            if(inv.getValue() < 100.0)
                filtered.add(inv);
        }

        return filtered;

    }


    public static void main(String[] args) {
        System.out.println("Starting to create connection");
        InvoiceDao idao = new InvoiceDao();
        Invoice inv1 = new Invoice("Mazsola", 99);
        Invoice inv2 = new Invoice("Manocska", 100);
        idao.save(inv1);
        idao.save(inv2);
        List<Invoice> result = new InvoiceFilter().filter();
        System.out.println(result);
    }
}