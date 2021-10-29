package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Refund;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class RefundDao implements DaoInterface<Refund>{
    private static RefundDao refundDao;

    public static RefundDao getRefundDao() {
        if (refundDao == null) {
            refundDao = new RefundDao();
        }
        return refundDao;
    }

    @Override
    public void save(Refund refund) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(refund);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Refund refund) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(refund);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Refund refund) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(refund);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Refund> readAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Refund> refunds = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM " + Refund.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return refunds;
    }
}
