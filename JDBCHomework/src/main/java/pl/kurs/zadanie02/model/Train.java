package pl.kurs.zadanie02.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Train {
    private String nazwa;
    private double dlugosc;
    private boolean wars;
    private int liczbaMiejsc;

    public Train(String nazwa, double dlugosc, boolean wars, int liczbaMiejsc) {
        this.nazwa = nazwa;
        this.dlugosc = dlugosc;
        this.wars = wars;
        this.liczbaMiejsc = liczbaMiejsc;
    }
}
