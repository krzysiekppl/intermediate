package pl.sda.intermediate;

import java.util.ArrayList;
import java.util.List;

public class CustomerStatistics {

    Customer[] people = new Customer[]{
            new Customer( "Anna", "Nowak   ", 33, "1200"),
            new Customer( "Beata", "Kowalska", 22, "1200"),
            new Customer( "Marek", " Nowak", 25, "1250"),
            new Customer( "Adam", "Twardowski", 33, "4100"),
            new Customer( "Monika  ", "Kos", 25, "2500"),
            new Customer( "Adam ", "Rudy", 45, "3333"),
            new Customer( "Marek", "Rudy", 15, 2210),
            new Customer( "Adam", "Madej", 15, 3333)
    };

    public List<Customer> customerList(){
        List<Customer> customers = new ArrayList<Customer>();
        for (Customer person : people) {
            customers.add(person);
        }
        return customers;
    }
}
