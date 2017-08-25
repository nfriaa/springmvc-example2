package net.isetjb.product;

import net.isetjb.category.Category;
import net.isetjb.category.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController
{
    // Define the log object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

//
//    public void setProductService(ProductService productService)
//    {
//        this.productService = productService;
//    }
    private int DEFAULT_ITEMS_PER_PAGE = 20;

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!! sans sort
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView listProducts(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size)
    {

        //////////////////// Log a simple message
        log.debug("debug level log");
        log.info("info level log");
        log.warn("warn level log");
        log.error("error level log");
        // Log a formatted string with parameters
        log.debug("another info log with {}, {} and {} arguments", 1, "2", 3.0);
        ////////////////////

        ModelAndView modelAndView = new ModelAndView("product/list");

        long totalCount = productService.getAllProductsCount();
        int requestedPageSize = (size == null) ? DEFAULT_ITEMS_PER_PAGE : Math.abs(size);
        int requestedPageNumber = (page == null) ? 0 : Math.abs((page - 1));
        int numberOfPages = (int) (totalCount / requestedPageSize);
        if (requestedPageNumber > numberOfPages)
        {
            requestedPageNumber = numberOfPages;
        }

        Page<Product> products = productService.getAllProducts(new PageRequest(requestedPageNumber, requestedPageSize));

        modelAndView.addObject("products", products);
        modelAndView.addObject("currentPageNumber", products.getNumber() + 1);
        modelAndView.addObject("requestedPageSize", requestedPageSize);
        modelAndView.addObject("totalCount", totalCount);
        modelAndView.addObject("numberOfPages", numberOfPages);

        return modelAndView;
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model)
    {
        model.addAttribute("product", productService.getProductById(id));
        return "product/detail";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("product/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model)
    {
        model.addAttribute("product", productService.getProductById(id));

        Iterable<Category> getAllCategories = categoryService.getAllCategories();
        model.addAttribute("allCategories", getAllCategories);

        return "product/edit";
    }

    @RequestMapping("product/new")
    public String newProduct(Model model)
    {
        model.addAttribute("product", new Product());

        Iterable<Category> getAllCategories = categoryService.getAllCategories();
        model.addAttribute("allCategories", getAllCategories);

        return "product/edit";
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            Iterable<Category> getAllCategories = categoryService.getAllCategories();
            model.addAttribute("allCategories", getAllCategories);

            return "product/edit";
        }

        productService.saveProduct(product);
        return "redirect:/products";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable Integer id)
    {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
