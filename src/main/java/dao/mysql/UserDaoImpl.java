package dao.mysql;

import com.sun.istack.NotNull;
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
     * Работает!!!!!!!!!!!!, но по отдельности
     */
    /*
     * Поиск клиента по id
     * */
    @Override
    public User read(int id) {
        String sqlUser = "SELECT id, user_login, user_password, user_role, passport_id FROM users WHERE id = ? AND user_role LIKE ?";
        String sqlPassport = "SELECT id, name, surname, patronymic, birthday, address FROM passports WHERE id = ?";

        PreparedStatement statementUser = null;
        PreparedStatement statementPassport = null;

        ResultSet resultSetUser = null;
        ResultSet resultSetPassport = null;

        User user = null;
        ClientPassport passport = null;
        try (Connection connection = jdbcConnector.getConnection()) {
            statementUser = connection.prepareStatement(sqlUser);
            statementPassport = connection.prepareStatement(sqlPassport);

            statementUser.setInt(1, id);
            statementUser.setString(2, UserRoleConst.CLIENT_ROLE);
            resultSetUser = statementUser.executeQuery();
            if (resultSetUser.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSetUser.getString("user_login"));
                user.setPassword(resultSetUser.getString("user_password"));
                user.setRole(resultSetUser.getString("user_role"));

                int passport_id = resultSetUser.getInt("passport_id");
                statementPassport.setInt(1, passport_id);
                resultSetPassport = statementPassport.executeQuery();
                if (!resultSetUser.wasNull()) {
                    if (resultSetPassport.next()){
                        passport = new ClientPassport();
                        passport.setId(resultSetPassport.getInt("id"));
                        passport.setName(resultSetPassport.getString("name"));
                        passport.setSurname(resultSetPassport.getString("surname"));
                        passport.setPatronymic(resultSetPassport.getString("patronymic"));
                        passport.setBirthday(resultSetPassport.getTimestamp("birthday"));
                        passport.setAddress(resultSetPassport.getString("address"));
                    }
                    user.setPassport(passport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSetUser != null) {
                    resultSetUser.close();
                }
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * Работает!!!!!!!!!, но по отдельности
     */
    /*
     * Список всех users
     * */
    @Override
    public List<User> readAll() {
        String sqlUser = "SELECT id, user_login, user_password, user_role, passport_id FROM users";
        String sqlPassport = "SELECT id, name, surname, patronymic, birthday, address FROM passports WHERE id = ?";


        PreparedStatement statementUser = null;
        PreparedStatement statementPassport = null;

        ResultSet resultSetUser = null;
        ResultSet resultSetPassport = null;


        List<User> users = new ArrayList<>();
        try (Connection connection = jdbcConnector.getConnection()) {
            statementUser = connection.prepareStatement(sqlUser);
            statementPassport = connection.prepareStatement(sqlPassport);

            resultSetUser = statementUser.executeQuery();
            User user = null;
            ClientPassport passport = null;
            while (resultSetUser.next()) {
                user = new User();
                user.setId(resultSetUser.getInt("id"));
                user.setLogin(resultSetUser.getString("user_login"));
                user.setPassword(resultSetUser.getString("user_password"));
                user.setRole(resultSetUser.getString("user_role"));

                int passport_id = resultSetUser.getInt("passport_id");
                statementPassport.setInt(1, passport_id);
                resultSetPassport = statementPassport.executeQuery();
                if (!resultSetUser.wasNull()) {
                    if (resultSetPassport.next()){
                        passport = new ClientPassport();
                        passport.setId(resultSetPassport.getInt("id"));
                        passport.setName(resultSetPassport.getString("name"));
                        passport.setSurname(resultSetPassport.getString("surname"));
                        passport.setPatronymic(resultSetPassport.getString("patronymic"));
                        passport.setBirthday(resultSetPassport.getTimestamp("birthday"));
                        passport.setAddress(resultSetPassport.getString("address"));
                    }
                    user.setPassport(passport);
                }
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSetUser != null) {
                    resultSetUser.close();
                }
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    /**
     * Работает!!!!!, но по отдельности
     */
    /*
     * Список всех клиентов
     * */
    @Override
    public List<User> findAllClients() {
        String sqlUser = "SELECT id, user_login, user_password, user_role, passport_id FROM users WHERE user_role = ?";
        String sqlPassport = "SELECT id, name, surname, patronymic, birthday, address FROM passports WHERE id = ?";


        PreparedStatement statementUser = null;
        PreparedStatement statementPassport = null;

        ResultSet resultSetUser = null;
        ResultSet resultSetPassport = null;


        List<User> users = new ArrayList<>();
        try (Connection connection = jdbcConnector.getConnection()) {
            statementUser = connection.prepareStatement(sqlUser);
            statementPassport = connection.prepareStatement(sqlPassport);

            statementUser.setString(1, UserRoleConst.CLIENT_ROLE);
            resultSetUser = statementUser.executeQuery();
            User user = null;
            ClientPassport passport = null;
            while (resultSetUser.next()) {
                user = new User();
                user.setId(resultSetUser.getInt("id"));
                user.setLogin(resultSetUser.getString("user_login"));
                user.setPassword(resultSetUser.getString("user_password"));
                user.setRole(resultSetUser.getString("user_role"));

                int passport_id = resultSetUser.getInt("passport_id");
                statementPassport.setInt(1, passport_id);
                resultSetPassport = statementPassport.executeQuery();
                if (!resultSetUser.wasNull()) {
                    if (resultSetPassport.next()){
                        passport = new ClientPassport();
                        passport.setId(resultSetPassport.getInt("id"));
                        passport.setName(resultSetPassport.getString("name"));
                        passport.setSurname(resultSetPassport.getString("surname"));
                        passport.setPatronymic(resultSetPassport.getString("patronymic"));
                        passport.setBirthday(resultSetPassport.getTimestamp("birthday"));
                        passport.setAddress(resultSetPassport.getString("address"));
                    }
                    user.setPassport(passport);
                }
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSetUser != null) {
                    resultSetUser.close();
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
