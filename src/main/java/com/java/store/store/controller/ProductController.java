package com.java.store.store.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.store.store.domain.Product;
import com.java.store.store.domain.ProductBuilder;
import com.java.store.store.domain.Brand;
import com.java.store.store.domain.Category;
import com.java.store.store.domain.Size;
import com.java.store.store.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/add")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("allSizes", productService.getAllSizes());
		model.addAttribute("allBrands", productService.getAllBrands());
		model.addAttribute("allCategories", productService.getAllCategories());
		return "addProduct";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {
		Product newProduct = new ProductBuilder()
				.withTitle(product.getTitle())
				.stockAvailable(product.getStock())
				.withPrice(product.getPrice())
				.imageLink(product.getPicture())
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
				.build();		
		productService.saveProduct(newProduct);	
		return "redirect:product-list";
	}
	
	@RequestMapping("/product-list")
	public String productList(Model model) {
		List<Product> products = productService.findAllProducts();
		model.addAttribute("products", products);
		return "productList";
	}
	
	@RequestMapping("/edit")
	public String editProduct(@RequestParam("id") Long id, Model model) {
		Product product = productService.findProductById(id);
		String preselectedSizes = "";
		for (Size size : product.getSizes()) {
			preselectedSizes += (size.getValue() + ",");
		}
		String preselectedBrands = "";
		for (Brand brand : product.getBrands()) {
			preselectedBrands += (brand.getName() + ",");
		}
		String preselectedCategories = "";
		for (Category category : product.getCategories()) {
			preselectedCategories += (category.getName() + ",");
		}		
		model.addAttribute("product", product);
		model.addAttribute("preselectedSizes", preselectedSizes);
		model.addAttribute("preselectedBrands", preselectedBrands);
		model.addAttribute("preselectedCategories", preselectedCategories);
		model.addAttribute("allSizes", productService.getAllSizes());
		model.addAttribute("allBrands", productService.getAllBrands());
		model.addAttribute("allCategories", productService.getAllCategories());
		return "editProduct";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {		
		Product newProduct = new ProductBuilder()
				.withTitle(product.getTitle())
				.stockAvailable(product.getStock())
				.withPrice(product.getPrice())
				.imageLink(product.getPicture())
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
				.build();
		newProduct.setId(product.getId());
		productService.saveProduct(newProduct);	
		return "redirect:product-list";
	}
	
	@RequestMapping("/delete")
	public String deleteProduct(@RequestParam("id") Long id) {
		productService.deleteProductById(id);
		return "redirect:product-list";
	}
	
}
