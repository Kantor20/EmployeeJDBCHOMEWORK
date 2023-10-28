package pl.kurs.zadanie02;

import pl.kurs.zadanie02.connection.BasicDataConnection;
import pl.kurs.zadanie02.model.Train;
import pl.kurs.zadanie02.service.TrainService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        TrainService service = new TrainService(BasicDataConnection.getConnect());

        Train t1 = new Train("Norwid", 1500,true, 300);
        Train t2 = new Train("tadeusz", 500,false, 120);
        Train t3 = new Train("pedalLino", 5000,true, 600);
        Train t4 = new Train("Tramino", 100,false, 65);

//        service.addTrain("sobieski", t1);
        service.buyTrain(t3);
        service.buyTrain(t4);
//        service.addTrain("Tramino", t2);
    }
}
