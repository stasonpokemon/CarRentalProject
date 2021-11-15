package dao.mysql;

import utils.JDBCConnector;

import java.sql.Connection;
import java.sql.SQLException;

abstract public class BaseDaoImpl {
    private final JDBCConnector jdbcConnector = JDBCConnector.getInstance();
    protected Connection connection = jdbcConnector.getConnection();

    protected BaseDaoImpl() throws SQLException {
    }
}
