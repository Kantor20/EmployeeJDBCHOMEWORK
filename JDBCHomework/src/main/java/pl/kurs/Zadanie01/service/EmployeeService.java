package pl.kurs.Zadanie01.service;

import pl.kurs.Zadanie01.model.Employee;
import pl.kurs.Zadanie01.model.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService {
    private Connection connection;

    public EmployeeService(Connection connection) {
        this.connection = connection;
    }

    public void hireAnEmployee(Employee employee) throws SQLException {
        Employee employeeInSql = findEmployee(employee.getName(), employee.getLastName());

        if (employeeInSql != null && !employeeInSql.isHired()) {
            System.out.println("Employee exists and is already hired. Setting hired to true.");

            String sql = "update employees set hired = true where employeeId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeInSql.getId());
                statement.executeUpdate();
            }
        } else if (employeeInSql != null) {
            System.out.println("Employee exists and is already hired");

        } else {
            System.out.println("Employee does not exist. Adding to the database.");
            String sql = "insert into employees (name, lastName, position, salary, hired) values (?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, employee.getName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getPosition().name());
                statement.setDouble(4, employee.getSalary());
                statement.setBoolean(5, employee.isHired());
                statement.executeUpdate();
            }
        }
    }

    public void fireTheEmployee(int id) throws SQLException {
        String sql = "update employees set hired = false where employeeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("The employee was dismissed, but his data is still in the database");
        }
    }

    public void salaryIncrease(int id, Double percent) throws SQLException {
        String sql = "update employees set salary = salary + (salary * ? / 100) where employeeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, percent);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("Salary was increased by " + percent + " percent.");
        }
    }

    public void promoteAnEmployee(int id, Position newPosition) throws SQLException {
        double newSalary = newPosition.getSalary();

        String sql = "update employees set position = ?, salary = ? where employeeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPosition.name());
            statement.setDouble(2, newSalary);
            statement.setInt(3, id);
            statement.executeUpdate();
            System.out.println("Employee with id: " + id + " has been promoted to a position: " + newPosition + ".");
        }
    }

    public Employee findEmployee(String name, String lastName) throws SQLException {
        String sql = "select * from employees where name = ? and lastName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Employee empl = new Employee();
                empl.setId(resultSet.getInt("employeeId"));
                empl.setName(resultSet.getString("name"));
                empl.setLastName(resultSet.getString("lastName"));
                empl.setPosition(Position.valueOf(resultSet.getString("position")));
                empl.setSalary(resultSet.getDouble("salary"));
                empl.setHired(resultSet.getBoolean("hired"));
                return empl;
            }
            return null;
        }
    }
}
