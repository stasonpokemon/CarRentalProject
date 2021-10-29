package services;

import dao.RefundDao;
import pojo.Refund;

import java.util.List;

public class RefundService {
    private final RefundDao refundDao = RefundDao.getRefundDao();
    private static RefundService refundService;

    public static RefundService getRefundService() {
        if (refundService == null){
            refundService = new RefundService();
        }
        return refundService;
    }

    public List<Refund> findAllRefund(){
        List<Refund> refunds = refundDao.readAll();
        return refunds;
    }

    /*
    * Создать возврат
    * */
    public void addNewRefund(Refund refund){
        refundDao.save(refund);
    }

}
