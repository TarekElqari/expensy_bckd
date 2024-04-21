package ma.emsi.expensebackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.expensebackend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
