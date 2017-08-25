package net.isetjb.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    private int DEFAULT_ITEMS_PER_PAGE = 20;

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!! sans sort
     */
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView listCategories(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size)
    {
        ModelAndView modelAndView = new ModelAndView("category/list");

        long totalCount = categoryService.getAllCategoriesCount();
        int requestedPageSize = (size == null) ? DEFAULT_ITEMS_PER_PAGE : Math.abs(size);
        int requestedPageNumber = (page == null) ? 0 : Math.abs((page - 1));
        int numberOfPages = (int) (totalCount / requestedPageSize);
        if (requestedPageNumber > numberOfPages)
        {
            requestedPageNumber = numberOfPages;
        }

        Page<Category> categories = categoryService.getAllCategories(new PageRequest(requestedPageNumber, requestedPageSize));

        modelAndView.addObject("categories", categories);
        modelAndView.addObject("currentPageNumber", categories.getNumber() + 1);
        modelAndView.addObject("requestedPageSize", requestedPageSize);
        modelAndView.addObject("totalCount", totalCount);
        modelAndView.addObject("numberOfPages", numberOfPages);

        return modelAndView;
    }

    @RequestMapping("category/{id}")
    public String showCategory(@PathVariable Integer id, Model model)
    {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "category/detail";
    }

    @RequestMapping("category/edit/{id}")
    public String editCategory(@PathVariable Integer id, Model model)
    {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "category/edit";
    }

    @RequestMapping("category/new")
    public String newCategory(Model model)
    {
        model.addAttribute("category", new Category());
        return "category/edit";
    }

    @RequestMapping(value = "category", method = RequestMethod.POST)
    public String saveCategory(@Valid Category category, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "category/edit";
        }

        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @RequestMapping("category/delete/{id}")
    public String deleteCategory(@PathVariable Integer id)
    {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

}
