package pojo;

import java.util.Objects;

public class Client {

    private int id;
    private String login;
    private String password;
    private ClientPassport clientPassport;

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
