package services;

import dao.AdminDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Admin;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class AdminService {

    private final AdminDao adminDao = AdminDao.getAdminDao();
    private static AdminService adminService = null;

    public static AdminService getAdminService() {
        if (adminService == null){
            adminService = new AdminService();
        }
        return adminService;
    }


    /*
    * Список всех администраторов
    * */
    public List<Admin> findAllAdmins(){
        List<Admin> admins = adminDao.readAll();
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
