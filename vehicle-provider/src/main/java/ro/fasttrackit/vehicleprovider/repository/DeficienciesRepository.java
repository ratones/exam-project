package ro.fasttrackit.vehicleprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.vehicleprovider.model.Deficiencies;

import java.util.List;

public interface DeficienciesRepository extends JpaRepository<Deficiencies, Integer> {
    List<Deficiencies> findByOrderId(Integer id);
}