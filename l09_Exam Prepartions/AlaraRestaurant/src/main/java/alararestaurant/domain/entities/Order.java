package alararestaurant.domain.entities;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//•	customer – text (required)
//        •	dateTime – date and time of the order (required)
//        •	type – OrderType enumeration with possible values: “ForHere, ToGo (default: ForHere)” (required)
//        •	employee – The employee who will process the order (required)
//        •	orderItems – collection of type OrderItem
@Entity(name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    private String customer;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    //    columnDefinition = "varchar(32) default 'ForHere'"
    private OrderType type;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @OneToMany(mappedBy = "order", targetEntity = OrderItem.class)
    private List<OrderItem> orderItems;

    public Order() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
