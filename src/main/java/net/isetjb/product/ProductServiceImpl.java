package net.isetjb.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Product service implementation
 */
@Service
public class ProductServiceImpl implements ProductService
{

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Integer id)
    {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveProduct(Product product)
    {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id)
    {
        productRepository.delete(id);
    }

    @Override
    public Iterable<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @Override
    public long getAllProductsCount()
    {
        return productRepository.count();
    }

//    @Override
//    public Page<Product> getAllProducts(int pageNumber, int pageSize)
//    {
//        return productRepository.findAll(new PageRequest(pageNumber, pageSize));
//    }
    @Override
    public Page<Product> getAllProducts(Pageable pageable)
    {
        return productRepository.findAll(pageable);
    }
}
