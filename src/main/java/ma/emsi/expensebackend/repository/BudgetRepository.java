package ma.emsi.expensebackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.emsi.expensebackend.entity.Budget;
import ma.emsi.expensebackend.entity.User;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    public Budget findFirstByOrderById();

    	
}