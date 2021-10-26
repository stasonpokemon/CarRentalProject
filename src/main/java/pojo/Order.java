package pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "car_id", nullable=false)
    private Car car;
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;
    private double price;
    private String status;
    @Column(name = "order_date", columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.price, price) == 0 && Objects.equals(car, order.car) && Objects.equals(client, order.client) && Objects.equals(status, order.status) && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, client, price, status, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", car=" + car.getModel() +
                ", clientId=" + client.getId() +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
