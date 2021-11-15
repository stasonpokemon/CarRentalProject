package dao.mysql;

import dao.Dao;
import pojo.Refund;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RefundDaoImpl extends BaseDaoImpl implements RefundDaoI{

    public RefundDaoImpl() throws SQLException {
    }

    private static RefundDaoImpl refundDaoImpl;

    public static RefundDaoImpl getRefundDaoImpl() throws SQLException {
        if (refundDaoImpl == null) {
            refundDaoImpl = new RefundDaoImpl();
        }
        return refundDaoImpl;
    }

    @Override
    public void save(Refund refund) {
        String sql = "INSERT INTO refunds (id, damage_status, type_damage, price)  VALUES(?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, refund.getId());
            statement.setString(2, refund.getDamageStatus());
            statement.setString(3, refund.getTypeDamage());
            statement.setDouble(4, refund.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Refund read(int id) {
        String sql = "SELECT id, damage_status, type_damage,  price FROM refunds WHERE id = ?";
        PreparedStatement statement;
        ResultSet resultSet;
        Refund refund = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                refund = new Refund();
                refund.setId(resultSet.getInt("id"));
                refund.setDamageStatus(resultSet.getString("damage_status"));
                refund.setTypeDamage(resultSet.getString("type_damage"));
                refund.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return refund;
    }

    @Override
    public void update(Refund refund) {
        String sql = "UPDATE refunds SET damage_status = ?, type_damage = ?, price = ?  WHERE id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, refund.getDamageStatus());
            statement.setString(2, refund.getTypeDamage());
            statement.setDouble(3, refund.getPrice());
            statement.setInt(4, refund.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Refund refund) {
        String sql = "DELETE FROM refunds WHERE id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, refund.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Refund> readAll() {
        String sql = "SELECT id, damage_status, type_damage, price FROM refunds";
        PreparedStatement statement;
        ResultSet resultSet;
        List<Refund> refunds = new ArrayList<>();
        Refund refund;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                refund = new Refund();
                refund.setId(resultSet.getInt("id"));
                refund.setDamageStatus(resultSet.getString("damage_status"));
                refund.setTypeDamage(resultSet.getString("type_damage"));
                refund.setPrice(resultSet.getDouble("price"));
                refunds.add(refund);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return refunds;
    }

    @Override
    public int getMaxRefundId() {
        String sql = "SELECT MAX(id) FROM refunds";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
