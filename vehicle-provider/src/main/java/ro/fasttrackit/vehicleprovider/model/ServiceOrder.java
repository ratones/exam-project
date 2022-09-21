package ro.fasttrackit.vehicleprovider.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "service_orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date orderDate;
    private OrderCategory category;
    private OrderStatus status;
    private Date dateCompleted;
    private String notes;

    @ManyToOne
    private Vehicle vehicle;

}
