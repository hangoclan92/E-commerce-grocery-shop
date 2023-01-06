package edu.hanu.ecomshop.controller;

import edu.hanu.ecomshop.model.Product;
import edu.hanu.ecomshop.service.CategoryService;
import edu.hanu.ecomshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", "/index", "/home"})
    public String home(Model model) {
        model.addAttribute("products", getAllProducts());
        model.addAttribute("productsCount", productsCount());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    @RequestMapping("/searchByCategory")
    public String searchByCategory(@RequestParam("categoryId") long categoryId, Model model) {
        List<Product> products = productService.findAllByCategoryId(categoryId);
        model.addAttribute("products", productService.findAllByCategoryId(categoryId));
        model.addAttribute("productsCount", products.size());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    @RequestMapping("/category")
    public String showCategory(Model model) {
        model.addAttribute("products", getAllProducts());
        model.addAttribute("productsCount", productsCount());
        model.addAttribute("categories", categoryService.findAll());
        return "category";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam("search") String search, Model model) {
        List<Product> products = productService.findAllByName(search);
        model.addAttribute("products", productService.findAllByName(search));
        model.addAttribute("productsCount", products.size());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    private List<Product> getAllProducts() {
        return productService.findAllByOrderByIdAsc();
    }

    private long productsCount() {
        return productService.count();
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 8;

        Page<Product> page = productService.findPaginated(pageNo, pageSize);
        List<Product> productList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", productList);
        return "index";
    }

    @GetMapping("/test")
    public String viewHomePage(Model model){
       return findPaginated(1, model);
    }
}
