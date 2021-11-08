package dao.mysql;

import dao.Dao;
import pojo.ClientPassport;
import utils.JDBCConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientPassportDaoImpl implements Dao<ClientPassport> {

    private final JDBCConnector jdbcConnector = JDBCConnector.getInstance();
    private static ClientPassportDaoImpl clientPassportDaoImpl;

    public ClientPassportDaoImpl() throws SQLException {
    }

    public static ClientPassportDaoImpl getClientPassportDaoImpl() throws SQLException {
        if (clientPassportDaoImpl == null) {
            clientPassportDaoImpl = new ClientPassportDaoImpl();
        }
        return clientPassportDaoImpl;
    }

    @Override
    public void save(ClientPassport passport) {
        String sql = "INSERT  INTO passports (name,surname,patronymic,birthday,address) VALUES(?,?,?,?,?)";
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, passport.getName());
            statement.setString(2, passport.getSurname());
            statement.setString(3, passport.getPatronymic());
            statement.setTimestamp(4, (Timestamp) passport.getBirthday());
            statement.setString(5, passport.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClientPassport read(int id) {
        String sql = "SELECT id,name,surname,patronymic,birthday,address FROM passports WHERE id = ?";
        ClientPassport passport = null;
        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passport = new ClientPassport();
                passport.setId(id);
                passport.setName(resultSet.getString("name"));
                passport.setSurname(resultSet.getString("surname"));
                passport.setPatronymic(resultSet.getString("patronymic"));
                passport.setBirthday(resultSet.getTimestamp("birthday"));
                passport.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passport;
    }

    @Override
    public void update(ClientPassport passport) {
        String sql = "UPDATE passports SET name = ?,surname = ?,patronymic = ?, birthday = ?,address = ? WHERE id = ?";
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, passport.getName());
            statement.setString(2, passport.getSurname());
            statement.setString(3, passport.getPatronymic());
            statement.setTimestamp(4, (Timestamp) passport.getBirthday());
            statement.setString(5, passport.getAddress());
            statement.setInt(6, passport.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ClientPassport passport) {
        String sql = "DELETE FROM passports WHERE id = ?";
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passport.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClientPassport> readAll() {
        String sql = "SELECT id,name,surname,patronymic,birthday,address FROM passports";
        List<ClientPassport> passports = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
            ClientPassport passport = null;
            while (resultSet.next()) {
                passport = new ClientPassport();
                passport.setId(resultSet.getInt("id"));
                passport.setName(resultSet.getString("name"));
                passport.setSurname(resultSet.getString("surname"));
                passport.setPatronymic(resultSet.getString("patronymic"));
                passport.setBirthday(resultSet.getTimestamp("birthday"));
                passport.setAddress(resultSet.getString("address"));
                passports.add(passport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passports;
    }
}
