package net.isetjb;

import net.isetjb.product.Product;
import net.isetjb.product.ProductRepository;
import net.isetjb.category.CategoryRepository;
import net.isetjb.category.Category;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * class to init sample data for demo
 *
 * @author nafaa
 */
@Component
public class DataInitializer
{
    private final String[] SAMPLES_1 =
    {
        "voluptatem", "accusantium", "doloremque", "laudantium", "totam", "rem",
        "aperiam", "eaque", "ipsa", "illo", "dicta", "dolores"
    };

    private final String[] SAMPLES_2 =
    {
        "sed", "quia", "magni", "sunt", "eos", "qui",
        "ratione", "leo", "placerat", "vitae", "eleifend", "amet"
    };

    private final String[] ALPHA_1 =
    {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void initSampleData()
    {

        // category :
        List<Category> _sampleCategories = new ArrayList<>();
        Category cat1 = new Category();
        cat1.setId(1);
        cat1.setName("Catégorie 1");
        Category cat2 = new Category();
        cat2.setId(2);
        cat2.setName("Catégorie 2");
        _sampleCategories.add(cat1);
        _sampleCategories.add(cat2);
        categoryRepository.save(_sampleCategories);

        // products :
        List<Product> _sampleProducts = new ArrayList<>();

        Random _random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 99; i++)
        {
            for (String temp1 : SAMPLES_1)
            {
                for (String temp2 : SAMPLES_2)
                {
                    Product p = new Product();
                    p.setName(temp1 + " " + temp2 + " " + ALPHA_1[_random.nextInt(26)] + ALPHA_1[_random.nextInt(26)] + _random.nextInt(100));
                    p.setPrice(BigDecimal.valueOf(_random.nextInt(10000) + _random.nextFloat()));
                    p.setCategory(cat1);
                    _sampleProducts.add(p);
                }
            }

            for (String temp2 : SAMPLES_2)
            {
                for (String temp1 : SAMPLES_1)
                {
                    Product p = new Product();
                    p.setName(temp2 + " " + temp1 + " " + ALPHA_1[_random.nextInt(26)] + ALPHA_1[_random.nextInt(26)] + _random.nextInt(100));
                    p.setPrice(BigDecimal.valueOf(_random.nextInt(10000) + _random.nextFloat()));
                    p.setCategory(cat2);
                    _sampleProducts.add(p);
                }
            }
        }

        Collections.shuffle(_sampleProducts);

        productRepository.save(_sampleProducts);
    }
}
