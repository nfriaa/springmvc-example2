package net.isetjb.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService
{
    Category getCategoryById(Integer id);

    Category saveCategory(Category product);

    void deleteCategory(Integer id);

    Iterable<Category> getAllCategories();

    long getAllCategoriesCount();

    Page<Category> getAllCategories(Pageable pageable);
}
