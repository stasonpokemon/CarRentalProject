package dao.mysql;

import dao.Dao;
import pojo.Car;

public interface CarDaoI extends Dao<Car> {


    /*
     * Смена статуса на (занято)
     * */
    void setCarStatusToOccupied(Car car);

    /*
     * Смена статуса на (свободно)
     * */
    void setCarStatusToFree(Car car);

    /*
     * Смена статуса повреждения на (повреждён)
     * */
    void setCarDamageStatusToWithDamage(Car car);

    /*
     * Смена статуса повреждения на (не повреждён)
     * */
    void setCarDamageStatusToWithoutDamage(Car car);
}
