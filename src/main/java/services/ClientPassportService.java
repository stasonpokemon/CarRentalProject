package services;

import dao.mysql.ClientPassportDaoImpl;
import pojo.ClientPassport;

import java.sql.SQLException;

public class ClientPassportService {
    private final ClientPassportDaoImpl clientPassportDaoImpl = ClientPassportDaoImpl.getClientPassportDaoImpl();
    private static ClientPassportService passportService;

    public ClientPassportService() throws SQLException {
    }

    public static ClientPassportService getPassportService() {
        if (passportService == null){
            try {
                passportService = new ClientPassportService();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passportService;
    }

    public void addNewPassport(ClientPassport passport){
        int maxClientPassportId = clientPassportDaoImpl.getMaxClientPassportId();
        if (maxClientPassportId != 0){
            passport.setId(maxClientPassportId + 1);
        }else {
            passport.setId(1);
        }
        clientPassportDaoImpl.save(passport);
    }

    public ClientPassport read(ClientPassport passport){
        return clientPassportDaoImpl.read(passport.getId());
    }
}
