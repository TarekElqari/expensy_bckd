package ma.emsi.expensebackend.service.impl;

import ma.emsi.expensebackend.entity.Category;
import ma.emsi.expensebackend.service.facade.CategoryFacade;
import ma.emsi.expensebackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryFacadeImpl implements CategoryFacade {

    private final CategoryRepository categoryRepository;

    public CategoryFacadeImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
    	categoryRepository.deleteById(id);
    }
}
