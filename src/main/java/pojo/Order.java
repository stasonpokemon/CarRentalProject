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
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    private double price;
    @Column(name = "status")
    private String orderStatus;
    @Column(name = "order_date", columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "refund_id", referencedColumnName = "id")
    private Refund refund;

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

    public User getClient() {
        return user;
    }

    public void setClient(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String status) {
        this.orderStatus = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.price, price) == 0 && Objects.equals(car, order.car) && Objects.equals(user, order.user) && Objects.equals(orderStatus, order.orderStatus) && Objects.equals(orderDate, order.orderDate) && Objects.equals(refund, order.refund);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, user, price, orderStatus, orderDate, refund);
    }

    @Override
    public String toString() {
        return String.format("%-6s%-30s%-15s%-15s%-20s%-15s",id, car.getModel(), user.getLogin(), price, orderStatus,orderDate);

//        return "Order{" +
//                "id=" + id +
//                ", car=" + car.getModel() +
//                ", client=" + client.getLogin() +
//                ", price=" + price +
//                ", status='" + status + '\'' +
//                ", orderDate=" + orderDate +
////                ", refund=" + refund.getId() +
//                '}';
    }
}
