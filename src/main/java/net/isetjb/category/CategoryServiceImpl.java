package net.isetjb.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Category service implementation
 */
@Service
public class CategoryServiceImpl implements CategoryService
{

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Integer id)
    {
        return categoryRepository.findOne(id);
    }

    @Override
    public Category saveCategory(Category category)
    {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id)
    {
        categoryRepository.delete(id);
    }

    @Override
    public Iterable<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    @Override
    public long getAllCategoriesCount()
    {
        return categoryRepository.count();
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable)
    {
        return categoryRepository.findAll(pageable);
    }
}
