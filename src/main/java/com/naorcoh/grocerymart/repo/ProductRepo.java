package com.naorcoh.grocerymart.repo;

import com.naorcoh.grocerymart.filter.GroceryProductFilter;
import com.naorcoh.grocerymart.interfaces.RepoParam;
import com.naorcoh.grocerymart.model.CheckBox;
import com.naorcoh.grocerymart.model.Product;
import com.naorcoh.grocerymart.model.enums.Brand;
import com.naorcoh.grocerymart.model.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ProductRepo implements RepoParam {
    @Autowired
    GroceryProductFilter groceryProductFilter;

    public static List<Product> getProductList() {

        Stream<Product> productStream = Stream.of(

                new Product(1, "Chocolate Candy Mix", Brand.HERSHEY, " non", Category.CHOCOLATES, 2.0, 5.0, "/images/cc1.webp", "/images/cc1.webp"),
                new Product(2, "Gourmet Milk Chocolate Hazelnut", Brand.FARRERO, " non", Category.CHOCOLATES, 10.0, 6.0, "/images/ff1.webp", "/images/ff2.webp"),
                new Product(3, "Milk Chocolate Peppermint Hot Cocoa", Brand.GHIRARDELLI, " non", Category.CHOCOLATES, 1.0, 7.0, "/images/mc1.webp", "/images/mc2.webp"),


                new Product(4, "Bamboo Kitchen Cutlery Tool Set", Brand.HOME_BASICS, " non", Category.KITCHEN_TOOLS, 3.0, 4.0, "/images/bm1.jpeg", "/images/bm2.jpeg"),
                new Product(5, "Plastic Handle Spatula - 10", Brand.HOME_BASICS, " non", Category.KITCHEN_TOOLS, 4.0, 6.0, "/images/sp1.jpeg", "/images/sp1.jpeg"),
                new Product(6, "Japanese Chef Knife", Brand.MITSUMOTO_SAKARI, " non", Category.KITCHEN_TOOLS, 2.0, 8.0, "/images/jk1.webp", "/images/jk2.webp"),
                new Product(7, "DentaLife ActivFresh Mini Dental Supplements for Small Dogs", Brand.PURINA, "non", Category.DENTAL_CHEWS, 6.0, 8.0, "/images/dog2.png", "/images/dog2.png"),
                new Product(8, "Busy Bone Tiny Chew Treats for Extra Small Dogs", Brand.PURINA, "non", Category.BONES, 4.0, 7.0, "/images/boneTiny.png", "/images/boneTi")


        );


        return productStream.collect(Collectors.toList());
    }

    public  List<Product> getProductListV2() {

        Stream<Product> productStream = Stream.of(

                new Product(1, "Chocolate Candy Mix", Brand.HERSHEY, " non", Category.CHOCOLATES, 2.0, 5.0, "/images/cc1.webp", "/images/cc1.webp"),
                new Product(2, "Gourmet Milk Chocolate Hazelnut", Brand.FARRERO, " non", Category.CHOCOLATES, 10.0, 6.0, "/images/ff1.webp", "/images/ff2.webp"),
                new Product(3, "Milk Chocolate Peppermint Hot Cocoa", Brand.GHIRARDELLI, " non", Category.CHOCOLATES, 1.0, 7.0, "/images/mc1.webp", "/images/mc2.webp"),


                new Product(4, "Bamboo Kitchen Cutlery Tool Set", Brand.HOME_BASICS, " non", Category.KITCHEN_TOOLS, 3.0, 4.0, "/images/bm1.jpeg", "/images/bm2.jpeg"),
                new Product(5, "Plastic Handle Spatula - 10", Brand.HOME_BASICS, " non", Category.KITCHEN_TOOLS, 4.0, 6.0, "/images/sp1.jpeg", "/images/sp1.jpeg"),
                new Product(6, "Japanese Chef Knife", Brand.MITSUMOTO_SAKARI, " non", Category.KITCHEN_TOOLS, 2.0, 8.0, "/images/jk1.webp", "/images/jk2.webp"),
                new Product(7, "DentaLife ActivFresh Mini Dental Supplements for Small Dogs", Brand.PURINA, "non", Category.DENTAL_CHEWS, 6.0, 8.0, "/images/dog2.png", "/images/dog2.png"),
                new Product(8, "Busy Bone Tiny Chew Treats for Extra Small Dogs", Brand.PURINA, "non", Category.BONES, 4.0, 7.0, "/images/boneTiny.png", "/images/boneTiny.png")


        );


        return productStream.collect(Collectors.toList());
    }

    public boolean isChecked(){
        CheckBox checkBox = new CheckBox(true);

        return checkBox.isBrandIsChecked();
    }



    public List<Product> getProductsByCategory(String category) {

        Category eCategory = Category.valueOf(category);

        if (category != null)
            return ProductRepo.getProductList().stream()
                    .filter(product -> product.getCategory().equals(eCategory))
                    .toList();

        throw new RuntimeException();
    }

    public List<Product> getProductsByFilter(List<String> categories, List<String> brandsList, Integer minPrice, Integer maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();
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


        //categories request param control flow
        if (isFilterByCategories) {
           return groceryProductFilter.filterByCategories(categories);
        }
        if (isFilterByCategoriesAndMinPrice) {
            return groceryProductFilter.filterByCategoriesAndMinPrice(categories, minPrice);
        }
        if (isFilterByCategoriesAndMaxPrice) {
            return groceryProductFilter.filterByCategoriesAndMaxPrice(categories, maxPrice);
        }
        if (isFilterByCategoriesAndPriceRange) {
            return groceryProductFilter.filterByCategoriesAndPriceRange(categories, minPrice, maxPrice);
        }

        //brands request param control flow
        if (isFilterByBrands) {
            return groceryProductFilter.filterByBrands(brandsList);
        }
        if (isFilterByBrandsAndMinPrice) {
            return groceryProductFilter.filterByBrandsAndMinPrice(brandsList, minPrice);
        }
        if (isFilterByBrandsAndMaxPrice) {
            return groceryProductFilter.filterByBrandsAndMaxPrice(brandsList, maxPrice);
        }
        if (isFilterByBrandsAndPriceRange) {
            return groceryProductFilter.filterByBrandsAndPriceRange(brandsList, minPrice, maxPrice);
        }

        //brands & categories and price filter
        if (isFilterByBranAndCategories)  {
            return groceryProductFilter.filterByBranAndCategories(categories, brandsList);
        }
        if (isFilterByBranAndCategoriesAndMinPrice)  {
            return groceryProductFilter.filterByBranAndCategoriesAndMinPrice(categories, brandsList, minPrice);
        }
        if (isFilterByBranAndCategoriesAndMaxPrice) {

            return groceryProductFilter.filterByBranAndCategoriesAndMaxPrice(categories, brandsList, maxPrice);
        }
        if (isFilterByBranAndCategoriesAndPriceRange) {

            filteredProducts = groceryProductFilter.filterByBranAndCategoriesAndPriceRange(categories, brandsList, minPrice, maxPrice);
        }

        return filteredProducts;
    }
    @Override
    public List<Product> getProductsByFilter(List<String> categories, List<String> brandsList) {
        List<Product> filteredProducts = new ArrayList<>();

        if (categories != null & brandsList == null) {
            for (Product product : getProductList()) {
                boolean categoriesFilter = categories.contains(product.getCategory().toString());
                boolean isInList = !filteredProducts.contains(product);

                if (categoriesFilter & isInList) {
                    filteredProducts.add(product);
                }

            }


            return filteredProducts;
        }

        if (brandsList != null & categories == null) {

            for (Product product : getProductList()) {
                boolean brandListFilter = brandsList.contains(product.getBrand().toString());
                boolean isInList = !filteredProducts.contains(product);


                if (brandListFilter & isInList) {
                    filteredProducts.add(product);
                }

            }


            return filteredProducts;

        }

        if (brandsList != null & categories != null) {

            //list contain initialized Category of products filtered by brand
            List<String> initCategory = new ArrayList<>();

            //filter products by brand to find which categories is initialized
            for (Product product : getProductList()) {
                boolean isInList = !initCategory.contains(product.getBrand().toString());
                boolean brandFilter = brandsList.contains(product.getBrand().toString());

                if (brandFilter & isInList) {
                    initCategory.add(product.getCategory().toString());
                }

            }


            //check if product match to criteria
            for (Product product : getProductList()) {

                //boolean variables
                boolean isCategoriesContainsProductCategory = categories.contains(product.getCategory().toString());
                boolean isBrandListContainsProductBrand = brandsList.contains(product.getBrand().toString());
                boolean isProductCategoryNotInit = !initCategory.contains(product.getCategory().toString());


                /*
                filter products by check if product category and brand find in url request parameter
                if it true add to list
                **/
                if (isCategoriesContainsProductCategory & isBrandListContainsProductBrand) {

                    filteredProducts.add(product);

                }
                /*
                if product category not initialized also add product to list to allow multiple category to be in
                the list the condition check if filtered brands(initCategory) list contains category
                that initialized if product category not initialized add them to list
                 */
                else if (isProductCategoryNotInit & isCategoriesContainsProductCategory) {

                    filteredProducts.add(product);

                }


            }
        }


        return filteredProducts;
    }
    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        return null;
    }
    public Set<Product> getByFilterMap(Map<String, List<String>> filterParams) {
        Set<Product> productsByBrand = new HashSet<>();
        Set<Product> productsByCategory = new HashSet<>();
        Set<Product> productSet = new HashSet<>();
        Map<String, Set<Product>> filterByCategory = new HashMap<>();
        Map<String, Set<Product>> filterByBrand = new HashMap<>();

        Set<String> criteria = filterParams.keySet();


        if (criteria.contains("brand")) {
            for (String brand : filterParams.get("brand")) {
                for (Product product : getProductList()) {
                    if (product.getBrand().toString().equals(brand)) {
                        productsByBrand.add(product);
                        filterByBrand.put(product.getBrand().toString(), productsByBrand);
                    }
                }
            }
        }

        if (criteria.contains("category")) {
            for (String category : filterParams.get("category")) {
                for (Product product : getProductList()) {
                    if (product.getCategory().toString().equals(category)) {
                        productsByCategory.add(product);
                        filterByCategory.put(product.getCategory().toString(), productsByCategory);
                    }
                }
            }


            for (String key : filterByCategory.keySet()) {

                for (Product product : filterByCategory.get(key)) {
                    for (String brandKey : filterByBrand.keySet()) {
                        for (Product product1 : filterByBrand.get(brandKey)) {
                            if (product.getBrand().toString().equals(product1.getBrand().toString())) {
                                productSet.add(product);
                            }
                        }
                    }

                }
            }


        }


        return productSet;

    }


}

