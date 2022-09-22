package ro.fasttrackit.vehicleprovider.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "deficiecies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deficiencies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    @ManyToOne
    private ServiceOrder order;

}
