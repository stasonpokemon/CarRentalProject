package pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "passports")
public class ClientPassport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "day_birthday")
    private int dayBirthday;
    @Column(name = "month_birthday")
    private int monthBirthday;
    @Column(name = "year_birthday")
    private int yearBirthday;
    @Column(name = "address")
    private String address;
    @OneToOne(mappedBy = "passports")
    private Client client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getDayBirthday() {
        return dayBirthday;
    }

    public void setDayBirthday(int dayBirthday) {
        this.dayBirthday = dayBirthday;
    }

    public int getMonthBirthday() {
        return monthBirthday;
    }

    public void setMonthBirthday(int monthBirthday) {
        this.monthBirthday = monthBirthday;
    }

    public int getYearBirthday() {
        return yearBirthday;
    }

    public void setYearBirthday(int yearBirthday) {
        this.yearBirthday = yearBirthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientPassport that = (ClientPassport) o;
        return id == that.id && dayBirthday == that.dayBirthday && monthBirthday == that.monthBirthday && yearBirthday == that.yearBirthday && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(patronymic, that.patronymic) && Objects.equals(address, that.address) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, dayBirthday, monthBirthday, yearBirthday, address, client);
    }

    @Override
    public String toString() {
        return "ClientPassport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dayBirthday=" + dayBirthday +
                ", monthBirthday=" + monthBirthday +
                ", yearBirthday=" + yearBirthday +
                ", address='" + address + '\'' +
                ", client=" + client +
                '}';
    }
}
