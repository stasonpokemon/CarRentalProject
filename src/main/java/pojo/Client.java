package pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "client_login")
    private String login;
    @Column(name = "client_password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private ClientPassport passport;
    @OneToMany(mappedBy="client")
//    Инициализируется при создании паспорта...
    private List<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientPassport getPassport() {
        return passport;
    }

    public void setPassport(ClientPassport passport) {
        this.passport = passport;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(login, client.login) && Objects.equals(password, client.password) && Objects.equals(passport, client.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, passport);
    }

    @Override
    public String toString() {
        return String.format("%-6s%-30s%-15s", id, login, password);

//        return "Client{" +
//                "id=" + id +
//                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                '}';
    }
}
