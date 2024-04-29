package ma.emsi.expensebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.emsi.expensebackend.entity.Depot;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Long> {
}