package dao.mysql;

import pojo.ClientPassport;
import pojo.User;
import pojo.constant.UserRoleConst;
import utils.JDBCConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDaoI {
    private final JDBCConnector jdbcConnector = JDBCConnector.getInstance();
    private static UserDaoImpl userDaoImpl;

    public UserDaoImpl() throws SQLException {
    }

    public static UserDaoImpl getUserDaoImpl() throws SQLException {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }


    /**
     * Работает, но по отдельности
     */
    /*
     * Регистрация клиента
     * */
    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (user_login, user_password, user_role) VALUES(?, ?, ?)";
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, UserRoleConst.CLIENT_ROLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Работает, но по отдельности
     */
    /*
     * Поиск клиента по id
     * */
    @Override
    public User read(int id) {
        String sql = "SELECT id, user_login, user_password, user_role, passport_id FROM users WHERE id = ? AND user_role LIKE ?";
        ResultSet resultSet = null;
        User user = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, UserRoleConst.CLIENT_ROLE);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("user_login"));
                user.setPassword(resultSet.getString("user_password"));
                user.setRole(resultSet.getString("user_role"));
                int passport_id = resultSet.getInt("passport_id");
                if (!resultSet.wasNull()) {
                    ClientPassport passport = new ClientPassport();
                    passport.setId(passport_id);
                    user.setPassport(passport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * Работает, но по отдельности
     */
    /*
     * Список всех users
     * */
    @Override
    public List<User> readAll() {
        String sql = "SELECT id, user_login, user_password, user_role, passport_id FROM users";
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("user_login"));
                user.setPassword(resultSet.getString("user_password"));
                user.setRole(resultSet.getString("user_role"));
                int passport_id = resultSet.getInt("passport_id");
                if (!resultSet.wasNull()) {
                    ClientPassport passport = new ClientPassport();
                    passport.setId(passport_id);
                    user.setPassport(passport);
                }
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    /**
     * Работает, но по отдельности
     */
    /*
     * Список всех клиентов
     * */
    @Override
    public List<User> findAllClients() {
        String sql = "SELECT id, user_login, user_password, user_role, passport_id FROM users WHERE user_role LIKE ?";
        ResultSet resultSet = null;
        List<User> clients = new ArrayList<>();
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, UserRoleConst.CLIENT_ROLE);
            resultSet = statement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("user_login"));
                user.setPassword(resultSet.getString("user_password"));
                user.setRole(resultSet.getString("user_role"));
                int passport_id = resultSet.getInt("passport_id");
                if (!resultSet.wasNull()) {
                    ClientPassport passport = new ClientPassport();
                    passport.setId(passport_id);
                    user.setPassport(passport);
                }
                clients.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return clients;
    }


    /**
     * Работает, но по отдельности
     */
    @Override
    public void update(User user) {
        String sql = "UPDATE users SET user_login = ?, user_password = ?, user_role = ?, passport_id = ? WHERE id = ?";
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getPassport().getId());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Работает, но по отдельности
     */
    @Override
    public void delete(User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
