package ma.emsi.expensebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.emsi.expensebackend.entity.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}