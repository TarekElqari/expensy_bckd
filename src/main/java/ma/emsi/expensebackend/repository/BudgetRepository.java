package ma.emsi.expensebackend.repository;

import ma.emsi.expensebackend.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
