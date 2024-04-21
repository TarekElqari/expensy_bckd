package ma.emsi.expensebackend.service.facade;

import java.util.List;
import java.util.Optional;

import ma.emsi.expensebackend.entity.Category;

public interface CategoryFacade {

	List<Category> getAllCategory();

	Optional<Category> getCategoryById(Long id);

	Category saveCategory(Category category);

	void deleteCategory(Long id);

}
