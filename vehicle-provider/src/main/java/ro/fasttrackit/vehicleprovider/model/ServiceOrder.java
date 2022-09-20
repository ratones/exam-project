package ro.fasttrackit.vehicleprovider.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "service_orders")
@Data
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate orderDate;
    private OrderCategory category;
    private OrderStatus status;
    private LocalDate dateCompleted;
    private String notes;

    @ManyToOne
    private Vehicle vehicle;

}
