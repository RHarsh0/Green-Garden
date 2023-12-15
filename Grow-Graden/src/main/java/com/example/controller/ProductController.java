package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Planter;
import com.example.model.Product;
import com.example.service.PlanterService;
import com.example.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	
	private ProductService productService;
	private PlanterService planterService;
	
	@Autowired
	public ProductController(ProductService productService, PlanterService planterService) {
		super();
		this.productService = productService;
		this.planterService = planterService;
	}

	// baseurl/api/products?category=ssds&
	// To Be Checked
	@GetMapping("/products")
	public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,		// get list of the product by category
			@RequestParam Integer minprice, @RequestParam Integer maxPrice, @RequestParam Integer minDiscount,
			@RequestParam String sort, @RequestParam String stock, @RequestParam String pageNumber,
			@RequestParam Integer pageSize) {

		Page<Product> res = productService.getAllProducts(category, minprice, maxPrice, minDiscount, sort, stock,
				minDiscount, pageSize);

		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}

	@GetMapping("/plants")
	public ResponseEntity<List<Product>> getAllPlant() {				// get the list of all the plants
		List<Product> products = productService.getAllPlants();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/seeds")
	public ResponseEntity<List<Product>> getAllSeed() {				// Get the list of all the seeds
		List<Product> products = productService.getAllSeeds();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	

	@GetMapping("/products/id/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable Integer productId) {		//get the product by id
		Product product = productService.findProductById(productId);
		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);

	}

	@GetMapping("/products/{type}")
	public List<Product> getPlants(@PathVariable String type,					// get the list of all the products (pagenated)
			@RequestParam(name = "sortField1", required = false) String sortField1,
			@RequestParam(name = "sortDirection1", required = false) String sortDirection1,
			@RequestParam(name = "sortField2", required = false) String sortField2,
			@RequestParam(name = "sortDirection2", required = false) String sortDirection2,
			@RequestParam(name = "sortField3", required = false) String sortField3,
			@RequestParam(name = "sortDirection3", required = false) String sortDirection3,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

		return productService.getPlantsWithSortingAndPagination(type, sortField1, sortDirection1, sortField2,
				sortDirection2, sortField3, sortDirection3, pageNo, pageSize);
	}
	
	
	@GetMapping("/planters/{planterId}")
	public ResponseEntity<Planter> viewPlanterById(@PathVariable Integer planterId) {		// get the planters by id
		return new ResponseEntity<Planter>(planterService.viewPlanterById(planterId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/planters")
	public ResponseEntity<List<Planter>> viewAllPlanter(						// view all planters(pagenated)
			@RequestParam(defaultValue = "0", required = false) Integer page,
			@RequestParam(defaultValue = "10", required = false) Integer size,
			@RequestParam(required = false) Map<String,String> map) {
		return new ResponseEntity<List<Planter>>(planterService.viewAllPlanter(page,size,map), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/plantersBy/{shape}")
	public ResponseEntity<List<Planter>> ViewPlanterByPlanterShape(@PathVariable String shape){		// get the planters by shape
		return new ResponseEntity<List<Planter>>(planterService.ViewPlanterByPlanterShape(shape),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/planters/{min}/{max}")
	public ResponseEntity<List<Planter>> viewAllPlantersbetweenRange(@PathVariable Double min,@PathVariable Double max){		//get all the planters by the price range
		return new ResponseEntity<List<Planter>>(planterService.viewAllPlantersbetweenRange(min, max),HttpStatus.ACCEPTED);
	}

}
