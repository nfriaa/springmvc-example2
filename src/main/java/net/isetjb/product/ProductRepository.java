package net.isetjb.product;

//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
    // it's code will be generated by spring ;-)
    Iterable<Product> findByName(String name);
}
