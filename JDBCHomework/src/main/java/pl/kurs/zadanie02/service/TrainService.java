package pl.kurs.zadanie02.service;

import org.apache.commons.dbcp2.BasicDataSource;
import pl.kurs.zadanie02.model.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TrainService {
    private BasicDataSource dataSource;

    public TrainService(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addTrain(String trainNameToRemove, Train newTrain) throws SQLException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement("delete from pociag where nazwa = ?");
                preparedStatement.setString(1, trainNameToRemove);
                int deleteRows = preparedStatement.executeUpdate();
                System.out.println("Usunieto: " + deleteRows);
                if (deleteRows == 0) {
                    throw new SQLException("Nie mozna usunac pociagu");
                }
            } finally {
                if (preparedStatement != null) preparedStatement.close();
            }
            PreparedStatement preparedStatement2 = null;
            try {
                preparedStatement2 = connection.prepareStatement("insert into pociag values(null,?,?,?,?)");
                preparedStatement2.setString(1, newTrain.getNazwa());
                preparedStatement2.setDouble(2, newTrain.getDlugosc());
                preparedStatement2.setBoolean(3, newTrain.isWars());
                preparedStatement2.setInt(4, newTrain.getLiczbaMiejsc());

                int insertRows = preparedStatement2.executeUpdate();
                System.out.println("Dodano: " + insertRows);
            } finally {
                if (preparedStatement2 != null) preparedStatement2.close();
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("ROLLBACK!");
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
        }
    }

    public void buyTrain(Train train) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement checkStatement = connection.prepareStatement("select nazwa from pociag where nazwa = ? ");

        checkStatement.setString(1, train.getNazwa());

        ResultSet resultSet = checkStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("Pociag już istnieje!");
        } else {
            try (PreparedStatement statement = connection.prepareStatement("insert into pociag values (null,?,?,?,?)")) {
                statement.setString(1, train.getNazwa());
                statement.setDouble(2, train.getDlugosc());
                statement.setBoolean(3, train.isWars());
                statement.setInt(4, train.getLiczbaMiejsc());


                int insertRows = statement.executeUpdate();
                System.out.println("Zakupiono: " + insertRows);
            }
        }
    }


}
