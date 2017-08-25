package net.isetjb.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>
{
    Iterable<Category> findByName(String name);
}
