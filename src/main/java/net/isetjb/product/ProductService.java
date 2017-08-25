package net.isetjb.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService
{
    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);

    Iterable<Product> getAllProducts();

    long getAllProductsCount();

    //Page<Product> getAllProducts(int pageNumber, int pageSize);
    Page<Product> getAllProducts(Pageable pageable);
}
