package ma.emsi.expensebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.emsi.expensebackend.entity.Depense;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {
}