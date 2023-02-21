package com.naorcoh.grocerymart.interfaces;

import com.naorcoh.grocerymart.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductFilter {
    public List<Product> filterByCategories(List<String> categories);
    public List<Product> filterByCategoriesAndMinPrice(List<String> categories, Integer min_price);
    public List<Product> filterByCategoriesAndMaxPrice(List<String> categories, Integer maxPrice);
    public List<Product> filterByCategoriesAndPriceRange(List<String> categories, Integer minPrice, Integer maxPrice);
    public List<Product> filterByBrands(List<String> brandsList);
    public List<Product> filterByBrandsAndMinPrice(List<String> brandsList, Integer minPrice);
    public List<Product> filterByBrandsAndMaxPrice(List<String> brandsList, Integer maxPrice);
    public List<Product> filterByBrandsAndPriceRange(List<String> brandsList, Integer minPrice, Integer maxPrice);
    public List<Product> filterByBranAndCategories(List<String> categories, List<String> brandsList);
    public List<Product> filterByBranAndCategoriesAndMinPrice(List<String> categories, List<String> brandsList, Integer minPrice);
    public List<Product> filterByBranAndCategoriesAndMaxPrice(List<String> categories, List<String> brandsList, Integer maxPrice);
    public List<Product> filterByBranAndCategoriesAndPriceRange(List<String> categories, List<String> brandsList, Integer minPrice, Integer maxPrice);

}
