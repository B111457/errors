package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.models.Category;
import com.javaexpress.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	@ResponseStatus(code=HttpStatus.CREATED)
	public Category createCategory(@RequestBody Category category)
	{
		return categoryService.createCategory(category);
	}
	
	@GetMapping(value = "/fetch/{categoryId}")
	@Cacheable(cacheNames = "categories",key="#categoryId")
	public Category fetchCategory(@PathVariable Integer categoryId)
	{
		return categoryService.fetchCategory(categoryId);
	}
	
	@PutMapping("/update/{categoryId}")
	public Category updateCategory(@RequestBody Category category, @PathVariable Integer categoryId)
	{
		return categoryService.updateCategory(category,categoryId);
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public void deleteCategory(@PathVariable Integer categoryId)
	{
		categoryService.deleteCategory(categoryId);
	}
	
	

}
