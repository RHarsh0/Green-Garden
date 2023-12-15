package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.exception.ProductException;
import com.example.model.Plant;
import com.example.model.Product;

public interface ProductService {

	/**
	 * This method create new product to the databasebase for application.
	 * @param Product
	 * @return Product
	 */
	public Product createProduct(Product product);

	/**
	 * This method finds the product by the product-id
	 * @param id
	 * @return product
	 * @throws ProductException
	 */
	public Product findProductById(Integer id) throws ProductException;

	/**
	 * This method delets the product by productId and returs the delete status.
	 * @param productId
	 * @return string value product delete or not
	 * @throws ProductException
	 */
	public String deleteProductById(Integer productId) throws ProductException;

	/**
	 * This method updates the product by productId and returs the Updated Product.
	 * @param productId
	 * @param Product
	 * @return Product
	 * @throws ProductException
	 */
	public Product updateProduct(Integer productId, Product requestProduct) throws ProductException;

	// /**
	//  * This method finds all the product by category and returs the List<Product> products.
	//  * @param category
	//  * @return List<Product>
	//  */
	// public List<Product> findProductByCategory(String category);

	/**
	 * This method finds all the products returs the List<Product> products.
	 * @return List<Product>
	 */
	public List<Product> allProdcuts();

	/**
	 * This method gets  all the product pagewise and returs Page<Product>.
	 * @param category
	 * @param minPrice
	 * @param maxPrice
	 * @param minDiscount
	 * @param sort
	 * @param stock
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<Product>
	 */
	public Page<Product> getAllProducts(String category, Integer minPrice, Integer maxPrice, Integer minDiscount,
			String sort, String stock, Integer pageNumber, Integer pageSize);

	/**
	 * This method finds all the plants and returs the List<Product> that are of the category plant.
	 * @return List<Product>
	 */
	public List<Product> getAllPlants() throws ProductException;


	/**
	 * This method finds all the seeds and returs the List<Product> that are of the category seeds.
	 * @return List<Product>
	 */
	public List<Product> getAllSeeds() throws ProductException;

	// ---------Ankush- ------------;
	/**
	 * This method gets all the product based on the query written.
	 * @param producttype
	 * @param sortField1
	 * @param sortDirection1
	 * @param sortField2
	 * @param sortDirection2
	 * @param sortField3
	 * @param sortDirection3
	 * @param pageNo
	 * @param pageSize
	 * @return List<Product>
	 */
	public List<Product> getPlantsWithSortingAndPagination(String producttype, String sortField1, String sortDirection1,
			String sortField2, String sortDirection2, String sortField3, String sortDirection3, int pageNo,
			int pageSize);

}
