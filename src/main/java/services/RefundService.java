package services;

import dao.mysql.RefundDaoImpl;
import pojo.Refund;

import java.sql.SQLException;
import java.util.List;

public class RefundService {
    private final RefundDaoImpl refundDaoImpl = RefundDaoImpl.getRefundDaoImpl();
    private static RefundService refundService;

    public RefundService() throws SQLException {
    }

    public static RefundService getRefundService() {
        if (refundService == null) {
            try {
                refundService = new RefundService();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return refundService;
    }

    public List<Refund> findAllRefund() {
        return refundDaoImpl.readAll();
    }

    /*
     * Создать возврат
     * */
    public void addNewRefund(Refund refund) {
        int maxRefundId = refundDaoImpl.getMaxRefundId();
        if (maxRefundId != 0) {
            refund.setId(maxRefundId + 1);
        } else {
            refund.setId(1);
        }
        refundDaoImpl.save(refund);
    }

    public void update(Refund refund) {
        refundDaoImpl.update(refund);
    }

}
