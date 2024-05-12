package ma.emsi.expensebackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.emsi.expensebackend.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    public Budget findFirstByOrderById();

    	
}