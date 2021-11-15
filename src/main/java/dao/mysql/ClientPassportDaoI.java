package dao.mysql;

import dao.Dao;
import pojo.ClientPassport;

public interface ClientPassportDaoI extends Dao<ClientPassport> {
    int getMaxClientPassportId();
}
