package pl.kurs.Zadanie01.service;

import pl.kurs.Zadanie01.model.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EmploymentService {
    private final Connection connection;

    public EmploymentService(Connection connection) {
        this.connection = connection;
    }

    public double totalSalaries() throws SQLException {
        String sql = "select sum(salary) as total from employees where hired = true";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("total");
            }
        }
        return 0;
    }

    public int totalEmployees() throws SQLException {
        String sql = "select count(*) as count from employees where hired = true";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
            return 0;
        }
    }

    public int totalGender(boolean isFemale) throws SQLException {
        String condition = isFemale ? "right(name, 1) = 'a'" : "right(name, 1) != 'a'";
        String sql = "select count(*) as count from employees where hired = true and " + condition;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
            return 0;
        }
    }

    public Map<Position, Integer> employeePositions() throws SQLException {
        Map<Position, Integer> positionMap = new HashMap<>();
        String sql = "select position, count(*) as count from employees where hired = true group by position";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Position position = Position.valueOf(resultSet.getString("position"));
                int count = resultSet.getInt("count");
                positionMap.put(position, count);
            }
        }
        return positionMap;
    }
}
