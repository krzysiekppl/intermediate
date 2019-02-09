package pl.sda.intermediate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CustomerStatistics {

    Customer[] people = new Customer[]{
            new Customer("Anna", "Nowak   ", 33, "1200"),
            new Customer("Beata", "Kowalska", 22, "1200"),
            new Customer("Marek", " Nowak", 25, "1250"),
            new Customer("Adam", "Twardowski", 33, "4100"),
            new Customer("Monika  ", "Kos", 25, "2500"),
            new Customer("Adam ", "Rudy", 45, "3333"),
            new Customer("Marek", "Rudy", 15, 2210),
            new Customer("Adam", "Madej", 15, 3333)
    };

    //1. Napisz metodę, która zamieni tablicę people na listę people - ta metoda może być potem wykorzystywana przez Was w następnych metodach

    public List<Customer> customersListForEachVersion() {
        List<Customer> customers = new ArrayList<Customer>();
        for (Customer person : people) {
            customers.add(person);
        }
        return customers;
    }

    public List<Customer> customerList() {
        return Arrays.asList(people);
    }

    public List<Customer> customersListWithStream() {
        return Arrays.stream(people).collect(Collectors.toList());
    }

    //2. Napisz metodę, która przyjmie tablicę people i zwróci listę Stringów <imię nazwisko> (elementem listy będzie np "Anna Nowak")

    public List<String> customersNamesList() {
        List<String> peopleNames = new ArrayList<>();
        for (Customer person : people) {
            peopleNames.add(person.getName() + " " + person.getSurname());
        }
        return peopleNames;
    }

    public List<String> customersNamesListWithStream() {
        return customerList().stream()
                .map(s -> s.getName() + " " + s.getSurname())
                .collect(Collectors.toList());
    }

    //3. Napisz metodę, która zwróci mapę osób <id,osoba>

    public Map<Integer, Customer> customersIdMap() {
        Map<Integer, Customer> peopleMap = new HashMap<>();
        for (Customer person : people) {
            peopleMap.put(person.getId(), person);
        }
        return peopleMap;
    }

    public Map<Integer, Customer> customersIdMapWithStream() {
        return Arrays.stream(people)
                .collect(Collectors.toMap(Customer::getId, c -> c));
    }

    //4. Napisz metodę, która zwróci mapę osób według zarobków <zarobki,osoby_z_zarobkami>

    public Map<BigDecimal, List<Customer>> groupCustomersBySalary() {
        Map<BigDecimal, List<Customer>> salaryMap = new HashMap<>();
        for (Customer person : people) {
            if (salaryMap.containsKey(person.getSalary())) {
                List<Customer> innerList = salaryMap.get(person.getSalary());
                innerList.add(person);
            } else {
                List<Customer> innerList = new ArrayList<>();
                innerList.add(person);
                salaryMap.put(person.getSalary(), innerList);
            }
        }
        return salaryMap;
    }

    public Map<BigDecimal, List<Customer>> groupCustomersBySalaryWithStream() {
        return Arrays.stream(people)
                .collect(groupingBy(Customer::getSalary));
    }

    //5. Napisz metodę, która zwróci statystykę ile jest osób z danymi zarobkami <zarobki,liczba_osób>

    public Map<BigDecimal, Long> countCustomersWithSameSalary() {
        Map<BigDecimal, Long> salaryMap = new HashMap<>();
        for (Customer person : people) {
            if (salaryMap.containsKey(person.getSalary())) {
                Long value = salaryMap.get(person.getSalary());
                salaryMap.put(person.getSalary(), value + 1);
            } else {
                salaryMap.put(person.getSalary(), 1L);
            }
        }
        return salaryMap;
    }

    public Map<BigDecimal, Long> countCustomersWithSameSalaryWithStream() {
        return Arrays.stream(people)
                .collect(groupingBy(Customer::getSalary, counting()));
    }

    //6. Napisz metodę, która zwróci mapę map <imię,<zarobki,liczba_osób_z_takimi_zarobkami>>

    public Map<String, Map<BigDecimal, Long>> groupCustomersByNamesAndSalary() {
        Map<String, Map<BigDecimal, Long>> groupSalaryMap = new HashMap<>();
        for (Customer person : people) {
            if (groupSalaryMap.containsKey(person.getName().trim())) {
                Map<BigDecimal, Long> innerMap = groupSalaryMap.get(person.getName().trim());
                if (innerMap.containsKey(person.getSalary())) {
                    Long counter = innerMap.get(person.getSalary());
                    innerMap.put(person.getSalary(), counter + 1);
                } else {
                    innerMap.put(person.getSalary(), 1L);
                }
            } else {
                Map<BigDecimal, Long> innerMap = new HashMap<>();
                innerMap.put(person.getSalary(), 1L);
                groupSalaryMap.put(person.getName(), innerMap);
            }
        }
        return groupSalaryMap;
    }

    public Map<String, Map<BigDecimal, Long>> groupCustomersByNamesAndSalaryWithStream() {
        return Arrays.stream(people)
                .collect(groupingBy(customer -> customer.getName().trim(),
                        groupingBy(Customer::getSalary, counting())));
    }

    //7. Napisz metodę, która zwróci mapę <imię,<suma_zarobków_osób_o_taki_imieniu>>

    public Map<String, BigDecimal> sumOfSalaryGroupByName() {
        Map<String, BigDecimal> salaryMap = new HashMap<>();
        for (Customer person : people) {
            if (salaryMap.containsKey(person.getName().trim())) {
                BigDecimal currentSalarySum = salaryMap.get(person.getName());
                salaryMap.put(person.getName(), currentSalarySum.add(person.getSalary()));
            } else {
                salaryMap.put(person.getName().trim(), person.getSalary());
            }
        }
        return salaryMap;
    }

//    public Map<String, Long> sumOfSalaryGroupByNameWithStream() {
//        return Arrays.stream(people)
//                .collect(groupingBy(customer -> customer.getName().trim(),));
//
//    }
}
