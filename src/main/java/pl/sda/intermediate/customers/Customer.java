package pl.sda.intermediate.customers;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Customer {

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private BigDecimal salary;
    private static Integer COUNTER;

    public Customer(String name, String surname, Integer age, Integer salary) {
        this.id = COUNTER + 1;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = BigDecimal.valueOf(salary);
    }

    public Customer(String name, String surname, Integer age, String salary) {
        this(name, surname, age, Integer.valueOf(salary));
    }
}
