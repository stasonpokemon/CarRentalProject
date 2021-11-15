package pojo;

import java.util.Objects;


public class Car {
    private int id;
    private String model;
    private double pricePerDay;
    private String employmentStatus;
    private String damageStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getDamageStatus() {
        return damageStatus;
    }

    public void setDamageStatus(String damageStatus) {
        this.damageStatus = damageStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Double.compare(car.pricePerDay, pricePerDay) == 0 && Objects.equals(model, car.model) && Objects.equals(employmentStatus, car.employmentStatus) && Objects.equals(damageStatus, car.damageStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, pricePerDay, employmentStatus, damageStatus);
    }

    @Override
    public String toString() {
        return String.format("%-15s%-30s%-15s%-25s%-15s", id, model, pricePerDay, employmentStatus, damageStatus);
    }
}
