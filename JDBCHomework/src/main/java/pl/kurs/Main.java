package pl.kurs;

import pl.kurs.Zadanie01.connection.Config;
import pl.kurs.Zadanie01.model.Employee;
import pl.kurs.Zadanie01.model.Position;
import pl.kurs.Zadanie01.service.EmployeeService;
import pl.kurs.Zadanie01.service.EmploymentService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        EmployeeService service = new EmployeeService(Config.getConnection());
        EmploymentService emplService = new EmploymentService(Config.getConnection());

        Employee newEmpl = new Employee("Igor", "Kowal", Position.INTERN, true);
        Employee newEmpl2 = new Employee("Alicja", "Zajac", Position.MID_DEV, true);
        Employee newEmpl3 = new Employee("Kamil", "Dupa", Position.MID_DEV, true);
        Employee newEmpl4 = new Employee("Adnrzej", "Michalina", Position.INTERN, true);
        Employee newEmpl5 = new Employee("Krzysztof", "Jezyna", Position.LEAD_DEV, true);


//        service.hireAnEmployee(newEmpl4);
//        service.hireAnEmployee(newEmpl5);
//        service.fireTheEmployee(15);
//        service.promoteAnEmployee(14, Position.MID_DEV);
        System.out.println(emplService.totalSalaries());
        System.out.println(emplService.totalEmployees());
        System.out.println(emplService.totalGender(true));
        System.out.println(emplService.employeePositions());
        service.salaryIncrease(11, 10.0);
        service.salaryIncrease(13, 50.0);
//        service.salaryIncrease(14,10.0);
//        service.promoteAnEmployee(14, Position.JUNIOR_DEV);
//        service.hireAnEmployee(newEmpl);
//        service.salaryIncrease(11,10.0);

        Employee findEmployee = service.findEmployee("Alicja", "Zajac");
//        System.out.println(findEmployee);
    }
}
