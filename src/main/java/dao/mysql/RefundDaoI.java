package dao.mysql;

import dao.Dao;
import pojo.Refund;

public interface RefundDaoI extends Dao<Refund> {
    int getMaxRefundId();
}
