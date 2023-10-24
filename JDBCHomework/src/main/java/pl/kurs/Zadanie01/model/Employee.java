package pl.kurs.Zadanie01.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    private int id;
    private String name;
    private String lastName;
    private Position position;
    private double salary;
    private boolean hired;

    public Employee(String name, String lastName, Position position, boolean hired) {
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.hired = hired;
    }

    public double getSalary() {
        return position.getSalary();
    }
}
