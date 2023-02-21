package com.naorcoh.grocerymart.controller;

import com.naorcoh.grocerymart.filter.GroceryProductFilter;
import com.naorcoh.grocerymart.model.Product;
import com.naorcoh.grocerymart.model.enums.Brand;
import com.naorcoh.grocerymart.model.enums.Category;
import com.naorcoh.grocerymart.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    GroceryProductFilter groceryProductFilter;

    @RequestMapping(value = {"/products"}, method = {RequestMethod.GET})
    public String displayProduct(
            @RequestParam(value = "category", name = "category", required = false) List<String> categories,
            @RequestParam(value = "brand", name = "brand", required = false) List<String> brandsList,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            Model model

    ) {
        List<Product> productsList = productService.getProductList();


        //brands & categories and price booleans
        boolean isFilterByBranAndCategories = brandsList != null & categories != null & minPrice == null & maxPrice == null;
        boolean isFilterByBranAndCategoriesAndMaxPrice = brandsList != null & categories != null & minPrice == null & maxPrice != null;
        boolean isFilterByBranAndCategoriesAndPriceRange = brandsList != null & categories != null & minPrice != null & maxPrice != null;
        boolean isFilterByBranAndCategoriesAndMinPrice = brandsList != null & categories != null & minPrice != null & maxPrice == null;

        //categories & price booleans
        boolean isFilterByCategories = categories != null & brandsList == null & minPrice == null & maxPrice == null;
        boolean isFilterByCategoriesAndMinPrice = categories != null & brandsList == null & minPrice != null & maxPrice == null;
        boolean isFilterByCategoriesAndMaxPrice = categories != null & brandsList == null & minPrice == null & maxPrice != null;
        boolean isFilterByCategoriesAndPriceRange = categories != null & brandsList == null & minPrice != null & maxPrice != null;

        //brands & price booleans
        boolean isFilterByBrandsAndMaxPrice = brandsList != null & categories == null & minPrice == null & maxPrice != null;
        boolean isFilterByBrands = brandsList != null & categories == null & minPrice == null & maxPrice == null;
        boolean isFilterByBrandsAndMinPrice = brandsList != null & categories == null & minPrice != null & maxPrice == null;
        boolean isFilterByBrandsAndPriceRange = brandsList != null & categories == null & minPrice != null & maxPrice != null;

        //model attribute
        model.addAttribute("productsList", productsList);
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("brandsList", productService.getBrandsList());


        //categories request param control flow
        if (isFilterByCategories) {
            productsList = groceryProductFilter.filterByCategories(categories);
            model.addAttribute("productsList", productsList);



        }

        if (isFilterByCategoriesAndMinPrice) {
            productsList = groceryProductFilter.filterByCategoriesAndMinPrice(categories, minPrice);
            model.addAttribute("productsList", productsList);

        }

        if (isFilterByCategoriesAndMaxPrice) {
            productsList = groceryProductFilter.filterByCategoriesAndMaxPrice(categories, maxPrice);
            model.addAttribute("productsList", productsList);

        }

        if (isFilterByCategoriesAndPriceRange) {
            productsList = groceryProductFilter.filterByCategoriesAndPriceRange(categories, minPrice, maxPrice);
            model.addAttribute("productsList", productsList);

        }

        //brands request param control flow
        if (isFilterByBrands) {
            productsList = groceryProductFilter.filterByBrands(brandsList);
            model.addAttribute("productsList", productsList);


        }

        if (isFilterByBrandsAndMinPrice) {
            productsList = groceryProductFilter.filterByBrandsAndMinPrice(brandsList, minPrice);
            model.addAttribute("productsList", productsList);

        }

        if (isFilterByBrandsAndMaxPrice) {
            productsList = groceryProductFilter.filterByBrandsAndMaxPrice(brandsList, maxPrice);
            model.addAttribute("productsList", productsList);

        }

        if (isFilterByBrandsAndPriceRange) {
            productsList = groceryProductFilter.filterByBrandsAndPriceRange(brandsList, minPrice, maxPrice);
            model.addAttribute("productsList", productsList);


        }

        //brands & categories and price filter
        if (isFilterByBranAndCategories) {
            productsList = groceryProductFilter.filterByBranAndCategories(categories, brandsList);
            model.addAttribute("productsList", productsList);

        }

        if (isFilterByBranAndCategoriesAndMinPrice) {
            productsList = groceryProductFilter.filterByBranAndCategoriesAndMinPrice(categories, brandsList, minPrice);
            model.addAttribute("productsList", productsList);

        }

        if (isFilterByBranAndCategoriesAndMaxPrice) {
            productsList = groceryProductFilter.filterByBranAndCategoriesAndMaxPrice(categories, brandsList, maxPrice);
            model.addAttribute("productsList", productsList);

        }

        if (isFilterByBranAndCategoriesAndPriceRange) {
            productsList = groceryProductFilter.filterByBranAndCategoriesAndPriceRange(categories, brandsList, minPrice, maxPrice);
            model.addAttribute("productsList", productsList);
        }


        return "products";
    }


    @PostMapping("/products")
    public String postData(
            @RequestParam(value = "category", name = "category", required = false) List<String> categories,
            @RequestParam(value = "brand", name = "brand", required = false) List<String> brandsList,
            RedirectAttributes redirectAttributes
    ) {



        if (categories != null & brandsList != null) {

            String categoriesRequestParam = String.join(",",  categories);
            String brandRequestParam = String.join(",", brandsList);


            for (Category category : productService.getCategories()) {
                category.setActive(categories.contains(category.toString()));

            }
            for (Brand brand : productService.getBrandsList()) {
                brand.setActive(brandsList.contains(brand.toString()));

            }





            return "redirect:/products?category=" + categoriesRequestParam + "&" + "brand=" + brandRequestParam;
        }

        if (categories != null & brandsList == null) {
            for (Brand brand : productService.getBrandsList()) {
                brand.setActive(false);
            }
        }

        if (brandsList != null & categories == null) {
            for (Category category : productService.getCategories()) {
                category.setActive(false);
            }
        }

        if (categories != null) {
            redirectAttributes.addAttribute("category", categories);
            for (Category category : productService.getCategories()) {
                category.setActive(categories.contains(category.toString()));
            }




            return "redirect:/products";
        }

        if (brandsList != null) {
            redirectAttributes.addAttribute("brand", brandsList);
            for (Brand brand : productService.getBrandsList()) {
                brand.setActive(brandsList.contains(brand.toString()));
            }

            return "redirect:/products";
        }



        for (Category category : productService.getCategories()) {
            category.setActive(false);
        }

        for (Brand brand : productService.getBrandsList()) {
            brand.setActive(false);
        }

        return "redirect:/products";
    }


    @GetMapping("/filter")
    public String getProductsByFilter(
            @RequestParam(value = "category", name = "category", required = false) List<String> categories,
            @RequestParam(value = "brand", name = "brand", required = false) List<String> brandList,
            @RequestParam(name = "min_price", required = false) Integer min_price,
            @RequestParam(name = "max_price", required = false) Integer max_price,
            Model model
    ) {

        List<Product> productsList = productService.getProductsByFilter(categories, brandList, min_price, max_price);

        model.addAttribute("productsList", productsList);


        return "products.html";
    }

    @GetMapping("/filterMap/{ByCriteria}")
    public String getByFilterMap(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
                                 Model model) {

        model.addAttribute("list", productService.getByFilterMap(filterParams));

        return "products.html";
    }


}
