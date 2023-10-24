package pl.kurs.Zadanie01.model;

import lombok.Getter;

@Getter
public enum Position {
    INTERN(5000.0),
    JUNIOR_DEV(10000.0),
    MID_DEV(15000.0),
    SENIOR_DEV(20000.0),
    LEAD_DEV(25000.0);

    private final double salary;

    Position(double salary) {
        this.salary = salary;
    }
}
