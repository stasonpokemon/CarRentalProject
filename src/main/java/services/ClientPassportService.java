package services;

import dao.ClientPassportDao;
import pojo.ClientPassport;

public class ClientPassportService {
    private final ClientPassportDao clientPassportDao = ClientPassportDao.getPassportDao();
    private static ClientPassportService passportService;

    public static ClientPassportService getPassportService() {
        if (passportService == null){
            passportService = new ClientPassportService();
        }
        return passportService;
    }

    public void addNewPassport(ClientPassport passport){
        clientPassportDao.save(passport);
    }
}
