package com.naorcoh.grocerymart.filter;

import com.naorcoh.grocerymart.interfaces.ProductFilter;
import com.naorcoh.grocerymart.model.Product;
import com.naorcoh.grocerymart.repo.ProductRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroceryProductFilter implements ProductFilter {
    List<Product> productsList;

    @PostConstruct
    public void initProductList() {
        this.productsList = ProductRepo.getProductList();
    }


    @Override
    public List<Product> filterByCategories(List<String> categories) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : productsList) {
            boolean categoriesFilter = categories.contains(product.getCategory().toString());
            boolean isInList = !filteredProducts.contains(product);

            if (categoriesFilter & isInList) {
                filteredProducts.add(product);
            }

        }


        return filteredProducts;
    }

    @Override
    public List<Product> filterByCategoriesAndMinPrice(List<String> categories, Integer min_price) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : productsList) {
            boolean categoriesFilter = categories.contains(product.getCategory().toString());
            boolean isInList = !filteredProducts.contains(product);
            boolean productPriceBigger = product.getCurrentPrice() >= min_price;

            if (categoriesFilter & isInList & productPriceBigger) {
                filteredProducts.add(product);
            }


        }


        return filteredProducts;
    }

    @Override
    public List<Product> filterByCategoriesAndMaxPrice(List<String> categories, Integer maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : productsList) {
            boolean categoriesFilter = categories.contains(product.getCategory().toString());
            boolean isInList = !filteredProducts.contains(product);
            boolean productPriceLess = product.getCurrentPrice() <= maxPrice;

            if (categoriesFilter & isInList & productPriceLess) {
                filteredProducts.add(product);
            }

        }


        return filteredProducts;
    }

    @Override
    public List<Product> filterByCategoriesAndPriceRange(List<String> categories, Integer minPrice, Integer maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : productsList) {
            boolean categoriesFilter = categories.contains(product.getCategory().toString());
            boolean isInList = !filteredProducts.contains(product);
            boolean productPriceBigger = product.getCurrentPrice() >= minPrice;
            boolean productPriceLess = product.getCurrentPrice() <= maxPrice;

            if (categoriesFilter & isInList & productPriceBigger & productPriceLess) {
                filteredProducts.add(product);
            }


        }


        return filteredProducts;
    }

    @Override
    public List<Product> filterByBrands(List<String> brandsList) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : productsList) {
            boolean brandListFilter = brandsList.contains(product.getBrand().toString());
            boolean isInList = !filteredProducts.contains(product);

            if (brandListFilter & isInList) {
                filteredProducts.add(product);
            }

        }


        return filteredProducts;
    }

    @Override
    public List<Product> filterByBrandsAndMinPrice(List<String> brandsList, Integer minPrice) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : productsList) {
            boolean brandListFilter = brandsList.contains(product.getBrand().toString());
            boolean isInList = !filteredProducts.contains(product);
            boolean productPriceBigger = product.getCurrentPrice() >= minPrice;



            if (brandListFilter & isInList & productPriceBigger) {
                filteredProducts.add(product);
            }

        }


        return filteredProducts;
    }

    @Override
    public List<Product> filterByBrandsAndMaxPrice(List<String> brandsList, Integer maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : productsList) {
            boolean brandListFilter = brandsList.contains(product.getBrand().toString());
            boolean isInList = !filteredProducts.contains(product);
            boolean productPriceLess = product.getCurrentPrice() <= maxPrice;


            if (brandListFilter & isInList & productPriceLess) {
                filteredProducts.add(product);
            }

        }


        return filteredProducts;
    }

    @Override
    public List<Product> filterByBrandsAndPriceRange(List<String> brandsList, Integer minPrice, Integer maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : productsList) {
            boolean brandListFilter = brandsList.contains(product.getBrand().toString());
            boolean isInList = !filteredProducts.contains(product);
            boolean productPriceBigger = product.getCurrentPrice() >= minPrice;
            boolean productPriceLess = product.getCurrentPrice() <= maxPrice;


            if (brandListFilter & isInList & productPriceBigger & productPriceLess) {
                filteredProducts.add(product);
            }

        }


        return filteredProducts;
    }

    @Override
    public List<Product> filterByBranAndCategories(List<String> categories, List<String> brandsList) {
        List<Product> filteredProducts = new ArrayList<>();
        //list contain initialized Category of products filtered by brand
        List<String> initCategory = new ArrayList<>();

        //filter products by brand to find which categories is initialized
        for (Product product : productsList) {
            boolean isInList = !initCategory.contains(product.getBrand().toString());
            boolean brandFilter = brandsList.contains(product.getBrand().toString());

            if (brandFilter & isInList) {
                initCategory.add(product.getCategory().toString());
            }

        }


        //check if product match to criteria
        for (Product product : productsList) {

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

        return filteredProducts;
    }

    @Override
    public List<Product> filterByBranAndCategoriesAndMinPrice(List<String> categories, List<String> brandsList, Integer minPrice) {

        List<Product> filteredProducts = new ArrayList<>();
        //list contain initialized Category of products filtered by brand
        List<String> initCategory = new ArrayList<>();

        //filter products by brand to find which categories is initialized
        for (Product product : productsList) {
            boolean isInList = !initCategory.contains(product.getBrand().toString());
            boolean brandFilter = brandsList.contains(product.getBrand().toString());

            if (brandFilter & isInList) {
                initCategory.add(product.getCategory().toString());
            }

        }


        //check if product match to criteria
        for (Product product : productsList) {

            //boolean variables
            boolean isCategoriesContainsProductCategory = categories.contains(product.getCategory().toString());
            boolean isBrandListContainsProductBrand = brandsList.contains(product.getBrand().toString());
            boolean isProductCategoryNotInit = !initCategory.contains(product.getCategory().toString());
            boolean productPriceBigger = product.getCurrentPrice() >= minPrice;



                /*
                filter products by check if product category and brand find in url request parameter
                if it true add to list
                **/
            if (isCategoriesContainsProductCategory & isBrandListContainsProductBrand & productPriceBigger) {

                filteredProducts.add(product);

            }
                /*
                if product category not initialized also add product to list to allow multiple category to be in
                the list the condition check if filtered brands(initCategory) list contains category
                that initialized if product category not initialized add them to list
                 */
            else if (isProductCategoryNotInit & isCategoriesContainsProductCategory & productPriceBigger) {

                filteredProducts.add(product);

            }


        }

        return filteredProducts;
    }

    @Override
    public List<Product> filterByBranAndCategoriesAndMaxPrice(List<String> categories, List<String> brandsList, Integer maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();
        //list contain initialized Category of products filtered by brand
        List<String> initCategory = new ArrayList<>();

        //filter products by brand to find which categories is initialized
        for (Product product : productsList) {
            boolean isInList = !initCategory.contains(product.getBrand().toString());
            boolean brandFilter = brandsList.contains(product.getBrand().toString());

            if (brandFilter & isInList) {
                initCategory.add(product.getCategory().toString());
            }

        }


        //check if product match to criteria
        for (Product product : productsList) {

            //boolean variables
            boolean isCategoriesContainsProductCategory = categories.contains(product.getCategory().toString());
            boolean isBrandListContainsProductBrand = brandsList.contains(product.getBrand().toString());
            boolean isProductCategoryNotInit = !initCategory.contains(product.getCategory().toString());
            boolean productPriceLess = product.getCurrentPrice() <= maxPrice;



                /*
                filter products by check if product category and brand find in url request parameter
                if it true add to list
                **/
            if (isCategoriesContainsProductCategory & isBrandListContainsProductBrand & productPriceLess) {

                filteredProducts.add(product);

            }
                /*
                if product category not initialized also add product to list to allow multiple category to be in
                the list the condition check if filtered brands(initCategory) list contains category
                that initialized if product category not initialized add them to list
                 */
            else if (isProductCategoryNotInit & isCategoriesContainsProductCategory & productPriceLess) {

                filteredProducts.add(product);

            }


        }

        return filteredProducts;
    }

    @Override
    public List<Product> filterByBranAndCategoriesAndPriceRange(List<String> categories, List<String> brandsList, Integer minPrice, Integer maxPrice) {

        List<Product> filteredProducts = new ArrayList<>();
        //list contain initialized Category of products filtered by brand
        List<String> initCategory = new ArrayList<>();

        //filter products by brand to find which categories is initialized
        for (Product product : productsList) {
            boolean isInList = !initCategory.contains(product.getBrand().toString());
            boolean brandFilter = brandsList.contains(product.getBrand().toString());

            if (brandFilter & isInList) {
                initCategory.add(product.getCategory().toString());
            }

        }


        //check if product match to criteria
        for (Product product : productsList) {

            //boolean variables
            boolean isCategoriesContainsProductCategory = categories.contains(product.getCategory().toString());
            boolean isBrandListContainsProductBrand = brandsList.contains(product.getBrand().toString());
            boolean isProductCategoryNotInit = !initCategory.contains(product.getCategory().toString());
            boolean productPriceBigger = product.getCurrentPrice() >= minPrice;
            boolean productPriceLess = product.getCurrentPrice() <= maxPrice;




                /*
                filter products by check if product category and brand find in url request parameter
                if it true add to list
                **/
            if (isCategoriesContainsProductCategory & isBrandListContainsProductBrand & productPriceBigger & productPriceLess) {

                filteredProducts.add(product);

            }
                /*
                if product category not initialized also add product to list to allow multiple category to be in
                the list the condition check if filtered brands(initCategory) list contains category
                that initialized if product category not initialized add them to list
                 */
            else if (isProductCategoryNotInit & isCategoriesContainsProductCategory & productPriceBigger & productPriceLess) {

                filteredProducts.add(product);

            }


        }

        return filteredProducts;
    }
}
