package pl.kurs.zadanie02.connection;

import org.apache.commons.dbcp2.BasicDataSource;

public class BasicDataConnection {

    public static BasicDataSource getConnect() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/pkp?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
