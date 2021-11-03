package utils;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/car_rental";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса JDBCConnector
    private static JDBCConnector instance = null;

    public static JDBCConnector getInstance() throws SQLException {
        if (instance == null) {
            instance = new JDBCConnector();
        }
        return instance;
    }
    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private JDBCConnector() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
//        DriverManager.registerDriver();

        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }
}
