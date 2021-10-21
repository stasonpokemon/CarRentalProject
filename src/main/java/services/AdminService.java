package services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Admin;
import pojo.Client;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class AdminService {

    private static AdminService adminService = null;

    public static AdminService getAdminService() {
        if (adminService == null){
            adminService = new AdminService();
        }
        return adminService;
    }

    public List<Admin> findAllAdmins(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Admin> admins = session.createQuery("FROM" +Admin.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return admins;
    }

    public Admin findAdminById(int adminId){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Admin admin = session.get(Admin.class, adminId);
        transaction.commit();
        session.close();
        return admin;
    }
}
