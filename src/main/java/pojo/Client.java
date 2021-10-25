package pojo;

import javax.persistence.*;
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
    private ClientPassport clientPassport;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "passports_id")
    private ClientPassport passports;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "pazssports_id")
    private ClientPassport pazssports;

    public ClientPassport getPazssports() {
        return pazssports;
    }

    public void setPazssports(ClientPassport pazssports) {
        this.pazssports = pazssports;
    }

    public ClientPassport getPassports() {
        return passports;
    }

    public void setPassports(ClientPassport passports) {
        this.passports = passports;
    }

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

    public ClientPassport getClientPassport() {
        return clientPassport;
    }

    public void setClientPassport(ClientPassport clientPassport) {
        this.clientPassport = clientPassport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(login, client.login) && Objects.equals(password, client.password) && Objects.equals(clientPassport, client.clientPassport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, clientPassport);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", clientPassport=" + clientPassport +
                '}';
    }
}
